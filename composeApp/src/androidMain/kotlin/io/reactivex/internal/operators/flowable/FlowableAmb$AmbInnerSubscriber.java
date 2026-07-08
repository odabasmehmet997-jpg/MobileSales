package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableAmbAmbInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -1185974347409665484L;
    final Subscriber<? super T> downstream;
    final int index;
    final AtomicLong missedRequested = new AtomicLong();
    final FlowableAmbAmbCoordinator<T> parent;
    boolean won;

    FlowableAmbAmbInnerSubscriber(FlowableAmbAmbCoordinator<T> flowableAmbAmbCoordinator, int i2, Subscriber<? super T> subscriber) {
        this.index = i2;
        this.downstream = subscriber;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this, this.missedRequested, subscription);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this, this.missedRequested, j2);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.won) {
            this.downstream.onNext(t);
            return;
        }
        throw null;
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.won) {
            this.downstream.onError(th);
            return;
        }
        throw null;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.won) {
            this.downstream.onComplete();
            return;
        }
        throw null;
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }
}
