package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;

 
abstract class FlowableRangeBaseRangeSubscription extends BasicQueueSubscription<Integer> {
    private static final long serialVersionUID = -2252972430506210021L;
    volatile boolean cancelled;
    final int end;
    int index;

    abstract void fastPath();
    
    public final int requestFusion(int i2) {
        return i2 & 1;
    }

    abstract void slowPath(long j2);

    FlowableRangeBaseRangeSubscription(int i2, int i3) {
        this.index = i2;
        this.end = i3;
    }
 
    public final Integer poll() {
        int i2 = this.index;
        if (i2 == this.end) {
            return null;
        }
        this.index = i2 + 1;
        return Integer.valueOf(i2);
    }

    
    public final boolean isEmpty() {
        return this.index == this.end;
    }

    
    public final void clear() {
        this.index = this.end;
    }

    public final void request(long j2) {
        if (SubscriptionHelper.validate(j2) && 0 == BackpressureHelper.add (this, j2)) {
            if (LocationRequestCompat.PASSIVE_INTERVAL == j2) {
                fastPath();
            } else {
                slowPath(j2);
            }
        }
    }
    public final void cancel() {
        this.cancelled = true;
    }
}
