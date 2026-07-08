package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowableLimitLimitSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 2288246011222124525L;
    final Subscriber<? super T> downstream;
    long remaining;
    Subscription upstream;

    FlowableLimitLimitSubscriber(Subscriber<? super T> subscriber, long j2) {
        this.downstream = subscriber;
        this.remaining = j2;
        lazySet(j2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            if (0 == remaining) {
                subscription.cancel();
                EmptySubscription.complete(this.downstream);
            } else {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        long j2 = this.remaining;
        if (0 < j2) {
            long j3 = j2 - 1;
            this.remaining = j3;
            this.downstream.onNext(t);
            if (0 == j3) {
                this.upstream.cancel();
                this.downstream.onComplete();
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (0 < remaining) {
            this.remaining = 0L;
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (0 < remaining) {
            this.remaining = 0L;
            this.downstream.onComplete();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        long j3;
        long j4;
        if (SubscriptionHelper.validate(j2)) {
            do {
                j3 = get();
                if (0 == j3) {
                    return;
                } else {
                    j4 = j3 <= j2 ? j3 : j2;
                }
            } while (!compareAndSet(j3, j3 - j4));
            this.upstream.request(j4);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.upstream.cancel();
    }
}
