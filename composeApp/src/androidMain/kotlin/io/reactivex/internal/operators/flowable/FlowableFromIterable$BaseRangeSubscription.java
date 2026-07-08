package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.Iterator;



abstract class FlowableFromIterableBaseRangeSubscription<T> extends BasicQueueSubscription<T> {
    private static final long serialVersionUID = -2252972430506210021L;
    volatile boolean cancelled;
    Iterator<? extends T> it;
    boolean once;

    abstract void fastPath();

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public final int requestFusion(int i2) {
        return i2 & 1;
    }

    abstract void slowPath(long j2);

    FlowableFromIterableBaseRangeSubscription(Iterator<? extends T> it) {
        this.it = it;
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final T poll() {
        Iterator<? extends T> it = this.it;
        if (null == it) {
            return null;
        }
        if (!this.once) {
            this.once = true;
        } else if (!it.hasNext()) {
            return null;
        }
        return ObjectHelper.requireNonNull(this.it.next(), "Iterator.next() returned a null value");
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final boolean isEmpty() {
        Iterator<? extends T> it = this.it;
        return null == it || !it.hasNext();
    }

    @Override // io.reactivex.internal.subscriptions.BasicQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public final void clear() {
        this.it = null;
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
