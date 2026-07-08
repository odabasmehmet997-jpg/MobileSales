package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeDelayDelayMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable, Runnable {
    private static final long serialVersionUID = 5566860102500855068L;
    final long delay;
    final MaybeObserver<? super T> downstream;
    Throwable error;
    final Scheduler scheduler;
    final TimeUnit unit;
    T value;

    MaybeDelayDelayMaybeObserver(MaybeObserver<? super T> maybeObserver, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.downstream = maybeObserver;
        this.delay = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // java.lang.Runnable
    public void run() {
        Throwable th = this.error;
        if (null != th) {
            this.downstream.onError(th);
            return;
        }
        T t = this.value;
        if (null != t) {
            this.downstream.onSuccess(t);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.value = t;
        schedule();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.error = th;
        schedule();
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        schedule();
    }

    void schedule() {
        DisposableHelper.replace(this, this.scheduler.scheduleDirect(this, this.delay, this.unit));
    }
}
