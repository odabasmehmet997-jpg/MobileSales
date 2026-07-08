package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableWithLatestFromManyWithLatestInnerSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = 3256684027868224024L;
    boolean hasValue;
    final int index;
    final FlowableWithLatestFromManyWithLatestFromSubscriber<?, ?> parent;

    FlowableWithLatestFromManyWithLatestInnerSubscriber(FlowableWithLatestFromManyWithLatestFromSubscriber<?, ?> flowableWithLatestFromManyWithLatestFromSubscriber, int i2) {
        this.parent = flowableWithLatestFromManyWithLatestFromSubscriber;
        this.index = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object obj) {
        if (!this.hasValue) {
            this.hasValue = true;
        }
        this.parent.innerNext(this.index, obj);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.innerError(this.index, th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.parent.innerComplete(this.index, this.hasValue);
    }

    void dispose() {
        SubscriptionHelper.cancel(this);
    }
}
