package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



abstract class FlowableSamplePublisherSamplePublisherSubscriber<T> extends AtomicReference<T> implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -3517602651313910099L;
    final Subscriber<? super T> downstream;
    final Publisher<?> sampler;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<Subscription> other = new AtomicReference<>();

    abstract void completion();

    abstract void run();

    FlowableSamplePublisherSamplePublisherSubscriber(Subscriber<? super T> subscriber, Publisher<?> publisher) {
        this.downstream = subscriber;
        this.sampler = publisher;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            if (null == other.get ()) {
                this.sampler.subscribe(new FlowableSubscriber<Object>(this) { // from class: io.reactivex.internal.operators.flowable.FlowableSamplePublisherSamplerSubscriber
                    final FlowableSamplePublisherSamplePublisherSubscriber<T> parent;

                    {
                        this.parent = this;
                    }

                    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
                    public void onSubscribe(Subscription subscription2) {
                        this.parent.setOther(subscription2);
                    }

                    @Override // org.reactivestreams.Subscriber
                    public void onNext(Object obj) {
                        this.parent.run();
                    }

                    @Override // org.reactivestreams.Subscriber
                    public void onError(Throwable th) {
                        this.parent.error(th);
                    }

                    @Override // org.reactivestreams.Subscriber
                    public void onComplete() {
                        this.parent.complete();
                    }
                });
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        lazySet(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        SubscriptionHelper.cancel(this.other);
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        SubscriptionHelper.cancel(this.other);
        completion();
    }

    void setOther(Subscription subscription) {
        SubscriptionHelper.setOnce(this.other, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.other);
        this.upstream.cancel();
    }

    public void error(Throwable th) {
        this.upstream.cancel();
        this.downstream.onError(th);
    }

    public void complete() {
        this.upstream.cancel();
        completion();
    }

    void emit() {
        T andSet = getAndSet(null);
        if (null != andSet) {
            if (0 != requested.get ()) {
                this.downstream.onNext(andSet);
                BackpressureHelper.produced(this.requested, 1L);
            } else {
                cancel();
                this.downstream.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
            }
        }
    }
}
