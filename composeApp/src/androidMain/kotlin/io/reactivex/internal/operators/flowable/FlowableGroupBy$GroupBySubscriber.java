package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;



public final class FlowableGroupByGroupBySubscriber<T, K, V> extends BasicIntQueueSubscription<GroupedFlowable<K, V>> implements FlowableSubscriber<T> {
    static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    final int bufferSize;
    final boolean delayError;
    boolean done;
    final Subscriber<? super GroupedFlowable<K, V>> downstream;
    Throwable error;
    final Queue<FlowableGroupByGroupedUnicast<K, V>> evictedGroups;
    volatile boolean finished;
    final Map<Object, FlowableGroupByGroupedUnicast<K, V>> groups;
    final Function<? super T, ? extends K> keySelector;
    boolean outputFused;
    final SpscLinkedArrayQueue<GroupedFlowable<K, V>> queue;
    Subscription upstream;
    final Function<? super T, ? extends V> valueSelector;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();
    final AtomicInteger groupCount = new AtomicInteger(1);

    public FlowableGroupByGroupBySubscriber(Subscriber<? super GroupedFlowable<K, V>> subscriber, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, int i2, boolean z, Map<Object, FlowableGroupByGroupedUnicast<K, V>> map, Queue<FlowableGroupByGroupedUnicast<K, V>> queue) {
        this.downstream = subscriber;
        this.keySelector = function;
        this.valueSelector = function2;
        this.bufferSize = i2;
        this.delayError = z;
        this.groups = map;
        this.evictedGroups = queue;
        this.queue = new SpscLinkedArrayQueue<>(i2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(this.bufferSize);
        }
    }

    public void onNext(Object t) {
        boolean z;
        FlowableGroupByGroupedUnicast flowableGroupByGroupedUnicast;
        if (this.done) {
            return;
        }
        SpscLinkedArrayQueue<GroupedFlowable<K, V>> spscLinkedArrayQueue = this.queue;
        try {
            K apply = this.keySelector.apply(t);
            Object obj = null != apply ? apply : NULL_KEY;
            FlowableGroupByGroupedUnicast<K, V> flowableGroupByGroupedUnicast2 = this.groups.get(obj);
            if (null != flowableGroupByGroupedUnicast2) {
                z = false;
                flowableGroupByGroupedUnicast = flowableGroupByGroupedUnicast2;
            } else {
                if (this.cancelled.get()) {
                    return;
                }
                FlowableGroupByGroupedUnicast createWith = FlowableGroupByGroupedUnicast.createWith(apply, this.bufferSize, this, this.delayError);
                this.groups.put(obj, createWith);
                this.groupCount.getAndIncrement();
                z = true;
                flowableGroupByGroupedUnicast = createWith;
            }
            try {
                flowableGroupByGroupedUnicast.onNext(ObjectHelper.requireNonNull(this.valueSelector.apply(t), "The valueSelector returned null"));
                completeEvictions();
                if (z) {
                    spscLinkedArrayQueue.offer(flowableGroupByGroupedUnicast);
                    drain();
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            this.upstream.cancel();
            onError(th2);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        Iterator<FlowableGroupByGroupedUnicast<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            it.next().onError(th);
        }
        this.groups.clear();
        Queue<FlowableGroupByGroupedUnicast<K, V>> queue = this.evictedGroups;
        if (null != queue) {
            queue.clear();
        }
        this.error = th;
        this.finished = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        Iterator<FlowableGroupByGroupedUnicast<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.groups.clear();
        Queue<FlowableGroupByGroupedUnicast<K, V>> queue = this.evictedGroups;
        if (null != queue) {
            queue.clear();
        }
        this.done = true;
        this.finished = true;
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
        if (this.cancelled.compareAndSet(false, true)) {
            completeEvictions();
            if (0 == groupCount.decrementAndGet ()) {
                this.upstream.cancel();
            }
        }
    }

    private void completeEvictions() {
        if (null != evictedGroups) {
            int i2 = 0;
            while (true) {
                FlowableGroupByGroupedUnicast<K, V> poll = this.evictedGroups.poll();
                if (null == poll) {
                    break;
                }
                poll.onComplete();
                i2++;
            }
            if (0 != i2) {
                this.groupCount.addAndGet(-i2);
            }
        }
    }

    public void cancel(K k2) {
        if (null == k2) {
            k2 = (K) NULL_KEY;
        }
        this.groups.remove(k2);
        if (0 == groupCount.decrementAndGet ()) {
            this.upstream.cancel();
            if (this.outputFused || 0 != this.getAndIncrement()) {
                return;
            }
            this.queue.clear();
        }
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
        SpscLinkedArrayQueue<GroupedFlowable<K, V>> spscLinkedArrayQueue = this.queue;
        Subscriber<? super GroupedFlowable<K, V>> subscriber = this.downstream;
        int i2 = 1;
        while (!this.cancelled.get()) {
            boolean z = this.finished;
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
            i2 = this.addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
    }

    void drainNormal() {
        SpscLinkedArrayQueue<GroupedFlowable<K, V>> spscLinkedArrayQueue = this.queue;
        Subscriber<? super GroupedFlowable<K, V>> subscriber = this.downstream;
        int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                boolean z = this.finished;
                GroupedFlowable<K, V> poll = spscLinkedArrayQueue.poll();
                boolean z2 = null == poll;
                if (checkTerminated(z, z2, subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(poll);
                j3++;
            }
            if (j3 == j2 && checkTerminated(this.finished, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                return;
            }
            if (0 != j3) {
                if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                    this.requested.addAndGet(-j3);
                }
                this.upstream.request(j3);
            }
            i2 = this.addAndGet(-i2);
        } while (0 != i2);
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
        if (this.cancelled.get()) {
            spscLinkedArrayQueue.clear();
            return true;
        }
        if (this.delayError) {
            if (!z || !z2) {
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
        if (!z) {
            return false;
        }
        Throwable th2 = this.error;
        if (null != th2) {
            spscLinkedArrayQueue.clear();
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
    public GroupedFlowable<K, V> poll() {
        return this.queue.poll();
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
