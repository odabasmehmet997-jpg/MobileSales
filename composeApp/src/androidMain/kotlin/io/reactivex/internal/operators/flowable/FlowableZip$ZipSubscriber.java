package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableZipZipSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -4627193790118206028L;
    volatile boolean done;
    final int limit;
    final FlowableZipZipCoordinator<T, R> parent;
    final int prefetch;
    long produced;
    SimpleQueue<T> queue;
    int sourceMode;

    FlowableZipZipSubscriber(FlowableZipZipCoordinator<T, R> flowableZipZipCoordinator, int i2) {
        this.parent = flowableZipZipCoordinator;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.sourceMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.parent.drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.sourceMode = 2;
                    this.queue = queueSubscription;
                    subscription.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            subscription.request(this.prefetch);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (2 != sourceMode) {
            this.queue.offer(t);
        }
        this.parent.drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.error(this, th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        this.parent.drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (1 != sourceMode) {
            long j3 = this.produced + j2;
            if (j3 >= this.limit) {
                this.produced = 0L;
                get().request(j3);
            } else {
                this.produced = j3;
            }
        }
    }
}
