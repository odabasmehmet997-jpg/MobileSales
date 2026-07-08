package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



public final class ReplayProcessor<T> extends FlowableProcessor<T> {
    boolean done;
    final AtomicReference<ReplaySubscription<T>[]> subscribers;
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final ReplaySubscription[] EMPTY = new ReplaySubscription[0];
    static final ReplaySubscription[] TERMINATED = new ReplaySubscription[0];

    @Override // io.reactivex.Flowable
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        final ReplaySubscription<T> replaySubscription = new ReplaySubscription<>(subscriber, this);
        subscriber.onSubscribe(replaySubscription);
        if (this.add(replaySubscription) && replaySubscription.cancelled) {
            this.remove(replaySubscription);
            return;
        }
        throw null;
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(final Subscription subscription) {
        if (done) {
            subscription.cancel();
        } else {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(final Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!done) {
            throw null;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (done) {
            RxJavaPlugins.onError(th);
        } else {
            done = true;
            throw null;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (done) {
            return;
        }
        done = true;
        throw null;
    }

    boolean add(final ReplaySubscription<T> replaySubscription) {
        ReplaySubscription<T>[] replaySubscriptionArr;
        ReplaySubscription[] replaySubscriptionArr2;
        do {
            replaySubscriptionArr = subscribers.get();
            if (replaySubscriptionArr == ReplayProcessor.TERMINATED) {
                return false;
            }
            final int length = replaySubscriptionArr.length;
            replaySubscriptionArr2 = new ReplaySubscription[length + 1];
            System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, length);
            replaySubscriptionArr2[length] = replaySubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, replaySubscriptionArr, replaySubscriptionArr2));
        return true;
    }

    void remove(final ReplaySubscription<T> replaySubscription) {
        ReplaySubscription<T>[] replaySubscriptionArr;
        ReplaySubscription[] replaySubscriptionArr2;
        do {
            replaySubscriptionArr = subscribers.get();
            if (replaySubscriptionArr == ReplayProcessor.TERMINATED || replaySubscriptionArr == ReplayProcessor.EMPTY) {
                return;
            }
            final int length = replaySubscriptionArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (replaySubscriptionArr[i2] == replaySubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                replaySubscriptionArr2 = ReplayProcessor.EMPTY;
            } else {
                final ReplaySubscription[] replaySubscriptionArr3 = new ReplaySubscription[length - 1];
                System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr3, 0, i2);
                System.arraycopy(replaySubscriptionArr, i2 + 1, replaySubscriptionArr3, i2, (length - i2) - 1);
                replaySubscriptionArr2 = replaySubscriptionArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, replaySubscriptionArr, replaySubscriptionArr2));
    }

    static final class ReplaySubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 466549804534799122L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        long emitted;
        Object index;
        final AtomicLong requested = new AtomicLong();
        final ReplayProcessor<T> state;

        ReplaySubscription(final Subscriber<? super T> subscriber, final ReplayProcessor<T> replayProcessor) {
            downstream = subscriber;
            state = replayProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void request(final long j2) {
            if (SubscriptionHelper.validate(j2)) {
                BackpressureHelper.add(requested, j2);
                state.getClass();
                throw null;
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            state.remove(this);
        }
    }

    static final class Node<T> extends AtomicReference<Node<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        Node(final T t) {
            value = t;
        }
    }

    static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        TimedNode(final T t, final long j2) {
            value = t;
            time = j2;
        }
    }
}
