package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableRepeatRepeatSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final Subscriber<? super T> downstream;
    long produced;
    long remaining;
    final SubscriptionArbiter sa;
    final Publisher<? extends T> source;

    FlowableRepeatRepeatSubscriber(Subscriber<? super T> subscriber, long j2, SubscriptionArbiter subscriptionArbiter, Publisher<? extends T> publisher) {
        this.downstream = subscriber;
        this.sa = subscriptionArbiter;
        this.source = publisher;
        this.remaining = j2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        this.sa.setSubscription(subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.produced++;
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        long j2 = this.remaining;
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            this.remaining = j2 - 1;
        }
        if (0 != j2) {
            subscribeNext();
        } else {
            this.downstream.onComplete();
        }
    }

    void subscribeNext() {
        if (0 == this.getAndIncrement()) {
            int i2 = 1;
            while (!this.sa.isCancelled()) {
                long j2 = this.produced;
                if (0 != j2) {
                    this.produced = 0L;
                    this.sa.produced(j2);
                }
                this.source.subscribe(this);
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
    }
}
