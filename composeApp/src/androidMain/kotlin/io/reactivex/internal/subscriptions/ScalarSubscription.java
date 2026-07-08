package io.reactivex.internal.subscriptions;

import io.reactivex.internal.fuseable.QueueSubscription;
import org.reactivestreams.Subscriber;
import java.util.concurrent.atomic.AtomicInteger;

public final class ScalarSubscription<T> extends AtomicInteger implements QueueSubscription<T> {
    static final int CANCELLED = 2;
    static final int NO_REQUEST = 0;
    static final int REQUESTED = 1;
    private static final long serialVersionUID = -3830916580126663321L;
    final Subscriber<? super T> subscriber;
    final T value;
    public int requestFusion(int i2) {
        return i2 & 1;
    }
    public ScalarSubscription(Subscriber<? super T> subscriber, T t) {
        this.subscriber = subscriber;
        this.value = t;
    }
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2) && compareAndSet (0, 1)) {
            Subscriber<? super T> subscriber = this.subscriber;
            subscriber.onNext(this.value);
            if (2 != this.get()) {
                subscriber.onComplete();
            }
        }
    }
    public void cancel() {
        lazySet (2);
    }
    public boolean isCancelled() {
        return 2 == this.get();
    }
    public boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }
    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
    public T poll() {
        if (0 != this.get()) {
            return null;
        }
        lazySet (1);
        return this.value;
    }
    public boolean isEmpty() {
        return 0 != this.get();
    }
    public void clear() {
        lazySet (1);
    }
}
