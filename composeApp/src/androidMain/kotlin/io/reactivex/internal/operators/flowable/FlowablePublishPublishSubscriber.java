package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowablePublishPublishSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
    static final FlowablePublishInnerSubscriber[] EMPTY = new FlowablePublishInnerSubscriber[0];
    static final FlowablePublishInnerSubscriber[] TERMINATED = new FlowablePublishInnerSubscriber[0];
    private static final long serialVersionUID = -202316842419149694L;
    final int bufferSize;
    final AtomicReference<FlowablePublishPublishSubscriber<T>> current;
    volatile SimpleQueue<T> queue;
    int sourceMode;
    volatile Object terminalEvent;
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicReference<FlowablePublishInnerSubscriber<T>[]> subscribers = new AtomicReference<>(EMPTY);
    final AtomicBoolean shouldConnect = new AtomicBoolean();

    FlowablePublishPublishSubscriber(AtomicReference<FlowablePublishPublishSubscriber<T>> atomicReference, int i2) {
        this.current = atomicReference;
        this.bufferSize = i2;
    }
 
    public void dispose() {
        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr = this.subscribers.get();
        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr2 = TERMINATED;
        if (flowablePublishInnerSubscriberArr == flowablePublishInnerSubscriberArr2 || this.subscribers.getAndSet(flowablePublishInnerSubscriberArr2) == flowablePublishInnerSubscriberArr2) {
            return;
        }
        EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
        SubscriptionHelper.cancel(this.upstream);
    }
 
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
                    this.terminalEvent = NotificationLite.complete();
                    dispatch();
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
            dispatch();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (null == terminalEvent) {
            this.terminalEvent = NotificationLite.error(th);
            dispatch();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    public void onComplete() {
        if (null == terminalEvent) {
            this.terminalEvent = NotificationLite.complete();
            dispatch();
        }
    }

    boolean add(FlowablePublishInnerSubscriber<T> flowablePublishInnerSubscriber) {
        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr;
        FlowablePublishInnerSubscriber[] flowablePublishInnerSubscriberArr2;
        do {
            flowablePublishInnerSubscriberArr = this.subscribers.get();
            if (flowablePublishInnerSubscriberArr == TERMINATED) {
                return false;
            }
            int length = flowablePublishInnerSubscriberArr.length;
            flowablePublishInnerSubscriberArr2 = new FlowablePublishInnerSubscriber[length + 1];
            System.arraycopy(flowablePublishInnerSubscriberArr, 0, flowablePublishInnerSubscriberArr2, 0, length);
            flowablePublishInnerSubscriberArr2[length] = flowablePublishInnerSubscriber;
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishInnerSubscriberArr, flowablePublishInnerSubscriberArr2));
        return true;
    }

    void remove(FlowablePublishInnerSubscriber<T> flowablePublishInnerSubscriber) {
        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr;
        FlowablePublishInnerSubscriber[] flowablePublishInnerSubscriberArr2;
        do {
            flowablePublishInnerSubscriberArr = this.subscribers.get();
            int length = flowablePublishInnerSubscriberArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (flowablePublishInnerSubscriberArr[i2].equals(flowablePublishInnerSubscriber)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                flowablePublishInnerSubscriberArr2 = EMPTY;
            } else {
                FlowablePublishInnerSubscriber[] flowablePublishInnerSubscriberArr3 = new FlowablePublishInnerSubscriber[length - 1];
                System.arraycopy(flowablePublishInnerSubscriberArr, 0, flowablePublishInnerSubscriberArr3, 0, i2);
                System.arraycopy(flowablePublishInnerSubscriberArr, i2 + 1, flowablePublishInnerSubscriberArr3, i2, (length - i2) - 1);
                flowablePublishInnerSubscriberArr2 = flowablePublishInnerSubscriberArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishInnerSubscriberArr, flowablePublishInnerSubscriberArr2));
    }

    boolean checkTerminated(Object obj, boolean z) {
        int i2 = 0;
        if (null != obj) {
            if (!NotificationLite.isComplete(obj)) {
                Throwable error = NotificationLite.getError(obj);
                LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                FlowablePublishInnerSubscriber<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
                if (0 != andSet.length) {
                    int length = andSet.length;
                    while (i2 < length) {
                        andSet[i2].child.onError(error);
                        i2++;
                    }
                } else {
                    RxJavaPlugins.onError(error);
                }
                return true;
            }
            if (z) {
                LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                FlowablePublishInnerSubscriber<T>[] andSet2 = this.subscribers.getAndSet(TERMINATED);
                int length2 = andSet2.length;
                while (i2 < length2) {
                    andSet2[i2].child.onComplete();
                    i2++;
                }
                return true;
            }
        }
        return false;
    }

    void dispatch() {
        T t;
        T t2;
        SimpleQueue<T> simpleQueue;
        boolean z;
        if (0 != this.getAndIncrement()) {
            return;
        }
        AtomicReference<FlowablePublishInnerSubscriber<T>[]> atomicReference = this.subscribers;
        boolean z2 = true;
        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr = atomicReference.get();
        int i2 = 1;
        while (true) {
            Object obj = this.terminalEvent;
            SimpleQueue<T> simpleQueue2 = t"his.queue;
            boolean z3 = (null == simpleQueue2 || simpleQueue2.isEmpty ()) && z2;
            if (checkTerminated(obj, z3)) {
                return;
            }
            if (!z3) {
                int length = flowablePublishInnerSubscriberArr.length;
                int i3 = 0;
                long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                for (FlowablePublishInnerSubscriber<T> flowablePublishInnerSubscriber : flowablePublishInnerSubscriberArr) {
                    long j3 = flowablePublishInnerSubscriber.get();
                    if (Long.MIN_VALUE != j3) {
                        j2 = Math.min(j2, j3 - flowablePublishInnerSubscriber.emitted);
                    } else {
                        i3++;
                    }
                }
                if (length != i3) {
                    int i4 = 0;
                    while (true) {
                        long j4 = i4;
                        if (j4 >= j2) {
                            break;
                        }
                        Object obj2 = this.terminalEvent;
                        try {
                            t2 = simpleQueue2.poll();
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.upstream.get().cancel();
                            obj2 = NotificationLite.error(th);
                            this.terminalEvent = obj2;
                            t2 = null;
                        }
                        boolean z4 = null == t2 && z2;
                        if (checkTerminated(obj2, z4)) {
                            return;
                        }
                        if (z4) {
                            z3 = z4;
                            break;
                        }
                        Object value = NotificationLite.getValue(t2);
                        int length2 = flowablePublishInnerSubscriberArr.length;
                        int i5 = 0;
                        boolean z5 = false;
                        while (i5 < length2) {
                            FlowablePublishInnerSubscriber<T> flowablePublishInnerSubscriber2 = flowablePublishInnerSubscriberArr[i5];
                            long j5 = flowablePublishInnerSubscriber2.get();
                            if (Long.MIN_VALUE != j5) {
                                if (LocationRequestCompat.PASSIVE_INTERVAL != j5) {
                                    simpleQueue = simpleQueue2;
                                    z = z4;
                                    flowablePublishInnerSubscriber2.emitted++;
                                } else {
                                    simpleQueue = simpleQueue2;
                                    z = z4;
                                }
                                flowablePublishInnerSubscriber2.child.onNext(value);
                            } else {
                                simpleQueue = simpleQueue2;
                                z = z4;
                                z5 = true;
                            }
                            i5++;
                            simpleQueue2 = simpleQueue;
                            z4 = z;
                        }
                        SimpleQueue<T> simpleQueue3 = simpleQueue2;
                        boolean z6 = z4;
                        i4++;
                        FlowablePublishInnerSubscriber<T>[] flowablePublishInnerSubscriberArr2 = atomicReference.get();
                        if (z5 || flowablePublishInnerSubscriberArr2 != flowablePublishInnerSubscriberArr) {
                            break;
                        }
                        simpleQueue2 = simpleQueue3;
                        z3 = z6;
                        z2 = true;
                    }
                } else {
                    Object obj3 = this.terminalEvent;
                    try {
                        t = simpleQueue2.poll();
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.upstream.get().cancel();
                        obj3 = NotificationLite.error(th2);
                        this.terminalEvent = obj3;
                        t = null;
                    }
                    if (checkTerminated(obj3, null == t && z2)) {
                        return;
                    }
                    if (this.sourceMode != z2) {
                        this.upstream.get().request(1L);
                    }
                }
            }
            i2 = addAndGet(-i2);
            if (0 == i2) {
                return;
            } else {
                flowablePublishInnerSubscriberArr = atomicReference.get();
            }
        }
    }
}
