package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowablePublishAltInnerSubscription<T> extends AtomicLong implements Subscription {
    private static final long serialVersionUID = 2845000326761540265L;
    final Subscriber<? super T> downstream;
    long emitted;
    final FlowablePublishAltPublishConnection<T> parent;

    FlowablePublishAltInnerSubscription(Subscriber<? super T> subscriber, FlowablePublishAltPublishConnection<T> flowablePublishAltPublishConnection) {
        this.downstream = subscriber;
        this.parent = flowablePublishAltPublishConnection;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        BackpressureHelper.addCancel(this, j2);
        this.parent.drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (Long.MIN_VALUE != this.getAndSet(Long.MIN_VALUE)) {
            this.parent.remove(this);
            this.parent.drain();
        }
    }

    public boolean isCancelled() {
        return Long.MIN_VALUE == this.get();
    }
}
