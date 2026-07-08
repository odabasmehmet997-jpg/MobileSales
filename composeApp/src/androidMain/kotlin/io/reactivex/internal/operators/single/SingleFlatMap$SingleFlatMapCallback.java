package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;



final class SingleFlatMapSingleFlatMapCallback<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = 3258103020495908596L;
    final SingleObserver<? super R> downstream;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;

    SingleFlatMapSingleFlatMapCallback(SingleObserver<? super R> singleObserver, Function<? super T, ? extends SingleSource<? extends R>> function) {
        this.downstream = singleObserver;
        this.mapper = function;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        try {
            SingleSource singleSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The single returned by the mapper is null");
            if (isDisposed()) {
                return;
            }
            singleSource.subscribe(new FlatMapSingleObserver(this, this.downstream));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    record FlatMapSingleObserver<R>(AtomicReference<Disposable> parent,
                                    SingleObserver<? super R> downstream) implements SingleObserver<R> {

        @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this.parent, disposable);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(R r) {
                this.downstream.onSuccess(r);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        }
}
