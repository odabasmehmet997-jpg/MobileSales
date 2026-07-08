package io.reactivex.internal.operators.mixed;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeFlatMapPublisherFlatMapPublisherSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<R>, MaybeObserver<T>, Subscription {
    private static final long serialVersionUID = -8948264376121066672L;
    final Subscriber<? super R> downstream;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final AtomicLong requested = new AtomicLong();
    Disposable upstream;

    MaybeFlatMapPublisherFlatMapPublisherSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function) {
        this.downstream = subscriber;
        this.mapper = function;
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object r) {
        this.downstream.onNext(r);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this, this.requested, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.upstream.dispose();
        SubscriptionHelper.cancel(this);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        try {
            ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher").subscribe(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this, this.requested, subscription);
    }
}
