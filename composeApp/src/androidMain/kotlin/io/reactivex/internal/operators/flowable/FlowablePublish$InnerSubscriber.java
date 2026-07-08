package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowablePublishInnerSubscriber<T> extends AtomicLong implements Subscription {
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    long emitted;
    volatile FlowablePublishPublishSubscriber<T> parent;

    FlowablePublishInnerSubscriber(Subscriber<? super T> subscriber) {
        this.child = subscriber;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.addCancel(this, j2);
            FlowablePublishPublishSubscriber<T> flowablePublishPublishSubscriber = this.parent;
            if (null != flowablePublishPublishSubscriber) {
                flowablePublishPublishSubscriber.dispatch();
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        FlowablePublishPublishSubscriber<T> flowablePublishPublishSubscriber;
        if (Long.MIN_VALUE == this.get() || Long.MIN_VALUE == this.getAndSet(Long.MIN_VALUE) || null == (flowablePublishPublishSubscriber = parent)) {
            return;
        }
        flowablePublishPublishSubscriber.remove(this);
        flowablePublishPublishSubscriber.dispatch();
    }
}
