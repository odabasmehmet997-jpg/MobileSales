package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class FlowableCollectCollectSubscriber<T, U> extends DeferredScalarSubscription<U> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -3589550218733891694L;
    final BiConsumer<? super U, ? super T> collector;
    boolean done;
    final U u;
    Subscription upstream;
    FlowableCollectCollectSubscriber(Subscriber<? super U> subscriber, U u, BiConsumer<? super U, ? super T> biConsumer) {
        super(subscriber);
        this.collector = biConsumer;
        this.u = u;
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        try {
            this.collector.accept(this.u, t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
        } else {
            this.done = true;
            downstream.onError(th);
        }
    }
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.complete(this.u);
    }
    @Override
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
