package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;

final class FlowableConcatMapSimpleScalarSubscription<T> extends AtomicBoolean implements Subscription {
    final Subscriber<? super T> downstream;
    final T value; 
    public void cancel() {
    }
    FlowableConcatMapSimpleScalarSubscription(T t, Subscriber<? super T> subscriber) {
        this.value = t;
        this.downstream = subscriber;
    } 
    public void request(long j2) {
        if (0 >= j2 || !compareAndSet(false, true)) {
            return;
        }
        Subscriber<? super T> subscriber = this.downstream;
        subscriber.onNext(this.value);
        subscriber.onComplete();
    }
}
