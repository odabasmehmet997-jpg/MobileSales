package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subjects.UnicastSubject;


final class OperatorWindowWithSize$WindowOverlap<T> extends Subscriber<T> implements Action0 {
    final Subscriber<? super Observable<T>> actual;
    volatile boolean done;
    final AtomicInteger drainWip;
    Throwable error;
    int index;
    int produced;
    final Queue<Subject<T, T>> queue;
    final AtomicLong requested;
    final int size;
    final int skip;
    final ArrayDeque<Subject<T, T>> windows;
    final AtomicInteger wip;

    @Override // rx.Observer
    public void onNext(Object t) {
        int i2 = this.index;
        ArrayDeque<Subject<T, T>> arrayDeque = this.windows;
        if (i2 == 0 && !this.actual.isUnsubscribed()) {
            this.wip.getAndIncrement();
            UnicastSubject unicastSubjectCreate = UnicastSubject.create(16, this);
            arrayDeque.offer(unicastSubjectCreate);
            this.queue.offer(unicastSubjectCreate);
            drain();
        }
        Iterator<Subject<T, T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onNext(t);
        }
        int i3 = this.produced + 1;
        if (i3 == this.size) {
            this.produced = i3 - this.skip;
            Subject<T, T> subjectPoll = arrayDeque.poll();
            if (subjectPoll != null) {
                subjectPoll.onCompleted();
            }
        } else {
            this.produced = i3;
        }
        int i4 = i2 + 1;
        if (i4 == this.skip) {
            this.index = 0;
        } else {
            this.index = i4;
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Iterator<Subject<T, T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onError(th);
        }
        this.windows.clear();
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // rx.Observer
    public void onCompleted() {
        Iterator<Subject<T, T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onCompleted();
        }
        this.windows.clear();
        this.done = true;
        drain();
    }

    @Override // rx.functions.Action0
    public void call() {
        if (this.wip.decrementAndGet() == 0) {
            unsubscribe();
        }
    }

    /*  WARN: Multi-variable type inference failed */
    void drain() {
        AtomicInteger atomicInteger = this.drainWip;
        if (atomicInteger.getAndIncrement() != 0) {
            return;
        }
        Subscriber<? super Observable<T>> subscriber = this.actual;
        Queue<Subject<T, T>> queue = this.queue;
        int iAddAndGet = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                boolean z = this.done;
                Subject<T, T> subjectPoll = queue.poll();
                boolean z2 = subjectPoll == null;
                if (checkTerminated(z, z2, subscriber, queue)) {
                    return;
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(subjectPoll);
                j3++;
            }
            if (j3 == j2 && checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
                return;
            }
            if (j3 != 0 && j2 != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.requested.addAndGet(-j3);
            }
            iAddAndGet = atomicInteger.addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<? super Subject<T, T>> subscriber, Queue<Subject<T, T>> queue) {
        if (subscriber.isUnsubscribed()) {
            queue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (th != null) {
            queue.clear();
            subscriber.onError(th);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onCompleted();
        return true;
    }

    final class WindowOverlapProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 4625807964358024108L;

        WindowOverlapProducer() {
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j2);
            }
            if (j2 != 0) {
                OperatorWindowWithSize$WindowOverlap operatorWindowWithSize$WindowOverlap = OperatorWindowWithSize$WindowOverlap.this;
                if (!get() && compareAndSet(false, true)) {
                    operatorWindowWithSize$WindowOverlap.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(operatorWindowWithSize$WindowOverlap.skip, j2 - 1), operatorWindowWithSize$WindowOverlap.size));
                } else {
                    OperatorWindowWithSize$WindowOverlap.this.request(BackpressureUtils.multiplyCap(operatorWindowWithSize$WindowOverlap.skip, j2));
                }
                BackpressureUtils.getAndAddRequest(operatorWindowWithSize$WindowOverlap.requested, j2);
                operatorWindowWithSize$WindowOverlap.drain();
            }
        }
    }
}
