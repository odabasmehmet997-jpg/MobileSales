package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;



final class FlowableWithLatestFromManyWithLatestFromSubscriber<T, R> extends AtomicInteger implements ConditionalSubscriber<T>, Subscription {
    private static final long serialVersionUID = 1577321883966341961L;
    final Function<? super Object[], R> combiner;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    final AtomicThrowable error;
    final AtomicLong requested;
    final FlowableWithLatestFromManyWithLatestInnerSubscriber[] subscribers;
    final AtomicReference<Subscription> upstream;
    final AtomicReferenceArray<Object> values;

    FlowableWithLatestFromManyWithLatestFromSubscriber(Subscriber<? super R> subscriber, Function<? super Object[], R> function, int i2) {
        this.downstream = subscriber;
        this.combiner = function;
        FlowableWithLatestFromManyWithLatestInnerSubscriber[] flowableWithLatestFromManyWithLatestInnerSubscriberArr = new FlowableWithLatestFromManyWithLatestInnerSubscriber[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            flowableWithLatestFromManyWithLatestInnerSubscriberArr[i3] = new FlowableWithLatestFromManyWithLatestInnerSubscriber(this, i3);
        }
        this.subscribers = flowableWithLatestFromManyWithLatestInnerSubscriberArr;
        this.values = new AtomicReferenceArray<>(i2);
        this.upstream = new AtomicReference<>();
        this.requested = new AtomicLong();
        this.error = new AtomicThrowable();
    }

    void subscribe(Publisher<?>[] publisherArr, int i2) {
        FlowableWithLatestFromManyWithLatestInnerSubscriber[] flowableWithLatestFromManyWithLatestInnerSubscriberArr = this.subscribers;
        AtomicReference<Subscription> atomicReference = this.upstream;
        for (int i3 = 0; i3 < i2 && SubscriptionHelper.CANCELLED != atomicReference.get(); i3++) {
            publisherArr[i3].subscribe(flowableWithLatestFromManyWithLatestInnerSubscriberArr[i3]);
        }
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (tryOnNext(t) || this.done) {
            return;
        }
        this.upstream.get().request(1L);
    }

    @Override // io.reactivex.internal.fuseable.ConditionalSubscriber
    public boolean tryOnNext(T t) {
        if (this.done) {
            return false;
        }
        AtomicReferenceArray<Object> atomicReferenceArray = this.values;
        int length = atomicReferenceArray.length();
        Object[] objArr = new Object[length + 1];
        objArr[0] = t;
        int i2 = 0;
        while (i2 < length) {
            Object obj = atomicReferenceArray.get(i2);
            if (null == obj) {
                return false;
            }
            i2++;
            objArr[i2] = obj;
        }
        try {
            HalfSerializer.onNext(this.downstream, ObjectHelper.requireNonNull(this.combiner.apply(objArr), "The combiner returned a null value"), this, this.error);
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            cancel();
            onError(th);
            return false;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        cancelAllBut(-1);
        HalfSerializer.onError(this.downstream, th, this, this.error);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        cancelAllBut(-1);
        HalfSerializer.onComplete(this.downstream, this, this.error);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
        for (FlowableWithLatestFromManyWithLatestInnerSubscriber flowableWithLatestFromManyWithLatestInnerSubscriber : this.subscribers) {
            flowableWithLatestFromManyWithLatestInnerSubscriber.dispose();
        }
    }

    void innerNext(int i2, Object obj) {
        this.values.set(i2, obj);
    }

    void innerError(int i2, Throwable th) {
        this.done = true;
        SubscriptionHelper.cancel(this.upstream);
        cancelAllBut(i2);
        HalfSerializer.onError(this.downstream, th, this, this.error);
    }

    void innerComplete(int i2, boolean z) {
        if (z) {
            return;
        }
        this.done = true;
        SubscriptionHelper.cancel(this.upstream);
        cancelAllBut(i2);
        HalfSerializer.onComplete(this.downstream, this, this.error);
    }

    void cancelAllBut(int i2) {
        FlowableWithLatestFromManyWithLatestInnerSubscriber[] flowableWithLatestFromManyWithLatestInnerSubscriberArr = this.subscribers;
        for (int i3 = 0; i3 < flowableWithLatestFromManyWithLatestInnerSubscriberArr.length; i3++) {
            if (i3 != i2) {
                flowableWithLatestFromManyWithLatestInnerSubscriberArr[i3].dispose();
            }
        }
    }
}
