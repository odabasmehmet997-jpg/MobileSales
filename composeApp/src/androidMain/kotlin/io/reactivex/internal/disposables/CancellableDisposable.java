package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Cancellable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;


public final class CancellableDisposable extends AtomicReference<Cancellable> implements Disposable {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(Cancellable cancellable) {
        super(cancellable);
    }

    public boolean isDisposed() {
        return null == this.get();
    }

    public void dispose() {
        Cancellable andSet;
        if (null == this.get() || null == (andSet = this.getAndSet(null))) {
            return;
        }
        try {
            andSet.cancel();
        } catch (Exception e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaPlugins.onError(e2);
        }
    }
}
