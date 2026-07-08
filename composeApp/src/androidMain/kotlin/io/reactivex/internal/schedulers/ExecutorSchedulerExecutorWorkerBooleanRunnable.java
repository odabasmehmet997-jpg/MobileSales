package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;



final class ExecutorSchedulerExecutorWorkerBooleanRunnable extends AtomicBoolean implements Runnable, Disposable {
    private static final long serialVersionUID = -2421395018820541164L;
    final Runnable actual;

    ExecutorSchedulerExecutorWorkerBooleanRunnable(Runnable runnable) {
        this.actual = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (get ()) {
            return;
        }
        try {
            this.actual.run();
        } finally {
            lazySet (true);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        lazySet (true);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return get ();
    }
}
