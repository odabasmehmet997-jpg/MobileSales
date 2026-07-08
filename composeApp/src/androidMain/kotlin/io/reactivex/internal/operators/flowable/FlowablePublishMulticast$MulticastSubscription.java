package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowablePublishMulticastMulticastSubscription<T> extends AtomicLong implements Subscription {
    private static final long serialVersionUID = 8664815189257569791L;
    final Subscriber<? super T> downstream;
    long emitted;
    final FlowablePublishMulticastMulticastProcessor<T> parent;

    FlowablePublishMulticastMulticastSubscription(Subscriber<? super T> subscriber, FlowablePublishMulticastMulticastProcessor<T> flowablePublishMulticastMulticastProcessor) {
        this.downstream = subscriber;
        this.parent = flowablePublishMulticastMulticastProcessor;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.addCancel(this, j2);
            this.parent.drain();
        }
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
