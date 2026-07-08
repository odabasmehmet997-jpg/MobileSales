package io.reactivex.internal.observers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class ResumeSingleObserver<T> implements SingleObserver<T> {
    final SingleObserver<? super T> downstream;
    final AtomicReference<Disposable> parent;
    public ResumeSingleObserver(final AtomicReference<Disposable> atomicReference, final SingleObserver<? super T> singleObserver) {
        parent = atomicReference;
        downstream = singleObserver;
    }
    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.replace(parent, disposable);
    }
    public void onSuccess(final T t) {
        downstream.onSuccess(t);
    }
    public void onError(final Throwable th) {
        downstream.onError(th);
    }
}
