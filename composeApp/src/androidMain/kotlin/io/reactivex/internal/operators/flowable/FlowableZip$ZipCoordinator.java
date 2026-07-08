package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableZipZipCoordinator<T, R> extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = -2434867452883857743L;
    volatile boolean cancelled;
    final Object[] current;
    final boolean delayErrors;
    final Subscriber<? super R> downstream;
    final AtomicThrowable errors;
    final AtomicLong requested;
    final FlowableZipZipSubscriber<T, R>[] subscribers;
    final Function<? super Object[], ? extends R> zipper;

    FlowableZipZipCoordinator(Subscriber<? super R> subscriber, Function<? super Object[], ? extends R> function, int i2, int i3, boolean z) {
        this.downstream = subscriber;
        this.zipper = function;
        this.delayErrors = z;
        FlowableZipZipSubscriber<T, R>[] flowableZipZipSubscriberArr = new FlowableZipZipSubscriber[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            flowableZipZipSubscriberArr[i4] = new FlowableZipZipSubscriber<>(this, i3);
        }
        this.current = new Object[i2];
        this.subscribers = flowableZipZipSubscriberArr;
        this.requested = new AtomicLong();
        this.errors = new AtomicThrowable();
    }

    void subscribe(Publisher<? extends T>[] publisherArr, int i2) {
        FlowableZipZipSubscriber<T, R>[] flowableZipZipSubscriberArr = this.subscribers;
        for (int i3 = 0; i3 < i2 && !this.cancelled; i3++) {
            if (!this.delayErrors && null != errors.get ()) {
                return;
            }
            publisherArr[i3].subscribe(flowableZipZipSubscriberArr[i3]);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        cancelAll();
    }

    void error(FlowableZipZipSubscriber<T, R> flowableZipZipSubscriber, Throwable th) {
        if (this.errors.addThrowable(th)) {
            flowableZipZipSubscriber.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void cancelAll() {
        for (FlowableZipZipSubscriber<T, R> flowableZipZipSubscriber : this.subscribers) {
            flowableZipZipSubscriber.cancel();
        }
    }

    void drain() {
        boolean z;
        T poll;
        boolean z2;
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super R> subscriber = this.downstream;
        FlowableZipZipSubscriber<T, R>[] flowableZipZipSubscriberArr = this.subscribers;
        int length = flowableZipZipSubscriberArr.length;
        Object[] objArr = this.current;
        int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j2 != j3) {
                if (this.cancelled) {
                    return;
                }
                if (!this.delayErrors && null != errors.get ()) {
                    cancelAll();
                    subscriber.onError(this.errors.terminate());
                    return;
                }
                boolean z3 = false;
                for (int i3 = 0; i3 < length; i3++) {
                    FlowableZipZipSubscriber<T, R> flowableZipZipSubscriber = flowableZipZipSubscriberArr[i3];
                    if (null == objArr[i3]) {
                        try {
                            z = flowableZipZipSubscriber.done;
                            SimpleQueue<T> simpleQueue = flowableZipZipSubscriber.queue;
                            poll = null != simpleQueue ? simpleQueue.poll() : null;
                            z2 = null == poll;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.errors.addThrowable(th);
                            if (!this.delayErrors) {
                                cancelAll();
                                subscriber.onError(this.errors.terminate());
                                return;
                            }
                        }
                        if (z && z2) {
                            cancelAll();
                            if (null != errors.get ()) {
                                subscriber.onError(this.errors.terminate());
                                return;
                            } else {
                                subscriber.onComplete();
                                return;
                            }
                        }
                        if (!z2) {
                            objArr[i3] = poll;
                        }
                        z3 = true;
                    }
                }
                if (z3) {
                    break;
                }
                try {
                    subscriber.onNext(ObjectHelper.requireNonNull(this.zipper.apply(objArr.clone()), "The zipper returned a null value"));
                    j3++;
                    Arrays.fill(objArr, null);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    cancelAll();
                    this.errors.addThrowable(th2);
                    subscriber.onError(this.errors.terminate());
                    return;
                }
            }
            if (j2 == j3) {
                if (this.cancelled) {
                    return;
                }
                if (!this.delayErrors && null != errors.get ()) {
                    cancelAll();
                    subscriber.onError(this.errors.terminate());
                    return;
                }
                for (int i4 = 0; i4 < length; i4++) {
                    FlowableZipZipSubscriber<T, R> flowableZipZipSubscriber2 = flowableZipZipSubscriberArr[i4];
                    if (null == objArr[i4]) {
                        try {
                            boolean z4 = flowableZipZipSubscriber2.done;
                            SimpleQueue<T> simpleQueue2 = flowableZipZipSubscriber2.queue;
                            T poll2 = null != simpleQueue2 ? simpleQueue2.poll() : null;
                            boolean z5 = null == poll2;
                            if (z4 && z5) {
                                cancelAll();
                                if (null != errors.get ()) {
                                    subscriber.onError(this.errors.terminate());
                                    return;
                                } else {
                                    subscriber.onComplete();
                                    return;
                                }
                            }
                            if (!z5) {
                                objArr[i4] = poll2;
                            }
                        } catch (Throwable th3) {
                            Exceptions.throwIfFatal(th3);
                            this.errors.addThrowable(th3);
                            if (!this.delayErrors) {
                                cancelAll();
                                subscriber.onError(this.errors.terminate());
                                return;
                            }
                        }
                    }
                }
            }
            if (0 != j3) {
                for (FlowableZipZipSubscriber<T, R> flowableZipZipSubscriber3 : flowableZipZipSubscriberArr) {
                    flowableZipZipSubscriber3.request(j3);
                }
                if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                    this.requested.addAndGet(-j3);
                }
            }
            i2 = addAndGet(-i2);
        } while (0 != i2);
    }
}
