package io.reactivex.internal.operators.single;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;



final class SingleTakeUntilTakeUntilOtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = 5170026210238877381L;
    final SingleTakeUntilTakeUntilMainObserver<?> parent;

    SingleTakeUntilTakeUntilOtherSubscriber(SingleTakeUntilTakeUntilMainObserver<?> singleTakeUntilTakeUntilMainObserver) {
        this.parent = singleTakeUntilTakeUntilMainObserver;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object obj) {
        if (SubscriptionHelper.cancel(this)) {
            this.parent.otherError(new CancellationException());
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.otherError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        Subscription subscription = get();
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper);
            this.parent.otherError(new CancellationException());
        }
    }

    public void dispose() {
        SubscriptionHelper.cancel(this);
    }
}
