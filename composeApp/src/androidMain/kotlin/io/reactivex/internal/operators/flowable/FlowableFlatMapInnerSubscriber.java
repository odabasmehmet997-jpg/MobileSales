package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicReference;

final class FlowableFlatMapInnerSubscriber<T, U> extends AtomicReference<Subscription> implements FlowableSubscriber<U>, Disposable {
    private static final long serialVersionUID = -4606175640614850599L;
    final int bufferSize;
    volatile boolean done;
    int fusionMode;
    final long id;
    final int limit;
    final FlowableFlatMapMergeSubscriber<T, U> parent;
    long produced;
    volatile SimpleQueue<U> queue;
    FlowableFlatMapInnerSubscriber(FlowableFlatMapMergeSubscriber<T, U> flowableFlatMapMergeSubscriber, long j2) {
        this.id = j2;
        this.parent = flowableFlatMapMergeSubscriber;
        int i2 = flowableFlatMapMergeSubscriber.bufferSize;
        this.bufferSize = i2;
        this.limit = i2 >> 2;
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.fusionMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.parent.drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.fusionMode = 2;
                    this.queue = queueSubscription;
                }
            }
            subscription.request(this.bufferSize);
        }
    }
    public void onNext(Object u) {
        if (2 != fusionMode) {
            this.parent.tryEmit(u, this);
        } else {
            this.parent.drain();
        }
    }
    public void onError(Throwable th) {
        lazySet(SubscriptionHelper.CANCELLED);
        this.parent.innerError(this, th);
    }
    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public void onComplete() {
        this.done = true;
        this.parent.drain();
    }
    void requestMore(long j2) {
        if (1 != fusionMode) {
            long j3 = this.produced + j2;
            if (j3 >= this.limit) {
                this.produced = 0L;
                get().request(j3);
            } else {
                this.produced = j3;
            }
        }
    }
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == this.get();
    }
}
