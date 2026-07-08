package io.reactivex.internal.operators.flowable;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableSequenceEqualSingleEqualCoordinator<T> extends AtomicInteger implements Disposable, FlowableSequenceEqualEqualCoordinatorHelper {
    private static final long serialVersionUID = -6178010334400373240L;
    final BiPredicate<? super T, ? super T> comparer;
    final SingleObserver<? super Boolean> downstream;
    final AtomicThrowable error = new AtomicThrowable();
    final FlowableSequenceEqualEqualSubscriber<T> first;
    final FlowableSequenceEqualEqualSubscriber<T> second;
    T v1;
    T v2;

    FlowableSequenceEqualSingleEqualCoordinator(SingleObserver<? super Boolean> singleObserver, int i2, BiPredicate<? super T, ? super T> biPredicate) {
        this.downstream = singleObserver;
        this.comparer = biPredicate;
        this.first = new FlowableSequenceEqualEqualSubscriber<>(this, i2);
        this.second = new FlowableSequenceEqualEqualSubscriber<>(this, i2);
    }

    void subscribe(Publisher<? extends T> publisher, Publisher<? extends T> publisher2) {
        publisher.subscribe(this.first);
        publisher2.subscribe(this.second);
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.first.cancel();
        this.second.cancel();
        if (0 == this.getAndIncrement()) {
            this.first.clear();
            this.second.clear();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == first.get();
    }

    void cancelAndClear() {
        this.first.cancel();
        this.first.clear();
        this.second.cancel();
        this.second.clear();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSequenceEqualEqualCoordinatorHelper
    public void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        int i2 = 1;
        do {
            SimpleQueue<T> simpleQueue = this.first.queue;
            SimpleQueue<T> simpleQueue2 = this.second.queue;
            if (null != simpleQueue && null != simpleQueue2) {
                while (!isDisposed()) {
                    if (null != error.get ()) {
                        cancelAndClear();
                        this.downstream.onError(this.error.terminate());
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
                            this.downstream.onError(this.error.terminate());
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
                            this.downstream.onError(this.error.terminate());
                            return;
                        }
                    }
                    boolean z4 = null == t2;
                    if (z && z3 && z2 && z4) {
                        this.downstream.onSuccess(Boolean.TRUE);
                        return;
                    }
                    if (z && z3 && z2 != z4) {
                        cancelAndClear();
                        this.downstream.onSuccess(Boolean.FALSE);
                        return;
                    }
                    if (!z2 && !z4) {
                        try {
                            if (!this.comparer.test(t, t2)) {
                                cancelAndClear();
                                this.downstream.onSuccess(Boolean.FALSE);
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
                            this.downstream.onError(this.error.terminate());
                            return;
                        }
                    }
                }
                this.first.clear();
                this.second.clear();
                return;
            }
            if (isDisposed()) {
                this.first.clear();
                this.second.clear();
                return;
            } else if (null != error.get ()) {
                cancelAndClear();
                this.downstream.onError(this.error.terminate());
                return;
            }
            i2 = addAndGet(-i2);
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
