package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;



final class FlowableBufferPublisherBufferSkipSubscriber<T, C extends Collection<? super T>> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -5616169793639412593L;
    C buffer;
    final Callable<C> bufferSupplier;
    boolean done;
    final Subscriber<? super C> downstream;
    int index;
    final int size;
    final int skip;
    Subscription upstream;

    FlowableBufferPublisherBufferSkipSubscriber(Subscriber<? super C> subscriber, int i2, int i3, Callable<C> callable) {
        this.downstream = subscriber;
        this.size = i2;
        this.skip = i3;
        this.bufferSupplier = callable;
    }
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            if (0 == this.get() && compareAndSet(0, 1)) {
                this.upstream.request(BackpressureHelper.addCap(BackpressureHelper.multiplyCap(j2, this.size), BackpressureHelper.multiplyCap(this.skip - this.size, j2 - 1)));
                return;
            }
            this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j2));
        }
    }

    public void cancel() {
        this.upstream.cancel();
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }
    }
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        C c2 = this.buffer;
        int i2 = this.index;
        int i3 = i2 + 1;
        if (0 == i2) {
            try {
                c2 = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                this.buffer = c2;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                onError(th);
                return;
            }
        }
        if (null != c2) {
            c2.add(t);
            if (c2.size() == this.size) {
                this.buffer = null;
                this.downstream.onNext(c2);
            }
        }
        if (i3 == this.skip) {
            i3 = 0;
        }
        this.index = i3;
    }
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        this.buffer = null;
        this.downstream.onError(th);
    }
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        C c2 = this.buffer;
        this.buffer = null;
        if (null != c2) {
            this.downstream.onNext(c2);
        }
        this.downstream.onComplete();
    }
}
