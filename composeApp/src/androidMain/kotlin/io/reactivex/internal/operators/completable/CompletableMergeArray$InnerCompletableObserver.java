package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



final class CompletableMergeArrayInnerCompletableObserver extends AtomicInteger implements CompletableObserver {
    private static final long serialVersionUID = -8360547806504310570L;
    final CompletableObserver downstream;
    final AtomicBoolean once;
    final CompositeDisposable set;

    CompletableMergeArrayInnerCompletableObserver(CompletableObserver completableObserver, AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, int i2) {
        this.downstream = completableObserver;
        this.once = atomicBoolean;
        this.set = compositeDisposable;
        lazySet(i2);
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        this.set.add(disposable);
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.set.dispose();
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        if (0 == this.decrementAndGet() && this.once.compareAndSet(false, true)) {
            this.downstream.onComplete();
        }
    }
}
