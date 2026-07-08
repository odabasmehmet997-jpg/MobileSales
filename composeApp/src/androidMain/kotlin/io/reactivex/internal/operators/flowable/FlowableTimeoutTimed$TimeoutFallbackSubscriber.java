package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimeoutTimedTimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T>, FlowableTimeoutTimedTimeoutSupport {
    private static final long serialVersionUID = 3764492702657003550L;
    long consumed;
    final Subscriber<? super T> downstream;
    Publisher<? extends T> fallback;
    final AtomicLong index;
    final SequentialDisposable task;
    final long timeout;
    final TimeUnit unit;
    final AtomicReference<Subscription> upstream;
    final Scheduler.Worker worker;

    FlowableTimeoutTimedTimeoutFallbackSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler.Worker worker, Publisher<? extends T> publisher) {
        super(true);
        this.downstream = subscriber;
        this.timeout = j2;
        this.unit = timeUnit;
        this.worker = worker;
        this.fallback = publisher;
        this.task = new SequentialDisposable();
        this.upstream = new AtomicReference<>();
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
                this.task.get().dispose();
                this.consumed++;
                this.downstream.onNext(t);
                startTimeout(j3);
            }
        }
    }

    void startTimeout(long j2) {
        this.task.replace(this.worker.schedule(new FlowableTimeoutTimedTimeoutTask(j2, this), this.timeout, this.unit));
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (LocationRequestCompat.PASSIVE_INTERVAL != index.getAndSet (LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onError(th);
            this.worker.dispose();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (LocationRequestCompat.PASSIVE_INTERVAL != index.getAndSet (LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onComplete();
            this.worker.dispose();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimedTimeoutSupport
    public void onTimeout(long j2) {
        if (this.index.compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            long j3 = this.consumed;
            if (0 != j3) {
                this.produced(j3);
            }
            Publisher<? extends T> publisher = this.fallback;
            this.fallback = null;
            publisher.subscribe(new FlowableTimeoutTimedFallbackSubscriber(this.downstream, this));
            this.worker.dispose();
        }
    }

    @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        this.worker.dispose();
    }
}
