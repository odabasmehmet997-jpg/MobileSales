package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimeoutTimeoutConsumer extends AtomicReference<Subscription> implements FlowableSubscriber<Object>, Disposable {
    private static final long serialVersionUID = 8708641127342403073L;
    final long idx;
    final FlowableTimeoutTimeoutSelectorSupport parent;

    FlowableTimeoutTimeoutConsumer(long j2, FlowableTimeoutTimeoutSelectorSupport flowableTimeoutTimeoutSelectorSupport) {
        this.idx = j2;
        this.parent = flowableTimeoutTimeoutSelectorSupport;
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
            subscription.cancel();
            lazySet(subscriptionHelper);
            this.parent.onTimeout(this.idx);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        Subscription subscription = get();
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper);
            this.parent.onTimeoutError(this.idx, th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        Subscription subscription = get();
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper);
            this.parent.onTimeout(this.idx);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == this.get();
    }
}
