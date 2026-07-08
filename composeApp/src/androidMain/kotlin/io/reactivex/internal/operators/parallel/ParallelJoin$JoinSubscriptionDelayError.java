package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import org.reactivestreams.Subscriber;



final class ParallelJoinJoinSubscriptionDelayError<T> extends ParallelJoinJoinSubscriptionBase<T> {
    private static final long serialVersionUID = -5737965195918321883L;

    ParallelJoinJoinSubscriptionDelayError(Subscriber<? super T> subscriber, int i2, int i3) {
        super(subscriber, i2, i3);
    }

    @Override // io.reactivex.internal.operators.parallel.ParallelJoinJoinSubscriptionBase
    void onNext(ParallelJoinJoinInnerSubscriber<T> parallelJoinJoinInnerSubscriber, T t) {
        if (0 == this.get() && compareAndSet(0, 1)) {
            if (0 != requested.get ()) {
                this.downstream.onNext(t);
                if (LocationRequestCompat.PASSIVE_INTERVAL != requested.get ()) {
                    this.requested.decrementAndGet();
                }
                parallelJoinJoinInnerSubscriber.request(1L);
            } else if (!parallelJoinJoinInnerSubscriber.getQueue().offer(t)) {
                parallelJoinJoinInnerSubscriber.cancel();
                this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                this.done.decrementAndGet();
                drainLoop();
                return;
            }
            if (0 == this.decrementAndGet()) {
                return;
            }
        } else {
            if (!parallelJoinJoinInnerSubscriber.getQueue().offer(t) && parallelJoinJoinInnerSubscriber.cancel()) {
                this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                this.done.decrementAndGet();
            }
            if (0 != this.getAndIncrement()) {
                return;
            }
        }
        drainLoop();
    }

    @Override // io.reactivex.internal.operators.parallel.ParallelJoinJoinSubscriptionBase
    void onError(Throwable th) {
        this.errors.addThrowable(th);
        this.done.decrementAndGet();
        drain();
    }

    @Override // io.reactivex.internal.operators.parallel.ParallelJoinJoinSubscriptionBase
    void onComplete() {
        this.done.decrementAndGet();
        drain();
    }

    @Override // io.reactivex.internal.operators.parallel.ParallelJoinJoinSubscriptionBase
    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        drainLoop();
    }

    void drainLoop() {
        boolean z;
        T poll;
        ParallelJoinJoinInnerSubscriber<T>[] parallelJoinJoinInnerSubscriberArr = this.subscribers;
        int length = parallelJoinJoinInnerSubscriberArr.length;
        Subscriber<? super T> subscriber = this.downstream;
        int i2 = 1;
        while (true) {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                if (this.cancelled) {
                    cleanup();
                    return;
                }
                boolean z2 = 0 == done.get ();
                int i3 = 0;
                boolean z3 = true;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    ParallelJoinJoinInnerSubscriber<T> parallelJoinJoinInnerSubscriber = parallelJoinJoinInnerSubscriberArr[i3];
                    SimplePlainQueue<T> simplePlainQueue = parallelJoinJoinInnerSubscriber.queue;
                    if (null != simplePlainQueue && null != (poll = simplePlainQueue.poll ())) {
                        subscriber.onNext(poll);
                        parallelJoinJoinInnerSubscriber.requestOne();
                        j3++;
                        if (j3 == j2) {
                            break;
                        } else {
                            z3 = false;
                        }
                    }
                    i3++;
                }
            }
            if (j3 == j2) {
                if (this.cancelled) {
                    cleanup();
                    return;
                }
                boolean z4 = 0 == done.get ();
                int i4 = 0;
                while (true) {
                    if (i4 < length) {
                        SimplePlainQueue<T> simplePlainQueue2 = parallelJoinJoinInnerSubscriberArr[i4].queue;
                        if (null != simplePlainQueue2 && !simplePlainQueue2.isEmpty()) {
                            z = false;
                            break;
                        }
                        i4++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z4 && z) {
                    if (null != errors.get ()) {
                        subscriber.onError(this.errors.terminate());
                        return;
                    } else {
                        subscriber.onComplete();
                        return;
                    }
                }
            }
            if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                this.requested.addAndGet(-j3);
            }
            int i5 = get();
            if (i5 == i2 && 0 == (i5 = this.addAndGet(-i2))) {
                return;
            } else {
                i2 = i5;
            }
        }
    }
}
