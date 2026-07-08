package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class FlowableReduceReduceSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -4663883003264602070L;
    final BiFunction<T, T, T> reducer;
    Subscription upstream;

    FlowableReduceReduceSubscriber(Subscriber<? super T> subscriber, BiFunction<T, T, T> biFunction) {
        super(subscriber);
        this.reducer = biFunction;
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
        if (SubscriptionHelper.CANCELLED == upstream) {
            return;
        }
        T t2 = value;
        if (null == t2) {
            value = t;
            return;
        }
        try {
            value = ObjectHelper.requireNonNull(this.reducer.apply(t2, t), "The reducer returned a null value");
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        Subscription subscription = this.upstream;
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper == subscription) {
            RxJavaPlugins.onError(th);
        } else {
            this.upstream = subscriptionHelper;
            downstream.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        Subscription subscription = this.upstream;
        final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (subscriptionHelper == subscription) {
            return;
        }
        this.upstream = subscriptionHelper;
        T t = value;
        if (null != t) {
            this.complete(t);
        } else {
            downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
        this.upstream = SubscriptionHelper.CANCELLED;
    }
}
