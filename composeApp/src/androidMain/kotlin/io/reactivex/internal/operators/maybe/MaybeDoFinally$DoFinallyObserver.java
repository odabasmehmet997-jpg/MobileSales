package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;



final class MaybeDoFinallyDoFinallyObserver<T> extends AtomicInteger implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = 4109457741734051389L;
    final MaybeObserver<? super T> downstream;
    final Action onFinally;
    Disposable upstream;

    MaybeDoFinallyDoFinallyObserver(MaybeObserver<? super T> maybeObserver, Action action) {
        this.downstream = maybeObserver;
        this.onFinally = action;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.downstream.onSuccess(t);
        runFinally();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
        runFinally();
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.downstream.onComplete();
        runFinally();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        runFinally();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    void runFinally() {
        if (compareAndSet(0, 1)) {
            try {
                this.onFinally.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }
    }
}
