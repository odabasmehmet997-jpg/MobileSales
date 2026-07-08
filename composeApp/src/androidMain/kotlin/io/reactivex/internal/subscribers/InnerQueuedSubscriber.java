package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;

public final class InnerQueuedSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final InnerQueuedSubscriberSupport<T> parent;
    final int prefetch;
    long produced;
    volatile SimpleQueue<T> queue;

    public InnerQueuedSubscriber(InnerQueuedSubscriberSupport<T> innerQueuedSubscriberSupport, int i2) {
        this.parent = innerQueuedSubscriberSupport;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(3);
                if (1 == requestFusion) {
                    this.fusionMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                }
                if (2 == requestFusion) {
                    this.fusionMode = 2;
                    this.queue = queueSubscription;
                    QueueDrainHelper.request(subscription, this.prefetch);
                    return;
                }
            }
            this.queue = QueueDrainHelper.createQueue(this.prefetch);
            QueueDrainHelper.request(subscription, this.prefetch);
        }
    }

    public void onNext(Object t) {
        if (0 == fusionMode) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    public void onComplete() {
        this.parent.innerComplete(this);
    }

    public void request(long j2) {
        if (1 != fusionMode) {
            long j3 = this.produced + j2;
            if (j3 >= this.limit) {
                this.produced = 0L;
                get ().request(j3);
            } else {
                this.produced = j3;
            }
        }
    }

    public void requestOne() {
        if (1 != fusionMode) {
            long j2 = this.produced + 1;
            if (j2 == this.limit) {
                this.produced = 0L;
                get ().request(j2);
            } else {
                this.produced = j2;
            }
        }
    }

    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public SimpleQueue<T> queue() {
        return this.queue;
    }
}
