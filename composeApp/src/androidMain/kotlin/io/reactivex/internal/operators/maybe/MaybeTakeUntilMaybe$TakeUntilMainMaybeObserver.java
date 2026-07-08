package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeTakeUntilMaybeTakeUntilMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -2187421758664251153L;
    final MaybeObserver<? super T> downstream;
    final TakeUntilOtherMaybeObserver<U> other = new TakeUntilOtherMaybeObserver<>(this);

    MaybeTakeUntilMaybeTakeUntilMainMaybeObserver(MaybeObserver<? super T> maybeObserver) {
        this.downstream = maybeObserver;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        DisposableHelper.dispose(this.other);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onSuccess(t);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        DisposableHelper.dispose(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onComplete();
        }
    }

    void otherError(Throwable th) {
        if (DisposableHelper.dispose(this)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void otherComplete() {
        if (DisposableHelper.dispose(this)) {
            this.downstream.onComplete();
        }
    }

    static final class TakeUntilOtherMaybeObserver<U> extends AtomicReference<Disposable> implements MaybeObserver<U> {
        private static final long serialVersionUID = -1266041316834525931L;
        final MaybeTakeUntilMaybeTakeUntilMainMaybeObserver<?, U> parent;

        TakeUntilOtherMaybeObserver(MaybeTakeUntilMaybeTakeUntilMainMaybeObserver<?, U> maybeTakeUntilMaybeTakeUntilMainMaybeObserver) {
            this.parent = maybeTakeUntilMaybeTakeUntilMainMaybeObserver;
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
}
