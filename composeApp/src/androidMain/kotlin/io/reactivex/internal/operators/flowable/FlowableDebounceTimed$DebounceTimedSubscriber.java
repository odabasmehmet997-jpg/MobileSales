package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;



final class FlowableDebounceTimedDebounceTimedSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -9102637559663639004L;
    boolean done;
    final Subscriber<? super T> downstream;
    volatile long index;
    final long timeout;
    Disposable timer;
    final TimeUnit unit;
    Subscription upstream;
    final Scheduler.Worker worker;

    FlowableDebounceTimedDebounceTimedSubscriber(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
        this.downstream = subscriber;
        this.timeout = j2;
        this.unit = timeUnit;
        this.worker = worker;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        long j2 = this.index + 1;
        this.index = j2;
        Disposable disposable = this.timer;
        if (null != disposable) {
            disposable.dispose();
        }
        FlowableDebounceTimedDebounceEmitter flowableDebounceTimedDebounceEmitter = new FlowableDebounceTimedDebounceEmitter(t, j2, this);
        this.timer = flowableDebounceTimedDebounceEmitter;
        flowableDebounceTimedDebounceEmitter.setResource(this.worker.schedule(flowableDebounceTimedDebounceEmitter, this.timeout, this.unit));
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        Disposable disposable = this.timer;
        if (null != disposable) {
            disposable.dispose();
        }
        this.downstream.onError(th);
        this.worker.dispose();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        Disposable disposable = this.timer;
        if (null != disposable) {
            disposable.dispose();
        }
        FlowableDebounceTimedDebounceEmitter flowableDebounceTimedDebounceEmitter = (FlowableDebounceTimedDebounceEmitter) disposable;
        if (null != flowableDebounceTimedDebounceEmitter) {
            flowableDebounceTimedDebounceEmitter.emit();
        }
        this.downstream.onComplete();
        this.worker.dispose();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.upstream.cancel();
        this.worker.dispose();
    }

    void emit(long j2, T t, FlowableDebounceTimedDebounceEmitter<T> flowableDebounceTimedDebounceEmitter) {
        if (j2 == this.index) {
            if (0 != this.get()) {
                this.downstream.onNext(t);
                BackpressureHelper.produced(this, 1L);
                flowableDebounceTimedDebounceEmitter.dispose();
            } else {
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            }
        }
    }
}
