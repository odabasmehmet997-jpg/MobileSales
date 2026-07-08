package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class FlowablePublishMulticastMulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
    static final FlowablePublishMulticastMulticastSubscription[] EMPTY = new FlowablePublishMulticastMulticastSubscription[0];
    static final FlowablePublishMulticastMulticastSubscription[] TERMINATED = new FlowablePublishMulticastMulticastSubscription[0];
    int consumed;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final int limit;
    final int prefetch;
    volatile SimpleQueue<T> queue;
    int sourceMode;
    final AtomicReference<FlowablePublishMulticastMulticastSubscription<T>[]> subscribers;
    final AtomicReference<Subscription> upstream;
    final AtomicInteger wip;

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(3);
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
                    QueueDrainHelper.request(subscription, this.prefetch);
                    return;
                }
            }
            this.queue = QueueDrainHelper.createQueue(this.prefetch);
            QueueDrainHelper.request(subscription, this.prefetch);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        SimpleQueue<T> simpleQueue;
        SubscriptionHelper.cancel(this.upstream);
        if (0 != wip.getAndIncrement () || null == (simpleQueue = queue)) {
            return;
        }
        simpleQueue.clear();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == upstream.get();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        if (0 == sourceMode && !this.queue.offer(t)) {
            this.upstream.get().cancel();
            onError(new MissingBackpressureException());
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
        if (this.done) {
            return;
        }
        this.done = true;
        drain();
    }

    boolean add(FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription) {
        FlowablePublishMulticastMulticastSubscription<T>[] flowablePublishMulticastMulticastSubscriptionArr;
        FlowablePublishMulticastMulticastSubscription[] flowablePublishMulticastMulticastSubscriptionArr2;
        do {
            flowablePublishMulticastMulticastSubscriptionArr = this.subscribers.get();
            if (flowablePublishMulticastMulticastSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = flowablePublishMulticastMulticastSubscriptionArr.length;
            flowablePublishMulticastMulticastSubscriptionArr2 = new FlowablePublishMulticastMulticastSubscription[length + 1];
            System.arraycopy(flowablePublishMulticastMulticastSubscriptionArr, 0, flowablePublishMulticastMulticastSubscriptionArr2, 0, length);
            flowablePublishMulticastMulticastSubscriptionArr2[length] = flowablePublishMulticastMulticastSubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishMulticastMulticastSubscriptionArr, flowablePublishMulticastMulticastSubscriptionArr2));
        return true;
    }

    void remove(FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription) {
        FlowablePublishMulticastMulticastSubscription<T>[] flowablePublishMulticastMulticastSubscriptionArr;
        FlowablePublishMulticastMulticastSubscription[] flowablePublishMulticastMulticastSubscriptionArr2;
        do {
            flowablePublishMulticastMulticastSubscriptionArr = this.subscribers.get();
            int length = flowablePublishMulticastMulticastSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (flowablePublishMulticastMulticastSubscriptionArr[i2] == flowablePublishMulticastMulticastSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                flowablePublishMulticastMulticastSubscriptionArr2 = EMPTY;
            } else {
                FlowablePublishMulticastMulticastSubscription[] flowablePublishMulticastMulticastSubscriptionArr3 = new FlowablePublishMulticastMulticastSubscription[length - 1];
                System.arraycopy(flowablePublishMulticastMulticastSubscriptionArr, 0, flowablePublishMulticastMulticastSubscriptionArr3, 0, i2);
                System.arraycopy(flowablePublishMulticastMulticastSubscriptionArr, i2 + 1, flowablePublishMulticastMulticastSubscriptionArr3, i2, (length - i2) - 1);
                flowablePublishMulticastMulticastSubscriptionArr2 = flowablePublishMulticastMulticastSubscriptionArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, flowablePublishMulticastMulticastSubscriptionArr, flowablePublishMulticastMulticastSubscriptionArr2));
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription = new FlowablePublishMulticastMulticastSubscription<>(subscriber, this);
        subscriber.onSubscribe(flowablePublishMulticastMulticastSubscription);
        if (add(flowablePublishMulticastMulticastSubscription)) {
            if (flowablePublishMulticastMulticastSubscription.isCancelled()) {
                remove(flowablePublishMulticastMulticastSubscription);
                return;
            } else {
                drain();
                return;
            }
        }
        Throwable th = this.error;
        if (null != th) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
    }

    void drain() {
        AtomicReference<FlowablePublishMulticastMulticastSubscription<T>[]> atomicReference;
        Throwable th;
        Throwable th2;
        if (0 != wip.getAndIncrement ()) {
            return;
        }
        SimpleQueue<T> simpleQueue = this.queue;
        int i2 = this.consumed;
        int i3 = this.limit;
        boolean z = 1 != sourceMode;
        AtomicReference<FlowablePublishMulticastMulticastSubscription<T>[]> atomicReference2 = this.subscribers;
        FlowablePublishMulticastMulticastSubscription<T>[] flowablePublishMulticastMulticastSubscriptionArr = atomicReference2.get();
        int i4 = 1;
        while (true) {
            int length = flowablePublishMulticastMulticastSubscriptionArr.length;
            if (null == simpleQueue || 0 == length) {
                atomicReference = atomicReference2;
            } else {
                int length2 = flowablePublishMulticastMulticastSubscriptionArr.length;
                long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                long j3 = Long.MAX_VALUE;
                int i5 = 0;
                while (i5 < length2) {
                    FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription = flowablePublishMulticastMulticastSubscriptionArr[i5];
                    AtomicReference<FlowablePublishMulticastMulticastSubscription<T>[]> atomicReference3 = atomicReference2;
                    long j4 = flowablePublishMulticastMulticastSubscription.get() - flowablePublishMulticastMulticastSubscription.emitted;
                    if (Long.MIN_VALUE == j4) {
                        length--;
                    } else if (j3 > j4) {
                        j3 = j4;
                    }
                    i5++;
                    atomicReference2 = atomicReference3;
                }
                atomicReference = atomicReference2;
                long j5 = 0;
                if (0 == length) {
                    j3 = 0;
                }
                while (j3 != j5) {
                    if (isDisposed()) {
                        simpleQueue.clear();
                        return;
                    }
                    boolean z2 = this.done;
                    if (z2 && !this.delayError && null != (th2 = error)) {
                        errorAll(th2);
                        return;
                    }
                    try {
                        T poll = simpleQueue.poll();
                        boolean z3 = null == poll;
                        if (z2 && z3) {
                            Throwable th3 = this.error;
                            if (null != th3) {
                                errorAll(th3);
                                return;
                            } else {
                                completeAll();
                                return;
                            }
                        }
                        if (z3) {
                            break;
                        }
                        int length3 = flowablePublishMulticastMulticastSubscriptionArr.length;
                        int i6 = 0;
                        boolean z4 = false;
                        while (i6 < length3) {
                            FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription2 = flowablePublishMulticastMulticastSubscriptionArr[i6];
                            long j6 = flowablePublishMulticastMulticastSubscription2.get();
                            if (Long.MIN_VALUE != j6) {
                                if (j6 != j2) {
                                    flowablePublishMulticastMulticastSubscription2.emitted++;
                                }
                                flowablePublishMulticastMulticastSubscription2.downstream.onNext(poll);
                            } else {
                                z4 = true;
                            }
                            i6++;
                            j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                        }
                        j3--;
                        if (z && (i2 = i2 + 1) == i3) {
                            this.upstream.get().request(i3);
                            i2 = 0;
                        }
                        FlowablePublishMulticastMulticastSubscription<T>[] flowablePublishMulticastMulticastSubscriptionArr2 = atomicReference.get();
                        if (z4 || flowablePublishMulticastMulticastSubscriptionArr2 != flowablePublishMulticastMulticastSubscriptionArr) {
                            flowablePublishMulticastMulticastSubscriptionArr = flowablePublishMulticastMulticastSubscriptionArr2;
                            break;
                        } else {
                            j5 = 0;
                            j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                        }
                    } catch (Throwable th4) {
                        Exceptions.throwIfFatal(th4);
                        SubscriptionHelper.cancel(this.upstream);
                        errorAll(th4);
                        return;
                    }
                }
                if (j3 == j5) {
                    if (isDisposed()) {
                        simpleQueue.clear();
                        return;
                    }
                    boolean z5 = this.done;
                    if (z5 && !this.delayError && null != (th = error)) {
                        errorAll(th);
                        return;
                    }
                    if (z5 && simpleQueue.isEmpty()) {
                        Throwable th5 = this.error;
                        if (null != th5) {
                            errorAll(th5);
                            return;
                        } else {
                            completeAll();
                            return;
                        }
                    }
                }
            }
            this.consumed = i2;
            i4 = this.wip.addAndGet(-i4);
            if (0 == i4) {
                return;
            }
            if (null == simpleQueue) {
                simpleQueue = this.queue;
            }
            flowablePublishMulticastMulticastSubscriptionArr = atomicReference.get();
            atomicReference2 = atomicReference;
        }
    }

    void errorAll(Throwable th) {
        for (FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription : this.subscribers.getAndSet(TERMINATED)) {
            if (Long.MIN_VALUE != flowablePublishMulticastMulticastSubscription.get ()) {
                flowablePublishMulticastMulticastSubscription.downstream.onError(th);
            }
        }
    }

    void completeAll() {
        for (FlowablePublishMulticastMulticastSubscription<T> flowablePublishMulticastMulticastSubscription : this.subscribers.getAndSet(TERMINATED)) {
            if (Long.MIN_VALUE != flowablePublishMulticastMulticastSubscription.get ()) {
                flowablePublishMulticastMulticastSubscription.downstream.onComplete();
            }
        }
    }
}
