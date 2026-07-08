package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeFlatMapNotificationFlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = 4375739915521278546L;
    final MaybeObserver<? super R> downstream;
    final Callable<? extends MaybeSource<? extends R>> onCompleteSupplier;
    final Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper;
    final Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper;
    Disposable upstream;

    MaybeFlatMapNotificationFlatMapMaybeObserver(MaybeObserver<? super R> maybeObserver, Function<? super T, ? extends MaybeSource<? extends R>> function, Function<? super Throwable, ? extends MaybeSource<? extends R>> function2, Callable<? extends MaybeSource<? extends R>> callable) {
        this.downstream = maybeObserver;
        this.onSuccessMapper = function;
        this.onErrorMapper = function2;
        this.onCompleteSupplier = callable;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        this.upstream.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        try {
            ObjectHelper.requireNonNull(this.onSuccessMapper.apply(t), "The onSuccessMapper returned a null MaybeSource").subscribe(new InnerObserver());
        } catch (Exception e2) {
            Exceptions.throwIfFatal(e2);
            this.downstream.onError(e2);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        try {
            ObjectHelper.requireNonNull(this.onErrorMapper.apply(th), "The onErrorMapper returned a null MaybeSource").subscribe(new InnerObserver());
        } catch (Exception e2) {
            Exceptions.throwIfFatal(e2);
            this.downstream.onError(new CompositeException(th, e2));
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        try {
            ObjectHelper.requireNonNull(this.onCompleteSupplier.call(), "The onCompleteSupplier returned a null MaybeSource").subscribe(new InnerObserver());
        } catch (Exception e2) {
            Exceptions.throwIfFatal(e2);
            this.downstream.onError(e2);
        }
    }

    final class InnerObserver implements MaybeObserver<R> {
        InnerObserver() {
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(MaybeFlatMapNotificationFlatMapMaybeObserver.this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(R r) {
            MaybeFlatMapNotificationFlatMapMaybeObserver.this.downstream.onSuccess(r);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            MaybeFlatMapNotificationFlatMapMaybeObserver.this.downstream.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            MaybeFlatMapNotificationFlatMapMaybeObserver.this.downstream.onComplete();
        }
    }
}
