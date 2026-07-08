package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;


public final class BlockingMultiObserver<T> extends CountDownLatch implements SingleObserver<T>, CompletableObserver, MaybeObserver<T> {
    volatile boolean cancelled;
    Throwable error;
    Disposable upstream;
    T value;

    public BlockingMultiObserver() {
        super(1);
    }

    void dispose() {
        cancelled = true;
        final Disposable disposable = upstream;
        if (null != disposable) {
            disposable.dispose();
        }
    }
    public void onSubscribe(final Disposable disposable) {
        upstream = disposable;
        if (cancelled) {
            disposable.dispose();
        }
    }
    public void onSuccess(final T t) {
        value = t;
        this.countDown();
    }
    public void onError(final Throwable th) {
        error = th;
        this.countDown();
    }
    public void onComplete() {
        this.countDown();
    }

    public T blockingGet() {
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
