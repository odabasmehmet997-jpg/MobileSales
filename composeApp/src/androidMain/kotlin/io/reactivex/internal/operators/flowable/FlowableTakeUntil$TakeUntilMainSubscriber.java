package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableTakeUntilTakeUntilMainSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -4945480365982832967L;
    final Subscriber<? super T> downstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final OtherSubscriber other = new OtherSubscriber();
    final AtomicThrowable error = new AtomicThrowable();

    FlowableTakeUntilTakeUntilMainSubscriber(Subscriber<? super T> subscriber) {
        this.downstream = subscriber;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        HalfSerializer.onNext(this.downstream, t, this, this.error);
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
        private static final long serialVersionUID = -3592821756711087922L;

        OtherSubscriber() {
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object obj) {
            SubscriptionHelper.cancel(this);
            onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(FlowableTakeUntilTakeUntilMainSubscriber.this.upstream);
            FlowableTakeUntilTakeUntilMainSubscriber flowableTakeUntilTakeUntilMainSubscriber = FlowableTakeUntilTakeUntilMainSubscriber.this;
            HalfSerializer.onError(flowableTakeUntilTakeUntilMainSubscriber.downstream, th, flowableTakeUntilTakeUntilMainSubscriber, flowableTakeUntilTakeUntilMainSubscriber.error);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(FlowableTakeUntilTakeUntilMainSubscriber.this.upstream);
            FlowableTakeUntilTakeUntilMainSubscriber flowableTakeUntilTakeUntilMainSubscriber = FlowableTakeUntilTakeUntilMainSubscriber.this;
            HalfSerializer.onComplete(flowableTakeUntilTakeUntilMainSubscriber.downstream, flowableTakeUntilTakeUntilMainSubscriber, flowableTakeUntilTakeUntilMainSubscriber.error);
        }
    }
}
