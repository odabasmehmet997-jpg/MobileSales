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



final class FlowableWindowWindowSkipSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = -8792836352386833856L;
    final int bufferSize;
    final Subscriber<? super Flowable<T>> downstream;
    final AtomicBoolean firstRequest;
    long index;
    final AtomicBoolean once;
    final long size;
    final long skip;
    Subscription upstream;
    UnicastProcessor<T> window;

    FlowableWindowWindowSkipSubscriber(Subscriber<? super Flowable<T>> subscriber, long j2, long j3, int i2) {
        super(1);
        this.downstream = subscriber;
        this.size = j2;
        this.skip = j3;
        this.once = new AtomicBoolean();
        this.firstRequest = new AtomicBoolean();
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
        if (null != unicastProcessor) {
            unicastProcessor.onNext(t);
        }
        if (j3 == this.size) {
            this.window = null;
            unicastProcessor.onComplete();
        }
        if (j3 == this.skip) {
            this.index = 0L;
        } else {
            this.index = j3;
        }
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
            if (!this.firstRequest.get() && this.firstRequest.compareAndSet(false, true)) {
                this.upstream.request(BackpressureHelper.addCap(BackpressureHelper.multiplyCap(this.size, j2), BackpressureHelper.multiplyCap(this.skip - this.size, j2 - 1)));
            } else {
                this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j2));
            }
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
