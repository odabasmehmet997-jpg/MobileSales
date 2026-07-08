package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;

abstract class FlowableRangeLongBaseRangeSubscription extends BasicQueueSubscription<Long> {
    private static final long serialVersionUID = -2252972430506210021L;
    volatile boolean cancelled;
    final long end;
    long index;

    abstract void fastPath();

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public final int requestFusion(int i2) {
        return i2 & 1;
    }

    abstract void slowPath(long j2);

    FlowableRangeLongBaseRangeSubscription(long j2, long j3) {
        this.index = j2;
        this.end = j3;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final Long poll() {
        long j2 = this.index;
        if (j2 == this.end) {
            return null;
        }
        this.index = 1 + j2;
        return Long.valueOf(j2);
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final boolean isEmpty() {
        return this.index == this.end;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final void clear() {
        this.index = this.end;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, org.reactivestreams.Subscription
    public final void request(long j2) {
        if (SubscriptionHelper.validate(j2) && 0 == BackpressureHelper.add (this, j2)) {
            if (LocationRequestCompat.PASSIVE_INTERVAL == j2) {
                fastPath();
            } else {
                slowPath(j2);
            }
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, org.reactivestreams.Subscription
    public final void cancel() {
        this.cancelled = true;
    }
}
