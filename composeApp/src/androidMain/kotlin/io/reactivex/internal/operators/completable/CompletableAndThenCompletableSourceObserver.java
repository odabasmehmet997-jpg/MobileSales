package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableAndThenCompletableSourceObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
    private static final long serialVersionUID = -4101678820158072998L;
    CompletableObserver actualObserver = null;
    CompletableSource next = null;
    public CompletableAndThenCompletableSourceObserver(CompletableObserver completableObserver, CompletableSource completableSource) {
        this.actualObserver = completableObserver;
        this.next = completableSource;
    }
    public CompletableAndThenCompletableSourceObserver() {

    }
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            this.actualObserver.onSubscribe(this);
        }
    }
    public void onError(Throwable th) {
        this.actualObserver.onError(th);
    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public void onComplete() {
        CompletableSource completableSource = this.next;
        final CompletableObserver completableObserver = this.actualObserver;
        completableSource.subscribe(new CompletableObserver(this, completableObserver) {
            final CompletableObserver downstream;
            final CompletableObserver parent;

            {
                this.parent = this;
                this.downstream = completableObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace((AtomicReference<Disposable>) this.parent, disposable);
            }

            public void onComplete() {
                this.downstream.onComplete();
            }
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        });
    }
    public void dispose() {
        DisposableHelper.dispose(this);
    }
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
}
