package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



abstract class FlowableObserveOnBaseObserveOnSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T>, Runnable {
    private static final long serialVersionUID = -8241002408341274697L;
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final int limit;
    boolean outputFused;
    final int prefetch;
    long produced;
    SimpleQueue<T> queue;
    final AtomicLong requested = new AtomicLong();
    int sourceMode;
    Subscription upstream;
    final Scheduler.Worker worker;

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public abstract void onSubscribe(Subscription subscription);

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public abstract T poll() throws Exception;

    abstract void runAsync();

    abstract void runBackfused();

    abstract void runSync();

    FlowableObserveOnBaseObserveOnSubscriber(Scheduler.Worker worker, boolean z, int i2) {
        this.worker = worker;
        this.delayError = z;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onNext(Object t) {
        if (this.done) {
            return;
        }
        if (2 == sourceMode) {
            trySchedule();
            return;
        }
        if (!this.queue.offer((T) t)) {
            this.upstream.cancel();
            this.error = new MissingBackpressureException("Queue is full?!");
            this.done = true;
        }
        trySchedule();
    }

    @Override // org.reactivestreams.Subscriber
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        trySchedule();
    }

    @Override // org.reactivestreams.Subscriber
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        trySchedule();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public final void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            trySchedule();
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        this.worker.dispose();
        if (this.outputFused || 0 != this.getAndIncrement()) {
            return;
        }
        this.queue.clear();
    }

    final void trySchedule() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        this.worker.schedule(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.outputFused) {
            runBackfused();
        } else if (1 == sourceMode) {
            runSync();
        } else {
            runAsync();
        }
    }

    final boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber) {
        if (this.cancelled) {
            clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (this.delayError) {
            if (!z2) {
                return false;
            }
            this.cancelled = true;
            Throwable th = this.error;
            if (null != th) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
            this.worker.dispose();
            return true;
        }
        Throwable th2 = this.error;
        if (null != th2) {
            this.cancelled = true;
            clear();
            subscriber.onError(th2);
            this.worker.dispose();
            return true;
        }
        if (!z2) {
            return false;
        }
        this.cancelled = true;
        subscriber.onComplete();
        this.worker.dispose();
        return true;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public final int requestFusion(int i2) {
        if (0 == (i2 & 2)) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final void clear() {
        this.queue.clear();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
