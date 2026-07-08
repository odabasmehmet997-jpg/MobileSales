package io.reactivex.internal.operators.single;

import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class SingleFlatMapPublisherSingleFlatMapPublisherObserver<S, T> extends AtomicLong implements SingleObserver<S>, FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 7759721921468635667L;
    Disposable disposable;
    final Subscriber<? super T> downstream;
    final Function<? super S, ? extends Publisher<? extends T>> mapper;
    final AtomicReference<Subscription> parent = new AtomicReference<>();

    SingleFlatMapPublisherSingleFlatMapPublisherObserver(Subscriber<? super T> subscriber, Function<? super S, ? extends Publisher<? extends T>> function) {
        this.downstream = subscriber;
        this.mapper = function;
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
        this.downstream.onSubscribe(this);
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(S s) {
        try {
            ObjectHelper.requireNonNull(this.mapper.apply(s), "the mapper returned a null Publisher").subscribe(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.parent, this, subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.parent, this, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.disposable.dispose();
        SubscriptionHelper.cancel(this.parent);
    }
}
