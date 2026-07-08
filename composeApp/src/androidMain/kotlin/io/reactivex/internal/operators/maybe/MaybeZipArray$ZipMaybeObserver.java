package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeZipArrayZipMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
    private static final long serialVersionUID = 3323743579927613702L;
    final int index;
    final MaybeZipArrayZipCoordinator<T, ?> parent;

    MaybeZipArrayZipMaybeObserver(MaybeZipArrayZipCoordinator<T, ?> maybeZipArrayZipCoordinator, int i2) {
        this.parent = maybeZipArrayZipCoordinator;
        this.index = i2;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.parent.innerSuccess(t, this.index);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.parent.innerError(th, this.index);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.parent.innerComplete(this.index);
    }
}
