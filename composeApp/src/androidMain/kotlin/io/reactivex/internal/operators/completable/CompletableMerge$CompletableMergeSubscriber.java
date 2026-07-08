package io.reactivex.internal.operators.completable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.jetbrains.annotations.UnknownNullability;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableMergeCompletableMergeSubscriber extends AtomicInteger implements FlowableSubscriber<CompletableSource>, Disposable {
    private static final long serialVersionUID = -2108443387387077490L;
    final boolean delayErrors;
    final CompletableObserver downstream;
    final int maxConcurrency;
    Subscription upstream;
    final CompositeDisposable set = new CompositeDisposable();
    final AtomicThrowable error = new AtomicThrowable();

    CompletableMergeCompletableMergeSubscriber(CompletableObserver completableObserver, int i2, boolean z) {
        this.downstream = completableObserver;
        this.maxConcurrency = i2;
        this.delayErrors = z;
        lazySet(1);
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.upstream.cancel();
        this.set.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.set.isDisposed();
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
    public void onNext(@UnknownNullability Object completableSource) {
        getAndIncrement();
        MergeInnerObserver mergeInnerObserver = new MergeInnerObserver();
        this.set.add(mergeInnerObserver);
        completableSource.subscribe(mergeInnerObserver);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (!this.delayErrors) {
            this.set.dispose();
            if (this.error.addThrowable(th)) {
                if (0 < this.getAndSet(0)) {
                    this.downstream.onError(this.error.terminate());
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
            return;
        }
        if (this.error.addThrowable(th)) {
            if (0 == this.decrementAndGet()) {
                this.downstream.onError(this.error.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (0 == this.decrementAndGet()) {
            if (null != error.get ()) {
                this.downstream.onError(this.error.terminate());
            } else {
                this.downstream.onComplete();
            }
        }
    }

    void innerError(MergeInnerObserver mergeInnerObserver, Throwable th) {
        this.set.delete(mergeInnerObserver);
        if (!this.delayErrors) {
            this.upstream.cancel();
            this.set.dispose();
            if (this.error.addThrowable(th)) {
                if (0 < this.getAndSet(0)) {
                    this.downstream.onError(this.error.terminate());
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
            return;
        }
        if (this.error.addThrowable(th)) {
            if (0 == this.decrementAndGet()) {
                this.downstream.onError(this.error.terminate());
                return;
            } else {
                if (Integer.MAX_VALUE != maxConcurrency) {
                    this.upstream.request(1L);
                    return;
                }
                return;
            }
        }
        RxJavaPlugins.onError(th);
    }

    void innerComplete(MergeInnerObserver mergeInnerObserver) {
        this.set.delete(mergeInnerObserver);
        if (0 == this.decrementAndGet()) {
            Throwable th = this.error.get();
            if (null != th) {
                this.downstream.onError(th);
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

    final class MergeInnerObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 251330541679988317L;

        MergeInnerObserver() {
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            CompletableMergeCompletableMergeSubscriber.this.innerError(this, th);
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            CompletableMergeCompletableMergeSubscriber.this.innerComplete(this);
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
