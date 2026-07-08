package io.reactivex.internal.operators.maybe;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimeoutPublisherTimeoutOtherMaybeObserver<T, U> extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = 8663801314800248617L;
    final MaybeTimeoutPublisherTimeoutMainMaybeObserver<T, U> parent;

    MaybeTimeoutPublisherTimeoutOtherMaybeObserver(MaybeTimeoutPublisherTimeoutMainMaybeObserver<T, U> maybeTimeoutPublisherTimeoutMainMaybeObserver) {
        this.parent = maybeTimeoutPublisherTimeoutMainMaybeObserver;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object obj) {
        get().cancel();
        this.parent.otherComplete();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.otherError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.parent.otherComplete();
    }
}
