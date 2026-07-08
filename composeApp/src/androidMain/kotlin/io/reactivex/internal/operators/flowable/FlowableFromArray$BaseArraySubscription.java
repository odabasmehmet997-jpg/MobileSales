package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;



abstract class FlowableFromArrayBaseArraySubscription<T> extends BasicQueueSubscription<T> {
    private static final long serialVersionUID = -2252972430506210021L;
    final T[] array;
    volatile boolean cancelled;
    int index;

    abstract void fastPath();

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public final int requestFusion(int i2) {
        return i2 & 1;
    }

    abstract void slowPath(long j2);

    FlowableFromArrayBaseArraySubscription(T[] tArr) {
        this.array = tArr;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final T poll() {
        int i2 = this.index;
        T[] tArr = this.array;
        if (i2 == tArr.length) {
            return null;
        }
        this.index = i2 + 1;
        return ObjectHelper.requireNonNull(tArr[i2], "array element is null");
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final boolean isEmpty() {
        return this.index == this.array.length;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final void clear() {
        this.index = this.array.length;
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
