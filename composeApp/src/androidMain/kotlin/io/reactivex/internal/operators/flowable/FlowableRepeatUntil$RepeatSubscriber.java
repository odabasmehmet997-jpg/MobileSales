package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableRepeatUntilRepeatSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final Subscriber<? super T> downstream;
    long produced;
    final SubscriptionArbiter sa;
    final Publisher<? extends T> source;
    final BooleanSupplier stop;

    FlowableRepeatUntilRepeatSubscriber(Subscriber<? super T> subscriber, BooleanSupplier booleanSupplier, SubscriptionArbiter subscriptionArbiter, Publisher<? extends T> publisher) {
        this.downstream = subscriber;
        this.sa = subscriptionArbiter;
        this.source = publisher;
        this.stop = booleanSupplier;
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
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        try {
            if (this.stop.getAsBoolean()) {
                this.downstream.onComplete();
            } else {
                subscribeNext();
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.downstream.onError(th);
        }
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
