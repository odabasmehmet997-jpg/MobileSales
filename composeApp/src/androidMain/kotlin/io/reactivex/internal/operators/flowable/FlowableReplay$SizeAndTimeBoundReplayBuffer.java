package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;



final class FlowableReplaySizeAndTimeBoundReplayBuffer<T> extends FlowableReplayBoundedReplayBuffer<T> {
    private static final long serialVersionUID = 3457957419649567404L;
    final int limit;
    final long maxAge;
    final Scheduler scheduler;
    final TimeUnit unit;

    FlowableReplaySizeAndTimeBoundReplayBuffer(int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.limit = i2;
        this.maxAge = j2;
        this.unit = timeUnit;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayBoundedReplayBuffer
    Object enterTransform(Object obj) {
        return new Timed(obj, this.scheduler.now(this.unit), this.unit);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayBoundedReplayBuffer
    Object leaveTransform(Object obj) {
        return ((Timed) obj).value();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayBoundedReplayBuffer
    void truncate() {
        FlowableReplayNode flowableReplayNode;
        long now = this.scheduler.now(this.unit) - this.maxAge;
        FlowableReplayNode flowableReplayNode2 = get();
        FlowableReplayNode flowableReplayNode3 = flowableReplayNode2.get();
        int i2 = 0;
        while (true) {
            FlowableReplayNode flowableReplayNode4 = flowableReplayNode3;
            flowableReplayNode = flowableReplayNode2;
            flowableReplayNode2 = flowableReplayNode4;
            if (null == flowableReplayNode2) {
                break;
            }
            int i3 = this.size;
            if (i3 > this.limit && 1 < i3) {
                i2++;
                this.size = i3 - 1;
                flowableReplayNode3 = flowableReplayNode2.get();
            } else {
                if (((Timed) flowableReplayNode2.value).time() > now) {
                    break;
                }
                i2++;
                this.size--;
                flowableReplayNode3 = flowableReplayNode2.get();
            }
        }
        if (0 != i2) {
            setFirst(flowableReplayNode);
        }
    }

    void truncateFinal() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        FlowableReplayNode flowableReplayNode = get();
        FlowableReplayNode flowableReplayNode2 = flowableReplayNode.get();
        int i2 = 0;
        while (true) {
            FlowableReplayNode flowableReplayNode3 = flowableReplayNode2;
            FlowableReplayNode flowableReplayNode4 = flowableReplayNode;
            flowableReplayNode = flowableReplayNode3;
            if (null == flowableReplayNode || 1 >= size || ((Timed) flowableReplayNode.value).time() > now) {
                break;
            }
            i2++;
            this.size--;
            flowableReplayNode2 = flowableReplayNode.get();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayBoundedReplayBuffer
    FlowableReplayNode getHead() {
        FlowableReplayNode flowableReplayNode;
        long now = this.scheduler.now(this.unit) - this.maxAge;
        FlowableReplayNode flowableReplayNode2 = get();
        FlowableReplayNode flowableReplayNode3 = flowableReplayNode2.get();
        while (true) {
            FlowableReplayNode flowableReplayNode4 = flowableReplayNode3;
            flowableReplayNode = flowableReplayNode2;
            flowableReplayNode2 = flowableReplayNode4;
            if (null != flowableReplayNode2) {
                Timed timed = (Timed) flowableReplayNode2.value;
                if (NotificationLite.isComplete(timed.value()) || NotificationLite.isError(timed.value()) || timed.time() > now) {
                    break;
                }
                flowableReplayNode3 = flowableReplayNode2.get();
            } else {
                break;
            }
        }
        return flowableReplayNode;
    }
}
