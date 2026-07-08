package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;



final class FlowableObserveOnObserveOnConditionalSubscriber<T> extends FlowableObserveOnBaseObserveOnSubscriber<T> {
    private static final long serialVersionUID = 644624475404284533L;
    long consumed;
    final ConditionalSubscriber<? super T> downstream;

    FlowableObserveOnObserveOnConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Scheduler.Worker worker, boolean z, int i2) {
        super(worker, z, i2);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableObserveOnBaseObserveOnSubscriber, io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.sourceMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    return;
                }
                if (2 == requestFusion) {
                    this.sourceMode = 2;
                    this.queue = queueSubscription;
                    this.downstream.onSubscribe(this);
                    subscription.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch);
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableObserveOnBaseObserveOnSubscriber
    void runSync() {
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        SimpleQueue<T> simpleQueue = this.queue;
        long j2 = this.produced;
        int i2 = 1;
        while (true) {
            long j3 = this.requested.get();
            while (j2 != j3) {
                try {
                    T poll = simpleQueue.poll();
                    if (this.cancelled) {
                        return;
                    }
                    if (null == poll) {
                        this.cancelled = true;
                        conditionalSubscriber.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (conditionalSubscriber.tryOnNext(poll)) {
                        j2++;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    this.upstream.cancel();
                    conditionalSubscriber.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
            if (this.cancelled) {
                return;
            }
            if (simpleQueue.isEmpty()) {
                this.cancelled = true;
                conditionalSubscriber.onComplete();
                this.worker.dispose();
                return;
            } else {
                int i3 = this.get();
                if (i2 == i3) {
                    this.produced = j2;
                    i2 = this.addAndGet(-i2);
                    if (0 == i2) {
                        return;
                    }
                } else {
                    i2 = i3;
                }
            }
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableObserveOnBaseObserveOnSubscriber
    void runAsync() {
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        SimpleQueue<T> simpleQueue = this.queue;
        long j2 = this.produced;
        long j3 = this.consumed;
        int i2 = 1;
        while (true) {
            long j4 = this.requested.get();
            while (j2 != j4) {
                boolean z = this.done;
                try {
                    T poll = simpleQueue.poll();
                    boolean z2 = null == poll;
                    if (checkTerminated(z, z2, conditionalSubscriber)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    if (conditionalSubscriber.tryOnNext(poll)) {
                        j2++;
                    }
                    j3++;
                    if (j3 == this.limit) {
                        this.upstream.request(j3);
                        j3 = 0;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    this.upstream.cancel();
                    simpleQueue.clear();
                    conditionalSubscriber.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
            if (j2 == j4 && checkTerminated(this.done, simpleQueue.isEmpty(), conditionalSubscriber)) {
                return;
            }
            int i3 = this.get();
            if (i2 == i3) {
                this.produced = j2;
                this.consumed = j3;
                i2 = this.addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            } else {
                i2 = i3;
            }
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableObserveOnBaseObserveOnSubscriber
    void runBackfused() {
        int i2 = 1;
        while (!this.cancelled) {
            boolean z = this.done;
            this.downstream.onNext(null);
            if (z) {
                this.cancelled = true;
                Throwable th = this.error;
                if (null != th) {
                    this.downstream.onError(th);
                } else {
                    this.downstream.onComplete();
                }
                this.worker.dispose();
                return;
            }
            i2 = this.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableObserveOnBaseObserveOnSubscriber, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public T poll() throws Exception {
        T poll = this.queue.poll();
        if (null != poll && 1 != sourceMode) {
            long j2 = this.consumed + 1;
            if (j2 == this.limit) {
                this.consumed = 0L;
                this.upstream.request(j2);
            } else {
                this.consumed = j2;
            }
        }
        return poll;
    }
}
