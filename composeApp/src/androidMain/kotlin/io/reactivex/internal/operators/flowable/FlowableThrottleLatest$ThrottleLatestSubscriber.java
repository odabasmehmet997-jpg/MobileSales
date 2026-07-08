package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableThrottleLatestThrottleLatestSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = -8296689127439125014L;
    volatile boolean cancelled;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    final boolean emitLast;
    long emitted;
    Throwable error;
    final AtomicReference<T> latest = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();
    final long timeout;
    volatile boolean timerFired;
    boolean timerRunning;
    final TimeUnit unit;
    Subscription upstream;
    final Scheduler.Worker worker;

    FlowableThrottleLatestThrottleLatestSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
        this.downstream = subscriber;
        this.timeout = j2;
        this.unit = timeUnit;
        this.worker = worker;
        this.emitLast = z;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.latest.set(t);
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        this.worker.dispose();
        if (0 == this.getAndIncrement()) {
            this.latest.lazySet(null);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.timerFired = true;
        drain();
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        AtomicReference<T> atomicReference = this.latest;
        AtomicLong atomicLong = this.requested;
        Subscriber<? super T> subscriber = this.downstream;
        int i2 = 1;
        while (!this.cancelled) {
            boolean z = this.done;
            if (z && null != error) {
                atomicReference.lazySet(null);
                subscriber.onError(this.error);
                this.worker.dispose();
                return;
            }
            boolean z2 = null == atomicReference.get ();
            if (z) {
                if (!z2 && this.emitLast) {
                    T andSet = atomicReference.getAndSet(null);
                    long j2 = this.emitted;
                    if (j2 != atomicLong.get()) {
                        this.emitted = j2 + 1;
                        subscriber.onNext(andSet);
                        subscriber.onComplete();
                    } else {
                        subscriber.onError(new MissingBackpressureException("Could not emit final value due to lack of requests"));
                    }
                } else {
                    atomicReference.lazySet(null);
                    subscriber.onComplete();
                }
                this.worker.dispose();
                return;
            }
            if (z2) {
                if (this.timerFired) {
                    this.timerRunning = false;
                    this.timerFired = false;
                }
            } else if (!this.timerRunning || this.timerFired) {
                T andSet2 = atomicReference.getAndSet(null);
                long j3 = this.emitted;
                if (j3 != atomicLong.get()) {
                    subscriber.onNext(andSet2);
                    this.emitted = j3 + 1;
                    this.timerFired = false;
                    this.timerRunning = true;
                    this.worker.schedule(this, this.timeout, this.unit);
                } else {
                    this.upstream.cancel();
                    subscriber.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
                    this.worker.dispose();
                    return;
                }
            }
            i2 = addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
        atomicReference.lazySet(null);
    }
}
