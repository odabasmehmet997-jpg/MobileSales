package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimeoutPublisherTimeoutMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -5955289211445418871L;
    final MaybeObserver<? super T> downstream;
    final MaybeSource<? extends T> fallback;
    final MaybeTimeoutPublisherTimeoutOtherMaybeObserver<T, U> other = new MaybeTimeoutPublisherTimeoutOtherMaybeObserver<>(this);
    final MaybeTimeoutPublisherTimeoutFallbackMaybeObserver<T> otherObserver;

    MaybeTimeoutPublisherTimeoutMainMaybeObserver(final MaybeObserver<? super T> maybeObserver, final MaybeSource<? extends T> maybeSource) {
        downstream = maybeObserver;
        fallback = maybeSource;
        otherObserver = null != maybeSource ? new MaybeTimeoutPublisherTimeoutFallbackMaybeObserver<>(maybeObserver) : null;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        SubscriptionHelper.cancel(other);
        final MaybeTimeoutPublisherTimeoutFallbackMaybeObserver<T> maybeTimeoutPublisherTimeoutFallbackMaybeObserver = otherObserver;
        if (null != maybeTimeoutPublisherTimeoutFallbackMaybeObserver) {
            DisposableHelper.dispose(maybeTimeoutPublisherTimeoutFallbackMaybeObserver);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        SubscriptionHelper.cancel(other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != getAndSet(disposableHelper)) {
            downstream.onSuccess(t);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        SubscriptionHelper.cancel(other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != getAndSet(disposableHelper)) {
            downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        SubscriptionHelper.cancel(other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != getAndSet(disposableHelper)) {
            downstream.onComplete();
        }
    }

    public void otherError(final Throwable th) {
        if (DisposableHelper.dispose(this)) {
            downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    public void otherComplete() {
        if (DisposableHelper.dispose(this)) {
            final MaybeSource<? extends T> maybeSource = fallback;
            if (null == maybeSource) {
                downstream.onError(new TimeoutException());
            } else {
                maybeSource.subscribe(otherObserver);
            }
        }
    }
}
