package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;

final class FlowableCreateDropAsyncEmitter<T> extends FlowableCreateNoOverflowBaseAsyncEmitter<T> {
    private static final long serialVersionUID = 8360058422307496563L;
    void onOverflow() {
    }
    FlowableCreateDropAsyncEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }
    public void onComplete() {

    }
}
