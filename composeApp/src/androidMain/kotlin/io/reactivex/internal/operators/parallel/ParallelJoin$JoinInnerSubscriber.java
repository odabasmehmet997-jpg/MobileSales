package io.reactivex.internal.operators.parallel;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class ParallelJoinJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 8410034718427740355L;
    final int limit;
    final ParallelJoinJoinSubscriptionBase<T> parent;
    final int prefetch;
    long produced;
    volatile SimplePlainQueue<T> queue;

    ParallelJoinJoinInnerSubscriber(ParallelJoinJoinSubscriptionBase<T> parallelJoinJoinSubscriptionBase, int i2) {
        this.parent = parallelJoinJoinSubscriptionBase;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, this.prefetch);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.parent.onNext(this, t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.parent.onComplete();
    }

    public void requestOne() {
        long j2 = this.produced + 1;
        if (j2 == this.limit) {
            this.produced = 0L;
            get().request(j2);
        } else {
            this.produced = j2;
        }
    }

    public void request(long j2) {
        long j3 = this.produced + j2;
        if (j3 >= this.limit) {
            this.produced = 0L;
            get().request(j3);
        } else {
            this.produced = j3;
        }
    }

    public boolean cancel() {
        return SubscriptionHelper.cancel(this);
    }

    SimplePlainQueue<T> getQueue() {
        SimplePlainQueue<T> simplePlainQueue = this.queue;
        if (null != simplePlainQueue) {
            return simplePlainQueue;
        }
        SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.prefetch);
        this.queue = spscArrayQueue;
        return spscArrayQueue;
    }
}
