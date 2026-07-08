package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableDelayDelay extends AtomicReference<Disposable> implements CompletableObserver, Runnable, Disposable {
    private static final long serialVersionUID = 465972761105851022L;
    final long delay;
    final boolean delayError;
    final CompletableObserver downstream;
    Throwable error;
    final Scheduler scheduler;
    final TimeUnit unit;

    CompletableDelayDelay(CompletableObserver completableObserver, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.downstream = completableObserver;
        this.delay = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        DisposableHelper.replace(this, this.scheduler.scheduleDirect(this, this.delay, this.unit));
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.error = th;
        DisposableHelper.replace(this, this.scheduler.scheduleDirect(this, this.delayError ? this.delay : 0L, this.unit));
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // java.lang.Runnable
    public void run() {
        Throwable th = this.error;
        this.error = null;
        if (null != th) {
            this.downstream.onError(th);
        } else {
            this.downstream.onComplete();
        }
    }
}
