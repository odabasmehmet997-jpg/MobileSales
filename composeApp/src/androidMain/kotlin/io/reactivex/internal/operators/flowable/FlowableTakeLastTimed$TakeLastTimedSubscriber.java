package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableTakeLastTimedTakeLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -5677354903406201275L;
    volatile boolean cancelled;
    final long count;
    final boolean delayError;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    Throwable error;
    final SpscLinkedArrayQueue<Object> queue;
    final AtomicLong requested = new AtomicLong();
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;
    Subscription upstream;

    FlowableTakeLastTimedTakeLastTimedSubscriber(Subscriber<? super T> subscriber, long j2, long j3, TimeUnit timeUnit, Scheduler scheduler, int i2, boolean z) {
        this.downstream = subscriber;
        this.count = j2;
        this.time = j3;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.queue = new SpscLinkedArrayQueue<>(i2);
        this.delayError = z;
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
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        long now = this.scheduler.now(this.unit);
        spscLinkedArrayQueue.offer(Long.valueOf(now), t);
        trim(now, spscLinkedArrayQueue);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.delayError) {
            trim(this.scheduler.now(this.unit), this.queue);
        }
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        trim(this.scheduler.now(this.unit), this.queue);
        this.done = true;
        drain();
    }

    void trim(long j2, SpscLinkedArrayQueue<Object> spscLinkedArrayQueue) {
        long j3 = this.time;
        long j4 = this.count;
        boolean z = LocationRequestCompat.PASSIVE_INTERVAL == j4;
        while (!spscLinkedArrayQueue.isEmpty()) {
            if (((Long) spscLinkedArrayQueue.peek()).longValue() >= j2 - j3 && (z || (spscLinkedArrayQueue.size() >> 1) <= j4)) {
                return;
            }
            spscLinkedArrayQueue.poll();
            spscLinkedArrayQueue.poll();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super T> subscriber = this.downstream;
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        boolean z = this.delayError;
        int i2 = 1;
        do {
            if (this.done) {
                if (checkTerminated(spscLinkedArrayQueue.isEmpty(), subscriber, z)) {
                    return;
                }
                long j2 = this.requested.get();
                long j3 = 0;
                while (true) {
                    if (checkTerminated(null == spscLinkedArrayQueue.peek (), subscriber, z)) {
                        return;
                    }
                    if (j2 != j3) {
                        spscLinkedArrayQueue.poll();
                        subscriber.onNext(spscLinkedArrayQueue.poll());
                        j3++;
                    } else if (0 != j3) {
                        BackpressureHelper.produced(this.requested, j3);
                    }
                }
            }
            i2 = addAndGet(-i2);
        } while (0 != i2);
    }

    boolean checkTerminated(boolean z, Subscriber<? super T> subscriber, boolean z2) {
        if (this.cancelled) {
            this.queue.clear();
            return true;
        }
        if (z2) {
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (null != th) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
            return true;
        }
        Throwable th2 = this.error;
        if (null != th2) {
            this.queue.clear();
            subscriber.onError(th2);
            return true;
        }
        if (!z) {
            return false;
        }
        subscriber.onComplete();
        return true;
    }
}
