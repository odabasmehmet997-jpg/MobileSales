package io.reactivex.internal.operators.maybe;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class MaybeDelayOtherPublisherOtherSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = -1215060610805418006L;
    final MaybeObserver<? super T> downstream;
    Throwable error;
    T value;

    MaybeDelayOtherPublisherOtherSubscriber(MaybeObserver<? super T> maybeObserver) {
        this.downstream = maybeObserver;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object obj) {
        Subscription subscription = get();
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper);
            subscription.cancel();
            onComplete();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        Throwable th2 = this.error;
        if (null == th2) {
            this.downstream.onError(th);
        } else {
            this.downstream.onError(new CompositeException(th2, th));
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        Throwable th = this.error;
        if (null != th) {
            this.downstream.onError(th);
            return;
        }
        T t = this.value;
        if (null != t) {
            this.downstream.onSuccess(t);
        } else {
            this.downstream.onComplete();
        }
    }
}
