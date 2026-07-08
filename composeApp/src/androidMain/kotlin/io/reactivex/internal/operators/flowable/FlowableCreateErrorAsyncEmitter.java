package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import org.reactivestreams.Subscriber;

final class FlowableCreateErrorAsyncEmitter<T> extends FlowableCreateNoOverflowBaseAsyncEmitter<T> {
    private static final long serialVersionUID = 338953216916120960L;
    FlowableCreateErrorAsyncEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    } 
    void onOverflow() {
        onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
    }
    @Override
    public void onComplete() {

    }
}
