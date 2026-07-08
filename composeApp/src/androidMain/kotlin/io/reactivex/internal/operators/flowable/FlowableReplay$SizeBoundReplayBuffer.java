package io.reactivex.internal.operators.flowable;



final class FlowableReplaySizeBoundReplayBuffer<T> extends FlowableReplayBoundedReplayBuffer<T> {
    private static final long serialVersionUID = -5898283885385201806L;
    final int limit;

    FlowableReplaySizeBoundReplayBuffer(int i2) {
        this.limit = i2;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayBoundedReplayBuffer
    void truncate() {
        if (this.size > this.limit) {
            removeFirst();
        }
    }
}
