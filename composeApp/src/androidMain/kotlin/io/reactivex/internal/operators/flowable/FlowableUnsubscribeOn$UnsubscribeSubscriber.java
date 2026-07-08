package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;



final class FlowableUnsubscribeOnUnsubscribeSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 1015244841293359600L;
    final Subscriber<? super T> downstream;
    final Scheduler scheduler;
    Subscription upstream;

    FlowableUnsubscribeOnUnsubscribeSubscriber(Subscriber<? super T> subscriber, Scheduler scheduler) {
        this.downstream = subscriber;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (get()) {
            return;
        }
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (get()) {
            RxJavaPlugins.onError(th);
        } else {
            this.downstream.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (get()) {
            return;
        }
        this.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        this.upstream.request(j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (compareAndSet(false, true)) {
            this.scheduler.scheduleDirect(new Cancellation());
        }
    }

    final class Cancellation implements Runnable {
        Cancellation() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FlowableUnsubscribeOnUnsubscribeSubscriber.this.upstream.cancel();
        }
    }
}
