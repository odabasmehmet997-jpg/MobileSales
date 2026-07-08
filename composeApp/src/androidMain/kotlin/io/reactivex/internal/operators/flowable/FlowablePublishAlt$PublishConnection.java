package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowablePublishAltPublishConnection<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
    static final FlowablePublishAltInnerSubscription[] EMPTY = new FlowablePublishAltInnerSubscription[0];
    static final FlowablePublishAltInnerSubscription[] TERMINATED = new FlowablePublishAltInnerSubscription[0];
    private static final long serialVersionUID = -1672047311619175801L;
    final int bufferSize;
    int consumed;
    final AtomicReference<FlowablePublishAltPublishConnection<T>> current;
    volatile boolean done;
    Throwable error;
    volatile SimpleQueue<T> queue;
    int sourceMode;
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicBoolean connect = new AtomicBoolean();
    final AtomicReference<FlowablePublishAltInnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

    FlowablePublishAltPublishConnection(AtomicReference<FlowablePublishAltPublishConnection<T>> atomicReference, int i2) {
        this.current = atomicReference;
        this.bufferSize = i2;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.subscribers.getAndSet(TERMINATED);
        LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
        SubscriptionHelper.cancel(this.upstream);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.subscribers.get() == TERMINATED;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(7);
                if (1 == requestFusion) {
                    this.sourceMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    drain();
                    return;
                }
                if (2 == requestFusion) {
                    this.sourceMode = 2;
                    this.queue = queueSubscription;
                    subscription.request(this.bufferSize);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.bufferSize);
            subscription.request(this.bufferSize);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (0 == sourceMode && !this.queue.offer(t)) {
            onError(new MissingBackpressureException("Prefetch queue is full?!"));
        } else {
            drain();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        SimpleQueue<T> simpleQueue = this.queue;
        int i2 = this.consumed;
        int i3 = this.bufferSize;
        int i4 = i3 - (i3 >> 2);
        boolean z = 1 != sourceMode;
        int i5 = 1;
        SimpleQueue<T> simpleQueue2 = simpleQueue;
        int i6 = i2;
        while (true) {
            if (null != simpleQueue2) {
                FlowablePublishAltInnerSubscription<T>[] flowablePublishAltInnerSubscriptionArr = this.subscribers.get();
                long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                boolean z2 = false;
                for (FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription : flowablePublishAltInnerSubscriptionArr) {
                    long j3 = flowablePublishAltInnerSubscription.get();
                    if (Long.MIN_VALUE != j3) {
                        j2 = Math.min(j3 - flowablePublishAltInnerSubscription.emitted, j2);
                        z2 = true;
                    }
                }
                if (!z2) {
                    j2 = 0;
                }
                for (long j4 = 0; j2 != j4; j4 = 0) {
                    boolean z3 = this.done;
                    try {
                        T poll = simpleQueue2.poll();
                        boolean z4 = null == poll;
                        if (checkTerminated(z3, z4)) {
                            return;
                        }
                        if (z4) {
                            break;
                        }
                        for (FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription2 : flowablePublishAltInnerSubscriptionArr) {
                            if (!flowablePublishAltInnerSubscription2.isCancelled()) {
                                flowablePublishAltInnerSubscription2.downstream.onNext(poll);
                                flowablePublishAltInnerSubscription2.emitted++;
                            }
                        }
                        if (z && (i6 = i6 + 1) == i4) {
                            this.upstream.get().request(i4);
                            i6 = 0;
                        }
                        j2--;
                        if (flowablePublishAltInnerSubscriptionArr != this.subscribers.get()) {
                            break;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.upstream.get().cancel();
                        simpleQueue2.clear();
                        this.done = true;
                        signalError(th);
                        return;
                    }
                }
                if (checkTerminated(this.done, simpleQueue2.isEmpty())) {
                    return;
                }
            }
            this.consumed = i6;
            i5 = addAndGet(-i5);
            if (0 == i5) {
                return;
            }
            if (null == simpleQueue2) {
                simpleQueue2 = this.queue;
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2) {
        if (!z || !z2) {
            return false;
        }
        Throwable th = this.error;
        if (null != th) {
            signalError(th);
            return true;
        }
        for (FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription : this.subscribers.getAndSet(TERMINATED)) {
            if (!flowablePublishAltInnerSubscription.isCancelled()) {
                flowablePublishAltInnerSubscription.downstream.onComplete();
            }
        }
        return true;
    }

    void signalError(Throwable th) {
        for (FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription : this.subscribers.getAndSet(TERMINATED)) {
            if (!flowablePublishAltInnerSubscription.isCancelled()) {
                flowablePublishAltInnerSubscription.downstream.onError(th);
            }
        }
    }

    boolean add(FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription) {
        FlowablePublishAltInnerSubscription<T>[] flowablePublishAltInnerSubscriptionArr;
        FlowablePublishAltInnerSubscription[] flowablePublishAltInnerSubscriptionArr2;
        do {
            flowablePublishAltInnerSubscriptionArr = this.subscribers.get();
            if (flowablePublishAltInnerSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = flowablePublishAltInnerSubscriptionArr.length;
            flowablePublishAltInnerSubscriptionArr2 = new FlowablePublishAltInnerSubscription[length + 1];
            System.arraycopy(flowablePublishAltInnerSubscriptionArr, 0, flowablePublishAltInnerSubscriptionArr2, 0, length);
            flowablePublishAltInnerSubscriptionArr2[length] = flowablePublishAltInnerSubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishAltInnerSubscriptionArr, flowablePublishAltInnerSubscriptionArr2));
        return true;
    }

    void remove(FlowablePublishAltInnerSubscription<T> flowablePublishAltInnerSubscription) {
        FlowablePublishAltInnerSubscription<T>[] flowablePublishAltInnerSubscriptionArr;
        FlowablePublishAltInnerSubscription[] flowablePublishAltInnerSubscriptionArr2;
        do {
            flowablePublishAltInnerSubscriptionArr = this.subscribers.get();
            int length = flowablePublishAltInnerSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (flowablePublishAltInnerSubscriptionArr[i2] == flowablePublishAltInnerSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                flowablePublishAltInnerSubscriptionArr2 = EMPTY;
            } else {
                FlowablePublishAltInnerSubscription[] flowablePublishAltInnerSubscriptionArr3 = new FlowablePublishAltInnerSubscription[length - 1];
                System.arraycopy(flowablePublishAltInnerSubscriptionArr, 0, flowablePublishAltInnerSubscriptionArr3, 0, i2);
                System.arraycopy(flowablePublishAltInnerSubscriptionArr, i2 + 1, flowablePublishAltInnerSubscriptionArr3, i2, (length - i2) - 1);
                flowablePublishAltInnerSubscriptionArr2 = flowablePublishAltInnerSubscriptionArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishAltInnerSubscriptionArr, flowablePublishAltInnerSubscriptionArr2));
    }
}
