package io.reactivex.disposables;

import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;

abstract class ReferenceDisposable<T> extends AtomicReference<T> implements Disposable {
    private static final long serialVersionUID = 6537757548749041217L;
    protected abstract void onDisposed(T t);
    ReferenceDisposable(T t) {
        super(ObjectHelper.requireNonNull(t, "value is null"));
    }
    public final void dispose() {
        T andSet;
        if (null == this.get() || null == (andSet = this.getAndSet(null))) {
            return;
        }
        onDisposed(andSet);
    }
    public final boolean isDisposed() {
        return null == this.get();
    }
}
