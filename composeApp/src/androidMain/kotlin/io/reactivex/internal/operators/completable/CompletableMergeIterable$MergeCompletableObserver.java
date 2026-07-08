package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



final class CompletableMergeIterableMergeCompletableObserver extends AtomicBoolean implements CompletableObserver {
    private static final long serialVersionUID = -7730517613164279224L;
    final CompletableObserver downstream;
    final CompositeDisposable set;
    final AtomicInteger wip;

    CompletableMergeIterableMergeCompletableObserver(CompletableObserver completableObserver, CompositeDisposable compositeDisposable, AtomicInteger atomicInteger) {
        this.downstream = completableObserver;
        this.set = compositeDisposable;
        this.wip = atomicInteger;
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        this.set.add(disposable);
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.set.dispose();
        if (compareAndSet(false, true)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        if (0 == wip.decrementAndGet () && compareAndSet(false, true)) {
            this.downstream.onComplete();
        }
    }
}
