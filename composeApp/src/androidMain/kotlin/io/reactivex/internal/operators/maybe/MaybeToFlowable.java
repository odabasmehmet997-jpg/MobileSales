package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import org.reactivestreams.Subscriber;



public final class MaybeToFlowable<T> extends Flowable<T> {
    final MaybeSource<T> source;

    public MaybeToFlowable(final MaybeSource<T> maybeSource) {
        source = maybeSource;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        source.subscribe(new MaybeToFlowableSubscriber(subscriber));
    }

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarSubscription<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable upstream;

        MaybeToFlowableSubscriber(final Subscriber<? super T> subscriber) {
            super(subscriber);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(final Disposable disposable) {
            if (DisposableHelper.validate(upstream, disposable)) {
                upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(final T t) {
            complete(t);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(final Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
        public void cancel() {
            super.cancel();
            upstream.dispose();
        }
    }
}
