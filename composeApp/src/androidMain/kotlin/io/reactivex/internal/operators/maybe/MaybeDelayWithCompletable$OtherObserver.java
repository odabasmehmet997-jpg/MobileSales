package io.reactivex.internal.operators.maybe;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeDelayWithCompletableOtherObserver<T> extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
    private static final long serialVersionUID = 703409937383992161L;
    final MaybeObserver<? super T> downstream;
    final MaybeSource<T> source;

    MaybeDelayWithCompletableOtherObserver(MaybeObserver<? super T> maybeObserver, MaybeSource<T> maybeSource) {
        this.downstream = maybeObserver;
        this.source = maybeSource;
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        MaybeSource<T> maybeSource = this.source;
        final MaybeObserver<? super T> maybeObserver = this.downstream;
        maybeSource.subscribe(new MaybeObserver<T>(this, maybeObserver) { // from class: io.reactivex.internal.operators.maybe.MaybeDelayWithCompletableDelayWithMainObserver
            final MaybeObserver<? super T> downstream;
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
        });
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
}
