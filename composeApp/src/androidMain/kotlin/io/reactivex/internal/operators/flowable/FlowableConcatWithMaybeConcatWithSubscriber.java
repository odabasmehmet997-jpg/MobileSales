package io.reactivex.internal.operators.flowable;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import java.util.concurrent.atomic.AtomicReference;

final class FlowableConcatWithMaybeConcatWithSubscriber<T> extends SinglePostCompleteSubscriber<T, T> implements MaybeObserver<T> {
    private static final long serialVersionUID = -7346385463600070225L;
    boolean inMaybe;
    MaybeSource<? extends T> other;
    final AtomicReference<Disposable> otherDisposable;
    FlowableConcatWithMaybeConcatWithSubscriber(Subscriber<? super T> subscriber, MaybeSource<? extends T> maybeSource) {
        super(subscriber);
        this.other = maybeSource;
        this.otherDisposable = new AtomicReference<>();
    }
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce((CompletableAndThenCompletableSourceObserver) this.otherDisposable, disposable);
    }
    public void onNext(Object t) {
        produced++;
        downstream.onNext(t);
    }
    public void onError(Throwable th) {
        downstream.onError(th);
    }
    public void onSuccess(T t) {
        this.complete(t);
    }
    public void onComplete() {
        if (this.inMaybe) {
            downstream.onComplete();
            return;
        }
        this.inMaybe = true;
        upstream = SubscriptionHelper.CANCELLED;
        MaybeSource<? extends T> maybeSource = this.other;
        this.other = null;
        maybeSource.subscribe(this);
    }
    public void cancel() {
        super.cancel();
        DisposableHelper.dispose(this.otherDisposable);
    }
}
