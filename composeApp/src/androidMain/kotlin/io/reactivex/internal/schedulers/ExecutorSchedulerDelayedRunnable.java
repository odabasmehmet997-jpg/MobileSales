package io.reactivex.internal.schedulers;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.Functions;
import java.util.concurrent.atomic.AtomicReference;



final class ExecutorSchedulerDelayedRunnable extends AtomicReference<Runnable> implements Runnable, Disposable {
    private static final long serialVersionUID = -4101336210206799084L;
    final SequentialDisposable direct;
    final SequentialDisposable timed;

    ExecutorSchedulerDelayedRunnable(Runnable runnable) {
        super(runnable);
        this.timed = new SequentialDisposable();
        this.direct = new SequentialDisposable();
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = get ();
        if (null != runnable) {
            try {
                runnable.run();
                lazySet (null);
                SequentialDisposable sequentialDisposable = this.timed;
                final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
                sequentialDisposable.lazySet(disposableHelper);
                this.direct.lazySet(disposableHelper);
            } catch (Throwable th) {
                lazySet (null);
                this.timed.lazySet(DisposableHelper.DISPOSED);
                this.direct.lazySet(DisposableHelper.DISPOSED);
                throw th;
            }
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return null == this.get();
    }

    @Override
    public void onError(Throwable th) {

    }

    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (null != this.getAndSet(null)) {
            this.timed.dispose();
            this.direct.dispose();
        }
    }

    public Runnable getWrappedRunnable() {
        Runnable runnable = get ();
        return null != runnable ? runnable : Functions.EMPTY_RUNNABLE;
    }
}
