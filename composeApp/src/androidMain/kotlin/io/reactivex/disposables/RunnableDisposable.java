package io.reactivex.disposables;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;
    RunnableDisposable(Runnable runnable) {
        super(runnable);
    }
    protected void onDisposed(Runnable runnable) {
        runnable.run();
    }
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }
    public void onError(Throwable th) {

    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
}
