package io.reactivex.internal.operators.flowable;



interface FlowableReplayReplayBuffer<T> {
    void complete();

    void error(Throwable th);

    void next(T t);

    void replay(FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription);
}
