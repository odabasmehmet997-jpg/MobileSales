package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeEqualSingleEqualObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
    private static final long serialVersionUID = -3031974433025990931L;
    final MaybeEqualSingleEqualCoordinator<T> parent;
    Object value;

    MaybeEqualSingleEqualObserver(MaybeEqualSingleEqualCoordinator<T> maybeEqualSingleEqualCoordinator) {
        this.parent = maybeEqualSingleEqualCoordinator;
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
        this.value = t;
        this.parent.done();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.parent.error(this, th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.parent.done();
    }
}
