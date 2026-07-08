package io.reactivex.internal.observers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;


public final class ConsumerSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -7012088219455310787L;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public ConsumerSingleObserver(final Consumer<? super T> consumer, final Consumer<? super Throwable> consumer2) {
        onSuccess = consumer;
        onError = consumer2;
    }

    public void onError(final Throwable th) {
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onError.accept(th);
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onSuccess(final T t) {
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onSuccess.accept(t);
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.DISPOSED == get();
    }

    public boolean hasCustomOnError() {
        return onError != Functions.ON_ERROR_MISSING;
    }
}
