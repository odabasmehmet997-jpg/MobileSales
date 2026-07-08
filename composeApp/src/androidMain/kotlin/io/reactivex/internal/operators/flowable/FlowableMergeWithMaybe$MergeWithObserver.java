package io.reactivex.internal.operators.flowable;

import _COROUTINE.ArtificialStackFrames;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableMergeWithMaybeMergeWithObserver<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
    static final int OTHER_STATE_HAS_VALUE = 1;
    private static final long serialVersionUID = -4592979584110982903L;
    volatile boolean cancelled;
    int consumed;
    final Subscriber<? super T> downstream;
    long emitted;
    final int limit;
    volatile boolean mainDone;
    volatile int otherState;
    final int prefetch;
    volatile SimplePlainQueue<T> queue;
    T singleItem;
    final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
    final OtherObserver<T> otherObserver = new OtherObserver<>(this);
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();

    FlowableMergeWithMaybeMergeWithObserver(Subscriber<? super T> subscriber) {
        this.downstream = subscriber;
        int bufferSize = Flowable.bufferSize();
        this.prefetch = bufferSize;
        this.limit = bufferSize - (bufferSize >> 2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this.mainSubscription, subscription, this.prefetch);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (compareAndSet(0, 1)) {
            long j2 = this.emitted;
            if (this.requested.get() != j2) {
                SimplePlainQueue<T> simplePlainQueue = this.queue;
                if (null == simplePlainQueue || simplePlainQueue.isEmpty()) {
                    this.emitted = j2 + 1;
                    this.downstream.onNext(t);
                    int i2 = this.consumed + 1;
                    if (i2 == this.limit) {
                        this.consumed = 0;
                        this.mainSubscription.get().request(i2);
                    } else {
                        this.consumed = i2;
                    }
                } else {
                    simplePlainQueue.offer(t);
                }
            } else {
                getOrCreateQueue().offer(t);
            }
            if (0 == this.decrementAndGet()) {
                return;
            }
        } else {
            getOrCreateQueue().offer(t);
            if (0 != this.getAndIncrement()) {
                return;
            }
        }
        drainLoop();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.error.addThrowable(th)) {
            DisposableHelper.dispose(this.otherObserver);
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.mainDone = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        BackpressureHelper.add(this.requested, j2);
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        SubscriptionHelper.cancel(this.mainSubscription);
        DisposableHelper.dispose(this.otherObserver);
        if (0 == this.getAndIncrement()) {
            this.queue = null;
            this.singleItem = null;
        }
    }

    void otherSuccess(T t) {
        if (compareAndSet(0, 1)) {
            long j2 = this.emitted;
            if (this.requested.get() != j2) {
                this.emitted = j2 + 1;
                this.downstream.onNext(t);
                this.otherState = 2;
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (0 == this.decrementAndGet()) {
                    return;
                }
            }
        } else {
            this.singleItem = t;
            this.otherState = 1;
            if (0 != this.getAndIncrement()) {
                return;
            }
        }
        drainLoop();
    }

    void otherError(Throwable th) {
        if (this.error.addThrowable(th)) {
            SubscriptionHelper.cancel(this.mainSubscription);
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void otherComplete() {
        this.otherState = 2;
        drain();
    }

    SimplePlainQueue<T> getOrCreateQueue() {
        SimplePlainQueue<T> simplePlainQueue = this.queue;
        if (null != simplePlainQueue) {
            return simplePlainQueue;
        }
        SpscArrayQueue spscArrayQueue = new SpscArrayQueue(Flowable.bufferSize());
        this.queue = spscArrayQueue;
        return spscArrayQueue;
    }

    void drain() {
        if (0 == this.getAndIncrement()) {
            drainLoop();
        }
    }

    void drainLoop() {
        Subscriber<? super T> subscriber = this.downstream;
        long j2 = this.emitted;
        int i2 = this.consumed;
        int i3 = this.limit;
        int i4 = 1;
        int i5 = 1;
        while (true) {
            long j3 = this.requested.get();
            while (j2 != j3) {
                if (this.cancelled) {
                    this.singleItem = null;
                    this.queue = null;
                    return;
                }
                if (null != error.get ()) {
                    this.singleItem = null;
                    this.queue = null;
                    subscriber.onError(this.error.terminate());
                    return;
                }
                int i6 = this.otherState;
                if (i6 == i4) {
                    T t = this.singleItem;
                    this.singleItem = null;
                    this.otherState = 2;
                    subscriber.onNext(t);
                    j2++;
                } else {
                    boolean z = this.mainDone;
                    SimplePlainQueue<T> simplePlainQueue = this.queue;
                    ArtificialStackFrames poll = null != simplePlainQueue ? simplePlainQueue.poll() : null;
                    boolean z2 = null == poll;
                    if (z && z2 && 2 == i6) {
                        this.queue = null;
                        subscriber.onComplete();
                        return;
                    } else {
                        if (z2) {
                            break;
                        }
                        subscriber.onNext(poll);
                        j2++;
                        i2++;
                        if (i2 == i3) {
                            this.mainSubscription.get().request(i3);
                            i2 = 0;
                        }
                        i4 = 1;
                    }
                }
            }
            if (j2 == j3) {
                if (this.cancelled) {
                    this.singleItem = null;
                    this.queue = null;
                    return;
                }
                if (null != error.get ()) {
                    this.singleItem = null;
                    this.queue = null;
                    subscriber.onError(this.error.terminate());
                    return;
                }
                boolean z3 = this.mainDone;
                SimplePlainQueue<T> simplePlainQueue2 = this.queue;
                boolean z4 = null == simplePlainQueue2 || simplePlainQueue2.isEmpty();
                if (z3 && z4 && 2 == otherState) {
                    this.queue = null;
                    subscriber.onComplete();
                    return;
                }
            }
            this.emitted = j2;
            this.consumed = i2;
            i5 = addAndGet(-i5);
            if (0 == i5) {
                return;
            } else {
                i4 = 1;
            }
        }
    }

    static final class OtherObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = -2935427570954647017L;
        final FlowableMergeWithMaybeMergeWithObserver<T> parent;

        OtherObserver(FlowableMergeWithMaybeMergeWithObserver<T> flowableMergeWithMaybeMergeWithObserver) {
            this.parent = flowableMergeWithMaybeMergeWithObserver;
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            this.parent.otherSuccess(t);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.parent.otherComplete();
        }
    }
}
