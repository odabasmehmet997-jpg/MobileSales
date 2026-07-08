package io.reactivex.internal.operators.mixed;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableConcatMapCompletableConcatMapCompletableObserver<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
    private static final long serialVersionUID = 3610901111000061034L;
    volatile boolean active;
    int consumed;
    volatile boolean disposed;
    volatile boolean done;
    final CompletableObserver downstream;
    final ErrorMode errorMode;
    final AtomicThrowable errors = new AtomicThrowable();
    final ConcatMapInnerObserver inner = new ConcatMapInnerObserver(this);
    final Function<? super T, ? extends CompletableSource> mapper;
    final int prefetch;
    final SimplePlainQueue<T> queue;
    Subscription upstream;

    FlowableConcatMapCompletableConcatMapCompletableObserver(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, ErrorMode errorMode, int i2) {
        this.downstream = completableObserver;
        this.mapper = function;
        this.errorMode = errorMode;
        this.prefetch = i2;
        this.queue = new SpscArrayQueue(i2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.queue.offer(t)) {
            drain();
        } else {
            this.upstream.cancel();
            onError(new MissingBackpressureException("Queue full?!"));
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (ErrorMode.IMMEDIATE == errorMode) {
                this.inner.dispose();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.downstream.onError(terminate);
                }
                if (0 == this.getAndIncrement()) {
                    this.queue.clear();
                    return;
                }
                return;
            }
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        this.upstream.cancel();
        this.inner.dispose();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    void innerError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (ErrorMode.IMMEDIATE == errorMode) {
                this.upstream.cancel();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.downstream.onError(terminate);
                }
                if (0 == this.getAndIncrement()) {
                    this.queue.clear();
                    return;
                }
                return;
            }
            this.active = false;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    void innerComplete() {
        this.active = false;
        drain();
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        while (!this.disposed) {
            if (!this.active) {
                if (ErrorMode.BOUNDARY == errorMode && null != errors.get ()) {
                    this.queue.clear();
                    this.downstream.onError(this.errors.terminate());
                    return;
                }
                boolean z = this.done;
                T poll = this.queue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    Throwable terminate = this.errors.terminate();
                    if (null != terminate) {
                        this.downstream.onError(terminate);
                        return;
                    } else {
                        this.downstream.onComplete();
                        return;
                    }
                }
                if (!z2) {
                    int i2 = this.prefetch;
                    int i3 = i2 - (i2 >> 1);
                    int i4 = this.consumed + 1;
                    if (i4 == i3) {
                        this.consumed = 0;
                        this.upstream.request(i3);
                    } else {
                        this.consumed = i4;
                    }
                    try {
                        CompletableSource completableSource = ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                        this.active = true;
                        completableSource.subscribe(this.inner);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.queue.clear();
                        this.upstream.cancel();
                        this.errors.addThrowable(th);
                        this.downstream.onError(this.errors.terminate());
                        return;
                    }
                }
            }
            if (0 == this.decrementAndGet()) {
                return;
            }
        }
        this.queue.clear();
    }

    static final class ConcatMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = 5638352172918776687L;
        final FlowableConcatMapCompletableConcatMapCompletableObserver<?> parent;

        ConcatMapInnerObserver(FlowableConcatMapCompletableConcatMapCompletableObserver<?> flowableConcatMapCompletableConcatMapCompletableObserver) {
            this.parent = flowableConcatMapCompletableConcatMapCompletableObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            this.parent.innerComplete();
        }

        void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
