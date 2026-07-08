package io.reactivex.internal.subscriptions;

import io.reactivex.internal.fuseable.QueueSubscription;
import org.reactivestreams.Subscriber;

public enum EmptySubscription implements QueueSubscription<Object> {
    INSTANCE;
    public void cancel() {
    }
    public void clear() {
    }
    public boolean isEmpty() {
        return true;
    }
    public Object poll() {
        return null;
    }
    public int requestFusion(int i2) {
        return i2 & 2;
    }
    public void request(long j2) {
        SubscriptionHelper.validate(j2);
    }
    public String toString() {
        return "EmptySubscription";
    }
    public static void error(Throwable th, Subscriber<?> subscriber) {
        subscriber.onSubscribe(INSTANCE);
        subscriber.onError(th);
    }
    public static void complete(Subscriber<?> subscriber) {
        subscriber.onSubscribe(INSTANCE);
        subscriber.onComplete();
    }
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }
    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
