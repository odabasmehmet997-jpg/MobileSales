package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Flowable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class UnicastProcessor<T> extends FlowableProcessor<T> {
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    final AtomicReference<Subscriber<? super T>> downstream;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final AtomicBoolean once;
    final SpscLinkedArrayQueue<T> queue;
    final AtomicLong requested;
    final BasicIntQueueSubscription<T> wip;
    public static <T> UnicastProcessor<T> create() {
        return new UnicastProcessor<>(Flowable.bufferSize());
    }
    public static <T> UnicastProcessor<T> create(final int i2, final Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "onTerminate");
        return new UnicastProcessor<>(i2, runnable);
    }
    UnicastProcessor(final int i2) {
        this(i2, null, true);
    }
    UnicastProcessor(final int i2, final Runnable runnable) {
        this(i2, runnable, true);
    }
    UnicastProcessor(final int i2, final Runnable runnable, final boolean z) {
        queue = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(i2, "capacityHint"));
        onTerminate = new AtomicReference<>(runnable);
        delayError = z;
        downstream = new AtomicReference<>();
        once = new AtomicBoolean();
        wip = new UnicastQueueSubscription();
        requested = new AtomicLong();
    }
    void doTerminate() {
        final Runnable andSet = onTerminate.getAndSet(null);
        if (null != andSet) {
            andSet.run();
        }
    }
    void drainRegular(final Subscriber<? super T> subscriber) {
        long j2;
        final SpscLinkedArrayQueue<T> spscLinkedArrayQueue = queue;
        boolean z = true;
        final boolean z2 = !delayError;
        int i2 = 1;
        while (true) {
            final long j3 = requested.get();
            long j4 = 0;
            while (true) {
                if (j3 == j4) {
                    j2 = j4;
                    break;
                }
                final boolean z3 = done;
                final T poll = spscLinkedArrayQueue.poll();
                final boolean z4 = null == poll && z;
                j2 = j4;
                if (this.checkTerminated(z2, z3, z4, subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (z4) {
                    break;
                }
                subscriber.onNext(poll);
                j4 = 1 + j2;
                z = true;
            }
            if (j3 == j4 && this.checkTerminated(z2, done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                return;
            }
            if (0 != j2 && LocationRequestCompat.PASSIVE_INTERVAL != j3) {
                requested.addAndGet(-j2);
            }
            i2 = wip.addAndGet(-i2);
            if (0 == i2) {
                return;
            } else {
                z = true;
            }
        }
    }
    void drainFused(final Subscriber<? super T> subscriber) {
        final SpscLinkedArrayQueue<T> spscLinkedArrayQueue = queue;
        final boolean z = delayError;
        int i2 = 1;
        while (!cancelled) {
            final boolean z2 = done;
            if (!z && z2 && null != this.error) {
                spscLinkedArrayQueue.clear();
                downstream.lazySet(null);
                subscriber.onError(error);
                return;
            }
            subscriber.onNext(null);
            if (z2) {
                downstream.lazySet(null);
                final Throwable th = error;
                if (null != th) {
                    subscriber.onError(th);
                    return;
                } else {
                    subscriber.onComplete();
                    return;
                }
            }
            i2 = wip.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
        downstream.lazySet(null);
    }
    void drain() {
        if (0 != this.wip.getAndIncrement ()) {
            return;
        }
        Subscriber<? super T> subscriber = downstream.get();
        int i2 = 1;
        while (null == subscriber) {
            i2 = wip.addAndGet(-i2);
            if (0 == i2) {
                return;
            } else {
                subscriber = downstream.get();
            }
        }
        if (enableOperatorFusion) {
            this.drainFused(subscriber);
        } else {
            this.drainRegular(subscriber);
        }
    }
    boolean checkTerminated(final boolean z, final boolean z2, final boolean z3, final Subscriber<? super T> subscriber, final SpscLinkedArrayQueue<T> spscLinkedArrayQueue) {
        if (cancelled) {
            spscLinkedArrayQueue.clear();
            downstream.lazySet(null);
            return true;
        }
        if (!z2) {
            return false;
        }
        if (z && null != this.error) {
            spscLinkedArrayQueue.clear();
            downstream.lazySet(null);
            subscriber.onError(error);
            return true;
        }
        if (!z3) {
            return false;
        }
        final Throwable th = error;
        downstream.lazySet(null);
        if (null != th) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
        return true;
    }public void onSubscribe(final Subscription subscription) {
        if (done || cancelled) {
            subscription.cancel();
        } else {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(final Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (done || cancelled) {
            return;
        }
        queue.offer((T) t);
        this.drain();
    }
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (done || cancelled) {
            RxJavaPlugins.onError(th);
            return;
        }
        error = th;
        done = true;
        this.doTerminate();
        this.drain();
    }
    public void onComplete() {
        if (done || cancelled) {
            return;
        }
        done = true;
        this.doTerminate();
        this.drain();
    }
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        if (!once.get() && once.compareAndSet(false, true)) {
            subscriber.onSubscribe(wip);
            downstream.set(subscriber);
            if (cancelled) {
                downstream.lazySet(null);
                return;
            } else {
                this.drain();
                return;
            }
        }
        EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), subscriber);
    }
    final class UnicastQueueSubscription extends BasicIntQueueSubscription<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        UnicastQueueSubscription() {
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
        public T poll() {
            return queue.poll();
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            queue.clear();
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(final int i2) {
            if (0 == (i2 & 2)) {
                return 0;
            }
            enableOperatorFusion = true;
            return 2;
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
        public void request(final long j2) {
            if (SubscriptionHelper.validate(j2)) {
                BackpressureHelper.add(requested, j2);
                drain();
            }
        }

        @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
        public void cancel() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            doTerminate();
            downstream.lazySet(null);
            if (0 == UnicastProcessor.this.wip.getAndIncrement ()) {
                downstream.lazySet(null);
                final UnicastProcessor unicastProcessor = UnicastProcessor.this;
                if (unicastProcessor.enableOperatorFusion) {
                    return;
                }
                unicastProcessor.queue.clear();
            }
        }
    }
}
