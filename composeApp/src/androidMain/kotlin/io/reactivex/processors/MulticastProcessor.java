package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
    static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
    final int bufferSize;
    int consumed;
    volatile boolean done;
    volatile Throwable error;
    int fusionMode;
    final int limit;
    final AtomicBoolean once;
    volatile SimpleQueue<T> queue;
    final boolean refcount;
    final AtomicReference<MulticastSubscription<T>[]> subscribers;
    final AtomicReference<Subscription> upstream;
    final AtomicInteger wip;

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(final Subscription subscription) {
        if (SubscriptionHelper.setOnce(upstream, subscription)) {
            if (subscription instanceof final QueueSubscription queueSubscription) {
                final int requestFusion = queueSubscription.requestFusion(3);
                if (1 == requestFusion) {
                    fusionMode = 1;
                    queue = queueSubscription;
                    done = true;
                    this.drain();
                    return;
                }
                if (2 == requestFusion) {
                    fusionMode = 2;
                    queue = queueSubscription;
                    subscription.request(bufferSize);
                    return;
                }
            }
            queue = new SpscArrayQueue(bufferSize);
            subscription.request(bufferSize);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(final Object t) {
        if (once.get()) {
            return;
        }
        if (0 == this.fusionMode) {
            ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            if (!queue.offer(t)) {
                SubscriptionHelper.cancel(upstream);
                this.onError(new MissingBackpressureException());
                return;
            }
        }
        this.drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (once.compareAndSet(false, true)) {
            error = th;
            done = true;
            this.drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (once.compareAndSet(false, true)) {
            done = true;
            this.drain();
        }
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        final Throwable th;
        final MulticastSubscription<T> multicastSubscription = new MulticastSubscription<>(subscriber, this);
        subscriber.onSubscribe(multicastSubscription);
        if (this.add(multicastSubscription)) {
            if (Long.MIN_VALUE == multicastSubscription.get ()) {
                this.remove(multicastSubscription);
                return;
            } else {
                this.drain();
                return;
            }
        }
        if ((once.get() || !refcount) && null != (th = this.error)) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
    }

    boolean add(final MulticastSubscription<T> multicastSubscription) {
        MulticastSubscription<T>[] multicastSubscriptionArr;
        MulticastSubscription[] multicastSubscriptionArr2;
        do {
            multicastSubscriptionArr = subscribers.get();
            if (multicastSubscriptionArr == MulticastProcessor.TERMINATED) {
                return false;
            }
            final int length = multicastSubscriptionArr.length;
            multicastSubscriptionArr2 = new MulticastSubscription[length + 1];
            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
            multicastSubscriptionArr2[length] = multicastSubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, multicastSubscriptionArr, multicastSubscriptionArr2));
        return true;
    }

    void remove(final MulticastSubscription<T> multicastSubscription) {
        while (true) {
            final MulticastSubscription<T>[] multicastSubscriptionArr = subscribers.get();
            final int length = multicastSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (multicastSubscriptionArr[i2] == multicastSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                if (refcount) {
                    if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, multicastSubscriptionArr, MulticastProcessor.TERMINATED)) {
                        SubscriptionHelper.cancel(upstream);
                        once.set(true);
                        return;
                    }
                } else if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, multicastSubscriptionArr, MulticastProcessor.EMPTY)) {
                    return;
                }
            } else {
                final MulticastSubscription[] multicastSubscriptionArr2 = new MulticastSubscription[length - 1];
                System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, i2);
                System.arraycopy(multicastSubscriptionArr, i2 + 1, multicastSubscriptionArr2, i2, (length - i2) - 1);
                if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, multicastSubscriptionArr, multicastSubscriptionArr2)) {
                    return;
                }
            }
        }
    }

    void drain() {
        T t;
        if (0 != this.wip.getAndIncrement ()) {
            return;
        }
        final AtomicReference<MulticastSubscription<T>[]> atomicReference = subscribers;
        int i2 = consumed;
        final int i3 = limit;
        final int i4 = fusionMode;
        int i5 = 1;
        while (true) {
            final SimpleQueue<T> simpleQueue = queue;
            if (null != simpleQueue) {
                final MulticastSubscription<T>[] multicastSubscriptionArr = atomicReference.get();
                if (0 != multicastSubscriptionArr.length) {
                    final int length = multicastSubscriptionArr.length;
                    long j2 = -1;
                    long j3 = -1;
                    int i6 = 0;
                    while (i6 < length) {
                        final MulticastSubscription<T> multicastSubscription = multicastSubscriptionArr[i6];
                        final long j4 = multicastSubscription.get();
                        if (0 <= j4) {
                            if (j3 == j2) {
                                j3 = j4 - multicastSubscription.emitted;
                            } else {
                                j3 = Math.min(j3, j4 - multicastSubscription.emitted);
                            }
                        }
                        i6++;
                        j2 = -1;
                    }
                    int i7 = i2;
                    while (0 < j3) {
                        final MulticastSubscription<T>[] multicastSubscriptionArr2 = atomicReference.get();
                        if (multicastSubscriptionArr2 == MulticastProcessor.TERMINATED) {
                            simpleQueue.clear();
                            return;
                        }
                        if (multicastSubscriptionArr != multicastSubscriptionArr2) {
                            break;
                        }
                        boolean z = done;
                        try {
                            t = simpleQueue.poll();
                        } catch (final Throwable th) {
                            Exceptions.throwIfFatal(th);
                            SubscriptionHelper.cancel(upstream);
                            error = th;
                            done = true;
                            t = null;
                            z = true;
                        }
                        final boolean z2 = null == t;
                        if (z && z2) {
                            final Throwable th2 = error;
                            if (null != th2) {
                                for (final MulticastSubscription<T> multicastSubscription2 : atomicReference.getAndSet(MulticastProcessor.TERMINATED)) {
                                    multicastSubscription2.onError(th2);
                                }
                                return;
                            }
                            for (final MulticastSubscription<T> multicastSubscription3 : atomicReference.getAndSet(MulticastProcessor.TERMINATED)) {
                                multicastSubscription3.onComplete();
                            }
                            return;
                        }
                        if (z2) {
                            break;
                        }
                        for (final MulticastSubscription<T> multicastSubscription4 : multicastSubscriptionArr) {
                            multicastSubscription4.onNext(t);
                        }
                        j3--;
                        if (1 != i4 && (i7 = i7 + 1) == i3) {
                            upstream.get().request(i3);
                            i7 = 0;
                        }
                    }
                    if (0 == j3) {
                        final MulticastSubscription<T>[] multicastSubscriptionArr3 = atomicReference.get();
                        final MulticastSubscription<T>[] multicastSubscriptionArr4 = MulticastProcessor.TERMINATED;
                        if (multicastSubscriptionArr3 == multicastSubscriptionArr4) {
                            simpleQueue.clear();
                            return;
                        }
                        if (multicastSubscriptionArr != multicastSubscriptionArr3) {
                            i2 = i7;
                        } else if (done && simpleQueue.isEmpty()) {
                            final Throwable th3 = error;
                            if (null != th3) {
                                for (final MulticastSubscription<T> multicastSubscription5 : atomicReference.getAndSet(multicastSubscriptionArr4)) {
                                    multicastSubscription5.onError(th3);
                                }
                                return;
                            }
                            for (final MulticastSubscription<T> multicastSubscription6 : atomicReference.getAndSet(multicastSubscriptionArr4)) {
                                multicastSubscription6.onComplete();
                            }
                            return;
                        }
                    }
                    i2 = i7;
                }
            }
            consumed = i2;
            i5 = wip.addAndGet(-i5);
            if (0 == i5) {
                return;
            }
        }
    }

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -363282618957264509L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(final Subscriber<? super T> subscriber, final MulticastProcessor<T> multicastProcessor) {
            downstream = subscriber;
            parent = multicastProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void request(final long j2) {
            long j3;
            long j4;
            if (SubscriptionHelper.validate(j2)) {
                do {
                    j3 = this.get();
                    if (Long.MIN_VALUE == j3) {
                        return;
                    }
                    j4 = LocationRequestCompat.PASSIVE_INTERVAL;
                    if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                        return;
                    }
                    final long j5 = j3 + j2;
                    if (0 <= j5) {
                        j4 = j5;
                    }
                } while (!this.compareAndSet(j3, j4));
                parent.drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (Long.MIN_VALUE != getAndSet(Long.MIN_VALUE)) {
                parent.remove(this);
            }
        }

        void onNext(final T t) {
            if (Long.MIN_VALUE != get()) {
                emitted++;
                downstream.onNext(t);
            }
        }

        void onError(final Throwable th) {
            if (Long.MIN_VALUE != get()) {
                downstream.onError(th);
            }
        }

        void onComplete() {
            if (Long.MIN_VALUE != get()) {
                downstream.onComplete();
            }
        }
    }
}
