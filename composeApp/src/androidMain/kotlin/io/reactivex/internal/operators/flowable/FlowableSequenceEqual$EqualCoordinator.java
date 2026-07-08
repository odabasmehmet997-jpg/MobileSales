package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableSequenceEqualEqualCoordinator<T> extends DeferredScalarSubscription<Boolean> implements FlowableSequenceEqualEqualCoordinatorHelper {
    private static final long serialVersionUID = -6178010334400373240L;
    final BiPredicate<? super T, ? super T> comparer;
    final AtomicThrowable error;
    final FlowableSequenceEqualEqualSubscriber<T> first;
    final FlowableSequenceEqualEqualSubscriber<T> second;
    T v1;
    T v2;
    final AtomicInteger wip;

    FlowableSequenceEqualEqualCoordinator(Subscriber<? super Boolean> subscriber, int i2, BiPredicate<? super T, ? super T> biPredicate) {
        super(subscriber);
        this.comparer = biPredicate;
        this.wip = new AtomicInteger();
        this.first = new FlowableSequenceEqualEqualSubscriber<>(this, i2);
        this.second = new FlowableSequenceEqualEqualSubscriber<>(this, i2);
        this.error = new AtomicThrowable();
    }

    void subscribe(Publisher<? extends T> publisher, Publisher<? extends T> publisher2) {
        publisher.subscribe(this.first);
        publisher2.subscribe(this.second);
    }

    @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription, io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        super.cancel();
        this.first.cancel();
        this.second.cancel();
        if (0 == wip.getAndIncrement ()) {
            this.first.clear();
            this.second.clear();
        }
    }

    void cancelAndClear() {
        this.first.cancel();
        this.first.clear();
        this.second.cancel();
        this.second.clear();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSequenceEqualEqualCoordinatorHelper
    public void drain() {
        if (0 != wip.getAndIncrement ()) {
            return;
        }
        int i2 = 1;
        do {
            SimpleQueue<T> simpleQueue = this.first.queue;
            SimpleQueue<T> simpleQueue2 = this.second.queue;
            if (null != simpleQueue && null != simpleQueue2) {
                while (!this.isCancelled()) {
                    if (null != error.get ()) {
                        cancelAndClear();
                        downstream.onError(this.error.terminate());
                        return;
                    }
                    boolean z = this.first.done;
                    T t = this.v1;
                    if (null == t) {
                        try {
                            t = simpleQueue.poll();
                            this.v1 = t;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            cancelAndClear();
                            this.error.addThrowable(th);
                            downstream.onError(this.error.terminate());
                            return;
                        }
                    }
                    boolean z2 = null == t;
                    boolean z3 = this.second.done;
                    T t2 = this.v2;
                    if (null == t2) {
                        try {
                            t2 = simpleQueue2.poll();
                            this.v2 = t2;
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            cancelAndClear();
                            this.error.addThrowable(th2);
                            downstream.onError(this.error.terminate());
                            return;
                        }
                    }
                    boolean z4 = null == t2;
                    if (z && z3 && z2 && z4) {
                        this.complete(Boolean.TRUE);
                        return;
                    }
                    if (z && z3 && z2 != z4) {
                        cancelAndClear();
                        this.complete(Boolean.FALSE);
                        return;
                    }
                    if (!z2 && !z4) {
                        try {
                            if (!this.comparer.test(t, t2)) {
                                cancelAndClear();
                                this.complete(Boolean.FALSE);
                                return;
                            } else {
                                this.v1 = null;
                                this.v2 = null;
                                this.first.request();
                                this.second.request();
                            }
                        } catch (Throwable th3) {
                            Exceptions.throwIfFatal(th3);
                            cancelAndClear();
                            this.error.addThrowable(th3);
                            downstream.onError(this.error.terminate());
                            return;
                        }
                    }
                }
                this.first.clear();
                this.second.clear();
                return;
            }
            if (this.isCancelled()) {
                this.first.clear();
                this.second.clear();
                return;
            } else if (null != error.get ()) {
                cancelAndClear();
                downstream.onError(this.error.terminate());
                return;
            }
            i2 = this.wip.addAndGet(-i2);
        } while (0 != i2);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSequenceEqualEqualCoordinatorHelper
    public void innerError(Throwable th) {
        if (this.error.addThrowable(th)) {
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }
}
