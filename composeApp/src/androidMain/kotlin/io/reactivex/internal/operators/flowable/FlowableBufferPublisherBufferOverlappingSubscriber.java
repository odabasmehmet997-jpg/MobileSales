package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableBufferPublisherBufferOverlappingSubscriber<T, C extends Collection<? super T>> extends AtomicLong implements FlowableSubscriber<T>, Subscription, BooleanSupplier {
    private static final long serialVersionUID = -7370244972039324525L;
    final Callable<C> bufferSupplier;
    volatile boolean cancelled;
    boolean done;
    final Subscriber<? super C> downstream;
    int index;
    long produced;
    final int size;
    final int skip;
    Subscription upstream;
    final AtomicBoolean once = new AtomicBoolean();
    final ArrayDeque<C> buffers = new ArrayDeque<>();
    FlowableBufferPublisherBufferOverlappingSubscriber(Subscriber<? super C> subscriber, int i2, int i3, Callable<C> callable) {
        this.downstream = subscriber;
        this.size = i2;
        this.skip = i3;
        this.bufferSupplier = callable;
    }
    public boolean getAsBoolean() {
        return this.cancelled;
    }
    public void request(long j2) {
        if (!SubscriptionHelper.validate(j2) || QueueDrainHelper.postCompleteRequest(j2, this.downstream, this.buffers, this, this)) {
            return;
        }
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.upstream.request(BackpressureHelper.addCap(this.size, BackpressureHelper.multiplyCap(this.skip, j2 - 1)));
        } else {
            this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j2));
        }
    }
    public void cancel() {
        this.cancelled = true;
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
        ArrayDeque<C> arrayDeque = this.buffers;
        int i2 = this.index;
        int i3 = i2 + 1;
        if (0 == i2) {
            try {
                arrayDeque.offer(ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                onError(th);
                return;
            }
        }
        Collection collection = arrayDeque.peek();
        if (null != collection && collection.size() + 1 == this.size) {
            arrayDeque.poll();
            collection.add(t);
            this.produced++;
            this.downstream.onNext(collection);
        }
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).add(t);
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
        this.buffers.clear();
        this.downstream.onError(th);
    }

    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        long j2 = this.produced;
        if (0 != j2) {
            BackpressureHelper.produced(this, j2);
        }
        QueueDrainHelper.postComplete(this.downstream, this.buffers, this, this);
    }
}
