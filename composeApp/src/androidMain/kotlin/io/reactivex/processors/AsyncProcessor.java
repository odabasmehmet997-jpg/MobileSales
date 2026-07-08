package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import com.proje.mobilesales.features.sales.view.newadd.T;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;

public final class AsyncProcessor<T> extends FlowableProcessor<T> {
    static final AsyncSubscription[] EMPTY = new AsyncSubscription[0];
    static final AsyncSubscription[] TERMINATED = new AsyncSubscription[0];
    Throwable error;
    final AtomicReference<AsyncSubscription[]> subscribers = new AtomicReference<>(AsyncProcessor.EMPTY);
    T value;
    AsyncProcessor() {
    }
    public void onSubscribe(final Subscription subscription) {
        if (subscribers.get() == AsyncProcessor.TERMINATED) subscription.cancel();
        else {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(final Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (subscribers.get() == AsyncProcessor.TERMINATED) {
            return;
        }
        value = ( T ) t;
    }
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        final AsyncSubscription<T>[] asyncSubscriptionArr = subscribers.get();
        final AsyncSubscription<T>[] asyncSubscriptionArr2 = AsyncProcessor.TERMINATED;
        if (asyncSubscriptionArr == asyncSubscriptionArr2) {
            RxJavaPlugins.onError(th);
            return;
        }
        value = null;
        error = th;
        for (final AsyncSubscription<T> asyncSubscription : subscribers.getAndSet(asyncSubscriptionArr2)) {
            asyncSubscription.onError(th);
        }
    }
    public void onComplete() {
        final AsyncSubscription<T>[] asyncSubscriptionArr = subscribers.get();
        final AsyncSubscription<T>[] asyncSubscriptionArr2 = AsyncProcessor.TERMINATED;
        if (asyncSubscriptionArr == asyncSubscriptionArr2) {
            return;
        }
        final T t = value;
        final AsyncSubscription<T>[] andSet = subscribers.getAndSet(asyncSubscriptionArr2);
        int i2 = 0;
        if (t == null) {
            final int length = andSet.length;
            while (i2 < length) {
                andSet[i2].onComplete();
                i2++;
            }
            return;
        }
        final int length2 = andSet.length;
        while (i2 < length2) {
            andSet[i2].complete(t);
            i2++;
        }
    }
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        final AsyncSubscription<T> asyncSubscription = new AsyncSubscription<>(subscriber, this);
        subscriber.onSubscribe(asyncSubscription);
        if (this.add(asyncSubscription)) {
            if (asyncSubscription.isCancelled()) {
                this.remove(asyncSubscription);
                return;
            }
            return;
        }
        final Throwable th = error;
        if (null != th) {
            subscriber.onError(th);
            return;
        }
        final T t = value;
        if (null != t) {
            asyncSubscription.complete(t);
        } else {
            asyncSubscription.onComplete();
        }
    }
    boolean add(final AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription<T>[] asyncSubscriptionArr;
        AsyncSubscription[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = subscribers.get();
            if (asyncSubscriptionArr == AsyncProcessor.TERMINATED) {
                return false;
            }
            final int length = asyncSubscriptionArr.length;
            asyncSubscriptionArr2 = new AsyncSubscription[length + 1];
            System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr2, 0, length);
            asyncSubscriptionArr2[length] = asyncSubscription;
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, (AsyncSubscription<com.proje.mobilesales.features.sales.view.newadd.T>[]) asyncSubscriptionArr, asyncSubscriptionArr2));
        return true;
    }
    void remove(final AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription[] asyncSubscriptionArr;
        AsyncSubscription[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = subscribers.get();
            final int length = asyncSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (asyncSubscriptionArr[i2] == asyncSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                asyncSubscriptionArr2 = AsyncProcessor.EMPTY;
            } else {
                final AsyncSubscription[] asyncSubscriptionArr3 = new AsyncSubscription[length - 1];
                System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr3, 0, i2);
                System.arraycopy(asyncSubscriptionArr, i2 + 1, asyncSubscriptionArr3, i2, (length - i2) - 1);
                asyncSubscriptionArr2 = asyncSubscriptionArr3;
            }
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, asyncSubscriptionArr, asyncSubscriptionArr2));
    }
    public static final class AsyncSubscription<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncProcessor<T> parent;

        AsyncSubscription(final Subscriber<? super T> subscriber, final AsyncProcessor<T> asyncProcessor) {
            super(subscriber);
            parent = asyncProcessor;
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
        public void cancel() {
            if (this.tryCancel()) {
                parent.remove(this);
            }
        }

        void onComplete() {
            if (isCancelled()) {
                return;
            }
            this.downstream.onComplete();
        }

        void onError(final Throwable th) {
            if (isCancelled()) {
                RxJavaPlugins.onError(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
