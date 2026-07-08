package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimeoutMaybeTimeoutFallbackMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
    private static final long serialVersionUID = 8663801314800248617L;
    final MaybeObserver<? super T> downstream;

    MaybeTimeoutMaybeTimeoutFallbackMaybeObserver(final MaybeObserver<? super T> maybeObserver) {
        downstream = maybeObserver;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        downstream.onSuccess(t);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        downstream.onComplete();
    }
}
