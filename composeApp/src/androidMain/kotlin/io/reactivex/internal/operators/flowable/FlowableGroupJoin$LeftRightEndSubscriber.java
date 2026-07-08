package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableGroupJoinLeftRightEndSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object>, Disposable {
    private static final long serialVersionUID = 1883890389173668373L;
    final int index;
    final boolean isLeft;
    final FlowableGroupJoinJoinSupport parent;

    FlowableGroupJoinLeftRightEndSubscriber(FlowableGroupJoinJoinSupport flowableGroupJoinJoinSupport, boolean z, int i2) {
        this.parent = flowableGroupJoinJoinSupport;
        this.isLeft = z;
        this.index = i2;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == this.get();
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object obj) {
        if (SubscriptionHelper.cancel(this)) {
            this.parent.innerClose(this.isLeft, this);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.parent.innerCloseError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.parent.innerClose(this.isLeft, this);
    }
}
