package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


abstract class FlowableSampleTimedSampleTimedSubscriber<T> extends AtomicReference<T> implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = -3517602651313910099L;
    final Subscriber<? super T> downstream;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final SequentialDisposable timer = new SequentialDisposable();

    abstract void complete();

    FlowableSampleTimedSampleTimedSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.downstream = subscriber;
        this.period = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            SequentialDisposable sequentialDisposable = this.timer;
            Scheduler scheduler = this.scheduler;
            long j2 = this.period;
            sequentialDisposable.replace(scheduler.schedulePeriodicallyDirect(this, j2, j2, this.unit));
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    public void onNext(Object t) {
        lazySet((T) t);
    }

    public void onError(Throwable th) {
        cancelTimer();
        this.downstream.onError(th);
    }

    public void onComplete() {
        cancelTimer();
        complete();
    }

    void cancelTimer() {
        DisposableHelper.dispose(this.timer);
    }

    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
        }
    }

    public void cancel() {
        cancelTimer();
        this.upstream.cancel();
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
