package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class LambdaObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -7251123623727029452L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;
    final Consumer<? super Disposable> onSubscribe;

    public LambdaObserver(final Consumer<? super T> consumer, final Consumer<? super Throwable> consumer2, final Action action, final Consumer<? super Disposable> consumer3) {
        onNext = consumer;
        onError = consumer2;
        onComplete = action;
        onSubscribe = consumer3;
    }

    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            try {
                onSubscribe.accept(this);
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                disposable.dispose();
                this.onError(th);
            }
        }
    }
    public void onNext(final Object t) {
        if (this.isDisposed()) {
            return;
        }
        try {
            onNext.accept(t);
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            this.get().dispose();
            this.onError(th);
        }
    }

    public void onError(final Throwable th) {
        if (!this.isDisposed()) {
            this.lazySet(DisposableHelper.DISPOSED);
            try {
                onError.accept(th);
                return;
            } catch (final Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RxJavaPlugins.onError(new CompositeException(th, th2));
                return;
            }
        }
        RxJavaPlugins.onError(th);
    }
    public void onComplete() {
        if (this.isDisposed()) {
            return;
        }
        this.lazySet(DisposableHelper.DISPOSED);
        try {
            onComplete.run();
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
