package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableWindowBoundarySupplierWindowBoundaryMainSubscriber<T, B> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
    static final FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<Object, Object> BOUNDARY_DISPOSED = new FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<>(null);
    static final Object NEXT_WINDOW = new Object();
    private static final long serialVersionUID = 2233020065421370272L;
    final int capacityHint;
    volatile boolean done;
    final Subscriber<? super Flowable<T>> downstream;
    long emitted;
    final Callable<? extends Publisher<B>> other;
    Subscription upstream;
    UnicastProcessor<T> window;
    final AtomicReference<FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<T, B>> boundarySubscriber = new AtomicReference<>();
    final AtomicInteger windows = new AtomicInteger(1);
    final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicBoolean stopWindows = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();

    FlowableWindowBoundarySupplierWindowBoundaryMainSubscriber(Subscriber<? super Flowable<T>> subscriber, int i2, Callable<? extends Publisher<B>> callable) {
        this.downstream = subscriber;
        this.capacityHint = i2;
        this.other = callable;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            this.queue.offer(NEXT_WINDOW);
            drain();
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.queue.offer(t);
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        disposeBoundary();
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        disposeBoundary();
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.stopWindows.compareAndSet(false, true)) {
            disposeBoundary();
            if (0 == windows.decrementAndGet ()) {
                this.upstream.cancel();
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        BackpressureHelper.add(this.requested, j2);
    }

    void disposeBoundary() {
        AtomicReference<FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<T, B>> atomicReference = this.boundarySubscriber;
        FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<Object, Object> flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber = BOUNDARY_DISPOSED;
        Disposable disposable = atomicReference.getAndSet(flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber);
        if (null == disposable || disposable == flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber) {
            return;
        }
        disposable.dispose();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (0 == windows.decrementAndGet ()) {
            this.upstream.cancel();
        }
    }

    void innerNext(FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<T, B> flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber) {
        LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.boundarySubscriber, flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber, null);
        this.queue.offer(NEXT_WINDOW);
        drain();
    }

    void innerError(Throwable th) {
        this.upstream.cancel();
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void innerComplete() {
        this.upstream.cancel();
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
                        if (j2 != this.requested.get()) {
                            UnicastProcessor<T> create = UnicastProcessor.create(this.capacityHint, this);
                            this.window = create;
                            this.windows.getAndIncrement();
                            try {
                                Publisher publisher = ObjectHelper.requireNonNull(this.other.call(), "The other Callable returned a null Publisher");
                                FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber = new FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber(this);
                                if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.boundarySubscriber, null, flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber)) {
                                    publisher.subscribe(flowableWindowBoundarySupplierWindowBoundaryInnerSubscriber);
                                    j2++;
                                    subscriber.onNext(create);
                                }
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                atomicThrowable.addThrowable(th);
                                this.done = true;
                            }
                        } else {
                            this.upstream.cancel();
                            disposeBoundary();
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
