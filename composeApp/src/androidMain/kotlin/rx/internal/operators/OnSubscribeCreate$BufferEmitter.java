package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscriber;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;


final class OnSubscribeCreate$BufferEmitter<T> extends OnSubscribeCreate$BaseEmitter<T> {
    private static final long serialVersionUID = 2427151001689639875L;
    volatile boolean done;
    Throwable error;
    final Queue<Object> queue;
    final AtomicInteger wip;

    public OnSubscribeCreate$BufferEmitter(Subscriber<? super T> subscriber, int i2) {
        super(subscriber);
        this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(i2) : new SpscUnboundedAtomicArrayQueue<>(i2);
        this.wip = new AtomicInteger();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter
    void onRequested() {
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter
    void onUnsubscribed() {
        if (this.wip.getAndIncrement() == 0) {
            this.queue.clear();
        }
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Subscriber<? super T> subscriber = this.actual;
        Queue<Object> queue = this.queue;
        int iAddAndGet = 1;
        do {
            long j2 = get();
            long j3 = 0;
            while (j3 != j2) {
                if (subscriber.isUnsubscribed()) {
                    queue.clear();
                    return;
                }
                boolean z = this.done;
                Object objPoll = queue.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        super.onError(th);
                        return;
                    } else {
                        super.onCompleted();
                        return;
                    }
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(NotificationLite.getValue(objPoll));
                j3++;
            }
            if (j3 == j2) {
                if (subscriber.isUnsubscribed()) {
                    queue.clear();
                    return;
                }
                boolean z3 = this.done;
                boolean zIsEmpty = queue.isEmpty();
                if (z3 && zIsEmpty) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        super.onError(th2);
                        return;
                    } else {
                        super.onCompleted();
                        return;
                    }
                }
            }
            if (j3 != 0) {
                BackpressureUtils.produced(this, j3);
            }
            iAddAndGet = this.wip.addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
    }
}
