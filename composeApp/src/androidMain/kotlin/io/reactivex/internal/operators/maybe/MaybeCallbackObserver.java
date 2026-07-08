package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;



public final class MaybeCallbackObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -6076952298809384986L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public MaybeCallbackObserver(final Consumer<? super T> consumer, final Consumer<? super Throwable> consumer2, final Action action) {
        onSuccess = consumer;
        onError = consumer2;
        onComplete = action;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onSuccess.accept(t);
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onError.accept(th);
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onComplete.run();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    public boolean hasCustomOnError() {
        return onError != Functions.ON_ERROR_MISSING;
    }
}
