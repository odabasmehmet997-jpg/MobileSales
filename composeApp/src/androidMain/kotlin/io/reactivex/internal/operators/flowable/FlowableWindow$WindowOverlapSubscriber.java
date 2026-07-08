package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableWindowWindowOverlapSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = 2428527070996323976L;
    final int bufferSize;
    volatile boolean cancelled;
    volatile boolean done;
    final Subscriber<? super Flowable<T>> downstream;
    Throwable error;
    final AtomicBoolean firstRequest;
    long index;
    final AtomicBoolean once;
    long produced;
    final SpscLinkedArrayQueue<UnicastProcessor<T>> queue;
    final AtomicLong requested;
    final long size;
    final long skip;
    Subscription upstream;
    final ArrayDeque<UnicastProcessor<T>> windows;
    final AtomicInteger wip;

    FlowableWindowWindowOverlapSubscriber(Subscriber<? super Flowable<T>> subscriber, long j2, long j3, int i2) {
        super(1);
        this.downstream = subscriber;
        this.size = j2;
        this.skip = j3;
        this.queue = new SpscLinkedArrayQueue<>(i2);
        this.windows = new ArrayDeque<>();
        this.once = new AtomicBoolean();
        this.firstRequest = new AtomicBoolean();
        this.requested = new AtomicLong();
        this.wip = new AtomicInteger();
        this.bufferSize = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        long j2 = this.index;
        if (0 == j2 && !this.cancelled) {
            getAndIncrement();
            UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize, this);
            this.windows.offer(create);
            this.queue.offer(create);
            drain();
        }
        long j3 = j2 + 1;
        Iterator<UnicastProcessor<T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onNext(t);
        }
        long j4 = this.produced + 1;
        if (j4 == this.size) {
            this.produced = j4 - this.skip;
            UnicastProcessor<T> poll = this.windows.poll();
            if (null != poll) {
                poll.onComplete();
            }
        } else {
            this.produced = j4;
        }
        if (j3 == this.skip) {
            this.index = 0L;
        } else {
            this.index = j3;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        Iterator<UnicastProcessor<T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onError(th);
        }
        this.windows.clear();
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        Iterator<UnicastProcessor<T>> it = this.windows.iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.windows.clear();
        this.done = true;
        drain();
    }

    void drain() {
        if (0 != wip.getAndIncrement ()) {
            return;
        }
        Subscriber<? super Flowable<T>> subscriber = this.downstream;
        SpscLinkedArrayQueue<UnicastProcessor<T>> spscLinkedArrayQueue = this.queue;
        int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                boolean z = this.done;
                UnicastProcessor<T> poll = spscLinkedArrayQueue.poll();
                boolean z2 = null == poll;
                if (checkTerminated(z, z2, subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(poll);
                j3++;
            }
            if (j3 == j2 && checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                return;
            }
            if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                this.requested.addAndGet(-j3);
            }
            i2 = this.wip.addAndGet(-i2);
        } while (0 != i2);
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
        if (this.cancelled) {
            spscLinkedArrayQueue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (null != th) {
            spscLinkedArrayQueue.clear();
            subscriber.onError(th);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onComplete();
        return true;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            if (!this.firstRequest.get() && this.firstRequest.compareAndSet(false, true)) {
                this.upstream.request(BackpressureHelper.addCap(this.size, BackpressureHelper.multiplyCap(this.skip, j2 - 1)));
            } else {
                this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j2));
            }
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        if (this.once.compareAndSet(false, true)) {
            run();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (0 == this.decrementAndGet()) {
            this.upstream.cancel();
        }
    }
}
