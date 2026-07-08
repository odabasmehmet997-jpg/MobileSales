package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeSwitchIfEmptySwitchIfEmptyMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -2223459372976438024L;
    final MaybeObserver<? super T> downstream;
    final MaybeSource<? extends T> other;

    MaybeSwitchIfEmptySwitchIfEmptyMaybeObserver(MaybeObserver<? super T> maybeObserver, MaybeSource<? extends T> maybeSource) {
        this.downstream = maybeObserver;
        this.other = maybeSource;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.downstream.onSuccess(t);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        Disposable disposable = get();
        if (DisposableHelper.DISPOSED == disposable || !compareAndSet(disposable, null)) {
            return;
        }
        this.other.subscribe(new OtherMaybeObserver(this.downstream, this));
    }

    record OtherMaybeObserver<T>(MaybeObserver<? super T> downstream,
                                 AtomicReference<Disposable> parent) implements MaybeObserver<T> {

        @Override // io.reactivex.MaybeObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.parent, disposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onSuccess(T t) {
                this.downstream.onSuccess(t);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                this.downstream.onComplete();
            }
        }
}
