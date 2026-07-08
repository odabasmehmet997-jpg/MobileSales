package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableReplayReplaySubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
    static final FlowableReplayInnerSubscription[] EMPTY = new FlowableReplayInnerSubscription[0];
    static final FlowableReplayInnerSubscription[] TERMINATED = new FlowableReplayInnerSubscription[0];
    private static final long serialVersionUID = 7224554242710036740L;
    final FlowableReplayReplayBuffer<T> buffer;
    boolean done;
    long maxChildRequested;
    long maxUpstreamRequested;
    final AtomicInteger management = new AtomicInteger();
    final AtomicReference<FlowableReplayInnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);
    final AtomicBoolean shouldConnect = new AtomicBoolean();

    FlowableReplayReplaySubscriber(FlowableReplayReplayBuffer<T> flowableReplayReplayBuffer) {
        this.buffer = flowableReplayReplayBuffer;
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.subscribers.get() == TERMINATED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.subscribers.set(TERMINATED);
        SubscriptionHelper.cancel(this);
    }

    boolean add(FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription) {
        FlowableReplayInnerSubscription<T>[] flowableReplayInnerSubscriptionArr;
        FlowableReplayInnerSubscription[] flowableReplayInnerSubscriptionArr2;
        flowableReplayInnerSubscription.getClass();
        do {
            flowableReplayInnerSubscriptionArr = this.subscribers.get();
            if (flowableReplayInnerSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = flowableReplayInnerSubscriptionArr.length;
            flowableReplayInnerSubscriptionArr2 = new FlowableReplayInnerSubscription[length + 1];
            System.arraycopy(flowableReplayInnerSubscriptionArr, 0, flowableReplayInnerSubscriptionArr2, 0, length);
            flowableReplayInnerSubscriptionArr2[length] = flowableReplayInnerSubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowableReplayInnerSubscriptionArr, flowableReplayInnerSubscriptionArr2));
        return true;
    }

    void remove(FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription) {
        FlowableReplayInnerSubscription<T>[] flowableReplayInnerSubscriptionArr;
        FlowableReplayInnerSubscription[] flowableReplayInnerSubscriptionArr2;
        do {
            flowableReplayInnerSubscriptionArr = this.subscribers.get();
            int length = flowableReplayInnerSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (flowableReplayInnerSubscriptionArr[i2].equals(flowableReplayInnerSubscription)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                flowableReplayInnerSubscriptionArr2 = EMPTY;
            } else {
                FlowableReplayInnerSubscription[] flowableReplayInnerSubscriptionArr3 = new FlowableReplayInnerSubscription[length - 1];
                System.arraycopy(flowableReplayInnerSubscriptionArr, 0, flowableReplayInnerSubscriptionArr3, 0, i2);
                System.arraycopy(flowableReplayInnerSubscriptionArr, i2 + 1, flowableReplayInnerSubscriptionArr3, i2, (length - i2) - 1);
                flowableReplayInnerSubscriptionArr2 = flowableReplayInnerSubscriptionArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowableReplayInnerSubscriptionArr, flowableReplayInnerSubscriptionArr2));
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            manageRequests();
            for (FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription : this.subscribers.get()) {
                this.buffer.replay(flowableReplayInnerSubscription);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        this.buffer.next(t);
        for (FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription : this.subscribers.get()) {
            this.buffer.replay(flowableReplayInnerSubscription);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (!this.done) {
            this.done = true;
            this.buffer.error(th);
            for (FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription : this.subscribers.getAndSet(TERMINATED)) {
                this.buffer.replay(flowableReplayInnerSubscription);
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.buffer.complete();
        for (FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription : this.subscribers.getAndSet(TERMINATED)) {
            this.buffer.replay(flowableReplayInnerSubscription);
        }
    }

    void manageRequests() {
        if (0 != management.getAndIncrement ()) {
            return;
        }
        int i2 = 1;
        while (!isDisposed()) {
            FlowableReplayInnerSubscription<T>[] flowableReplayInnerSubscriptionArr = this.subscribers.get();
            long j2 = this.maxChildRequested;
            long j3 = j2;
            for (FlowableReplayInnerSubscription<T> flowableReplayInnerSubscription : flowableReplayInnerSubscriptionArr) {
                j3 = Math.max(j3, flowableReplayInnerSubscription.totalRequested.get());
            }
            long j4 = this.maxUpstreamRequested;
            Subscription subscription = get();
            long j5 = j3 - j2;
            if (0 != j5) {
                this.maxChildRequested = j3;
                if (null == subscription) {
                    long j6 = j4 + j5;
                    if (0 > j6) {
                        j6 = LocationRequestCompat.PASSIVE_INTERVAL;
                    }
                    this.maxUpstreamRequested = j6;
                } else if (0 != j4) {
                    this.maxUpstreamRequested = 0L;
                    subscription.request(j4 + j5);
                } else {
                    subscription.request(j5);
                }
            } else if (0 != j4 && null != subscription) {
                this.maxUpstreamRequested = 0L;
                subscription.request(j4);
            }
            i2 = this.management.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
    }
}
