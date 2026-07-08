package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

final class FlowableConcatMapConcatMapImmediate<T, R> extends FlowableConcatMapBaseConcatMapSubscriber<T, R> {
    private static final long serialVersionUID = 7898995095634264146L;
    final Subscriber<? super R> downstream;
    final AtomicInteger wip;
    FlowableConcatMapConcatMapImmediate(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function, int i2) {
        super(function, i2);
        this.downstream = subscriber;
        this.wip = new AtomicInteger();
    }
    void subscribeActual() {
        this.downstream.onSubscribe(this);
    }
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            this.inner.cancel();
            if (0 == this.getAndIncrement()) {
                this.downstream.onError(this.errors.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public void innerNext(Object r) {
        if (0 == this.get() && compareAndSet(0, 1)) {
            this.downstream.onNext(r);
            if (compareAndSet(1, 0)) {
                return;
            }
            this.downstream.onError(this.errors.terminate());
        }
    }
    public void innerError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            this.upstream.cancel();
            if (0 == this.getAndIncrement()) {
                this.downstream.onError(this.errors.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public void request(long j2) {
        this.inner.request(j2);
    }
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.inner.cancel();
        this.upstream.cancel();
    }
    void drain() {
        if (0 == wip.getAndIncrement ()) {
            while (!this.cancelled) {
                if (!this.active) {
                    boolean z = this.done;
                    try {
                        Object poll = this.queue.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            this.downstream.onComplete();
                            return;
                        }
                        if (!z2) {
                            try {
                                Publisher publisher = ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                if (1 != sourceMode) {
                                    int i2 = this.consumed + 1;
                                    if (i2 == this.limit) {
                                        this.consumed = 0;
                                        this.upstream.request(i2);
                                    } else {
                                        this.consumed = i2;
                                    }
                                }
                                if (publisher instanceof Callable) {
                                    try {
                                        Object call = ((Callable) publisher).call();
                                        if (null == call) {
                                            continue;
                                        } else if (this.inner.isUnbounded()) {
                                            if (0 == this.get() && compareAndSet(0, 1)) {
                                                this.downstream.onNext(call);
                                                if (!compareAndSet(1, 0)) {
                                                    this.downstream.onError(this.errors.terminate());
                                                    return;
                                                }
                                            }
                                        } else {
                                            this.active = true;
                                            this.inner.setSubscription(new FlowableConcatMapSimpleScalarSubscription(call, this.inner));
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.cancel();
                                        this.errors.addThrowable(th);
                                        this.downstream.onError(this.errors.terminate());
                                        return;
                                    }
                                } else {
                                    this.active = true;
                                    publisher.subscribe(this.inner);
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                this.upstream.cancel();
                                this.errors.addThrowable(th2);
                                this.downstream.onError(this.errors.terminate());
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        Exceptions.throwIfFatal(th3);
                        this.upstream.cancel();
                        this.errors.addThrowable(th3);
                        this.downstream.onError(this.errors.terminate());
                        return;
                    }
                }
                if (0 == wip.decrementAndGet ()) {
                    return;
                }
            }
        }
    }
}
