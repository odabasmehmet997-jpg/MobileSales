package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;



class FlowableReplayBoundedReplayBuffer<T> extends AtomicReference<FlowableReplayNode> implements FlowableReplayReplayBuffer<T> {
    private static final long serialVersionUID = 2346567790059478686L;
    long index;
    int size;
    FlowableReplayNode tail;

    Object enterTransform(Object obj) {
        return obj;
    }

    Object leaveTransform(Object obj) {
        return obj;
    }

    void truncate() {
    }

    FlowableReplayBoundedReplayBuffer() {
        FlowableReplayNode flowableReplayNode = new FlowableReplayNode(null, 0L);
        this.tail = flowableReplayNode;
        set(flowableReplayNode);
    }

    final void addLast(FlowableReplayNode flowableReplayNode) {
        this.tail.set(flowableReplayNode);
        this.tail = flowableReplayNode;
        this.size++;
    }

    final void removeFirst() {
        FlowableReplayNode flowableReplayNode = get().get();
        if (null == flowableReplayNode) {
            throw new IllegalStateException("Empty list!");
        }
        this.size--;
        setFirst(flowableReplayNode);
    }

    final void removeSome(int i2) {
        FlowableReplayNode flowableReplayNode = get();
        while (0 < i2) {
            flowableReplayNode = flowableReplayNode.get();
            i2--;
            this.size--;
        }
        setFirst(flowableReplayNode);
        FlowableReplayNode flowableReplayNode2 = get();
        if (null == flowableReplayNode2.get ()) {
            this.tail = flowableReplayNode2;
        }
    }

    final void setFirst(FlowableReplayNode flowableReplayNode) {
        set(flowableReplayNode);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public final void next(T t) {
        Object enterTransform = enterTransform(NotificationLite.next(t));
        long j2 = this.index + 1;
        this.index = j2;
        addLast(new FlowableReplayNode(enterTransform, j2));
        truncate();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public final void error(Throwable th) {
        Object enterTransform = enterTransform(NotificationLite.error(th));
        long j2 = this.index + 1;
        this.index = j2;
        addLast(new FlowableReplayNode(enterTransform, j2));
        truncateFinal();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public final void complete() {
        Object enterTransform = enterTransform(NotificationLite.complete());
        long j2 = this.index + 1;
        this.index = j2;
        addLast(new FlowableReplayNode(enterTransform, j2));
        truncateFinal();
    }

    final void trimHead() {
        FlowableReplayNode flowableReplayNode = get();
        if (null != flowableReplayNode.value) {
            FlowableReplayNode flowableReplayNode2 = new FlowableReplayNode(null, 0L);
            flowableReplayNode2.lazySet(flowableReplayNode.get());
            set(flowableReplayNode2);
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableReplayReplayBuffer
    public final void replay(FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription) {
        FlowableReplayNode flowableReplayNode;
        synchronized (flowableReplayInnerSubscription) {
            if (flowableReplayInnerSubscription.emitting) {
                flowableReplayInnerSubscription.missed = true;
                return;
            }
            flowableReplayInnerSubscription.emitting = true;
            while (!flowableReplayInnerSubscription.isDisposed()) {
                long j2 = flowableReplayInnerSubscription.get();
                final boolean z = LocationRequestCompat.PASSIVE_INTERVAL == j2;
                FlowableReplayNode flowableReplayNode2 = flowableReplayInnerSubscription.index();
                if (null == flowableReplayNode2) {
                    flowableReplayNode2 = this.getHead();
                    flowableReplayInnerSubscription.index = flowableReplayNode2;
                    BackpressureHelper.add(flowableReplayInnerSubscription.totalRequested, flowableReplayNode2.index);
                }
                long j3 = 0;
                while (0 != j2 && null != (flowableReplayNode = flowableReplayNode2.get ())) {
                    final Object leaveTransform = this.leaveTransform(flowableReplayNode.value);
                    try {
                        if (NotificationLite.accept(leaveTransform, flowableReplayInnerSubscription.child)) {
                            flowableReplayInnerSubscription.index = null;
                            return;
                        }
                        j3++;
                        j2--;
                        if (flowableReplayInnerSubscription.isDisposed()) {
                            flowableReplayInnerSubscription.index = null;
                            return;
                        }
                        flowableReplayNode2 = flowableReplayNode;
                    } catch (final Throwable th) {
                        Exceptions.throwIfFatal(th);
                        flowableReplayInnerSubscription.index = null;
                        flowableReplayInnerSubscription.dispose();
                        if (NotificationLite.isError(leaveTransform) || NotificationLite.isComplete(leaveTransform)) {
                            return;
                        }
                        flowableReplayInnerSubscription.child.onError(th);
                        return;
                    }
                }
                if (0 != j3) {
                    flowableReplayInnerSubscription.index = flowableReplayNode2;
                    if (!z) {
                        flowableReplayInnerSubscription.produced(j3);
                    }
                }
                synchronized (flowableReplayInnerSubscription) {
                    if (!flowableReplayInnerSubscription.missed) {
                        flowableReplayInnerSubscription.emitting = false;
                        return;
                    }
                    flowableReplayInnerSubscription.missed = false;
                }
            }
            flowableReplayInnerSubscription.index = null;
        }
    }

    void truncateFinal() {
        trimHead();
    }

    final void collect(Collection<? super T> collection) {
        FlowableReplayNode head = getHead();
        while (true) {
            head = head.get();
            if (null == head) {
                return;
            }
            Object leaveTransform = leaveTransform(head.value);
            if (NotificationLite.isComplete(leaveTransform) || NotificationLite.isError(leaveTransform)) {
                return;
            } else {
                collection.add((Object) NotificationLite.getValue(leaveTransform));
            }
        }
    }

    boolean hasError() {
        Object obj = this.tail.value;
        return null != obj && NotificationLite.isError(leaveTransform(obj));
    }

    boolean hasCompleted() {
        Object obj = this.tail.value;
        return null != obj && NotificationLite.isComplete(leaveTransform(obj));
    }

    FlowableReplayNode getHead() {
        return get();
    }
}
