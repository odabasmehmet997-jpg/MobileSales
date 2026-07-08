package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class FlowableCountCountSubscriber extends DeferredScalarSubscription<Long> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = 4973004223787171406L;
    long count;
    Subscription upstream;
    FlowableCountCountSubscriber(Subscriber<? super Long> subscriber) {
        super(subscriber);
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(Object obj) {
        this.count++;
    }
    public void onError(Throwable th) {
        downstream.onError(th);
    }
    public void onComplete() {
        this.complete(this.count);
    }
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
