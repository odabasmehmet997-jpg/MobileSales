package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableTimerTimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
    private static final long serialVersionUID = 3167244060586201109L;
    final CompletableObserver downstream;

    CompletableTimerTimerDisposable(CompletableObserver completableObserver) {
        this.downstream = completableObserver;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    void setFuture(Disposable disposable) {
        DisposableHelper.replace(this, disposable);
    }
}
