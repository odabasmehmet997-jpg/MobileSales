package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



record FlowableTimeoutTimedFallbackSubscriber<T>(Subscriber<? super T> downstream,
                                                  SubscriptionArbiter arbiter) implements FlowableSubscriber<T> {

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        this.arbiter.setSubscription(subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.downstream.onComplete();
    }
}
