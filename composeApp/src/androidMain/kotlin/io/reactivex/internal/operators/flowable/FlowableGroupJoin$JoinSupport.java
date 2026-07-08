package io.reactivex.internal.operators.flowable;



interface FlowableGroupJoinJoinSupport {
    void innerClose(boolean z, FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber);

    void innerCloseError(Throwable th);

    void innerComplete(FlowableGroupJoinLeftRightSubscriber flowableGroupJoinLeftRightSubscriber);

    void innerError(Throwable th);

    void innerValue(boolean z, Object obj);
}
