package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class SingleZipArrayZipSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
    private static final long serialVersionUID = 3323743579927613702L;
    final int index;
    final SingleZipArrayZipCoordinator<T, ?> parent;

    SingleZipArrayZipSingleObserver(SingleZipArrayZipCoordinator<T, ?> singleZipArrayZipCoordinator, int i2) {
        this.parent = singleZipArrayZipCoordinator;
        this.index = i2;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        this.parent.innerSuccess(t, this.index);
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.parent.innerError(th, this.index);
    }
}
