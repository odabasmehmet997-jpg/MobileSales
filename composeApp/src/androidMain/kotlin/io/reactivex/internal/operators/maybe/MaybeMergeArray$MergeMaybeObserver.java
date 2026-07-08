package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicLong;



final class MaybeMergeArrayMergeMaybeObserver<T> extends BasicIntQueueSubscription<T> implements MaybeObserver<T> {
    private static final long serialVersionUID = -660395290758764731L;
    volatile boolean cancelled;
    long consumed;
    final Subscriber<? super T> downstream;
    boolean outputFused;
    final MaybeMergeArraySimpleQueueWithConsumerIndex<Object> queue;
    final int sourceCount;
    final CompositeDisposable set = new CompositeDisposable();
    final AtomicLong requested = new AtomicLong();
    final AtomicThrowable error = new AtomicThrowable();

    MaybeMergeArrayMergeMaybeObserver(Subscriber<? super T> subscriber, int i2, MaybeMergeArraySimpleQueueWithConsumerIndex<Object> maybeMergeArraySimpleQueueWithConsumerIndex) {
        this.downstream = subscriber;
        this.sourceCount = i2;
        this.queue = maybeMergeArraySimpleQueueWithConsumerIndex;
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
    public T poll() throws Exception {
        T t;
        do {
            t = (T) this.queue.poll();
        } while (NotificationLite.COMPLETE == t);
        return t;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        this.queue.clear();
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
        this.set.dispose();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        this.set.add(disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.queue.offer(t);
        drain();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        if (this.error.addThrowable(th)) {
            this.set.dispose();
            this.queue.offer(NotificationLite.COMPLETE);
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.queue.offer(NotificationLite.COMPLETE);
        drain();
    }

    boolean isCancelled() {
        return this.cancelled;
    }

    void drainNormal() {
        Subscriber<? super T> subscriber = this.downstream;
        MaybeMergeArraySimpleQueueWithConsumerIndex<Object> maybeMergeArraySimpleQueueWithConsumerIndex = this.queue;
        long j2 = this.consumed;
        int i2 = 1;
        do {
            long j3 = this.requested.get();
            while (j2 != j3) {
                if (this.cancelled) {
                    maybeMergeArraySimpleQueueWithConsumerIndex.clear();
                    return;
                }
                if (null != error.get ()) {
                    maybeMergeArraySimpleQueueWithConsumerIndex.clear();
                    subscriber.onError(this.error.terminate());
                    return;
                } else {
                    if (maybeMergeArraySimpleQueueWithConsumerIndex.consumerIndex() == this.sourceCount) {
                        subscriber.onComplete();
                        return;
                    }
                    Object poll = maybeMergeArraySimpleQueueWithConsumerIndex.poll();
                    if (null == poll) {
                        break;
                    } else if (NotificationLite.COMPLETE != poll) {
                        subscriber.onNext(poll);
                        j2++;
                    }
                }
            }
            if (j2 == j3) {
                if (null != error.get ()) {
                    maybeMergeArraySimpleQueueWithConsumerIndex.clear();
                    subscriber.onError(this.error.terminate());
                    return;
                } else {
                    while (NotificationLite.COMPLETE == maybeMergeArraySimpleQueueWithConsumerIndex.peek()) {
                        maybeMergeArraySimpleQueueWithConsumerIndex.drop();
                    }
                    if (maybeMergeArraySimpleQueueWithConsumerIndex.consumerIndex() == this.sourceCount) {
                        subscriber.onComplete();
                        return;
                    }
                }
            }
            this.consumed = j2;
            i2 = this.addAndGet(-i2);
        } while (0 != i2);
    }

    void drainFused() {
        Subscriber<? super T> subscriber = this.downstream;
        MaybeMergeArraySimpleQueueWithConsumerIndex<Object> maybeMergeArraySimpleQueueWithConsumerIndex = this.queue;
        int i2 = 1;
        while (!this.cancelled) {
            Throwable th = this.error.get();
            if (null != th) {
                maybeMergeArraySimpleQueueWithConsumerIndex.clear();
                subscriber.onError(th);
                return;
            }
            boolean z = maybeMergeArraySimpleQueueWithConsumerIndex.producerIndex() == this.sourceCount;
            if (!maybeMergeArraySimpleQueueWithConsumerIndex.isEmpty()) {
                subscriber.onNext(null);
            }
            if (z) {
                subscriber.onComplete();
                return;
            } else {
                i2 = this.addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
        maybeMergeArraySimpleQueueWithConsumerIndex.clear();
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
}
