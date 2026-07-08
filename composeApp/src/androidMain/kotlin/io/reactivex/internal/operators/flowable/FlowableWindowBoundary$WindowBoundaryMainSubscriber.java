package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableWindowBoundaryWindowBoundaryMainSubscriber<T, B> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    static final Object NEXT_WINDOW = new Object();
    private static final long serialVersionUID = 2233020065421370272L;
    final int capacityHint;
    volatile boolean done;
    final Subscriber<? super Flowable<T>> downstream;
    long emitted;
    UnicastProcessor<T> window;
    final FlowableWindowBoundaryWindowBoundaryInnerSubscriber<T, B> boundarySubscriber = new DisposableSubscriber<B>(this) { // from class: io.reactivex.internal.operators.flowable.FlowableWindowBoundaryWindowBoundaryInnerSubscriber
        boolean done;
        final FlowableWindowBoundaryWindowBoundaryMainSubscriber<T, B> parent;

        {
            this.parent = this;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object b2) {
            if (this.done) {
                return;
            }
            this.parent.innerNext();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.parent.innerError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.innerComplete();
        }
    };
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicInteger windows = new AtomicInteger(1);
    final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicBoolean stopWindows = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();

    FlowableWindowBoundaryWindowBoundaryMainSubscriber(Subscriber<? super Flowable<T>> subscriber, int i2) {
        this.downstream = subscriber;
        this.capacityHint = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this.upstream, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.queue.offer(t);
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        dispose();
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        dispose();
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.stopWindows.compareAndSet(false, true)) {
            dispose();
            if (0 == windows.decrementAndGet ()) {
                SubscriptionHelper.cancel(this.upstream);
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        BackpressureHelper.add(this.requested, j2);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (0 == windows.decrementAndGet ()) {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    void innerNext() {
        this.queue.offer(NEXT_WINDOW);
        drain();
    }

    void innerError(Throwable th) {
        SubscriptionHelper.cancel(this.upstream);
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void innerComplete() {
        SubscriptionHelper.cancel(this.upstream);
        this.done = true;
        drain();
    }
    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super Flowable<T>> subscriber = this.downstream;
        MpscLinkedQueue<Object> mpscLinkedQueue = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        long j2 = this.emitted;
        int i2 = 1;
        while (0 != windows.get ()) {
            UnicastProcessor<T> unicastProcessor = this.window;
            boolean z = this.done;
            if (z && null != atomicThrowable.get ()) {
                mpscLinkedQueue.clear();
                Throwable terminate = atomicThrowable.terminate();
                if (0 != unicastProcessor) {
                    this.window = null;
                    unicastProcessor.onError(terminate);
                }
                subscriber.onError(terminate);
                return;
            }
            Object poll = mpscLinkedQueue.poll();
            boolean z2 = null == poll;
            if (z && z2) {
                Throwable terminate2 = atomicThrowable.terminate();
                if (null == terminate2) {
                    if (0 != unicastProcessor) {
                        this.window = null;
                        unicastProcessor.onComplete();
                    }
                    subscriber.onComplete();
                    return;
                }
                if (0 != unicastProcessor) {
                    this.window = null;
                    unicastProcessor.onError(terminate2);
                }
                subscriber.onError(terminate2);
                return;
            }
            if (!z2) {
                if (poll != NEXT_WINDOW) {
                    unicastProcessor.onNext(poll);
                } else {
                    if (0 != unicastProcessor) {
                        this.window = null;
                        unicastProcessor.onComplete();
                    }
                    if (!this.stopWindows.get()) {
                        UnicastProcessor<T> create = UnicastProcessor.create(this.capacityHint, this);
                        this.window = create;
                        this.windows.getAndIncrement();
                        if (j2 != this.requested.get()) {
                            j2++;
                            subscriber.onNext(create);
                        } else {
                            SubscriptionHelper.cancel(this.upstream);
                            dispose();
                            atomicThrowable.addThrowable(new MissingBackpressureException("Could not deliver a window due to lack of requests"));
                            this.done = true;
                        }
                    }
                }
            } else {
                this.emitted = j2;
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
        mpscLinkedQueue.clear();
        this.window = null;
    }
}
