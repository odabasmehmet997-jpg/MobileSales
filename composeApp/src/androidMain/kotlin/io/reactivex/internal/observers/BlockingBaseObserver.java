package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;


public abstract class BlockingBaseObserver<T> extends CountDownLatch implements Observer<T>, Disposable {
    volatile boolean cancelled;
    Throwable error;
    Disposable upstream;
    T value;
    protected BlockingBaseObserver() {
        super(1);
    }
    public final void onSubscribe(final Disposable disposable) {
        upstream = disposable;
        if (cancelled) {
            disposable.dispose();
        }
    }
    public final void onComplete() {
        this.countDown();
    }
    public final void dispose() {
        cancelled = true;
        final Disposable disposable = upstream;
        if (null != disposable) {
            disposable.dispose();
        }
    }
    public final boolean isDisposed() {
        return cancelled;
    }
    public final T blockingGet() {
        if (0 != getCount()) {
            try {
                BlockingHelper.verifyNonBlocking();
                this.await();
            } catch (final InterruptedException e2) {
                this.dispose();
                throw ExceptionHelper.wrapOrThrow(e2);
            }
        }
        final Throwable th = error;
        if (null != th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
        return value;
    }
}
