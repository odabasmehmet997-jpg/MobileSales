package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableCreateEmitter extends AtomicReference<Disposable> implements Disposable {
    private static final long serialVersionUID = -2467358622224974244L;
    final CompletableObserver downstream;
    CompletableCreateEmitter(CompletableObserver completableObserver) {
        this.downstream = completableObserver;
    }
    public void onComplete() {
        Disposable andSet;
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper == disposable || disposableHelper == (andSet = this.getAndSet(disposableHelper))) {
            return;
        }
        try {
            this.downstream.onComplete();
        } finally {
            if (null != andSet) {
                andSet.dispose();
            }
        }
    }
    public void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
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
        return false;
    }
    public void setDisposable(Disposable disposable) {
        DisposableHelper.set(this, disposable);
    }
    public void setCancellable(Cancellable cancellable) {
        setDisposable(new CancellableDisposable(cancellable));
    }
    public void dispose() {
        DisposableHelper.dispose(this);
    }
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
    public String toString() {
        return String.format("%s{%s}", CompletableCreateEmitter.class.getSimpleName(), super.toString());
    }
}
