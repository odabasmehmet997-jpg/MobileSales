package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.processors.UnicastProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



final class FlowableWindowWindowExactSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = -2365647875069161133L;
    final int bufferSize;
    final Subscriber<? super Flowable<T>> downstream;
    long index;
    final AtomicBoolean once;
    final long size;
    Subscription upstream;
    UnicastProcessor<T> window;

    FlowableWindowWindowExactSubscriber(Subscriber<? super Flowable<T>> subscriber, long j2, int i2) {
        super(1);
        this.downstream = subscriber;
        this.size = j2;
        this.once = new AtomicBoolean();
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
        long j2 = this.index;
        UnicastProcessor<T> unicastProcessor = this.window;
        if (0 == j2) {
            getAndIncrement();
            unicastProcessor = UnicastProcessor.create(this.bufferSize, this);
            this.window = unicastProcessor;
            this.downstream.onNext(unicastProcessor);
        }
        long j3 = j2 + 1;
        unicastProcessor.onNext(t);
        if (j3 == this.size) {
            this.index = 0L;
            this.window = null;
            unicastProcessor.onComplete();
            return;
        }
        this.index = j3;
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        UnicastProcessor<T> unicastProcessor = this.window;
        if (null != unicastProcessor) {
            this.window = null;
            unicastProcessor.onError(th);
        }
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        UnicastProcessor<T> unicastProcessor = this.window;
        if (null != unicastProcessor) {
            this.window = null;
            unicastProcessor.onComplete();
        }
        this.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            this.upstream.request(BackpressureHelper.multiplyCap(this.size, j2));
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
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
