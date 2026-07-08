package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableRetryBiPredicateRetryBiSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final Subscriber<? super T> downstream;
    final BiPredicate<? super Integer, ? super Throwable> predicate;
    long produced;
    int retries;
    final SubscriptionArbiter sa;
    final Publisher<? extends T> source;

    FlowableRetryBiPredicateRetryBiSubscriber(Subscriber<? super T> subscriber, BiPredicate<? super Integer, ? super Throwable> biPredicate, SubscriptionArbiter subscriptionArbiter, Publisher<? extends T> publisher) {
        this.downstream = subscriber;
        this.sa = subscriptionArbiter;
        this.source = publisher;
        this.predicate = biPredicate;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        this.sa.setSubscription(subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.produced++;
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        try {
            BiPredicate<? super Integer, ? super Throwable> biPredicate = this.predicate;
            int i2 = this.retries + 1;
            this.retries = i2;
            if (!biPredicate.test(Integer.valueOf(i2), th)) {
                this.downstream.onError(th);
            } else {
                subscribeNext();
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.downstream.onComplete();
    }

    void subscribeNext() {
        if (0 == this.getAndIncrement()) {
            int i2 = 1;
            while (!this.sa.isCancelled()) {
                long j2 = this.produced;
                if (0 != j2) {
                    this.produced = 0L;
                    this.sa.produced(j2);
                }
                this.source.subscribe(this);
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
    }
}
