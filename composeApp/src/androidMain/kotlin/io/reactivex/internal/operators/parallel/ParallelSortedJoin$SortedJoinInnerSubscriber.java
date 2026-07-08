package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;



final class ParallelSortedJoinSortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
    private static final long serialVersionUID = 6751017204873808094L;
    final int index;
    final ParallelSortedJoinSortedJoinSubscription<T> parent;

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
    }

    ParallelSortedJoinSortedJoinInnerSubscriber(ParallelSortedJoinSortedJoinSubscription<T> parallelSortedJoinSortedJoinSubscription, int i2) {
        this.parent = parallelSortedJoinSortedJoinSubscription;
        this.index = i2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object list) {
        this.parent.innerNext(list, this.index);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.innerError(th);
    }

    void cancel() {
        SubscriptionHelper.cancel(this);
    }
}
