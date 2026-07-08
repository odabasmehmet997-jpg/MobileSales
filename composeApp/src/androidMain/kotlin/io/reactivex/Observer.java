package io.reactivex;

import io.reactivex.disposables.Disposable;

public interface Observer<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(Object t);

    void onSubscribe(Disposable disposable);
}
