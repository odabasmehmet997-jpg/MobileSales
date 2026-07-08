package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeFlattenFlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = 4375739915521278546L;
    final MaybeObserver<? super R> downstream;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    Disposable upstream;

    MaybeFlattenFlatMapMaybeObserver(final MaybeObserver<? super R> maybeObserver, final Function<? super T, ? extends MaybeSource<? extends R>> function) {
        downstream = maybeObserver;
        mapper = function;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        upstream.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.validate(upstream, disposable)) {
            upstream = disposable;
            downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        try {
            final MaybeSource maybeSource = ObjectHelper.requireNonNull(mapper.apply(t), "The mapper returned a null MaybeSource");
            if (this.isDisposed()) {
                return;
            }
            maybeSource.subscribe(new InnerObserver());
        } catch (final Exception e2) {
            Exceptions.throwIfFatal(e2);
            downstream.onError(e2);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        downstream.onComplete();
    }

    final class InnerObserver implements MaybeObserver<R> {
        InnerObserver() {
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(final Disposable disposable) {
            DisposableHelper.setOnce(MaybeFlattenFlatMapMaybeObserver.this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(final R r) {
            downstream.onSuccess(r);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(final Throwable th) {
            downstream.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            downstream.onComplete();
        }
    }
}
