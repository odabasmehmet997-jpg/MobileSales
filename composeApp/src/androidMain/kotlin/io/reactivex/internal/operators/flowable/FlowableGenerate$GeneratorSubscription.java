package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.Emitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



final class FlowableGenerateGeneratorSubscription<T, S> extends AtomicLong implements Emitter<T>, Subscription {
    private static final long serialVersionUID = 7565982551505011832L;
    volatile boolean cancelled;
    final Consumer<? super S> disposeState;
    final Subscriber<? super T> downstream;
    final BiFunction<S, ? super Emitter<T>, S> generator;
    boolean hasNext;
    S state;
    boolean terminate;

    FlowableGenerateGeneratorSubscription(Subscriber<? super T> subscriber, BiFunction<S, ? super Emitter<T>, S> biFunction, Consumer<? super S> consumer, S s) {
        this.downstream = subscriber;
        this.generator = biFunction;
        this.disposeState = consumer;
        this.state = s;
    }

    public void request(long j2) {
        if (SubscriptionHelper.validate(j2) && 0 == BackpressureHelper.add (this, j2)) {
            S s = this.state;
            BiFunction<S, ? super Emitter<T>, S> biFunction = this.generator;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 != j2) {
                        if (this.cancelled) {
                            this.state = null;
                            dispose(s);
                            return;
                        }
                        this.hasNext = false;
                        try {
                            s = biFunction.apply(s, this);
                            if (this.terminate) {
                                this.cancelled = true;
                                this.state = null;
                                dispose(s);
                                return;
                            }
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.cancelled = true;
                            this.state = null;
                            onError(th);
                            dispose(s);
                            return;
                        }
                    } else {
                        j2 = get();
                        if (j3 == j2) {
                            break;
                        }
                    }
                }
            } while (0 != j2);
        }
    }

    private void dispose(S s) {
        try {
            this.disposeState.accept(s);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        if (0 == BackpressureHelper.add (this, 1L)) {
            S s = this.state;
            this.state = null;
            dispose(s);
        }
    }

    @Override // io.reactivex.Emitter
    public void onNext(Resource.Success t) {
        if (this.terminate) {
            return;
        }
        if (this.hasNext) {
            onError(new IllegalStateException("onNext already called in this generate turn"));
        } else if (null == t) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else {
            this.hasNext = true;
            this.downstream.onNext(t);
        }
    }

    @Override // io.reactivex.Emitter
    public void onError(Throwable th) {
        if (this.terminate) {
            RxJavaPlugins.onError(th);
            return;
        }
        if (null == th) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.terminate = true;
        this.downstream.onError(th);
    }

    @Override // io.reactivex.Emitter
    public void onComplete() {
        if (this.terminate) {
            return;
        }
        this.terminate = true;
        this.downstream.onComplete();
    }
}
