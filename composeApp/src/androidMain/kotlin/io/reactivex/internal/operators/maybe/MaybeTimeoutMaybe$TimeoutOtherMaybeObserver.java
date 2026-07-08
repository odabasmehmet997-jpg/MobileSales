package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTimeoutMaybeTimeoutOtherMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<Object> {
    private static final long serialVersionUID = 8663801314800248617L;
    final MaybeTimeoutMaybeTimeoutMainMaybeObserver<T, U> parent;

    MaybeTimeoutMaybeTimeoutOtherMaybeObserver(MaybeTimeoutMaybeTimeoutMainMaybeObserver<T, U> maybeTimeoutMaybeTimeoutMainMaybeObserver) {
        this.parent = maybeTimeoutMaybeTimeoutMainMaybeObserver;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(Object obj) {
        this.parent.otherComplete();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.parent.otherError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.parent.otherComplete();
    }
}
