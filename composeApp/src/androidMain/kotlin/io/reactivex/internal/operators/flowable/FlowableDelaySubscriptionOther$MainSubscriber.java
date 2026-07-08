package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableDelaySubscriptionOtherMainSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 2259811067697317255L;
    final Subscriber<? super T> downstream;
    final Publisher<? extends T> main;
    final OtherSubscriber other = new OtherSubscriber();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();

    FlowableDelaySubscriptionOtherMainSubscriber(Subscriber<? super T> subscriber, Publisher<? extends T> publisher) {
        this.downstream = subscriber;
        this.main = publisher;
    }

    void next() {
        this.main.subscribe(this);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            SubscriptionHelper.deferredRequest(this.upstream, this, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.other);
        SubscriptionHelper.cancel(this.upstream);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this, subscription);
    }

    final class OtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = -3892798459447644106L;

        OtherSubscriber() {
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object obj) {
            Subscription subscription = get();
            final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
            if (subscriptionHelper != subscription) {
                lazySet(subscriptionHelper);
                subscription.cancel();
                FlowableDelaySubscriptionOtherMainSubscriber.this.next();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (SubscriptionHelper.CANCELLED != this.get()) {
                FlowableDelaySubscriptionOtherMainSubscriber.this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (SubscriptionHelper.CANCELLED != this.get()) {
                FlowableDelaySubscriptionOtherMainSubscriber.this.next();
            }
        }
    }
}
