package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;



public final class ForEachWhileObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Predicate<? super T> onNext;

    public ForEachWhileObserver(final Predicate<? super T> predicate, final Consumer<? super Throwable> consumer, final Action action) {
        onNext = predicate;
        onError = consumer;
        onComplete = action;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.Observer
    public void onNext(final Object t) {
        if (done) {
            return;
        }
        try {
            if (onNext.test(t)) {
                return;
            }
            this.dispose();
            this.onComplete();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            this.dispose();
            this.onError(th);
        }
    }

    @Override // io.reactivex.Observer
    public void onError(final Throwable th) {
        if (done) {
            RxJavaPlugins.onError(th);
            return;
        }
        done = true;
        try {
            onError.accept(th);
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (done) {
            return;
        }
        done = true;
        try {
            onComplete.run();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }
}
