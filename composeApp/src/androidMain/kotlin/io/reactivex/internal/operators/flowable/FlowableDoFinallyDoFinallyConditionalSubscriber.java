package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

final class FlowableDoFinallyDoFinallyConditionalSubscriber<T> extends BasicIntQueueSubscription<T> implements ConditionalSubscriber<T> {
    private static final long serialVersionUID = 4109457741734051389L;
    final ConditionalSubscriber<? super T> downstream;
    final Action onFinally;
    QueueSubscription<T> qs;
    boolean syncFused;
    Subscription upstream;
    FlowableDoFinallyDoFinallyConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Action action) {
        this.downstream = conditionalSubscriber;
        this.onFinally = action;
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (subscription instanceof QueueSubscription) {
                this.qs = (QueueSubscription) subscription;
            }
            this.downstream.onSubscribe(this);
        }
    }
    public void onNext(Object t) {
        this.downstream.onNext(t);
    }
    public boolean tryOnNext(T t) {
        return this.downstream.tryOnNext(t);
    }
    public void onError(Throwable th) {
        this.downstream.onError(th);
        runFinally();
    }
    public void onComplete() {
        this.downstream.onComplete();
        runFinally();
    }
    public void cancel() {
        this.upstream.cancel();
        runFinally();
    }
    public void request(long j2) {
        this.upstream.request(j2);
    }
    public int requestFusion(int i2) {
        QueueSubscription<T> queueSubscription = this.qs;
        if (null == queueSubscription || 0 != (i2 & 4)) {
            return 0;
        }
        int requestFusion = queueSubscription.requestFusion(i2);
        if (0 != requestFusion) {
            this.syncFused = 1 == requestFusion;
        }
        return requestFusion;
    }
    public void clear() {
        this.qs.clear();
    }
    public boolean isEmpty() {
        return this.qs.isEmpty();
    }
    public T poll() throws Exception {
        T poll = this.qs.poll();
        if (null == poll && this.syncFused) {
            runFinally();
        }
        return poll;
    }
    void runFinally() {
        if (this.compareAndSet(0, 1)) {
            try {
                this.onFinally.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }
    }
}
