package io.reactivex.internal.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected Subscription upstream;
    public abstract void onNext(Object obj);
    protected DeferredScalarSubscriber(Subscriber<? super R> subscriber) {
        super(subscriber);
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onError(Throwable th) {
        value = null;
        downstream.onError(th);
    }
    public void onComplete() {
        if (this.hasValue) {
            this.complete(value);
        } else {
            downstream.onComplete();
        }
    }
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
