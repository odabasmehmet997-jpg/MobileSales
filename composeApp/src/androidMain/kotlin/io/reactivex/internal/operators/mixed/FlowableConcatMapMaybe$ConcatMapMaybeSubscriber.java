package io.reactivex.internal.operators.mixed;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableConcatMapMaybeConcatMapMaybeSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    static final int STATE_ACTIVE = 1;
    static final int STATE_INACTIVE = 0;
    static final int STATE_RESULT_VALUE = 2;
    private static final long serialVersionUID = -9140123220065488293L;
    volatile boolean cancelled;
    int consumed;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    long emitted;
    final ErrorMode errorMode;
    R item;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    final int prefetch;
    final SimplePlainQueue<T> queue;
    volatile int state;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicThrowable errors = new AtomicThrowable();
    final ConcatMapMaybeObserver<R> inner = new ConcatMapMaybeObserver<>(this);

    FlowableConcatMapMaybeConcatMapMaybeSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends MaybeSource<? extends R>> function, int i2, ErrorMode errorMode) {
        this.downstream = subscriber;
        this.mapper = function;
        this.prefetch = i2;
        this.errorMode = errorMode;
        this.queue = new SpscArrayQueue(i2);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(this.prefetch);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (!this.queue.offer(t)) {
            this.upstream.cancel();
            onError(new MissingBackpressureException("queue full?!"));
        } else {
            drain();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (ErrorMode.IMMEDIATE == errorMode) {
                this.inner.dispose();
            }
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
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
        this.upstream.cancel();
        this.inner.dispose();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
            this.item = null;
        }
    }

    void innerSuccess(R r) {
        this.item = r;
        this.state = 2;
        drain();
    }

    void innerComplete() {
        this.state = 0;
        drain();
    }

    void innerError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (ErrorMode.END != errorMode) {
                this.upstream.cancel();
            }
            this.state = 0;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        Subscriber<? super R> subscriber = this.downstream;
        ErrorMode errorMode = this.errorMode;
        SimplePlainQueue<T> simplePlainQueue = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        AtomicLong atomicLong = this.requested;
        int i2 = this.prefetch;
        int i3 = i2 - (i2 >> 1);
        int i4 = 1;
        while (true) {
            if (this.cancelled) {
                simplePlainQueue.clear();
                this.item = null;
            } else {
                int i5 = this.state;
                if (null == atomicThrowable.get () || (ErrorMode.IMMEDIATE != errorMode && (ErrorMode.BOUNDARY != errorMode || 0 != i5))) {
                    if (0 == i5) {
                        boolean z = this.done;
                        T poll = simplePlainQueue.poll();
                        boolean z2 = null == poll;
                        if (z && z2) {
                            Throwable terminate = atomicThrowable.terminate();
                            if (null == terminate) {
                                subscriber.onComplete();
                                return;
                            } else {
                                subscriber.onError(terminate);
                                return;
                            }
                        }
                        if (!z2) {
                            int i6 = this.consumed + 1;
                            if (i6 == i3) {
                                this.consumed = 0;
                                this.upstream.request(i3);
                            } else {
                                this.consumed = i6;
                            }
                            try {
                                MaybeSource maybeSource = ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper returned a null MaybeSource");
                                this.state = 1;
                                maybeSource.subscribe(this.inner);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                this.upstream.cancel();
                                simplePlainQueue.clear();
                                atomicThrowable.addThrowable(th);
                                subscriber.onError(atomicThrowable.terminate());
                                return;
                            }
                        }
                    } else if (2 == i5) {
                        long j2 = this.emitted;
                        if (j2 != atomicLong.get()) {
                            R r = this.item;
                            this.item = null;
                            subscriber.onNext(r);
                            this.emitted = j2 + 1;
                            this.state = 0;
                        }
                    }
                }
            }
            i4 = addAndGet(-i4);
            if (0 == i4) {
                return;
            }
        }
        simplePlainQueue.clear();
        this.item = null;
        subscriber.onError(atomicThrowable.terminate());
    }

    static final class ConcatMapMaybeObserver<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
        private static final long serialVersionUID = -3051469169682093892L;
        final FlowableConcatMapMaybeConcatMapMaybeSubscriber<?, R> parent;

        ConcatMapMaybeObserver(FlowableConcatMapMaybeConcatMapMaybeSubscriber<?, R> flowableConcatMapMaybeConcatMapMaybeSubscriber) {
            this.parent = flowableConcatMapMaybeConcatMapMaybeSubscriber;
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(R r) {
            this.parent.innerSuccess(r);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.parent.innerComplete();
        }

        void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
