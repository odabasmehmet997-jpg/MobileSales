package io.reactivex.internal.operators.flowable;

import _COROUTINE.ArtificialStackFrames;
import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableSwitchMapSwitchMapSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    static final FlowableSwitchMapSwitchMapInnerSubscriber<Object, Object> CANCELLED;
    private static final long serialVersionUID = -3491074160481096299L;
    final int bufferSize;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    volatile long unique;
    Subscription upstream;
    final AtomicReference<FlowableSwitchMapSwitchMapInnerSubscriber<T, R>> active = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();
    final AtomicThrowable error = new AtomicThrowable();

    static {
        FlowableSwitchMapSwitchMapInnerSubscriber<Object, Object> flowableSwitchMapSwitchMapInnerSubscriber = new FlowableSwitchMapSwitchMapInnerSubscriber<>(null, -1L, 1);
        CANCELLED = flowableSwitchMapSwitchMapInnerSubscriber;
        flowableSwitchMapSwitchMapInnerSubscriber.cancel();
    }

    FlowableSwitchMapSwitchMapSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function, int i2, boolean z) {
        this.downstream = subscriber;
        this.mapper = function;
        this.bufferSize = i2;
        this.delayErrors = z;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        FlowableSwitchMapSwitchMapInnerSubscriber<T, R> flowableSwitchMapSwitchMapInnerSubscriber;
        if (this.done) {
            return;
        }
        long j2 = this.unique + 1;
        this.unique = j2;
        FlowableSwitchMapSwitchMapInnerSubscriber<T, R> flowableSwitchMapSwitchMapInnerSubscriber2 = this.active.get();
        if (null != flowableSwitchMapSwitchMapInnerSubscriber2) {
            flowableSwitchMapSwitchMapInnerSubscriber2.cancel();
        }
        try {
            Publisher publisher = ObjectHelper.requireNonNull(this.mapper.apply(t), "The publisher returned is null");
            FlowableSwitchMapSwitchMapInnerSubscriber flowableSwitchMapSwitchMapInnerSubscriber3 = new FlowableSwitchMapSwitchMapInnerSubscriber(this, j2, this.bufferSize);
            do {
                flowableSwitchMapSwitchMapInnerSubscriber = this.active.get();
                if (flowableSwitchMapSwitchMapInnerSubscriber == CANCELLED) {
                    return;
                }
            } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.active, flowableSwitchMapSwitchMapInnerSubscriber, flowableSwitchMapSwitchMapInnerSubscriber3));
            publisher.subscribe(flowableSwitchMapSwitchMapInnerSubscriber3);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (!this.done && this.error.addThrowable(th)) {
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            if (0 == unique) {
                this.upstream.request(LocationRequestCompat.PASSIVE_INTERVAL);
            } else {
                drain();
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        disposeInner();
    }

    void disposeInner() {
        FlowableSwitchMapSwitchMapInnerSubscriber<Object, Object> flowableSwitchMapSwitchMapInnerSubscriber;
        FlowableSwitchMapSwitchMapInnerSubscriber<T, R> flowableSwitchMapSwitchMapInnerSubscriber2 = this.active.get();
        FlowableSwitchMapSwitchMapInnerSubscriber<Object, Object> flowableSwitchMapSwitchMapInnerSubscriber3 = CANCELLED;
        if (flowableSwitchMapSwitchMapInnerSubscriber2 == flowableSwitchMapSwitchMapInnerSubscriber3 || (flowableSwitchMapSwitchMapInnerSubscriber = (FlowableSwitchMapSwitchMapInnerSubscriber) this.active.getAndSet(flowableSwitchMapSwitchMapInnerSubscriber3)) == flowableSwitchMapSwitchMapInnerSubscriber3 || null == flowableSwitchMapSwitchMapInnerSubscriber) {
            return;
        }
        flowableSwitchMapSwitchMapInnerSubscriber.cancel();
    }

    void drain() {
        boolean z;
        ArtificialStackFrames artificialStackFrames;
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super R> subscriber = this.downstream;
        int i2 = 1;
        while (!this.cancelled) {
            if (this.done) {
                if (this.delayErrors) {
                    if (null == active.get ()) {
                        if (null != error.get ()) {
                            subscriber.onError(this.error.terminate());
                            return;
                        } else {
                            subscriber.onComplete();
                            return;
                        }
                    }
                } else if (null != error.get ()) {
                    disposeInner();
                    subscriber.onError(this.error.terminate());
                    return;
                } else if (null == active.get ()) {
                    subscriber.onComplete();
                    return;
                }
            }
            FlowableSwitchMapSwitchMapInnerSubscriber<T, R> flowableSwitchMapSwitchMapInnerSubscriber = this.active.get();
            SimpleQueue<R> simpleQueue = null != flowableSwitchMapSwitchMapInnerSubscriber ? flowableSwitchMapSwitchMapInnerSubscriber.queue : null;
            if (null != simpleQueue) {
                if (flowableSwitchMapSwitchMapInnerSubscriber.done) {
                    if (!this.delayErrors) {
                        if (null != error.get ()) {
                            disposeInner();
                            subscriber.onError(this.error.terminate());
                            return;
                        } else if (simpleQueue.isEmpty()) {
                            LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.active, flowableSwitchMapSwitchMapInnerSubscriber, null);
                        }
                    } else if (simpleQueue.isEmpty()) {
                        LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.active, flowableSwitchMapSwitchMapInnerSubscriber, null);
                    }
                }
                long j2 = this.requested.get();
                long j3 = 0;
                while (true) {
                    z = false;
                    if (j3 != j2) {
                        if (!this.cancelled) {
                            boolean z2 = flowableSwitchMapSwitchMapInnerSubscriber.done;
                            try {
                                artificialStackFrames = simpleQueue.poll();
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                flowableSwitchMapSwitchMapInnerSubscriber.cancel();
                                this.error.addThrowable(th);
                                artificialStackFrames = null;
                                z2 = true;
                            }
                            boolean z3 = null == artificialStackFrames;
                            if (flowableSwitchMapSwitchMapInnerSubscriber != this.active.get()) {
                                break;
                            }
                            if (z2) {
                                if (!this.delayErrors) {
                                    if (null == error.get ()) {
                                        if (z3) {
                                            LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.active, flowableSwitchMapSwitchMapInnerSubscriber, null);
                                            break;
                                        }
                                    } else {
                                        subscriber.onError(this.error.terminate());
                                        return;
                                    }
                                } else if (z3) {
                                    LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.active, flowableSwitchMapSwitchMapInnerSubscriber, null);
                                    break;
                                }
                            }
                            if (z3) {
                                break;
                            }
                            subscriber.onNext(artificialStackFrames);
                            j3++;
                        } else {
                            return;
                        }
                    } else {
                        break;
                    }
                }
                z = true;
                if (0 != j3 && !this.cancelled) {
                    if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                        this.requested.addAndGet(-j3);
                    }
                    flowableSwitchMapSwitchMapInnerSubscriber.request(j3);
                }
                if (z) {
                    continue;
                }
            }
            i2 = addAndGet(-i2);
            if (0 == i2) {
                return;
            }
        }
    }
}
