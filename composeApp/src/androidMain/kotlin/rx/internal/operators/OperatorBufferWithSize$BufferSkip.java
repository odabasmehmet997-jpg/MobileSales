package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Producer;
import rx.Subscriber;


final class OperatorBufferWithSize$BufferSkip<T> extends Subscriber<T> {
    final Subscriber<? super List<T>> actual;
    List<T> buffer;
    final int count;
    long index;
    final int skip;

    @Override // rx.Observer
    public void onNext(Object t) {
        long j2 = this.index;
        List arrayList = this.buffer;
        if (j2 == 0) {
            arrayList = new ArrayList(this.count);
            this.buffer = arrayList;
        }
        long j3 = j2 + 1;
        if (j3 == this.skip) {
            this.index = 0L;
        } else {
            this.index = j3;
        }
        if (arrayList != null) {
            arrayList.add(t);
            if (arrayList.size() == this.count) {
                this.buffer = null;
                this.actual.onNext(arrayList);
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.buffer = null;
        this.actual.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        List<T> list = this.buffer;
        if (list != null) {
            this.buffer = null;
            this.actual.onNext(list);
        }
        this.actual.onCompleted();
    }

    final class BufferSkipProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 3428177408082367154L;

        BufferSkipProducer() {
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j2);
            }
            if (j2 != 0) {
                OperatorBufferWithSize$BufferSkip operatorBufferWithSize$BufferSkip = OperatorBufferWithSize$BufferSkip.this;
                if (get() || !compareAndSet(false, true)) {
                    operatorBufferWithSize$BufferSkip.request(BackpressureUtils.multiplyCap(j2, operatorBufferWithSize$BufferSkip.skip));
                } else {
                    operatorBufferWithSize$BufferSkip.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j2, operatorBufferWithSize$BufferSkip.count), BackpressureUtils.multiplyCap(operatorBufferWithSize$BufferSkip.skip - operatorBufferWithSize$BufferSkip.count, j2 - 1)));
                }
            }
        }
    }
}
