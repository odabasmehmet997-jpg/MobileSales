package io.reactivex.internal.operators.parallel;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



abstract class ParallelJoinJoinSubscriptionBase<T> extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = 3100232009247827843L;
    volatile boolean cancelled;
    final Subscriber<? super T> downstream;
    final ParallelJoinJoinInnerSubscriber<T>[] subscribers;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();
    final AtomicInteger done = new AtomicInteger();

    abstract void drain();

    abstract void onComplete();

    abstract void onError(Throwable th);

    abstract void onNext(ParallelJoinJoinInnerSubscriber<T> parallelJoinJoinInnerSubscriber, T t);

    ParallelJoinJoinSubscriptionBase(Subscriber<? super T> subscriber, int i2, int i3) {
        this.downstream = subscriber;
        ParallelJoinJoinInnerSubscriber<T>[] parallelJoinJoinInnerSubscriberArr = new ParallelJoinJoinInnerSubscriber[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            parallelJoinJoinInnerSubscriberArr[i4] = new ParallelJoinJoinInnerSubscriber<>(this, i3);
        }
        this.subscribers = parallelJoinJoinInnerSubscriberArr;
        this.done.lazySet(i2);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        cancelAll();
        if (0 == this.getAndIncrement()) {
            cleanup();
        }
    }

    void cancelAll() {
        for (ParallelJoinJoinInnerSubscriber<T> parallelJoinJoinInnerSubscriber : this.subscribers) {
            parallelJoinJoinInnerSubscriber.cancel();
        }
    }

    void cleanup() {
        for (ParallelJoinJoinInnerSubscriber<T> parallelJoinJoinInnerSubscriber : this.subscribers) {
            parallelJoinJoinInnerSubscriber.queue = null;
        }
    }
}
