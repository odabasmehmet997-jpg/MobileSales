package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimeoutMaybeTimeoutMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -5955289211445418871L;
    final MaybeObserver<? super T> downstream;
    final MaybeSource<? extends T> fallback;
    final MaybeTimeoutMaybeTimeoutOtherMaybeObserver<T, U> other = new MaybeTimeoutMaybeTimeoutOtherMaybeObserver<>(this);
    final MaybeTimeoutMaybeTimeoutFallbackMaybeObserver<T> otherObserver;

    MaybeTimeoutMaybeTimeoutMainMaybeObserver(MaybeObserver<? super T> maybeObserver, MaybeSource<? extends T> maybeSource) {
        this.downstream = maybeObserver;
        this.fallback = maybeSource;
        this.otherObserver = null != maybeSource ? new MaybeTimeoutMaybeTimeoutFallbackMaybeObserver<>(maybeObserver) : null;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        DisposableHelper.dispose(this.other);
        MaybeTimeoutMaybeTimeoutFallbackMaybeObserver<T> maybeTimeoutMaybeTimeoutFallbackMaybeObserver = this.otherObserver;
        if (null != maybeTimeoutMaybeTimeoutFallbackMaybeObserver) {
            DisposableHelper.dispose(maybeTimeoutMaybeTimeoutFallbackMaybeObserver);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onSuccess(t);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onComplete();
        }
    }

    public void otherError(Throwable th) {
        if (DisposableHelper.dispose(this)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    public void otherComplete() {
        if (DisposableHelper.dispose(this)) {
            MaybeSource<? extends T> maybeSource = this.fallback;
            if (null == maybeSource) {
                this.downstream.onError(new TimeoutException());
            } else {
                maybeSource.subscribe(this.otherObserver);
            }
        }
    }
}
