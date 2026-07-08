package io.reactivex.internal.subscriptions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.operators.completable.CompletableCreateEmitter;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class AsyncSubscription extends AtomicLong implements Subscription, Disposable {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<Subscription> actual;
    final AtomicReference<Disposable> resource;
    public AsyncSubscription() {
        this.resource = new AtomicReference<>();
        this.actual = new AtomicReference<>();
    }
    public AsyncSubscription(Disposable disposable) {
        this();
        this.resource.lazySet(disposable);
    }
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.actual, this, j2);
    }
    public void cancel() {
        dispose ();
    }
    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == actual.get ();
    }
    public void onError(Throwable th) {

    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public boolean setResource(Disposable disposable) {
        return DisposableHelper.set((CompletableCreateEmitter) this.resource, disposable);
    }
    public boolean replaceResource(Disposable disposable) {
        return DisposableHelper.replace(this.resource, disposable);
    }
    public void setSubscription(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, subscription);
    }
}
