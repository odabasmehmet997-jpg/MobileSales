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



final class FlowableSkipLastTimedSkipLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -5677354903406201275L;
    volatile boolean cancelled;
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

    FlowableSkipLastTimedSkipLastTimedSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2, boolean z) {
        this.downstream = subscriber;
        this.time = j2;
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
        this.queue.offer(Long.valueOf(this.scheduler.now(this.unit)), t);
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
        TimeUnit timeUnit = this.unit;
        Scheduler scheduler = this.scheduler;
        long j2 = this.time;
        int i2 = 1;
        do {
            long j3 = this.requested.get();
            long j4 = 0;
            while (j4 != j3) {
                boolean z2 = this.done;
                Long l = (Long) spscLinkedArrayQueue.peek();
                boolean z3 = null == l;
                boolean z4 = z3 || l.longValue () > scheduler.now (timeUnit) - j2;
                if (checkTerminated(z2, z4, subscriber, z)) {
                    return;
                }
                if (z4) {
                    break;
                }
                spscLinkedArrayQueue.poll();
                subscriber.onNext(spscLinkedArrayQueue.poll());
                j4++;
            }
            if (0 != j4) {
                BackpressureHelper.produced(this.requested, j4);
            }
            i2 = addAndGet(-i2);
        } while (0 != i2);
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
        if (this.cancelled) {
            this.queue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
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
        if (!z2) {
            return false;
        }
        subscriber.onComplete();
        return true;
    }
}
