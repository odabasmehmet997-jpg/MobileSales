package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;



final class SingleTakeUntilTakeUntilMainObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -622603812305745221L;
    final SingleObserver<? super T> downstream;
    final SingleTakeUntilTakeUntilOtherSubscriber other = new SingleTakeUntilTakeUntilOtherSubscriber(this);

    SingleTakeUntilTakeUntilMainObserver(SingleObserver<? super T> singleObserver) {
        this.downstream = singleObserver;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        this.other.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        this.other.dispose();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onSuccess(t);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.other.dispose();
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable && disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void otherError(Throwable th) {
        Disposable andSet;
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable && disposableHelper != (andSet = this.getAndSet(disposableHelper))) {
            if (null != andSet) {
                andSet.dispose();
            }
            this.downstream.onError(th);
            return;
        }
        RxJavaPlugins.onError(th);
    }
}
