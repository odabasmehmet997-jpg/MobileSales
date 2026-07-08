package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicReference;

final class FlowableCombineLatestCombineLatestInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -8730235182291002949L;
    final int index;
    final int limit;
    final FlowableCombineLatestCombineLatestCoordinator<T, ?> parent;
    final int prefetch;
    int produced;
    FlowableCombineLatestCombineLatestInnerSubscriber(FlowableCombineLatestCombineLatestCoordinator<T, ?> flowableCombineLatestCombineLatestCoordinator, int i2, int i3) {
        this.parent = flowableCombineLatestCombineLatestCoordinator;
        this.index = i2;
        this.prefetch = i3;
        this.limit = i3 - (i3 >> 2);
    }
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, this.prefetch);
    }
    public void onNext(Object t) {
        this.parent.innerValue(this.index, (T) t);
    }
    public void onError(Throwable th) {
        this.parent.innerError(this.index, th);
    }
    public void onComplete() {
        this.parent.innerComplete(this.index);
    }
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }
    public void requestOne() {
        int i2 = this.produced + 1;
        if (i2 == this.limit) {
            this.produced = 0;
            get().request(i2);
        } else {
            this.produced = i2;
        }
    }
}
