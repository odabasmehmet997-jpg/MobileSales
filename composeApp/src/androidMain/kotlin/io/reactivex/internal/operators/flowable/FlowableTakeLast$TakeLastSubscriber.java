package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableTakeLastTakeLastSubscriber<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 7240042530241604978L;
    volatile boolean cancelled;
    final int count;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicInteger wip = new AtomicInteger();

    FlowableTakeLastTakeLastSubscriber(Subscriber<? super T> subscriber, int i2) {
        this.downstream = subscriber;
        this.count = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.count == size()) {
            poll();
        }
        offer(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
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
        this.cancelled = true;
        this.upstream.cancel();
    }

    void drain() {
        if (0 == wip.getAndIncrement ()) {
            Subscriber<? super T> subscriber = this.downstream;
            long j2 = this.requested.get();
            while (!this.cancelled) {
                if (this.done) {
                    long j3 = 0;
                    while (j3 != j2) {
                        if (this.cancelled) {
                            return;
                        }
                        T poll = poll();
                        if (null == poll) {
                            subscriber.onComplete();
                            return;
                        } else {
                            subscriber.onNext(poll);
                            j3++;
                        }
                    }
                    if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                        j2 = this.requested.addAndGet(-j3);
                    }
                }
                if (0 == wip.decrementAndGet ()) {
                    return;
                }
            }
        }
    }
}
