package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;

final class CompletableConcatArrayConcatInnerObserver extends AtomicInteger implements CompletableObserver {
    private static final long serialVersionUID = -7965400327305809232L;
    final CompletableObserver downstream;
    int index;
    final SequentialDisposable f1826sd = new SequentialDisposable();
    final CompletableSource[] sources;
    CompletableConcatArrayConcatInnerObserver(CompletableObserver completableObserver, CompletableSource[] completableSourceArr) {
        this.downstream = completableObserver;
        this.sources = completableSourceArr;
    }
    public void onSubscribe(Disposable disposable) {
        this.f1826sd.replace(disposable);
    }
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }
    public void onComplete() {
        next();
    }
    void next() {
        if (!this.f1826sd.isDisposed() && getAndIncrement() == 0) {
            CompletableSource[] completableSourceArr = this.sources;
            while (!this.f1826sd.isDisposed()) {
                int i2 = this.index;
                this.index = i2 + 1;
                if (i2 == completableSourceArr.length) {
                    this.downstream.onComplete();
                    return;
                } else {
                    completableSourceArr[i2].subscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
