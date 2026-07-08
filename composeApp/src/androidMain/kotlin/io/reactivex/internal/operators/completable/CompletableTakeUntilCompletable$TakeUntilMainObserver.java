package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableTakeUntilCompletableTakeUntilMainObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
    private static final long serialVersionUID = 3533011714830024923L;
    final CompletableObserver downstream;
    final OtherObserver other = new OtherObserver(this);
    final AtomicBoolean once = new AtomicBoolean();

    CompletableTakeUntilCompletableTakeUntilMainObserver(CompletableObserver completableObserver) {
        this.downstream = completableObserver;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (this.once.compareAndSet(false, true)) {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.once.get();
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        if (this.once.compareAndSet(false, true)) {
            DisposableHelper.dispose(this.other);
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        if (this.once.compareAndSet(false, true)) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void innerComplete() {
        if (this.once.compareAndSet(false, true)) {
            DisposableHelper.dispose(this);
            this.downstream.onComplete();
        }
    }

    void innerError(Throwable th) {
        if (this.once.compareAndSet(false, true)) {
            DisposableHelper.dispose(this);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    static final class OtherObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = 5176264485428790318L;
        final CompletableTakeUntilCompletableTakeUntilMainObserver parent;

        OtherObserver(CompletableTakeUntilCompletableTakeUntilMainObserver completableTakeUntilCompletableTakeUntilMainObserver) {
            this.parent = completableTakeUntilCompletableTakeUntilMainObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            this.parent.innerComplete();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }
    }
}
