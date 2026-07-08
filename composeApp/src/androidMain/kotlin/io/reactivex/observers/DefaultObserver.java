package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.EndConsumerHelper;


public abstract class DefaultObserver<T> implements Observer<T> {
    private Disposable upstream;
    protected void onStart() {
    }
    public final void onSubscribe(final Disposable disposable) {
        if (EndConsumerHelper.validate(upstream, disposable, this.getClass())) {
            upstream = disposable;
            this.onStart();
        }
    }
}
