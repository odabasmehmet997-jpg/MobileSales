package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Subscription;

final class FlowableConcatMapConcatMapInner<R> extends SubscriptionArbiter implements FlowableSubscriber<R> {
    private static final long serialVersionUID = 897683679971470653L;
    final FlowableConcatMapConcatMapSupport<R> parent;
    long produced;
    FlowableConcatMapConcatMapInner(FlowableConcatMapConcatMapSupport<R> flowableConcatMapConcatMapSupport) {
        super(false);
        this.parent = flowableConcatMapConcatMapSupport;
    }
    public void onSubscribe(Subscription subscription) {
        this.setSubscription(subscription);
    }
    public void onNext(Object r) {
        this.produced++;
        this.parent.innerNext(r);
    }
    public void onError(Throwable th) {
        long j2 = this.produced;
        if (0 != j2) {
            this.produced = 0L;
            this.produced(j2);
        }
        this.parent.innerError(th);
    }
    public void onComplete() {
        long j2 = this.produced;
        if (0 != j2) {
            this.produced = 0L;
            this.produced(j2);
        }
        this.parent.innerComplete();
    }
}
