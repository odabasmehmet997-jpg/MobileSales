package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeFlatMapBiSelectorFlatMapBiMainObserverInnerObserver<T, U, R> extends AtomicReference<Disposable> implements MaybeObserver<U> {
    private static final long serialVersionUID = -2897979525538174559L;
    final MaybeObserver<? super R> downstream;
    final BiFunction<? super T, ? super U, ? extends R> resultSelector;
    T value;

    MaybeFlatMapBiSelectorFlatMapBiMainObserverInnerObserver(MaybeObserver<? super R> maybeObserver, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        this.downstream = maybeObserver;
        this.resultSelector = biFunction;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(U u) {
        T t = this.value;
        this.value = null;
        try {
            this.downstream.onSuccess(ObjectHelper.requireNonNull(this.resultSelector.apply(t, u), "The resultSelector returned a null value"));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.downstream.onError(th);
        }
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
