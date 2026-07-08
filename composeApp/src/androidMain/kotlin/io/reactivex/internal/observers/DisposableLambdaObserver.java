package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;



public final class DisposableLambdaObserver<T> implements Observer<T>, Disposable {
    final Observer<? super T> downstream;
    final Action onDispose;
    final Consumer<? super Disposable> onSubscribe;
    Disposable upstream;

    public DisposableLambdaObserver(final Observer<? super T> observer, final Consumer<? super Disposable> consumer, final Action action) {
        downstream = observer;
        onSubscribe = consumer;
        onDispose = action;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(final Disposable disposable) {
        try {
            onSubscribe.accept(disposable);
            if (DisposableHelper.validate(upstream, disposable)) {
                upstream = disposable;
                downstream.onSubscribe(this);
            }
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            disposable.dispose();
            upstream = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, downstream);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(final Object t) {
        downstream.onNext(t);
    }

    @Override // io.reactivex.Observer
    public void onError(final Throwable th) {
        final Disposable disposable = upstream;
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable) {
            upstream = disposableHelper;
            downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        final Disposable disposable = upstream;
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable) {
            upstream = disposableHelper;
            downstream.onComplete();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        final Disposable disposable = upstream;
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable) {
            upstream = disposableHelper;
            try {
                onDispose.run();
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            disposable.dispose();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return upstream.isDisposed();
    }
}
