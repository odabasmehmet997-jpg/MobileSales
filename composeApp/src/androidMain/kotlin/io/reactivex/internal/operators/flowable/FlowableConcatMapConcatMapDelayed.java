package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import java.util.concurrent.Callable;

final class FlowableConcatMapConcatMapDelayed<T, R> extends FlowableConcatMapBaseConcatMapSubscriber<T, R> {
    private static final long serialVersionUID = -2945777694260521066L;
    final Subscriber<? super R> downstream;
    final boolean veryEnd;
    FlowableConcatMapConcatMapDelayed(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function, int i2, boolean z) {
        super(function, i2);
        this.downstream = subscriber;
        this.veryEnd = z;
    }
    void subscribeActual() {
        this.downstream.onSubscribe(this);
    }
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            this.done = true;
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }
    public void innerNext(Object r) {
        this.downstream.onNext(r);
    }
    public void innerError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (!this.veryEnd) {
                this.upstream.cancel();
                this.done = true;
            }
            this.active = false;
            drain();
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
        Object obj;
        if (0 == this.getAndIncrement()) {
            while (!this.cancelled) {
                if (!this.active) {
                    boolean z = this.done;
                    if (z && !this.veryEnd && null != errors.get ()) {
                        this.downstream.onError(this.errors.terminate());
                        return;
                    }
                    try {
                        T poll = this.queue.poll();
                        boolean z2 = null == poll;
                        if (z && z2) {
                            Throwable terminate = this.errors.terminate();
                            if (null != terminate) {
                                this.downstream.onError(terminate);
                                return;
                            } else {
                                this.downstream.onComplete();
                                return;
                            }
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
                                        obj = ((Callable) publisher).call();
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.errors.addThrowable(th);
                                        if (!this.veryEnd) {
                                            this.upstream.cancel();
                                            this.downstream.onError(this.errors.terminate());
                                            return;
                                        }
                                        obj = null;
                                    }
                                    if (null == obj) {
                                        continue;
                                    } else if (this.inner.isUnbounded()) {
                                        this.downstream.onNext(obj);
                                    } else {
                                        this.active = true;
                                        this.inner.setSubscription(new FlowableConcatMapSimpleScalarSubscription(obj, this.inner));
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
                if (0 == this.decrementAndGet()) {
                    return;
                }
            }
        }
    }
}
