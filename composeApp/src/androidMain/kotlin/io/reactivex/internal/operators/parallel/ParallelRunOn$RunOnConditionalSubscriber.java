package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Scheduler;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;



final class ParallelRunOnRunOnConditionalSubscriber<T> extends ParallelRunOnBaseRunOnSubscriber<T> {
    private static final long serialVersionUID = 1075119423897941642L;
    final ConditionalSubscriber<? super T> downstream;

    ParallelRunOnRunOnConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, int i2, SpscArrayQueue<T> spscArrayQueue, Scheduler.Worker worker) {
        super(i2, spscArrayQueue, worker);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.parallel.ParallelRunOnBaseRunOnSubscriber, io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Throwable th;
        int i2 = this.consumed;
        SpscArrayQueue<T> spscArrayQueue = this.queue;
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        int i3 = this.limit;
        int i4 = 1;
        while (true) {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                if (this.cancelled) {
                    spscArrayQueue.clear();
                    return;
                }
                boolean z = this.done;
                if (z && null != (th = error)) {
                    spscArrayQueue.clear();
                    conditionalSubscriber.onError(th);
                    this.worker.dispose();
                    return;
                }
                T poll = spscArrayQueue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    conditionalSubscriber.onComplete();
                    this.worker.dispose();
                    return;
                } else {
                    if (z2) {
                        break;
                    }
                    if (conditionalSubscriber.tryOnNext(poll)) {
                        j3++;
                    }
                    i2++;
                    if (i2 == i3) {
                        this.upstream.request(i2);
                        i2 = 0;
                    }
                }
            }
            if (j3 == j2) {
                if (this.cancelled) {
                    spscArrayQueue.clear();
                    return;
                }
                if (this.done) {
                    Throwable th2 = this.error;
                    if (null != th2) {
                        spscArrayQueue.clear();
                        conditionalSubscriber.onError(th2);
                        this.worker.dispose();
                        return;
                    } else if (spscArrayQueue.isEmpty()) {
                        conditionalSubscriber.onComplete();
                        this.worker.dispose();
                        return;
                    }
                }
            }
            if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                this.requested.addAndGet(-j3);
            }
            int i5 = get();
            if (i5 == i4) {
                this.consumed = i2;
                i4 = addAndGet(-i4);
                if (0 == i4) {
                    return;
                }
            } else {
                i4 = i5;
            }
        }
    }
}
