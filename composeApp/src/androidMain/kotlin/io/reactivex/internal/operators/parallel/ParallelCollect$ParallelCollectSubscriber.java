package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.subscribers.DeferredScalarSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class ParallelCollectParallelCollectSubscriber<T, C> extends DeferredScalarSubscriber<T, C> {
    private static final long serialVersionUID = -4767392946044436228L;
    C collection;
    final BiConsumer<? super C, ? super T> collector;
    boolean done;

    ParallelCollectParallelCollectSubscriber(Subscriber<? super C> subscriber, C c2, BiConsumer<? super C, ? super T> biConsumer) {
        super(subscriber);
        this.collection = c2;
        this.collector = biConsumer;
    }

    @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(upstream, subscription)) {
            upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        try {
            this.collector.accept(this.collection, t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            cancel();
            onError(th);
        }
    }

    @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        this.collection = null;
        downstream.onError(th);
    }

    @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        C c2 = this.collection;
        this.collection = null;
        this.complete(c2);
    }

    @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        upstream.cancel();
    }
}
