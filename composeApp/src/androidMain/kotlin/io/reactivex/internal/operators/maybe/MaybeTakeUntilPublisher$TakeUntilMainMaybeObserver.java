package io.reactivex.internal.operators.maybe;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class MaybeTakeUntilPublisherTakeUntilMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -2187421758664251153L;
    final MaybeObserver<? super T> downstream;
    final TakeUntilOtherMaybeObserver<U> other = new TakeUntilOtherMaybeObserver<>(this);

    MaybeTakeUntilPublisherTakeUntilMainMaybeObserver(MaybeObserver<? super T> maybeObserver) {
        this.downstream = maybeObserver;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        SubscriptionHelper.cancel(this.other);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        SubscriptionHelper.cancel(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onSuccess(t);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        SubscriptionHelper.cancel(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        SubscriptionHelper.cancel(this.other);
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != this.getAndSet(disposableHelper)) {
            this.downstream.onComplete();
        }
    }

    void otherError(Throwable th) {
        if (DisposableHelper.dispose(this)) {
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void otherComplete() {
        if (DisposableHelper.dispose(this)) {
            this.downstream.onComplete();
        }
    }

    static final class TakeUntilOtherMaybeObserver<U> extends AtomicReference<Subscription> implements FlowableSubscriber<U> {
        private static final long serialVersionUID = -1266041316834525931L;
        final MaybeTakeUntilPublisherTakeUntilMainMaybeObserver<?, U> parent;

        TakeUntilOtherMaybeObserver(MaybeTakeUntilPublisherTakeUntilMainMaybeObserver<?, U> maybeTakeUntilPublisherTakeUntilMainMaybeObserver) {
            this.parent = maybeTakeUntilPublisherTakeUntilMainMaybeObserver;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object obj) {
            SubscriptionHelper.cancel(this);
            this.parent.otherComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.parent.otherComplete();
        }
    }
}
