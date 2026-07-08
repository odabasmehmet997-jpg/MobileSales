package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKt;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import rx.plugins.RxJavaSingleExecutionHook;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


public final class FlowableCache<T> extends AbstractFlowableWithUpstream<T, T> implements FlowableSubscriber<T> {
    static final CacheSubscription[] EMPTY = new CacheSubscription[0];
    static final CacheSubscription[] TERMINATED = new CacheSubscription[0];
    final int capacityHint = 0;
    volatile boolean done;
    Throwable error;
    final Node<T> head = null;
    final AtomicBoolean once;
    volatile long size;
    final AtomicReference<RxJavaSingleExecutionHook> subscribers;
    Node<T> tail;
    int tailOffset; 
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        CacheSubscription<T> cacheSubscription = new CacheSubscription<>(subscriber, this);
        subscriber.onSubscribe(cacheSubscription);
        add(cacheSubscription);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.source.subscribe(this);
        } else {
            replay(cacheSubscription);
        }
    }

    void add(CacheSubscription<T> cacheSubscription) {
        RxJavaSingleExecutionHook cacheSubscriptionArr;
        CacheSubscription[] cacheSubscriptionArr2;
        do {
            cacheSubscriptionArr = this.subscribers.get();
            if (cacheSubscriptionArr == TERMINATED) {
                return;
            }
            int length = cacheSubscriptionArr.length;
            cacheSubscriptionArr2 = new CacheSubscription[length + 1];
            System.arraycopy(cacheSubscriptionArr, 0, cacheSubscriptionArr2, 0, length);
            cacheSubscriptionArr2[length] = cacheSubscription;
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, cacheSubscriptionArr, cacheSubscriptionArr2));
    }

    void remove(CacheSubscription<T> cacheSubscription) {
        RxJavaSingleExecutionHook cacheSubscriptionArr;
        CacheSubscription[] cacheSubscriptionArr2;
        do {
            cacheSubscriptionArr = this.subscribers.get();
            int length = cacheSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (cacheSubscriptionArr[i2] == cacheSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                cacheSubscriptionArr2 = EMPTY;
            } else {
                CacheSubscription[] cacheSubscriptionArr3 = new CacheSubscription[length - 1];
                System.arraycopy(cacheSubscriptionArr, 0, cacheSubscriptionArr3, 0, i2);
                System.arraycopy(cacheSubscriptionArr, i2 + 1, cacheSubscriptionArr3, i2, (length - i2) - 1);
                cacheSubscriptionArr2 = cacheSubscriptionArr3;
            }
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, cacheSubscriptionArr, cacheSubscriptionArr2));
    }

    void replay(CacheSubscription<T> cacheSubscription) {
        if (0 != cacheSubscription.getAndIncrement ()) {
            return;
        }
        long j2 = cacheSubscription.index;
        int i2 = cacheSubscription.offset;
        Node<T> node = cacheSubscription.node;
        AtomicLong atomicLong = cacheSubscription.requested;
        Subscriber<? super T> subscriber = cacheSubscription.downstream;
        int i3 = this.capacityHint;
        int i4 = 1;
        while (true) {
            boolean z = this.done;
            boolean z2 = this.size == j2;
            if (z && z2) {
                cacheSubscription.node = null;
                Throwable th = this.error;
                if (null != th) {
                    subscriber.onError(th);
                    return;
                } else {
                    subscriber.onComplete();
                    return;
                }
            }
            if (!z2) {
                long j3 = atomicLong.get();
                if (Long.MIN_VALUE == j3) {
                    cacheSubscription.node = null;
                    return;
                } else if (j3 != j2) {
                    if (i2 == i3) {
                        node = node.next;
                        i2 = 0;
                    }
                    subscriber.onNext(node.values[i2]);
                    i2++;
                    j2++;
                }
            }
            cacheSubscription.index = j2;
            cacheSubscription.offset = i2;
            cacheSubscription.node = node;
            i4 = cacheSubscription.addAndGet(-i4);
            if (0 == i4) {
                return;
            }
        }
    }
    public void onSubscribe(Subscription subscription) {
        subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
    public void onNext(Object t) {
        int i2 = this.tailOffset;
        if (i2 == this.capacityHint) {
            Node<T> node = new Node<>(i2);
            node.values[0] = (T) t;
            this.tailOffset = 1;
            this.tail.next = node;
            this.tail = node;
        } else {
            this.tail.values[i2] = (T) t;
            this.tailOffset = i2 + 1;
        }
        this.size++;
        for (CacheSubscription<T> cacheSubscription : this.subscribers.get()) replay(cacheSubscription);
    }
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        for (CacheSubscription<T> cacheSubscription : this.subscribers.getAndSet(TERMINATED)) replay(cacheSubscription);
    }

    public void onComplete() {
        this.done = true;
        for (CacheSubscription<T> cacheSubscription : this.subscribers.getAndSet(TERMINATED)) replay(cacheSubscription);
    }

    static final class CacheSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 6770240836423125754L;
        final Subscriber<? super T> downstream;
        long index;
        Node<T> node;
        int offset;
        final FlowableCache<T> parent;
        final AtomicLong requested = new AtomicLong();

        CacheSubscription(Subscriber<? super T> subscriber, FlowableCache<T> flowableCache) {
            this.downstream = subscriber;
            this.parent = flowableCache;
            this.node = flowableCache.head;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j2) {
            if (SubscriptionHelper.validate(j2)) {
                BackpressureHelper.addCancel(this.requested, j2);
                this.parent.replay(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (Long.MIN_VALUE != requested.getAndSet (Long.MIN_VALUE)) {
                this.parent.remove(this);
            }
        }
    }

    static final class Node<T> {
        volatile Node<T> next;
        final T[] values;

        Node(int i2) {
            this.values = (T[]) new Object[i2];
        }
    }
}
