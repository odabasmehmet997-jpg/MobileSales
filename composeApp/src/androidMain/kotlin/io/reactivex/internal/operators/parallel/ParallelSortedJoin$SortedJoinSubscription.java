package io.reactivex.internal.operators.parallel;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class ParallelSortedJoinSortedJoinSubscription<T> extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = 3481980673745556697L;
    volatile boolean cancelled;
    final Comparator<? super T> comparator;
    final Subscriber<? super T> downstream;
    final int[] indexes;
    final List<T>[] lists;
    final ParallelSortedJoinSortedJoinInnerSubscriber<T>[] subscribers;
    final AtomicLong requested = new AtomicLong();
    final AtomicInteger remaining = new AtomicInteger();
    final AtomicReference<Throwable> error = new AtomicReference<>();

    ParallelSortedJoinSortedJoinSubscription(Subscriber<? super T> subscriber, int i2, Comparator<? super T> comparator) {
        this.downstream = subscriber;
        this.comparator = comparator;
        ParallelSortedJoinSortedJoinInnerSubscriber<T>[] parallelSortedJoinSortedJoinInnerSubscriberArr = new ParallelSortedJoinSortedJoinInnerSubscriber[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            parallelSortedJoinSortedJoinInnerSubscriberArr[i3] = new ParallelSortedJoinSortedJoinInnerSubscriber<>(this, i3);
        }
        this.subscribers = parallelSortedJoinSortedJoinInnerSubscriberArr;
        this.lists = new List[i2];
        this.indexes = new int[i2];
        this.remaining.lazySet(i2);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            if (0 == remaining.get ()) {
                drain();
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        cancelAll();
        if (0 == this.getAndIncrement()) {
            Arrays.fill(this.lists, null);
        }
    }

    void cancelAll() {
        for (ParallelSortedJoinSortedJoinInnerSubscriber<T> parallelSortedJoinSortedJoinInnerSubscriber : this.subscribers) {
            parallelSortedJoinSortedJoinInnerSubscriber.cancel();
        }
    }

    void innerNext(List<T> list, int i2) {
        this.lists[i2] = list;
        if (0 == remaining.decrementAndGet ()) {
            drain();
        }
    }

    void innerError(Throwable th) {
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.error, null, th)) {
            drain();
        } else if (th != this.error.get()) {
            RxJavaPlugins.onError(th);
        }
    }

    void drain() {
        int i2;
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super T> subscriber = this.downstream;
        List<T>[] listArr = this.lists;
        int[] iArr = this.indexes;
        int length = iArr.length;
        int i3 = 1;
        while (true) {
            long j2 = this.requested.get();
            long j3 = 0;
            while (true) {
                int i4 = 0;
                if (j3 == j2) {
                    break;
                }
                if (this.cancelled) {
                    Arrays.fill(listArr, null);
                    return;
                }
                Throwable th = this.error.get();
                if (null != th) {
                    cancelAll();
                    Arrays.fill(listArr, null);
                    subscriber.onError(th);
                    return;
                }
                int i5 = -1;
                T t = null;
                while (i4 < length) {
                    List<T> list = listArr[i4];
                    int i6 = iArr[i4];
                    if (list.size() != i6) {
                        if (null == t) {
                            t = list.get(i6);
                        } else {
                            T t2 = list.get(i6);
                            try {
                                if (0 < comparator.compare (t, t2)) {
                                    t = t2;
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                cancelAll();
                                Arrays.fill(listArr, null);
                                if (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.error, null, th2)) {
                                    RxJavaPlugins.onError(th2);
                                }
                                subscriber.onError(this.error.get());
                                return;
                            }
                        }
                        i5 = i4;
                    }
                    i4++;
                }
                if (null == t) {
                    Arrays.fill(listArr, null);
                    subscriber.onComplete();
                    return;
                } else {
                    subscriber.onNext(t);
                    iArr[i5] = iArr[i5] + 1;
                    j3++;
                }
            }
            i3 = i2;
        }
    }
}
