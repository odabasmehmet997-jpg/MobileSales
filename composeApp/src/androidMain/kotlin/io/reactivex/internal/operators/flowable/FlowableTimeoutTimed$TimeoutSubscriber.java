package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimeoutTimedTimeoutSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription, FlowableTimeoutTimedTimeoutSupport {
    private static final long serialVersionUID = 3764492702657003550L;
    final Subscriber<? super T> downstream;
    final long timeout;
    final TimeUnit unit;
    final Scheduler.Worker worker;
    final SequentialDisposable task = new SequentialDisposable();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    FlowableTimeoutTimedTimeoutSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
        this.downstream = subscriber;
        this.timeout = j2;
        this.unit = timeUnit;
        this.worker = worker;
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
                this.task.get().dispose();
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
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onError(th);
            this.worker.dispose();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL)) {
            this.task.dispose();
            this.downstream.onComplete();
            this.worker.dispose();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimedTimeoutSupport
    public void onTimeout(long j2) {
        if (compareAndSet(j2, LocationRequestCompat.PASSIVE_INTERVAL)) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(new TimeoutException(ExceptionHelper.timeoutMessage(this.timeout, this.unit)));
            this.worker.dispose();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
        this.worker.dispose();
    }
}
