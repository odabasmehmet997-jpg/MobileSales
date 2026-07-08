package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimeoutTimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T>, FlowableTimeoutTimeoutSelectorSupport {
    private static final long serialVersionUID = 3764492702657003550L;
    long consumed;
    final Subscriber<? super T> downstream;
    Publisher<? extends T> fallback;
    final AtomicLong index;
    final Function<? super T, ? extends Publisher<?>> itemTimeoutIndicator;
    final SequentialDisposable task;
    final AtomicReference<Subscription> upstream;

    FlowableTimeoutTimeoutFallbackSubscriber(Subscriber<? super T> subscriber, Function<? super T, ? extends Publisher<?>> function, Publisher<? extends T> publisher) {
        super(true);
        this.downstream = subscriber;
        this.itemTimeoutIndicator = function;
        this.task = new SequentialDisposable();
        this.upstream = new AtomicReference<>();
        this.fallback = publisher;
        this.index = new AtomicLong();
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            this.setSubscription(subscription);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        long j2 = this.index.get();
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            long j3 = j2 + 1;
            if (this.index.compareAndSet(j2, j3)) {
                Disposable disposable = this.task.get();
                if (null != disposable) {
                    disposable.dispose();
                }
                this.consumed++;
                this.downstream.onNext(t);
                try {
                    Publisher publisher = ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                    FlowableTimeoutTimeoutConsumer flowableTimeoutTimeoutConsumer = new FlowableTimeoutTimeoutConsumer(j3, this);
                    if (this.task.replace(flowableTimeoutTimeoutConsumer)) {
                        publisher.subscribe(flowableTimeoutTimeoutConsumer);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.get().cancel();
                    this.index.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL);
                    this.downstream.onError(th);
                }
            }
        }
    }

    void startFirstTimeout(Publisher<?> publisher) {
        if (null != publisher) {
            FlowableTimeoutTimeoutConsumer flowableTimeoutTimeoutConsumer = new FlowableTimeoutTimeoutConsumer(0L, this);
            if (this.task.replace(flowableTimeoutTimeoutConsumer)) {
                publisher.subscribe(flowableTimeoutTimeoutConsumer);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (LocationRequestCompat.PASSIVE_INTERVAL != index.getAndSet (LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onError(th);
            this.task.dispose();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (LocationRequestCompat.PASSIVE_INTERVAL != index.getAndSet (LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onComplete();
            this.task.dispose();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimedTimeoutSupport
    public void onTimeout(long j2) {
        if (this.index.compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            Publisher<? extends T> publisher = this.fallback;
            this.fallback = null;
            long j3 = this.consumed;
            if (0 != j3) {
                this.produced(j3);
            }
            publisher.subscribe(new FlowableTimeoutTimedFallbackSubscriber(this.downstream, this));
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimeoutSelectorSupport
    public void onTimeoutError(long j2, Throwable th) {
        if (this.index.compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        this.task.dispose();
    }
}
