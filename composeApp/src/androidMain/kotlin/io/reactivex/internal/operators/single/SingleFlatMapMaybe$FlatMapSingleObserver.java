package io.reactivex.internal.operators.single;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;



final class SingleFlatMapMaybeFlatMapSingleObserver<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -5843758257109742742L;
    final MaybeObserver<? super R> downstream;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

    SingleFlatMapMaybeFlatMapSingleObserver(MaybeObserver<? super R> maybeObserver, Function<? super T, ? extends MaybeSource<? extends R>> function) {
        this.downstream = maybeObserver;
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
            MaybeSource maybeSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
            if (isDisposed()) {
                return;
            }
            final MaybeObserver<? super R> maybeObserver = this.downstream;
            maybeSource.subscribe(new MaybeObserver<R>(this, maybeObserver) { // from class: io.reactivex.internal.operators.single.SingleFlatMapMaybeFlatMapMaybeObserver
                final MaybeObserver<? super R> downstream;
                final AtomicReference<Disposable> parent;

                {
                    this.parent = this;
                    this.downstream = maybeObserver;
                }

                @Override // io.reactivex.MaybeObserver
                public void onSubscribe(Disposable disposable) {
                    DisposableHelper.replace(this.parent, disposable);
                }

                @Override // io.reactivex.MaybeObserver
                public void onSuccess(R r) {
                    this.downstream.onSuccess(r);
                }

                @Override // io.reactivex.MaybeObserver
                public void onError(Throwable th) {
                    this.downstream.onError(th);
                }

                @Override // io.reactivex.MaybeObserver
                public void onComplete() {
                    this.downstream.onComplete();
                }
            });
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            onError(th);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }
}
