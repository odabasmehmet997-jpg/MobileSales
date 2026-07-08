package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableSkipUntilSkipUntilMainSubscriber<T> extends AtomicInteger implements ConditionalSubscriber<T>, Subscription {
    private static final long serialVersionUID = -6270983465606289181L;
    final Subscriber<? super T> downstream;
    volatile boolean gate;
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();
    final OtherSubscriber other = new OtherSubscriber();
    final AtomicThrowable error = new AtomicThrowable();

    FlowableSkipUntilSkipUntilMainSubscriber(Subscriber<? super T> subscriber) {
        this.downstream = subscriber;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (tryOnNext(t)) {
            return;
        }
        this.upstream.get().request(1L);
    }

    @Override // io.reactivex.internal.fuseable.ConditionalSubscriber
    public boolean tryOnNext(T t) {
        if (!this.gate) {
            return false;
        }
        HalfSerializer.onNext(this.downstream, t, this, this.error);
        return true;
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        SubscriptionHelper.cancel(this.other);
        HalfSerializer.onError(this.downstream, th, this, this.error);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        SubscriptionHelper.cancel(this.other);
        HalfSerializer.onComplete(this.downstream, this, this.error);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
        SubscriptionHelper.cancel(this.other);
    }

    final class OtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = -5592042965931999169L;

        OtherSubscriber() {
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object obj) {
            FlowableSkipUntilSkipUntilMainSubscriber.this.gate = true;
            get().cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(FlowableSkipUntilSkipUntilMainSubscriber.this.upstream);
            FlowableSkipUntilSkipUntilMainSubscriber flowableSkipUntilSkipUntilMainSubscriber = FlowableSkipUntilSkipUntilMainSubscriber.this;
            HalfSerializer.onError(flowableSkipUntilSkipUntilMainSubscriber.downstream, th, flowableSkipUntilSkipUntilMainSubscriber, flowableSkipUntilSkipUntilMainSubscriber.error);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            FlowableSkipUntilSkipUntilMainSubscriber.this.gate = true;
        }
    }
}
