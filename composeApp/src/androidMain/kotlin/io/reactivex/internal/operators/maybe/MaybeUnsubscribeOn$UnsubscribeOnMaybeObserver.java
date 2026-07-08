package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeUnsubscribeOnUnsubscribeOnMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable, Runnable {
    private static final long serialVersionUID = 3256698449646456986L;
    final MaybeObserver<? super T> downstream;
    Disposable ds;
    final Scheduler scheduler;

    MaybeUnsubscribeOnUnsubscribeOnMaybeObserver(final MaybeObserver<? super T> maybeObserver, final Scheduler scheduler) {
        downstream = maybeObserver;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        final Disposable andSet = this.getAndSet(disposableHelper);
        if (disposableHelper != andSet) {
            ds = andSet;
            scheduler.scheduleDirect(this);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ds.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        downstream.onSuccess(t);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        downstream.onComplete();
    }
}
