package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableCombineLatestCombineLatestCoordinator<T, R> extends BasicIntQueueSubscription<R> {
    private static final long serialVersionUID = -5082275438355852221L;
    volatile boolean cancelled;
    final Function<? super Object[], ? extends R> combiner;
    int completedSources;
    final boolean delayErrors;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    final AtomicReference<Throwable> error;
    final Object[] latest;
    int nonEmptySources;
    boolean outputFused;
    final SpscLinkedArrayQueue<Object> queue;
    final AtomicLong requested;
    final FlowableCombineLatestCombineLatestInnerSubscriber<T>[] subscribers;

    FlowableCombineLatestCombineLatestCoordinator(Subscriber<? super R> subscriber, Function<? super Object[], ? extends R> function, int i2, int i3, boolean z) {
        this.downstream = subscriber;
        this.combiner = function;
        FlowableCombineLatestCombineLatestInnerSubscriber<T>[] flowableCombineLatestCombineLatestInnerSubscriberArr = new FlowableCombineLatestCombineLatestInnerSubscriber[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            flowableCombineLatestCombineLatestInnerSubscriberArr[i4] = new FlowableCombineLatestCombineLatestInnerSubscriber<>(this, i4, i3);
        }
        this.subscribers = flowableCombineLatestCombineLatestInnerSubscriberArr;
        this.latest = new Object[i2];
        this.queue = new SpscLinkedArrayQueue<>(i3);
        this.requested = new AtomicLong();
        this.error = new AtomicReference<>();
        this.delayErrors = z;
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
        this.cancelled = true;
        cancelAll();
    }

    void subscribe(Publisher<? extends T>[] publisherArr, int i2) {
        FlowableCombineLatestCombineLatestInnerSubscriber<T>[] flowableCombineLatestCombineLatestInnerSubscriberArr = this.subscribers;
        for (int i3 = 0; i3 < i2 && !this.done && !this.cancelled; i3++) {
            publisherArr[i3].subscribe(flowableCombineLatestCombineLatestInnerSubscriberArr[i3]);
        }
    }

    void innerValue(int i2, T t) {
        boolean z;
        synchronized (this) {
            try {
                Object[] objArr = this.latest;
                int i3 = this.nonEmptySources;
                if (null == objArr[i2]) {
                    i3++;
                    this.nonEmptySources = i3;
                }
                objArr[i2] = t;
                if (objArr.length == i3) {
                    this.queue.offer(this.subscribers[i2], objArr.clone());
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            this.subscribers[i2].requestOne();
        } else {
            drain();
        }
    }

    void innerComplete(int i2) {
        synchronized (this) {
            try {
                Object[] objArr = this.latest;
                if (null != objArr[i2]) {
                    int i3 = this.completedSources + 1;
                    if (i3 == objArr.length) {
                        this.done = true;
                    } else {
                        this.completedSources = i3;
                        return;
                    }
                } else {
                    this.done = true;
                }
                drain();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void innerError(int i2, Throwable th) {
        if (ExceptionHelper.addThrowable(this.error, th)) {
            if (!this.delayErrors) {
                cancelAll();
                this.done = true;
                drain();
                return;
            }
            innerComplete(i2);
            return;
        }
        RxJavaPlugins.onError(th);
    }

    void drainOutput() {
        Subscriber<? super R> subscriber = this.downstream;
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        int i2 = 1;
        while (!this.cancelled) {
            Throwable th = this.error.get();
            if (null != th) {
                spscLinkedArrayQueue.clear();
                subscriber.onError(th);
                return;
            }
            boolean z = this.done;
            boolean isEmpty = spscLinkedArrayQueue.isEmpty();
            if (!isEmpty) {
                subscriber.onNext(null);
            }
            if (z && isEmpty) {
                subscriber.onComplete();
                return;
            } else {
                i2 = this.addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
        spscLinkedArrayQueue.clear();
    }

    void drainAsync() {
        Subscriber<? super R> subscriber = this.downstream;
        SpscLinkedArrayQueue<?> spscLinkedArrayQueue = this.queue;
        int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                boolean z = this.done;
                Object poll = spscLinkedArrayQueue.poll();
                boolean z2 = null == poll;
                if (checkTerminated(z, z2, subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (z2) {
                    break;
                }
                try {
                    subscriber.onNext(ObjectHelper.requireNonNull(this.combiner.apply((Object[]) spscLinkedArrayQueue.poll()), "The combiner returned a null value"));
                    ((FlowableCombineLatestCombineLatestInnerSubscriber) poll).requestOne();
                    j3++;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancelAll();
                    ExceptionHelper.addThrowable(this.error, th);
                    subscriber.onError(ExceptionHelper.terminate(this.error));
                    return;
                }
            }
            if (j3 == j2 && checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                return;
            }
            if (0 != j3 && LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                this.requested.addAndGet(-j3);
            }
            i2 = this.addAndGet(-i2);
        } while (0 != i2);
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        if (this.outputFused) {
            drainOutput();
        } else {
            drainAsync();
        }
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
        if (this.cancelled) {
            cancelAll();
            spscLinkedArrayQueue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (this.delayErrors) {
            if (!z2) {
                return false;
            }
            cancelAll();
            Throwable terminate = ExceptionHelper.terminate(this.error);
            if (null != terminate && terminate != ExceptionHelper.TERMINATED) {
                subscriber.onError(terminate);
            } else {
                subscriber.onComplete();
            }
            return true;
        }
        Throwable terminate2 = ExceptionHelper.terminate(this.error);
        if (null != terminate2 && terminate2 != ExceptionHelper.TERMINATED) {
            cancelAll();
            spscLinkedArrayQueue.clear();
            subscriber.onError(terminate2);
            return true;
        }
        if (!z2) {
            return false;
        }
        cancelAll();
        subscriber.onComplete();
        return true;
    }

    void cancelAll() {
        for (FlowableCombineLatestCombineLatestInnerSubscriber<T> flowableCombineLatestCombineLatestInnerSubscriber : this.subscribers) {
            flowableCombineLatestCombineLatestInnerSubscriber.cancel();
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public int requestFusion(int i2) {
        if (0 != (i2 & 4)) {
            return 0;
        }
        int i3 = i2 & 2;
        this.outputFused = 0 != i3;
        return i3;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public R poll() throws Exception {
        Object poll = this.queue.poll();
        if (null == poll) {
            return null;
        }
        R r = ObjectHelper.requireNonNull(this.combiner.apply(this.queue.poll()), "The combiner returned a null value");
        ((FlowableCombineLatestCombineLatestInnerSubscriber) poll).requestOne();
        return r;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        this.queue.clear();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
