package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



public final class PublishProcessor<T> extends FlowableProcessor<T> {
    Throwable error;
    final AtomicReference<PublishSubscription<T>[]> subscribers = new AtomicReference<>(PublishProcessor.EMPTY);
    static final PublishSubscription[] TERMINATED = new PublishSubscription[0];
    static final PublishSubscription[] EMPTY = new PublishSubscription[0];

    PublishProcessor() {
    }
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        final PublishSubscription<T> publishSubscription = new PublishSubscription<>(subscriber, this);
        subscriber.onSubscribe(publishSubscription);
        if (this.add(publishSubscription)) {
            if (publishSubscription.isCancelled()) {
                this.remove(publishSubscription);
            }
        } else {
            final Throwable th = error;
            if (null != th) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
        }
    }

    boolean add(final PublishSubscription<T> publishSubscription) {
        PublishSubscription<T>[] publishSubscriptionArr;
        PublishSubscription[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = subscribers.get();
            if (publishSubscriptionArr == PublishProcessor.TERMINATED) {
                return false;
            }
            final int length = publishSubscriptionArr.length;
            publishSubscriptionArr2 = new PublishSubscription[length + 1];
            System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr2, 0, length);
            publishSubscriptionArr2[length] = publishSubscription;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, publishSubscriptionArr, publishSubscriptionArr2));
        return true;
    }

    void remove(final PublishSubscription<T> publishSubscription) {
        PublishSubscription<T>[] publishSubscriptionArr;
        PublishSubscription[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = subscribers.get();
            if (publishSubscriptionArr == PublishProcessor.TERMINATED || publishSubscriptionArr == PublishProcessor.EMPTY) {
                return;
            }
            final int length = publishSubscriptionArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (publishSubscriptionArr[i2] == publishSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                publishSubscriptionArr2 = PublishProcessor.EMPTY;
            } else {
                final PublishSubscription[] publishSubscriptionArr3 = new PublishSubscription[length - 1];
                System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr3, 0, i2);
                System.arraycopy(publishSubscriptionArr, i2 + 1, publishSubscriptionArr3, i2, (length - i2) - 1);
                publishSubscriptionArr2 = publishSubscriptionArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, publishSubscriptionArr, publishSubscriptionArr2));
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(final Subscription subscription) {
        if (subscribers.get() == PublishProcessor.TERMINATED) {
            subscription.cancel();
        } else {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(final Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (final PublishSubscription<T> publishSubscription : subscribers.get()) {
            publishSubscription.onNext(t);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        final PublishSubscription<T>[] publishSubscriptionArr = subscribers.get();
        final PublishSubscription<T>[] publishSubscriptionArr2 = PublishProcessor.TERMINATED;
        if (publishSubscriptionArr == publishSubscriptionArr2) {
            RxJavaPlugins.onError(th);
            return;
        }
        error = th;
        for (final PublishSubscription<T> publishSubscription : subscribers.getAndSet(publishSubscriptionArr2)) {
            publishSubscription.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        final PublishSubscription<T>[] publishSubscriptionArr = subscribers.get();
        final PublishSubscription<T>[] publishSubscriptionArr2 = PublishProcessor.TERMINATED;
        if (publishSubscriptionArr == publishSubscriptionArr2) {
            return;
        }
        for (final PublishSubscription<T> publishSubscription : subscribers.getAndSet(publishSubscriptionArr2)) {
            publishSubscription.onComplete();
        }
    }

    static final class PublishSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 3562861878281475070L;
        final Subscriber<? super T> downstream;
        final PublishProcessor<T> parent;

        PublishSubscription(final Subscriber<? super T> subscriber, final PublishProcessor<T> publishProcessor) {
            downstream = subscriber;
            parent = publishProcessor;
        }

        public void onNext(final T t) {
            final long j2 = this.get();
            if (Long.MIN_VALUE == j2) {
                return;
            }
            if (0 != j2) {
                downstream.onNext(t);
                BackpressureHelper.producedCancel(this, 1L);
            } else {
                this.cancel();
                downstream.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
            }
        }

        public void onError(final Throwable th) {
            if (Long.MIN_VALUE != get()) {
                downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (Long.MIN_VALUE != get()) {
                downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(final long j2) {
            if (SubscriptionHelper.validate(j2)) {
                BackpressureHelper.addCancel(this, j2);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (Long.MIN_VALUE != getAndSet(Long.MIN_VALUE)) {
                parent.remove(this);
            }
        }

        public boolean isCancelled() {
            return Long.MIN_VALUE == get();
        }

        boolean isFull() {
            return 0 == get();
        }
    }
}
