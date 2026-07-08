package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeSwitchIfEmptySingleSwitchIfEmptyMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = 4603919676453758899L;
    final SingleObserver<? super T> downstream;
    final SingleSource<? extends T> other;

    MaybeSwitchIfEmptySingleSwitchIfEmptyMaybeObserver(SingleObserver<? super T> singleObserver, SingleSource<? extends T> singleSource) {
        this.downstream = singleObserver;
        this.other = singleSource;
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
        this.other.subscribe(new OtherSingleObserver(this.downstream, this));
    }

    record OtherSingleObserver<T>(SingleObserver<? super T> downstream,
                                  AtomicReference<Disposable> parent) implements SingleObserver<T> {

        @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.parent, disposable);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(T t) {
                this.downstream.onSuccess(t);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        }
}
