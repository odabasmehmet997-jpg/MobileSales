package io.reactivex.internal.operators.flowable;

interface FlowableConcatMapConcatMapSupport<T> {
    void innerComplete();
    void innerError(Throwable th);
    void innerNext(T t);
}
