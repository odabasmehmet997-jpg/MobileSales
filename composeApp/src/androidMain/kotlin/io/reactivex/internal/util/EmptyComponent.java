package io.reactivex.internal.util;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public enum EmptyComponent implements FlowableSubscriber<Object>, Observer<Object>, MaybeObserver<Object>, SingleObserver<Object>, CompletableObserver, Subscription, Disposable {
    INSTANCE;
    public void cancel() {
    }
    public void dispose() {
    }
    public boolean isDisposed() {
        return true;
    }
    public void onComplete() {
    }
    public void onNext(final Object obj) {
    }
    public void onSuccess(final Object obj) {
    }

    public void request(final long j2) {
    }

    public static <T> Subscriber<T> asSubscriber() {
        return ( Subscriber<T> ) EmptyComponent.INSTANCE;
    }

    public static <T> Observer<T> asObserver() {
        return ( Observer<T> ) EmptyComponent.INSTANCE;
    }

    public void onSubscribe(final Disposable disposable) {
        disposable.dispose();
    }

    public void onSubscribe(final Subscription subscription) {
        subscription.cancel();
    }

    public void onError(final Throwable th) {
        RxJavaPlugins.onError(th);
    }

    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
}
