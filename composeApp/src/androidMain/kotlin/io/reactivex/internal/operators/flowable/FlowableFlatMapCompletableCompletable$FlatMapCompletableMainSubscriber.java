package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableFlatMapCompletableCompletableFlatMapCompletableMainSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
    private static final long serialVersionUID = 8443155186132538303L;
    final boolean delayErrors;
    volatile boolean disposed;
    final CompletableObserver downstream;
    final Function<? super T, ? extends CompletableSource> mapper;
    final int maxConcurrency;
    Subscription upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final CompositeDisposable set = new CompositeDisposable();

    FlowableFlatMapCompletableCompletableFlatMapCompletableMainSubscriber(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, boolean z, int i2) {
        this.downstream = completableObserver;
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i2;
        lazySet(1);
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
            CompletableSource completableSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null CompletableSource");
            getAndIncrement();
            InnerObserver innerObserver = new InnerObserver();
            if (this.disposed || !this.set.add(innerObserver)) {
                return;
            }
            completableSource.subscribe(innerObserver);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (this.delayErrors) {
                if (0 == this.decrementAndGet()) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                } else {
                    if (Integer.MAX_VALUE != maxConcurrency) {
                        this.upstream.request(1L);
                        return;
                    }
                    return;
                }
            }
            dispose();
            if (0 < this.getAndSet(0)) {
                this.downstream.onError(this.errors.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (0 == this.decrementAndGet()) {
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
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        this.upstream.cancel();
        this.set.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.set.isDisposed();
    }

    void innerComplete(InnerObserver innerObserver) {
        this.set.delete(innerObserver);
        onComplete();
    }

    void innerError(InnerObserver innerObserver, Throwable th) {
        this.set.delete(innerObserver);
        onError(th);
    }

    final class InnerObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 8606673141535671828L;

        InnerObserver() {
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            FlowableFlatMapCompletableCompletableFlatMapCompletableMainSubscriber.this.innerComplete(this);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            FlowableFlatMapCompletableCompletableFlatMapCompletableMainSubscriber.this.innerError(this, th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
