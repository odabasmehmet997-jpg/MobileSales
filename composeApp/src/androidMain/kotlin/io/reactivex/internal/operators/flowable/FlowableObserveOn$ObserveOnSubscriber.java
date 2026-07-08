package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class FlowableObserveOnObserveOnSubscriber<T> extends FlowableObserveOnBaseObserveOnSubscriber<T> {
    private static final long serialVersionUID = -4547113800637756442L;
    final Subscriber<? super T> downstream;

    FlowableObserveOnObserveOnSubscriber(Subscriber<? super T> subscriber, Scheduler.Worker worker, boolean z, int i2) {
        super(worker, z, i2);
        this.downstream = subscriber;
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
        Subscriber<? super T> subscriber = this.downstream;
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
                        subscriber.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    subscriber.onNext(poll);
                    j2++;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    this.upstream.cancel();
                    subscriber.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
            if (this.cancelled) {
                return;
            }
            if (simpleQueue.isEmpty()) {
                this.cancelled = true;
                subscriber.onComplete();
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
        Subscriber<? super T> subscriber = this.downstream;
        SimpleQueue<T> simpleQueue = this.queue;
        long j2 = this.produced;
        int i2 = 1;
        while (true) {
            long j3 = this.requested.get();
            while (j2 != j3) {
                boolean z = this.done;
                try {
                    T poll = simpleQueue.poll();
                    boolean z2 = null == poll;
                    if (checkTerminated(z, z2, subscriber)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j2++;
                    if (j2 == this.limit) {
                        if (LocationRequestCompat.PASSIVE_INTERVAL != j3) {
                            j3 = this.requested.addAndGet(-j2);
                        }
                        this.upstream.request(j2);
                        j2 = 0;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    this.upstream.cancel();
                    simpleQueue.clear();
                    subscriber.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
            if (j2 == j3 && checkTerminated(this.done, simpleQueue.isEmpty(), subscriber)) {
                return;
            }
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
            long j2 = this.produced + 1;
            if (j2 == this.limit) {
                this.produced = 0L;
                this.upstream.request(j2);
            } else {
                this.produced = j2;
            }
        }
        return poll;
    }
}
