package io.reactivex.internal.operators.flowable;


interface FlowableSequenceEqualEqualCoordinatorHelper {
    void drain();

    void innerError(Throwable th);
}
