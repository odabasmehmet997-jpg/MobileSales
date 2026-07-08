package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;



final class FlowableUsingUsingSubscriber<T, D> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 5904473792286235046L;
    final Consumer<? super D> disposer;
    final Subscriber<? super T> downstream;
    final boolean eager;
    final D resource;
    Subscription upstream;

    FlowableUsingUsingSubscriber(Subscriber<? super T> subscriber, D d2, Consumer<? super D> consumer, boolean z) {
        this.downstream = subscriber;
        this.resource = d2;
        this.disposer = consumer;
        this.eager = z;
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
        this.downstream.onNext(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.eager) {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th2) {
                    th = th2;
                    Exceptions.throwIfFatal(th);
                }
            }
            th = null;
            this.upstream.cancel();
            if (null != th) {
                this.downstream.onError(new CompositeException(th, th));
                return;
            } else {
                this.downstream.onError(th);
                return;
            }
        }
        this.downstream.onError(th);
        this.upstream.cancel();
        disposeAfter();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.eager) {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.downstream.onError(th);
                    return;
                }
            }
            this.upstream.cancel();
            this.downstream.onComplete();
            return;
        }
        this.downstream.onComplete();
        this.upstream.cancel();
        disposeAfter();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        this.upstream.request(j2);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        disposeAfter();
        this.upstream.cancel();
    }

    void disposeAfter() {
        if (compareAndSet(false, true)) {
            try {
                this.disposer.accept(this.resource);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }
    }
}
