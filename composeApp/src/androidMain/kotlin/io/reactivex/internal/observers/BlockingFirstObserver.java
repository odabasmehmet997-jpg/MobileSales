package io.reactivex.internal.observers;


public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    public void onNext(final Object t) {
        if (null == this.value) {
            value = t;
            upstream.dispose();
            this.countDown();
        }
    }
    public void onError(final Throwable th) {
        if (null == this.value) {
            error = th;
        }
        this.countDown();
    }
}
