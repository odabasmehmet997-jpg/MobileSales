package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;


public abstract class DisposableObserver<T> implements Observer<T>, Disposable {
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    protected void onStart() {
    }
    public final void onSubscribe(final Disposable disposable) {
        if (EndConsumerHelper.setOnce(upstream, disposable, this.getClass())) {
            this.onStart();
        }
    }
    public final boolean isDisposed() {
        return DisposableHelper.DISPOSED == this.upstream.get();
    }
    public final void dispose() {
        DisposableHelper.dispose(upstream);
    }
}
