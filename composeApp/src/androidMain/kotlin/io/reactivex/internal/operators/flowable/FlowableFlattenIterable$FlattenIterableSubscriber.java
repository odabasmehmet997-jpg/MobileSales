package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableFlattenIterableFlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -3096000382929934955L;
    volatile boolean cancelled;
    int consumed;
    Iterator<? extends R> current;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    int fusionMode;
    final int limit;
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    SimpleQueue<T> queue;
    Subscription upstream;
    final AtomicReference<Throwable> error = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    FlowableFlattenIterableFlattenIterableSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Iterable<? extends R>> function, int i2) {
        this.downstream = subscriber;
        this.mapper = function;
        this.prefetch = i2;
        this.limit = i2 - (i2 >> 2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (subscription instanceof QueueSubscription queueSubscription) {
                int requestFusion = queueSubscription.requestFusion(3);
                if (1 == requestFusion) {
                    this.fusionMode = 1;
                    this.queue = queueSubscription;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    return;
                }
                if (2 == requestFusion) {
                    this.fusionMode = 2;
                    this.queue = queueSubscription;
                    this.downstream.onSubscribe(this);
                    subscription.request(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        if (0 == fusionMode && !this.queue.offer(t)) {
            onError(new MissingBackpressureException("Queue is full?!"));
        } else {
            drain();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (!this.done && ExceptionHelper.addThrowable(this.error, th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        drain();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<?> subscriber = this.downstream;
        SimpleQueue<T> simpleQueue = this.queue;
        boolean z = true;
        boolean z2 = 1 != fusionMode;
        Iterator<? extends R> it = this.current;
        int i2 = 1;
        while (true) {
            if (null == it) {
                boolean z3 = this.done;
                try {
                    T poll = simpleQueue.poll();
                    if (checkTerminated(z3, null == poll && z, subscriber, simpleQueue)) {
                        return;
                    }
                    if (null != poll) {
                        try {
                            it = this.mapper.apply(poll).iterator();
                            if (!it.hasNext()) {
                                consumedOne(z2);
                                it = null;
                            } else {
                                this.current = it;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.upstream.cancel();
                            ExceptionHelper.addThrowable(this.error, th);
                            subscriber.onError(ExceptionHelper.terminate(this.error));
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.upstream.cancel();
                    ExceptionHelper.addThrowable(this.error, th2);
                    Throwable terminate = ExceptionHelper.terminate(this.error);
                    this.current = null;
                    simpleQueue.clear();
                    subscriber.onError(terminate);
                    return;
                }
            }
            if (null != it) {
                long j2 = this.requested.get();
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        break;
                    }
                    if (checkTerminated(this.done, false, subscriber, simpleQueue)) {
                        return;
                    }
                    try {
                        subscriber.onNext(ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value"));
                        if (checkTerminated(this.done, false, subscriber, simpleQueue)) {
                            return;
                        }
                        j3++;
                        try {
                            if (!it.hasNext()) {
                                consumedOne(z2);
                                this.current = null;
                                it = null;
                                break;
                            }
                        } catch (Throwable th3) {
                            Exceptions.throwIfFatal(th3);
                            this.current = null;
                            this.upstream.cancel();
                            ExceptionHelper.addThrowable(this.error, th3);
                            subscriber.onError(ExceptionHelper.terminate(this.error));
                            return;
                        }
                    } catch (Throwable th4) {
                        Exceptions.throwIfFatal(th4);
                        this.current = null;
                        this.upstream.cancel();
                        ExceptionHelper.addThrowable(this.error, th4);
                        subscriber.onError(ExceptionHelper.terminate(this.error));
                        return;
                    }
                }
                if (j3 == j2) {
                    if (checkTerminated(this.done, simpleQueue.isEmpty() && null == it, subscriber, simpleQueue)) {
                        return;
                    }
                }
                if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                    this.requested.addAndGet(-j3);
                }
            }
            i2 = this.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
            z = true;
        }
    }

    void consumedOne(boolean z) {
        if (z) {
            int i2 = this.consumed + 1;
            if (i2 == this.limit) {
                this.consumed = 0;
                this.upstream.request(i2);
            } else {
                this.consumed = i2;
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, SimpleQueue<?> simpleQueue) {
        if (this.cancelled) {
            this.current = null;
            simpleQueue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (null == error.get ()) {
            if (!z2) {
                return false;
            }
            subscriber.onComplete();
            return true;
        }
        Throwable terminate = ExceptionHelper.terminate(this.error);
        this.current = null;
        simpleQueue.clear();
        subscriber.onError(terminate);
        return true;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        this.current = null;
        this.queue.clear();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return null == current && this.queue.isEmpty();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public R poll() throws Exception {
        Iterator<? extends R> it = this.current;
        while (true) {
            if (null == it) {
                T poll = this.queue.poll();
                if (null != poll) {
                    it = this.mapper.apply(poll).iterator();
                    if (it.hasNext()) {
                        this.current = it;
                        break;
                    }
                    it = null;
                } else {
                    return null;
                }
            } else {
                break;
            }
        }
        R r = ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
        if (!it.hasNext()) {
            this.current = null;
        }
        return r;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public int requestFusion(int i2) {
        return (0 == (i2 & 1) || 1 != fusionMode) ? 0 : 1;
    }
}
