package io.reactivex.internal.operators.mixed;

import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableAndThenPublisherAndThenPublisherSubscriber<R> extends AtomicReference<Subscription> implements FlowableSubscriber<R>, CompletableObserver, Subscription {
    private static final long serialVersionUID = -8948264376121066672L;
    final Subscriber<? super R> downstream;
    Publisher<? extends R> other;
    final AtomicLong requested = new AtomicLong();
    Disposable upstream;

    CompletableAndThenPublisherAndThenPublisherSubscriber(Subscriber<? super R> subscriber, Publisher<? extends R> publisher) {
        this.downstream = subscriber;
        this.other = publisher;
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
        Publisher<? extends R> publisher = this.other;
        if (null == publisher) {
            this.downstream.onComplete();
        } else {
            this.other = null;
            publisher.subscribe(this);
        }
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

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this, this.requested, subscription);
    }
}
