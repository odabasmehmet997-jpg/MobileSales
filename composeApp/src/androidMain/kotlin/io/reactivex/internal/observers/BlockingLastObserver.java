package io.reactivex.internal.observers;


public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    public void onNext(final Object t) {
        value = t;
    }
    public void onError(final Throwable th) {
        value = null;
        error = th;
        this.countDown();
    }
}
