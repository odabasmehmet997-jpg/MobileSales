package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Collection;



final class FlowableToListToListSubscriber<T, U extends Collection<? super T>> extends DeferredScalarSubscription<U> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -8134157938864266736L;
    Subscription upstream;

    FlowableToListToListSubscriber(Subscriber<? super U> subscriber, U u) {
        super(subscriber);
        value = u;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        Collection collection = value;
        if (null != collection) {
            collection.add(t);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        value = null;
        downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.complete(value);
    }

    @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
