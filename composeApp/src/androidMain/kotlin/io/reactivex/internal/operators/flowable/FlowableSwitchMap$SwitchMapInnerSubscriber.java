package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableSwitchMapSwitchMapInnerSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<R> {
    private static final long serialVersionUID = 3837284832786408377L;
    final int bufferSize;
    volatile boolean done;
    int fusionMode;
    final long index;
    final FlowableSwitchMapSwitchMapSubscriber<T, R> parent;
    volatile SimpleQueue<R> queue;

    FlowableSwitchMapSwitchMapInnerSubscriber(FlowableSwitchMapSwitchMapSubscriber<T, R> flowableSwitchMapSwitchMapSubscriber, long j2, int i2) {
        this.parent = flowableSwitchMapSwitchMapSubscriber;
        this.index = j2;
        this.bufferSize = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.fusionMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.parent.drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.fusionMode = 2;
                    this.queue = queueSubscription;
                    subscription.request(this.bufferSize);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.bufferSize);
            subscription.request(this.bufferSize);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object r) {
        FlowableSwitchMapSwitchMapSubscriber<T, R> flowableSwitchMapSwitchMapSubscriber = this.parent;
        if (this.index == flowableSwitchMapSwitchMapSubscriber.unique) {
            if (0 == fusionMode && !this.queue.offer(r)) {
                onError(new MissingBackpressureException("Queue full?!"));
            } else {
                flowableSwitchMapSwitchMapSubscriber.drain();
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        FlowableSwitchMapSwitchMapSubscriber<T, R> flowableSwitchMapSwitchMapSubscriber = this.parent;
        if (this.index == flowableSwitchMapSwitchMapSubscriber.unique && flowableSwitchMapSwitchMapSubscriber.error.addThrowable(th)) {
            if (!flowableSwitchMapSwitchMapSubscriber.delayErrors) {
                flowableSwitchMapSwitchMapSubscriber.upstream.cancel();
                flowableSwitchMapSwitchMapSubscriber.done = true;
            }
            this.done = true;
            flowableSwitchMapSwitchMapSubscriber.drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        FlowableSwitchMapSwitchMapSubscriber<T, R> flowableSwitchMapSwitchMapSubscriber = this.parent;
        if (this.index == flowableSwitchMapSwitchMapSubscriber.unique) {
            this.done = true;
            flowableSwitchMapSwitchMapSubscriber.drain();
        }
    }

    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public void request(long j2) {
        if (1 != fusionMode) {
            get().request(j2);
        }
    }
}
