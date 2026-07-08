package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class CallbackCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, Consumer<Throwable> {
    private static final long serialVersionUID = -4361286194466301354L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;

    public CallbackCompletableObserver(final Action action) {
        onError = this;
        onComplete = action;
    }

    public CallbackCompletableObserver(final Consumer<? super Throwable> consumer, final Action action) {
        onError = consumer;
        onComplete = action;
    }
    public void accept(final Throwable th) {
        RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
    }

    public Object invoke(Object obj) {

        return obj;
    }
    public void onComplete() {
        try {
            onComplete.run();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
        this.lazySet(DisposableHelper.DISPOSED);
    }
    public void onError(final Throwable th) {
        try {
            onError.accept(th);
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(th2);
        }
        this.lazySet(DisposableHelper.DISPOSED);
    }
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.DISPOSED == get();
    }

    public boolean hasCustomOnError() {
        return onError != this;
    }
}
