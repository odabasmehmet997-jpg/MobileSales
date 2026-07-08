package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimerTimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
    private static final long serialVersionUID = 2875964065294031672L;
    final MaybeObserver<? super Long> downstream;

    MaybeTimerTimerDisposable(final MaybeObserver<? super Long> maybeObserver) {
        downstream = maybeObserver;
    }

    @Override // java.lang.Runnable
    public void run() {
        downstream.onSuccess(0L);
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    void setFuture(final Disposable disposable) {
        DisposableHelper.replace(this, disposable);
    }
}
