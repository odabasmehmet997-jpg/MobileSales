package io.reactivex.internal.operators.parallel;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


abstract class ParallelRunOnBaseRunOnSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = 9222303586456402150L;
    volatile boolean cancelled;
    int consumed;
    volatile boolean done;
    Throwable error;
    final int limit;
    final int prefetch;
    final SpscArrayQueue<T> queue;
    final AtomicLong requested = new AtomicLong();
    Subscription upstream;
    final Scheduler.Worker worker;
    public abstract void onSubscribe(Subscription subscription);

    ParallelRunOnBaseRunOnSubscriber(int i2, SpscArrayQueue<T> spscArrayQueue, Scheduler.Worker worker) {
        this.prefetch = i2;
        this.queue = spscArrayQueue;
        this.limit = i2 - (i2 >> 2);
        this.worker = worker;
    }

    public final void onNext(Object t) {
        if (this.done) {
            return;
        }
        if (!this.queue.offer(t)) {
            this.upstream.cancel();
            onError(new MissingBackpressureException("Queue is full?!"));
        } else {
            schedule();
        }
    }

    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        schedule();
    }

    @Override // org.reactivestreams.Subscriber
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        schedule();
    }

    @Override // org.reactivestreams.Subscription
    public final void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            schedule();
        }
    }

    @Override // org.reactivestreams.Subscription
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        this.worker.dispose();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    final void schedule() {
        if (0 == this.getAndIncrement()) {
            this.worker.schedule(this);
        }
    }
}
