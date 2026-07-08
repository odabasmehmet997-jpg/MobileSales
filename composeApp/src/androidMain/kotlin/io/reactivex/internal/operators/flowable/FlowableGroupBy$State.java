package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableGroupByState<T, K> extends BasicIntQueueSubscription<T> implements Publisher<T> {
    private static final long serialVersionUID = -3852313036005250360L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final K key;
    boolean outputFused;
    final FlowableGroupByGroupBySubscriber<?, K, T> parent;
    int produced;
    final SpscLinkedArrayQueue<T> queue;
    final AtomicLong requested = new AtomicLong();
    final AtomicBoolean cancelled = new AtomicBoolean();
    final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();

    FlowableGroupByState(int i2, FlowableGroupByGroupBySubscriber<?, K, T> flowableGroupByGroupBySubscriber, K k2, boolean z) {
        this.queue = new SpscLinkedArrayQueue<>(i2);
        this.parent = flowableGroupByGroupBySubscriber;
        this.key = k2;
        this.delayError = z;
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
        if (this.cancelled.compareAndSet(false, true)) {
            this.parent.cancel(this.key);
            drain();
        }
    }

    @Override // org.reactivestreams.Publisher
    public void subscribe(Subscriber<? super T> subscriber) {
        if (this.once.compareAndSet(false, true)) {
            subscriber.onSubscribe(this);
            this.actual.lazySet(subscriber);
            drain();
            return;
        }
        EmptySubscription.error(new IllegalStateException("Only one Subscriber allowed!"), subscriber);
    }

    public void onNext(T t) {
        this.queue.offer(t);
        drain();
    }

    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    public void onComplete() {
        this.done = true;
        drain();
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        if (this.outputFused) {
            drainFused();
        } else {
            drainNormal();
        }
    }

    void drainFused() {
        Throwable th;
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        Subscriber<? super T> subscriber = this.actual.get();
        int i2 = 1;
        while (true) {
            if (null != subscriber) {
                if (this.cancelled.get()) {
                    return;
                }
                boolean z = this.done;
                if (z && !this.delayError && null != (th = error)) {
                    spscLinkedArrayQueue.clear();
                    subscriber.onError(th);
                    return;
                }
                subscriber.onNext(null);
                if (z) {
                    Throwable th2 = this.error;
                    if (null != th2) {
                        subscriber.onError(th2);
                        return;
                    } else {
                        subscriber.onComplete();
                        return;
                    }
                }
            }
            i2 = this.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
            if (null == subscriber) {
                subscriber = this.actual.get();
            }
        }
    }

    void drainNormal() {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        boolean z = this.delayError;
        Subscriber<? super T> subscriber = this.actual.get();
        int i2 = 1;
        while (true) {
            if (null != subscriber) {
                long j2 = this.requested.get();
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        break;
                    }
                    boolean z2 = this.done;
                    T poll = spscLinkedArrayQueue.poll();
                    boolean z3 = null == poll;
                    long j4 = j3;
                    if (checkTerminated(z2, z3, subscriber, z, j3)) {
                        return;
                    }
                    if (z3) {
                        j3 = j4;
                        break;
                    } else {
                        subscriber.onNext(poll);
                        j3 = j4 + 1;
                    }
                }
                if (j3 == j2) {
                    long j5 = j3;
                    if (checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, z, j3)) {
                        return;
                    } else {
                        j3 = j5;
                    }
                }
                if (0 != j3) {
                    if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                        this.requested.addAndGet(-j3);
                    }
                    this.parent.upstream.request(j3);
                }
            }
            i2 = this.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
            if (null == subscriber) {
                subscriber = this.actual.get();
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3, long j2) {
        if (this.cancelled.get()) {
            while (null != queue.poll ()) {
                j2++;
            }
            if (0 != j2) {
                this.parent.upstream.request(j2);
            }
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            Throwable th = this.error;
            if (null != th) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
            return true;
        }
        Throwable th2 = this.error;
        if (null != th2) {
            this.queue.clear();
            subscriber.onError(th2);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onComplete();
        return true;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public int requestFusion(int i2) {
        if (0 == (i2 & 2)) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public T poll() {
        T poll = this.queue.poll();
        if (null != poll) {
            this.produced++;
            return poll;
        }
        tryReplenish();
        return null;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        if (!this.queue.isEmpty()) {
            return false;
        }
        tryReplenish();
        return true;
    }

    void tryReplenish() {
        int i2 = this.produced;
        if (0 != i2) {
            this.produced = 0;
            this.parent.upstream.request(i2);
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        while (null != spscLinkedArrayQueue.poll ()) {
            this.produced++;
        }
        tryReplenish();
    }
}
