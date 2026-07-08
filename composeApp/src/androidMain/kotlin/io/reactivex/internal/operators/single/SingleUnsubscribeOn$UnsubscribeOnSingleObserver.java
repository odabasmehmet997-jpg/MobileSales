package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class SingleUnsubscribeOnUnsubscribeOnSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, Runnable {
    private static final long serialVersionUID = 3256698449646456986L;
    final SingleObserver<? super T> downstream;
    Disposable ds;
    final Scheduler scheduler;

    SingleUnsubscribeOnUnsubscribeOnSingleObserver(SingleObserver<? super T> singleObserver, Scheduler scheduler) {
        this.downstream = singleObserver;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        Disposable andSet = getAndSet(disposableHelper);
        if (disposableHelper != andSet) {
            this.ds = andSet;
            this.scheduler.scheduleDirect(this);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.ds.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        this.downstream.onSuccess(t);
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }
}
