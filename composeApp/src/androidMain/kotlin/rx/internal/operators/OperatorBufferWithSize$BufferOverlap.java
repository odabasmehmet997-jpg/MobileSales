package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;


final class OperatorBufferWithSize$BufferOverlap<T> extends Subscriber<T> {
    final Subscriber<? super List<T>> actual;
    final int count;
    long index;
    long produced;
    final ArrayDeque<List<T>> queue;
    final AtomicLong requested;
    final int skip;

    @Override // rx.Observer
    public void onNext(Object t) {
        long j2 = this.index;
        if (j2 == 0) {
            this.queue.offer(new ArrayList(this.count));
        }
        long j3 = j2 + 1;
        if (j3 == this.skip) {
            this.index = 0L;
        } else {
            this.index = j3;
        }
        Iterator<List<T>> it = this.queue.iterator();
        while (it.hasNext()) {
            it.next().add(t);
        }
        List<T> listPeek = this.queue.peek();
        if (listPeek == null || listPeek.size() != this.count) {
            return;
        }
        this.queue.poll();
        this.produced++;
        this.actual.onNext(listPeek);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.queue.clear();
        this.actual.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        long j2 = this.produced;
        if (j2 != 0) {
            if (j2 > this.requested.get()) {
                this.actual.onError(new MissingBackpressureException("More produced than requested? " + j2));
                return;
            }
            this.requested.addAndGet(-j2);
        }
        BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual);
    }

    final class BufferOverlapProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = -4015894850868853147L;

        BufferOverlapProducer() {
        }

        @Override // rx.Producer
        public void request(long j2) {
            OperatorBufferWithSize$BufferOverlap operatorBufferWithSize$BufferOverlap = OperatorBufferWithSize$BufferOverlap.this;
            if (!BackpressureUtils.postCompleteRequest(operatorBufferWithSize$BufferOverlap.requested, j2, operatorBufferWithSize$BufferOverlap.queue, operatorBufferWithSize$BufferOverlap.actual) || j2 == 0) {
                return;
            }
            if (get() || !compareAndSet(false, true)) {
                operatorBufferWithSize$BufferOverlap.request(BackpressureUtils.multiplyCap(operatorBufferWithSize$BufferOverlap.skip, j2));
            } else {
                operatorBufferWithSize$BufferOverlap.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(operatorBufferWithSize$BufferOverlap.skip, j2 - 1), operatorBufferWithSize$BufferOverlap.count));
            }
        }
    }
}
