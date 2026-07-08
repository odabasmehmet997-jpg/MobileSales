package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import org.reactivestreams.Subscriber;



final class FlowableOnErrorReturnOnErrorReturnSubscriber<T> extends SinglePostCompleteSubscriber<T, T> {
    private static final long serialVersionUID = -3740826063558713822L;
    final Function<? super Throwable, ? extends T> valueSupplier;

    FlowableOnErrorReturnOnErrorReturnSubscriber(Subscriber<? super T> subscriber, Function<? super Throwable, ? extends T> function) {
        super(subscriber);
        this.valueSupplier = function;
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onNext(Object t) {
        produced++;
        downstream.onNext(t);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        try {
            this.complete(ObjectHelper.requireNonNull(this.valueSupplier.apply(th), "The valueSupplier returned a null value"));
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        downstream.onComplete();
    }
}
