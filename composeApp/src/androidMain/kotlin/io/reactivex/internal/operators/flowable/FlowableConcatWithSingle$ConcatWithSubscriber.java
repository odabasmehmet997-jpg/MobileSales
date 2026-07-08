package io.reactivex.internal.operators.flowable;

import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableConcatWithSingleConcatWithSubscriber<T> extends SinglePostCompleteSubscriber<T, T> implements SingleObserver<T> {
    private static final long serialVersionUID = -7346385463600070225L;
    SingleSource<? extends T> other;
    final AtomicReference<Disposable> otherDisposable;

    FlowableConcatWithSingleConcatWithSubscriber(Subscriber<? super T> subscriber, SingleSource<? extends T> singleSource) {
        super(subscriber);
        this.other = singleSource;
        this.otherDisposable = new AtomicReference<>();
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this.otherDisposable, disposable);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onNext(Object t) {
        produced++;
        downstream.onNext(t);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        downstream.onError(th);
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        this.complete(t);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        upstream = SubscriptionHelper.CANCELLED;
        SingleSource<? extends T> singleSource = this.other;
        this.other = null;
        singleSource.subscribe(this);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        DisposableHelper.dispose(this.otherDisposable);
    }
}
