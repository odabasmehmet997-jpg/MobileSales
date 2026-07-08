package io.reactivex.disposables;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public interface Disposable {
    void dispose();
    boolean isDisposed();
    void onError(Throwable th);
    <T> Observable<T> observeOn(Scheduler scheduler);
}
