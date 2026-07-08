package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.InnerQueuedSubscriberSupport;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

final class FlowableConcatMapEagerConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, InnerQueuedSubscriberSupport<R> {
    private static final long serialVersionUID = -4255299542215038287L;
    volatile boolean cancelled;
    volatile InnerQueuedSubscriber<R> current;
    volatile boolean done;
    Subscriber<? super R> downstream;
    ErrorMode errorMode;
    Function<? super T, ? extends Publisher<? extends R>> mapper;
    int maxConcurrency;
    int prefetch;
    SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;
    Subscription upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();
    FlowableConcatMapEagerConcatMapEagerDelayErrorSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function, int i2, int i3, ErrorMode errorMode) {
        this.downstream = subscriber;
        this.mapper = function;
        this.maxConcurrency = i2;
        this.prefetch = i3;
        this.errorMode = errorMode;
        this.subscribers = new SpscLinkedArrayQueue<>(Math.min(i3, i2));
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            int i2 = this.maxConcurrency;
            subscription.request(Integer.MAX_VALUE == i2 ? LocationRequestCompat.PASSIVE_INTERVAL : i2);
        }
    }
    public void onNext(Object t) {
        try {
            Publisher publisher = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
            InnerQueuedSubscriber<R> innerQueuedSubscriber = new InnerQueuedSubscriber<>(this, this.prefetch);
            if (this.cancelled) {
                return;
            }
            this.subscribers.offer(innerQueuedSubscriber);
            publisher.subscribe(innerQueuedSubscriber);
            if (this.cancelled) {
                innerQueuedSubscriber.cancel();
                drainAndCancel();
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }
    public void onComplete() {
        this.done = true;
        drain();
    }
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        drainAndCancel();
    }
    void drainAndCancel() {
        if (0 == this.getAndIncrement()) {
            do {
                cancelAll();
            } while (0 != this.decrementAndGet());
        }
    }
    void cancelAll() {
        InnerQueuedSubscriber<R> innerQueuedSubscriber = this.current;
        this.current = null;
        if (null != innerQueuedSubscriber) {
            innerQueuedSubscriber.cancel();
        }
        while (true) {
            InnerQueuedSubscriber<R> poll = this.subscribers.poll();
            if (null == poll) {
                return;
            } else {
                poll.cancel();
            }
        }
    }
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }
    public void innerNext(InnerQueuedSubscriber<R> innerQueuedSubscriber, R r) {
        if (innerQueuedSubscriber.queue().offer(r)) {
            drain();
        } else {
            innerQueuedSubscriber.cancel();
            innerError(innerQueuedSubscriber, new MissingBackpressureException());
        }
    }
    public void innerError(InnerQueuedSubscriber<R> innerQueuedSubscriber, Throwable th) {
        if (this.errors.addThrowable(th)) {
            innerQueuedSubscriber.setDone();
            if (ErrorMode.END != errorMode) {
                this.upstream.cancel();
            }
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public void innerComplete(InnerQueuedSubscriber<R> innerQueuedSubscriber) {
        innerQueuedSubscriber.setDone();
        drain();
    }
    public void drain() {
        InnerQueuedSubscriber<R> innerQueuedSubscriber;
        int i2;
        boolean z = false;
        long j2 = 0;
        long j3;
        SimpleQueue<R> queue;
        if (0 != this.getAndIncrement()) {
            return;
        }
        InnerQueuedSubscriber<R> innerQueuedSubscriber2 = this.current;
        Subscriber<? super R> subscriber = this.downstream;
        ErrorMode errorMode = this.errorMode;
        int i3 = 1;
        while (true) {
            long j4 = this.requested.get();
            if (null != innerQueuedSubscriber2) {
                innerQueuedSubscriber = innerQueuedSubscriber2;
            } else {
                if (ErrorMode.END != errorMode && null != errors.get ()) {
                    cancelAll();
                    subscriber.onError(this.errors.terminate());
                    return;
                }
                boolean z2 = this.done;
                innerQueuedSubscriber = this.subscribers.poll();
                if (z2 && null == innerQueuedSubscriber) {
                    Throwable terminate = this.errors.terminate();
                    if (null != terminate) {
                        subscriber.onError(terminate);
                        return;
                    } else {
                        subscriber.onComplete();
                        return;
                    }
                }
                if (null != innerQueuedSubscriber) {
                    this.current = innerQueuedSubscriber;
                }
            }
            if (null == innerQueuedSubscriber || null == (queue = innerQueuedSubscriber.queue ())) {
                i2 = i3;
                z = false;
                j2 = 0;
                j3 = 0;
            } else {
                j3 = 0;
                while (true) {
                    i2 = i3;
                    if (j3 == j4) {
                        break;
                    }
                    if (this.cancelled) {
                        cancelAll();
                        return;
                    }
                    if (ErrorMode.IMMEDIATE == errorMode && null != errors.get ()) {
                        this.current = null;
                        innerQueuedSubscriber.cancel();
                        cancelAll();
                        subscriber.onError(this.errors.terminate());
                        return;
                    }
                    boolean isDone = innerQueuedSubscriber.isDone();
                    try {
                        R poll = queue.poll();
                        boolean z3 = null == poll;
                        if (isDone && z3) {
                            this.current = null;
                            this.upstream.request(1L);
                            innerQueuedSubscriber = null;
                            z = true;
                            break;
                        }
                        if (z3) {
                            break;
                        }
                        subscriber.onNext(poll);
                        j3++;
                        innerQueuedSubscriber.requestOne();
                        i3 = i2;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.current = null;
                        innerQueuedSubscriber.cancel();
                        cancelAll();
                        subscriber.onError(th);
                        return;
                    }
                }
            }
            if (j3 != j2 && LocationRequestCompat.PASSIVE_INTERVAL != j4) {
                this.requested.addAndGet(-j3);
            }
            if (z) {
                innerQueuedSubscriber2 = innerQueuedSubscriber;
                i3 = i2;
            } else {
                i3 = addAndGet(-i2);
                if (0 == i3) {
                    return;
                } else {
                    innerQueuedSubscriber2 = innerQueuedSubscriber;
                }
            }
        }
    }
}
