package io.reactivex.internal.operators.flowable;

import _COROUTINE.ArtificialStackFrames;
import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableFlatMapMaybeFlatMapMaybeSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 8600231336733376951L;
    volatile boolean cancelled;
    final boolean delayErrors;
    final Subscriber<? super R> downstream;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    final int maxConcurrency;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final CompositeDisposable set = new CompositeDisposable();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicInteger active = new AtomicInteger(1);
    final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

    FlowableFlatMapMaybeFlatMapMaybeSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z, int i2) {
        this.downstream = subscriber;
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            int i2 = this.maxConcurrency;
            if (Integer.MAX_VALUE == i2) {
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            } else {
                subscription.request(i2);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        try {
            MaybeSource maybeSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
            this.active.getAndIncrement();
            InnerObserver innerObserver = new InnerObserver();
            if (this.cancelled || !this.set.add(innerObserver)) {
                return;
            }
            maybeSource.subscribe(innerObserver);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.active.decrementAndGet();
        if (this.errors.addThrowable(th)) {
            if (!this.delayErrors) {
                this.set.dispose();
            }
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.active.decrementAndGet();
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        this.set.dispose();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    void innerSuccess(InnerObserver innerObserver, R r) {
        this.set.delete(innerObserver);
        if (0 == this.get()) {
            if (compareAndSet(0, 1)) {
                boolean z = 0 == active.decrementAndGet ();
                if (0 != requested.get ()) {
                    this.downstream.onNext(r);
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
                    if (z && (null == spscLinkedArrayQueue || spscLinkedArrayQueue.isEmpty())) {
                        Throwable terminate = this.errors.terminate();
                        if (null != terminate) {
                            this.downstream.onError(terminate);
                            return;
                        } else {
                            this.downstream.onComplete();
                            return;
                        }
                    }
                    BackpressureHelper.produced(this.requested, 1L);
                    if (Integer.MAX_VALUE != maxConcurrency) {
                        this.upstream.request(1L);
                    }
                } else {
                    SpscLinkedArrayQueue<R> orCreateQueue = getOrCreateQueue();
                    synchronized (orCreateQueue) {
                        orCreateQueue.offer(r);
                    }
                }
                if (0 == this.decrementAndGet()) {
                    return;
                }
                drainLoop();
            }
        }
        SpscLinkedArrayQueue<R> orCreateQueue2 = getOrCreateQueue();
        synchronized (orCreateQueue2) {
            orCreateQueue2.offer(r);
        }
        this.active.decrementAndGet();
        if (0 != this.getAndIncrement()) {
            return;
        }
        drainLoop();
    }

    SpscLinkedArrayQueue<R> getOrCreateQueue() {
        SpscLinkedArrayQueue<R> spscLinkedArrayQueue;
        do {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = this.queue.get();
            if (null != spscLinkedArrayQueue2) {
                return spscLinkedArrayQueue2;
            }
            spscLinkedArrayQueue = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.queue, null, spscLinkedArrayQueue));
        return spscLinkedArrayQueue;
    }

    void innerError(InnerObserver innerObserver, Throwable th) {
        this.set.delete(innerObserver);
        if (this.errors.addThrowable(th)) {
            if (!this.delayErrors) {
                this.upstream.cancel();
                this.set.dispose();
            } else if (Integer.MAX_VALUE != maxConcurrency) {
                this.upstream.request(1L);
            }
            this.active.decrementAndGet();
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    void innerComplete(InnerObserver innerObserver) {
        this.set.delete(innerObserver);
        if (0 == this.get()) {
            if (compareAndSet(0, 1)) {
                boolean z = 0 == active.decrementAndGet ();
                SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
                if (z && (null == spscLinkedArrayQueue || spscLinkedArrayQueue.isEmpty())) {
                    Throwable terminate = this.errors.terminate();
                    if (null != terminate) {
                        this.downstream.onError(terminate);
                        return;
                    } else {
                        this.downstream.onComplete();
                        return;
                    }
                }
                if (Integer.MAX_VALUE != maxConcurrency) {
                    this.upstream.request(1L);
                }
                if (0 == this.decrementAndGet()) {
                    return;
                }
                drainLoop();
                return;
            }
        }
        this.active.decrementAndGet();
        if (Integer.MAX_VALUE != maxConcurrency) {
            this.upstream.request(1L);
        }
        drain();
    }

    void drain() {
        if (0 == this.getAndIncrement()) {
            drainLoop();
        }
    }

    void clear() {
        SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
        if (null != spscLinkedArrayQueue) {
            spscLinkedArrayQueue.clear();
        }
    }

    void drainLoop() {
        Subscriber<? super R> subscriber = this.downstream;
        AtomicInteger atomicInteger = this.active;
        AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
        final int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (true) {
                final boolean z = false;
                if (j3 == j2) {
                    break;
                }
                if (this.cancelled) {
                    clear();
                    return;
                }
                if (!this.delayErrors && null != errors.get ()) {
                    Throwable terminate = this.errors.terminate();
                    clear();
                    subscriber.onError(terminate);
                    return;
                }
                boolean z2 = 0 == atomicInteger.get ();
                SpscLinkedArrayQueue<R> spscLinkedArrayQueue = atomicReference.get();
                ArtificialStackFrames poll = null != spscLinkedArrayQueue ? spscLinkedArrayQueue.poll() : null;
                boolean z3 = null == poll;
                if (z2 && z3) {
                    Throwable terminate2 = this.errors.terminate();
                    if (null != terminate2) {
                        subscriber.onError(terminate2);
                        return;
                    } else {
                        subscriber.onComplete();
                        return;
                    }
                }
                if (z3) {
                    break;
                }
                subscriber.onNext(poll);
                j3++;
            }
        } while (0 != i2);
    }

    final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
        private static final long serialVersionUID = -502562646270949838L;

        InnerObserver() {
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(R r) {
            FlowableFlatMapMaybeFlatMapMaybeSubscriber.this.innerSuccess(this, r);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            FlowableFlatMapMaybeFlatMapMaybeSubscriber.this.innerError(this, th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            FlowableFlatMapMaybeFlatMapMaybeSubscriber.this.innerComplete(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
