package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimeoutTimeoutSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription, FlowableTimeoutTimeoutSelectorSupport {
    private static final long serialVersionUID = 3764492702657003550L;
    final Subscriber<? super T> downstream;
    final Function<? super T, ? extends Publisher<?>> itemTimeoutIndicator;
    final SequentialDisposable task = new SequentialDisposable();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    FlowableTimeoutTimeoutSubscriber(Subscriber<? super T> subscriber, Function<? super T, ? extends Publisher<?>> function) {
        this.downstream = subscriber;
        this.itemTimeoutIndicator = function;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        long j2 = get();
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            long j3 = 1 + j2;
            if (compareAndSet(j2, j3)) {
                Disposable disposable = this.task.get();
                if (null != disposable) {
                    disposable.dispose();
                }
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
                    getAndSet(LocationRequestCompat.PASSIVE_INTERVAL);
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
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimedTimeoutSupport
    public void onTimeout(long j2) {
        if (compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(new TimeoutException());
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimeoutSelectorSupport
    public void onTimeoutError(long j2, Throwable th) {
        if (compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
        this.task.dispose();
    }
}
