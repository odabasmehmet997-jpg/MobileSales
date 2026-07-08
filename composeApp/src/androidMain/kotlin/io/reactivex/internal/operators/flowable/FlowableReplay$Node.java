package io.reactivex.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableReplayNode extends AtomicReference<FlowableReplayNode> {
    private static final long serialVersionUID = 245354315435971818L;
    final long index;
    final Object value;

    FlowableReplayNode(Object obj, long j2) {
        this.value = obj;
        this.index = j2;
    }
}
