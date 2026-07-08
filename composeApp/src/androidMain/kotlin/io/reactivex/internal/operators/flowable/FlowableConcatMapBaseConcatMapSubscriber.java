package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Function;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

abstract class FlowableConcatMapBaseConcatMapSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, FlowableConcatMapConcatMapSupport<R>, Subscription {
    private static final long serialVersionUID = -3511336836796789179L;
    volatile boolean active;
    volatile boolean cancelled;
    int consumed;
    volatile boolean done;
    final int limit;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int prefetch;
    SimpleQueue<T> queue;
    int sourceMode;
    Subscription upstream;
    final FlowableConcatMapConcatMapInner<R> inner = new FlowableConcatMapConcatMapInner<>(this);
    final AtomicThrowable errors = new AtomicThrowable();
    public abstract void cancel();
    abstract void drain();
    public abstract void innerError(Throwable th);
    public abstract void innerNext(Object obj);
    public abstract void onError(Throwable th);
    public abstract void request(long j2);
    abstract void subscribeActual();
    FlowableConcatMapBaseConcatMapSubscriber(Function<? super T, ? extends Publisher<? extends R>> function, int i2) {
        this.mapper = function;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.sourceMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    subscribeActual();
                    drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.sourceMode = 2;
                    this.queue = queueSubscription;
                    subscribeActual();
                    subscription.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            subscribeActual();
            subscription.request(this.prefetch);
        }
    }
    public final void onNext(Object t) {
        if (2 != sourceMode && !this.queue.offer((T) t)) {
            this.upstream.cancel();
            onError(new IllegalStateException("Queue full?!"));
        } else {
            drain();
        }
    }
    public final void onComplete() {
        this.done = true;
        drain();
    }
    public final void innerComplete() {
        this.active = false;
        drain();
    }
}
