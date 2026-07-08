package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;

import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public final class FutureObserver<T> extends CountDownLatch implements Observer<T>, Future<T>, Disposable {
    Throwable error;
    final AtomicReference<Disposable> upstream;
    T value;
    private Object LifecycleKt;

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
    }

    public FutureObserver() {
        super(1);
        upstream = new AtomicReference<>();
    }

    public boolean cancel(final boolean z) {
        Disposable disposable;
        Disposable disposableHelper;
        do {
            disposable = upstream.get();
            if (disposable == this || disposable == (disposableHelper = DisposableHelper.DISPOSED)) {
                return false;
            }
        } while (!LifecycleKt.m(upstream, disposable, disposableHelper));
        if (null != disposable) {
            disposable.dispose();
        }
        this.countDown();
        return true;
    }

    public boolean isCancelled() {
        return DisposableHelper.isDisposed(upstream.get());
    }

    public boolean isDone() {
        return 0 == getCount();
    }

    public T get() throws InterruptedException, ExecutionException {
        if (0 != getCount()) {
            BlockingHelper.verifyNonBlocking();
            this.await();
        }
        if (this.isCancelled()) {
            throw new CancellationException();
        }
        final Throwable th = error;
        if (null != th) {
            throw new ExecutionException(th);
        }
        return value;
    }
    public T get(final long j2, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (0 != getCount()) {
            BlockingHelper.verifyNonBlocking();
            if (!this.await(j2, timeUnit)) {
                throw new TimeoutException(ExceptionHelper.timeoutMessage(j2, timeUnit));
            }
        }
        if (this.isCancelled()) {
            throw new CancellationException();
        }
        final Throwable th = error;
        if (null != th) {
            throw new ExecutionException(th);
        }
        return value;
    }
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(upstream, disposable);
    }

    public void onNext(final Object t) {
        if (null != this.value) {
            upstream.get().dispose();
            this.onError(new IndexOutOfBoundsException("More than one element received"));
        } else {
            value = t;
        }
    }

    public void onError(final Throwable th) {
        Disposable disposable;
        if (null == this.error) {
            error = th;
            do {
                disposable = upstream.get();
                if (disposable == this || DisposableHelper.DISPOSED == disposable) {
                    RxJavaPlugins.onError(th);
                    return;
                }
            } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(upstream, disposable, this));
            this.countDown();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        Disposable disposable;
        if (null == this.value) {
            this.onError(new NoSuchElementException("The source is empty"));
            return;
        }
        do {
            disposable = upstream.get();
            if (disposable == this || DisposableHelper.DISPOSED == disposable) {
                return;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(upstream, disposable, this));
        this.countDown();
    }
    public boolean isDisposed() {
        return this.isDone();
    }
}
