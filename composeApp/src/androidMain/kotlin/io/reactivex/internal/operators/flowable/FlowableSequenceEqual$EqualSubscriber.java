package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableSequenceEqualEqualSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 4804128302091633067L;
    volatile boolean done;
    final int limit;
    final FlowableSequenceEqualEqualCoordinatorHelper parent;
    final int prefetch;
    long produced;
    volatile SimpleQueue<T> queue;
    int sourceMode;

    FlowableSequenceEqualEqualSubscriber(FlowableSequenceEqualEqualCoordinatorHelper flowableSequenceEqualEqualCoordinatorHelper, int i2) {
        this.parent = flowableSequenceEqualEqualCoordinatorHelper;
        this.limit = i2 - (i2 >> 2);
        this.prefetch = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(3);
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
        if (0 == sourceMode && !this.queue.offer(t)) {
            onError(new MissingBackpressureException());
        } else {
            this.parent.drain();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.innerError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        this.parent.drain();
    }

    public void request() {
        if (1 != sourceMode) {
            long j2 = this.produced + 1;
            if (j2 >= this.limit) {
                this.produced = 0L;
                get().request(j2);
            } else {
                this.produced = j2;
            }
        }
    }

    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    void clear() {
        SimpleQueue<T> simpleQueue = this.queue;
        if (null != simpleQueue) {
            simpleQueue.clear();
        }
    }
}
