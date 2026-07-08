package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;



final class ParallelFromPublisherParallelDispatcher<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -4470634016609963609L;
    volatile boolean cancelled;
    volatile boolean done;
    final long[] emissions;
    Throwable error;
    int index;
    final int limit;
    final int prefetch;
    int produced;
    SimpleQueue<T> queue;
    final AtomicLongArray requests;
    int sourceMode;
    final AtomicInteger subscriberCount = new AtomicInteger();
    final Subscriber<? super T>[] subscribers;
    Subscription upstream;

    ParallelFromPublisherParallelDispatcher(Subscriber<? super T>[] subscriberArr, int i2) {
        this.subscribers = subscriberArr;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
        int length = subscriberArr.length;
        int i3 = length + length;
        AtomicLongArray atomicLongArray = new AtomicLongArray(i3 + 1);
        this.requests = atomicLongArray;
        atomicLongArray.lazySet(i3, length);
        this.emissions = new long[length];
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.sourceMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    setupSubscribers();
                    drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.sourceMode = 2;
                    this.queue = queueSubscription;
                    setupSubscribers();
                    subscription.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            setupSubscribers();
            subscription.request(this.prefetch);
        }
    }

    void setupSubscribers() {
        Subscriber<? super T>[] subscriberArr = this.subscribers;
        int length = subscriberArr.length;
        int i2 = 0;
        while (i2 < length && !this.cancelled) {
            int i3 = i2 + 1;
            this.subscriberCount.lazySet(i3);
            subscriberArr[i2].onSubscribe(new RailSubscription(i2, length));
            i2 = i3;
        }
    }

    final class RailSubscription implements Subscription {

        /* renamed from: j, reason: collision with root package name */
        final int f40j;
        final int m;

        RailSubscription(int i2, int i3) {
            this.f40j = i2;
            this.m = i3;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j2) {
            long j3;
            if (SubscriptionHelper.validate(j2)) {
                AtomicLongArray atomicLongArray = ParallelFromPublisherParallelDispatcher.this.requests;
                do {
                    j3 = atomicLongArray.get(this.f40j);
                    if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                        return;
                    }
                } while (!atomicLongArray.compareAndSet(this.f40j, j3, BackpressureHelper.addCap(j3, j2)));
                if (ParallelFromPublisherParallelDispatcher.this.subscriberCount.get() == this.m) {
                    ParallelFromPublisherParallelDispatcher.this.drain();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (ParallelFromPublisherParallelDispatcher.this.requests.compareAndSet(this.f40j + this.m, 0L, 1L)) {
                ParallelFromPublisherParallelDispatcher parallelFromPublisherParallelDispatcher = ParallelFromPublisherParallelDispatcher.this;
                int i2 = this.m;
                parallelFromPublisherParallelDispatcher.cancel(i2 + i2);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (0 == sourceMode && !this.queue.offer(t)) {
            this.upstream.cancel();
            onError(new MissingBackpressureException("Queue is full?"));
        } else {
            drain();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    void cancel(int i2) {
        if (0 == requests.decrementAndGet (i2)) {
            this.cancelled = true;
            this.upstream.cancel();
            if (0 == this.getAndIncrement()) {
                this.queue.clear();
            }
        }
    }

    void drainAsync() {
        Throwable th;
        SimpleQueue<T> simpleQueue = this.queue;
        Subscriber<? super T>[] subscriberArr = this.subscribers;
        AtomicLongArray atomicLongArray = this.requests;
        long[] jArr = this.emissions;
        int length = jArr.length;
        int i2 = this.index;
        int i3 = this.produced;
        int i4 = 1;
        while (true) {
            int i5 = 0;
            int i6 = 0;
            while (!this.cancelled) {
                boolean z = this.done;
                if (z && null != (th = error)) {
                    simpleQueue.clear();
                    int length2 = subscriberArr.length;
                    while (i5 < length2) {
                        subscriberArr[i5].onError(th);
                        i5++;
                    }
                    return;
                }
                boolean isEmpty = simpleQueue.isEmpty();
                if (z && isEmpty) {
                    int length3 = subscriberArr.length;
                    while (i5 < length3) {
                        subscriberArr[i5].onComplete();
                        i5++;
                    }
                    return;
                }
                if (!isEmpty) {
                    long j2 = atomicLongArray.get(i2);
                    long j3 = jArr[i2];
                    if (j2 == j3 || 0 != atomicLongArray.get (length + i2)) {
                        i6++;
                    } else {
                        try {
                            T poll = simpleQueue.poll();
                            if (null != poll) {
                                subscriberArr[i2].onNext(poll);
                                jArr[i2] = j3 + 1;
                                i3++;
                                if (i3 == this.limit) {
                                    this.upstream.request(i3);
                                    i3 = 0;
                                }
                                i6 = 0;
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            this.upstream.cancel();
                            int length4 = subscriberArr.length;
                            while (i5 < length4) {
                                subscriberArr[i5].onError(th2);
                                i5++;
                            }
                            return;
                        }
                    }
                    i2++;
                    if (i2 == length) {
                        i2 = 0;
                    }
                    if (i6 == length) {
                    }
                }
                int i7 = get();
                if (i7 == i4) {
                    this.index = i2;
                    this.produced = i3;
                    i4 = addAndGet(-i4);
                    if (0 == i4) {
                        return;
                    }
                } else {
                    i4 = i7;
                }
            }
            simpleQueue.clear();
            return;
        }
    }

    void drainSync() {
        SimpleQueue<T> simpleQueue = this.queue;
        Subscriber<? super T>[] subscriberArr = this.subscribers;
        AtomicLongArray atomicLongArray = this.requests;
        long[] jArr = this.emissions;
        int length = jArr.length;
        int i2 = this.index;
        int i3 = 1;
        while (true) {
            int i4 = 0;
            int i5 = 0;
            while (!this.cancelled) {
                if (simpleQueue.isEmpty()) {
                    int length2 = subscriberArr.length;
                    while (i4 < length2) {
                        subscriberArr[i4].onComplete();
                        i4++;
                    }
                    return;
                }
                long j2 = atomicLongArray.get(i2);
                long j3 = jArr[i2];
                if (j2 == j3 || 0 != atomicLongArray.get (length + i2)) {
                    i5++;
                } else {
                    try {
                        T poll = simpleQueue.poll();
                        if (null == poll) {
                            int length3 = subscriberArr.length;
                            while (i4 < length3) {
                                subscriberArr[i4].onComplete();
                                i4++;
                            }
                            return;
                        }
                        subscriberArr[i2].onNext(poll);
                        jArr[i2] = j3 + 1;
                        i5 = 0;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.upstream.cancel();
                        int length4 = subscriberArr.length;
                        while (i4 < length4) {
                            subscriberArr[i4].onError(th);
                            i4++;
                        }
                        return;
                    }
                }
                i2++;
                if (i2 == length) {
                    i2 = 0;
                }
                if (i5 == length) {
                    int i6 = get();
                    if (i6 == i3) {
                        this.index = i2;
                        i3 = addAndGet(-i3);
                        if (0 == i3) {
                            return;
                        }
                    } else {
                        i3 = i6;
                    }
                }
            }
            simpleQueue.clear();
            return;
        }
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        if (1 == sourceMode) {
            drainSync();
        } else {
            drainAsync();
        }
    }
}
