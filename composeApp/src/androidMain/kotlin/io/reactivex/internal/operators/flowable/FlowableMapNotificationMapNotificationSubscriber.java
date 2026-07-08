package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

final class FlowableMapNotificationMapNotificationSubscriber<T, R> extends SinglePostCompleteSubscriber<T, R> {
    private static final long serialVersionUID = 2757120512858778108L;
    final Callable<? extends R> onCompleteSupplier;
    final Function<? super Throwable, ? extends R> onErrorMapper;
    final Function<? super T, ? extends R> onNextMapper;
    FlowableMapNotificationMapNotificationSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends R> function, Function<? super Throwable, ? extends R> function2, Callable<? extends R> callable) {
        super(subscriber);
        this.onNextMapper = function;
        this.onErrorMapper = function2;
        this.onCompleteSupplier = callable;
    }
    public void onNext(Object t) {
        try {
            Object requireNonNull = ObjectHelper.requireNonNull(this.onNextMapper.apply(t), "The onNext publisher returned is null");
            produced++;
            downstream.onNext(requireNonNull);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            downstream.onError(th);
        }
    }
    public void onError(Throwable th) {
        try {
            this.complete(ObjectHelper.requireNonNull(this.onErrorMapper.apply(th), "The onError publisher returned is null"));
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            downstream.onError(new CompositeException(th, th2));
        }
    }
    public void onComplete() {
        try {
            this.complete(ObjectHelper.requireNonNull(this.onCompleteSupplier.call(), "The onComplete publisher returned is null"));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            downstream.onError(th);
        }
    }
}
