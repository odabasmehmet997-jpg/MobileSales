package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

 
final class FlowableScanSeedScanSeedSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -1776795561228106469L;
    final BiFunction<R, ? super T, R> accumulator;
    volatile boolean cancelled;
    int consumed;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    Throwable error;
    final int limit;
    final int prefetch;
    final SimplePlainQueue<R> queue;
    final AtomicLong requested;
    Subscription upstream;
    R value;

    FlowableScanSeedScanSeedSubscriber(Subscriber<? super R> subscriber, BiFunction<R, ? super T, R> biFunction, R r, int i2) {
        this.downstream = subscriber;
        this.accumulator = biFunction;
        this.value = r;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
        SpscArrayQueue spscArrayQueue = new SpscArrayQueue(i2);
        this.queue = spscArrayQueue;
        spscArrayQueue.offer(r);
        this.requested = new AtomicLong();
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch - 1);
        }
    }

    
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        try {
            R r = ObjectHelper.requireNonNull(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
            this.value = r;
            this.queue.offer(r);
            drain();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        drain();
    }

    
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        drain();
    }

    
    public void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    void drain() {
        Throwable th;
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super R> subscriber = this.downstream;
        SimplePlainQueue<R> simplePlainQueue = this.queue;
        int i2 = this.limit;
        int i3 = this.consumed;
        int i4 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                if (this.cancelled) {
                    simplePlainQueue.clear();
                    return;
                }
                boolean z = this.done;
                if (z && null != (th = error)) {
                    simplePlainQueue.clear();
                    subscriber.onError(th);
                    return;
                }
                R poll = simplePlainQueue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    subscriber.onComplete();
                    return;
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(poll);
                j3++;
                i3++;
                if (i3 == i2) {
                    this.upstream.request(i2);
                    i3 = 0;
                }
            }
            if (j3 == j2 && this.done) {
                Throwable th2 = this.error;
                if (null != th2) {
                    simplePlainQueue.clear();
                    subscriber.onError(th2);
                    return;
                } else if (simplePlainQueue.isEmpty()) {
                    subscriber.onComplete();
                    return;
                }
            }
            if (0 != j3) {
                BackpressureHelper.produced(this.requested, j3);
            }
            this.consumed = i3;
            i4 = addAndGet(-i4);
        } while (0 != i4);
    }
}
