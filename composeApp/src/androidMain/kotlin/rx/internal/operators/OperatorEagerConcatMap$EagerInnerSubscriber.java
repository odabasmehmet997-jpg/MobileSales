package rx.internal.operators;

import java.util.Queue;
import rx.Subscriber;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;


final class OperatorEagerConcatMap$EagerInnerSubscriber<T> extends Subscriber<T> {
    volatile boolean done;
    Throwable error;
    final OperatorEagerConcatMap$EagerOuterSubscriber<?, T> parent;
    final Queue<Object> queue;

    public OperatorEagerConcatMap$EagerInnerSubscriber(OperatorEagerConcatMap$EagerOuterSubscriber<?, T> operatorEagerConcatMap$EagerOuterSubscriber, int i2) {
        Queue<Object> spscAtomicArrayQueue;
        this.parent = operatorEagerConcatMap$EagerOuterSubscriber;
        if (UnsafeAccess.isUnsafeAvailable()) {
            spscAtomicArrayQueue = new SpscArrayQueue<>(i2);
        } else {
            spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(i2);
        }
        this.queue = spscAtomicArrayQueue;
        request(i2);
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        this.queue.offer(NotificationLite.next(t));
        this.parent.drain();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        this.parent.drain();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.done = true;
        this.parent.drain();
    }

    void requestMore(long j2) {
        request(j2);
    }
}
