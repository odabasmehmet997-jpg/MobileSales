package io.reactivex.internal.operators.parallel;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



final class ParallelReduceFullParallelReduceFullMainSubscriber<T> extends DeferredScalarSubscription<T> {
    private static final long serialVersionUID = -5370107872170712765L;
    final AtomicReference<ParallelReduceFullSlotPair<T>> current;
    final AtomicReference<Throwable> error;
    final BiFunction<T, T, T> reducer;
    final AtomicInteger remaining;
    final ParallelReduceFullParallelReduceFullInnerSubscriber<T>[] subscribers;

    ParallelReduceFullParallelReduceFullMainSubscriber(Subscriber<? super T> subscriber, int i2, BiFunction<T, T, T> biFunction) {
        super(subscriber);
        this.current = new AtomicReference<>();
        this.remaining = new AtomicInteger();
        this.error = new AtomicReference<>();
        ParallelReduceFullParallelReduceFullInnerSubscriber<T>[] parallelReduceFullParallelReduceFullInnerSubscriberArr = new ParallelReduceFullParallelReduceFullInnerSubscriber[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            parallelReduceFullParallelReduceFullInnerSubscriberArr[i3] = new ParallelReduceFullParallelReduceFullInnerSubscriber<>(this, biFunction);
        }
        this.subscribers = parallelReduceFullParallelReduceFullInnerSubscriberArr;
        this.reducer = biFunction;
        this.remaining.lazySet(i2);
    }

    ParallelReduceFullSlotPair<T> addValue(T t) {
        ParallelReduceFullSlotPair<T> parallelReduceFullSlotPair;
        int tryAcquireSlot;
        while (true) {
            parallelReduceFullSlotPair = this.current.get();
            if (null == parallelReduceFullSlotPair) {
                parallelReduceFullSlotPair = new ParallelReduceFullSlotPair<>();
                if (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, null, parallelReduceFullSlotPair)) {
                    continue;
                }
            }
            tryAcquireSlot = parallelReduceFullSlotPair.tryAcquireSlot();
            if (0 <= tryAcquireSlot) {
                break;
            }
            LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, parallelReduceFullSlotPair, null);
        }
        if (0 == tryAcquireSlot) {
            parallelReduceFullSlotPair.first = t;
        } else {
            parallelReduceFullSlotPair.second = t;
        }
        if (!parallelReduceFullSlotPair.releaseSlot()) {
            return null;
        }
        LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.current, parallelReduceFullSlotPair, null);
        return parallelReduceFullSlotPair;
    }

    @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        for (ParallelReduceFullParallelReduceFullInnerSubscriber<T> parallelReduceFullParallelReduceFullInnerSubscriber : this.subscribers) {
            parallelReduceFullParallelReduceFullInnerSubscriber.cancel();
        }
    }

    void innerError(Throwable th) {
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.error, null, th)) {
            cancel();
            downstream.onError(th);
        } else if (th != this.error.get()) {
            RxJavaPlugins.onError(th);
        }
    }

    void innerComplete(T t) {
        if (null != t) {
            while (true) {
                ParallelReduceFullSlotPair<T> addValue = addValue(t);
                if (null == addValue) {
                    break;
                }
                try {
                    t = ObjectHelper.requireNonNull(this.reducer.apply(addValue.first, addValue.second), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    innerError(th);
                    return;
                }
            }
        }
        if (0 == remaining.decrementAndGet ()) {
            ParallelReduceFullSlotPair<T> parallelReduceFullSlotPair = this.current.get();
            this.current.lazySet(null);
            if (null != parallelReduceFullSlotPair) {
                this.complete(parallelReduceFullSlotPair.first);
            } else {
                downstream.onComplete();
            }
        }
    }
}
