package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class FlowableOnErrorNextOnErrorNextSubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 4063763155303814625L;
    final boolean allowFatal;
    boolean done;
    final Subscriber<? super T> downstream;
    final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;
    boolean once;
    long produced;

    FlowableOnErrorNextOnErrorNextSubscriber(Subscriber<? super T> subscriber, Function<? super Throwable, ? extends Publisher<? extends T>> function, boolean z) {
        super(false);
        this.downstream = subscriber;
        this.nextSupplier = function;
        this.allowFatal = z;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        this.setSubscription(subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        if (!this.once) {
            this.produced++;
        }
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.once) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            } else {
                this.downstream.onError(th);
                return;
            }
        }
        this.once = true;
        if (this.allowFatal && !(th instanceof Exception)) {
            this.downstream.onError(th);
            return;
        }
        try {
            Publisher publisher = ObjectHelper.requireNonNull(this.nextSupplier.apply(th), "The nextSupplier returned a null Publisher");
            long j2 = this.produced;
            if (0 != j2) {
                this.produced(j2);
            }
            publisher.subscribe(this);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.once = true;
        this.downstream.onComplete();
    }
}
