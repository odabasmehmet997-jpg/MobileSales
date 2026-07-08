package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;



final class FlowableTakeTakeSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -5636543848937116287L;
    boolean done;
    final Subscriber<? super T> downstream;
    final long limit;
    long remaining;
    Subscription upstream;

    FlowableTakeTakeSubscriber(Subscriber<? super T> subscriber, long j2) {
        this.downstream = subscriber;
        this.limit = j2;
        this.remaining = j2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (0 == limit) {
                subscription.cancel();
                this.done = true;
                EmptySubscription.complete(this.downstream);
                return;
            }
            this.downstream.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        long j2 = this.remaining;
        long j3 = j2 - 1;
        this.remaining = j3;
        if (0 < j2) {
            boolean z = 0 == j3;
            this.downstream.onNext(t);
            if (z) {
                this.upstream.cancel();
                onComplete();
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (!this.done) {
            this.done = true;
            this.upstream.cancel();
            this.downstream.onError(th);
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            if (!get() && compareAndSet(false, true) && j2 >= this.limit) {
                this.upstream.request(LocationRequestCompat.PASSIVE_INTERVAL);
            } else {
                this.upstream.request(j2);
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.upstream.cancel();
    }
}
