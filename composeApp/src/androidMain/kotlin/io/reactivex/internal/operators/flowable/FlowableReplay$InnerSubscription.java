package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowableReplayInnerSubscription<T> extends AtomicLong implements Subscription, Disposable {
    static final long CANCELLED = Long.MIN_VALUE;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    boolean emitting;
    Object index;
    boolean missed;
    final FlowableReplayReplaySubscriber<T> parent;
    final AtomicLong totalRequested = new AtomicLong();

    FlowableReplayInnerSubscription(FlowableReplayReplaySubscriber<T> flowableReplayReplaySubscriber, Subscriber<? super T> subscriber) {
        this.parent = flowableReplayReplaySubscriber;
        this.child = subscriber;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (!SubscriptionHelper.validate(j2) || CANCELLED == BackpressureHelper.addCancel (this, j2)) {
            return;
        }
        BackpressureHelper.add(this.totalRequested, j2);
        this.parent.manageRequests();
        this.parent.buffer.replay(this);
    }

    public long produced(long j2) {
        return BackpressureHelper.producedCancel(this, j2);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return CANCELLED == this.get();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (CANCELLED != this.getAndSet(FlowableReplayInnerSubscription.CANCELLED)) {
            this.parent.remove(this);
            this.parent.manageRequests();
            this.index = null;
        }
    }

    <U> U index() {
        return (U) this.index;
    }
}
