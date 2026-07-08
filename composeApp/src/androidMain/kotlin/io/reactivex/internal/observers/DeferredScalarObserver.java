package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable upstream;
    public abstract  void onNext(Object obj);
    protected DeferredScalarObserver(final Observer<? super R> observer) {
        super(observer);
    }
    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.validate(upstream, disposable)) {
            upstream = disposable;
            downstream.onSubscribe(this);
        }
    }
    public void onError(final Throwable th) {
        value = null;
        this.error(th);
    }
    public void onComplete() {
        final T t = ( T ) value;
        if (null != t) {
            value = null;
            this.complete(( R ) t);
        } else {
            this.complete();
        }
    }
    public void dispose() {
        super.dispose();
        upstream.dispose();
    }
}
