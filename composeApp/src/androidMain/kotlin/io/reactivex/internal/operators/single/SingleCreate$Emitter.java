package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;



final class SingleCreateEmitter<T> extends AtomicReference<Disposable> implements Disposable {
    private static final long serialVersionUID = -2467358622224974244L;
    final SingleObserver<? super T> downstream;

    SingleCreateEmitter(SingleObserver<? super T> singleObserver) {
        this.downstream = singleObserver;
    }

    public void onSuccess(T t) {
        Disposable andSet;
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper == disposable || disposableHelper == (andSet = this.getAndSet(disposableHelper))) {
            return;
        }
        try {
            if (null == t) {
                this.downstream.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.downstream.onSuccess(t);
            }
            if (null != andSet) {
                andSet.dispose();
            }
        } catch (Throwable th) {
            if (null != andSet) {
                andSet.dispose();
            }
            throw th;
        }
    }

    public void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public boolean tryOnError(Throwable th) {
        Disposable andSet;
        if (null == th) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper == disposable || disposableHelper == (andSet = this.getAndSet(disposableHelper))) {
            return false;
        }
        try {
            this.downstream.onError(th);
        } finally {
            if (null != andSet) {
                andSet.dispose();
            }
        }
    }

    public void setDisposable(Disposable disposable) {
        DisposableHelper.set(this, disposable);
    }

    public void setCancellable(Cancellable cancellable) {
        setDisposable(new CancellableDisposable(cancellable));
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return String.format("%s{%s}", SingleCreateEmitter.class.getSimpleName(), super.toString());
    }
}
