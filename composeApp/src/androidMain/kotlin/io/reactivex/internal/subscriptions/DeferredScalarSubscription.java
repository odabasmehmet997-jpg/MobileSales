package io.reactivex.internal.subscriptions;

import org.reactivestreams.Subscriber;

public class DeferredScalarSubscription<T> extends BasicIntQueueSubscription<T> {
    static final int CANCELLED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 2;
    static final int NO_REQUEST_HAS_VALUE = 1;
    static final int NO_REQUEST_NO_VALUE = 0;
    private static final long serialVersionUID = -2151279923272604993L;
    protected final Subscriber<? super T> downstream;
    protected T value;
    public DeferredScalarSubscription(Subscriber<? super T> subscriber) {
        this.downstream = subscriber;
    }
    public final void request(long j2) {
        T t;
        if (SubscriptionHelper.validate(j2)) {
            do {
                int i2 = get ();
                if (0 != (i2 & (-2))) {
                    return;
                }
                if (1 == i2) {
                    if (!compareAndSet (1, 3) || null == (t = value)) {
                        return;
                    }
                    this.value = null;
                    Subscriber<? super T> subscriber = this.downstream;
                    subscriber.onNext(t);
                    if (4 != this.get()) {
                        subscriber.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet (0, 2));
        }
    }
    public final void complete(T t) {
        int i2 = get ();
        while (8 != i2) {
            if (0 != (i2 & (-3))) {
                return;
            }
            if (2 == i2) {
                lazySet (3);
                Subscriber<? super T> subscriber = this.downstream;
                subscriber.onNext(t);
                if (4 != this.get()) {
                    subscriber.onComplete();
                    return;
                }
                return;
            }
            this.value = t;
            if (compareAndSet (0, 1)) {
                return;
            }
            i2 = get ();
            if (4 == i2) {
                this.value = null;
                return;
            }
        }
        this.value = t;
        lazySet (16);
        Subscriber<? super T> subscriber2 = this.downstream;
        subscriber2.onNext(t);
        if (4 != this.get()) {
            subscriber2.onComplete();
        }
    }
    public final int requestFusion(int i2) {
        if (0 == (i2 & 2)) {
            return 0;
        }
        lazySet (8);
        return 2;
    }
    public final T poll() {
        if (16 != this.get()) {
            return null;
        }
        lazySet (32);
        T t = this.value;
        this.value = null;
        return t;
    }
    public final boolean isEmpty() {
        return 16 != this.get();
    }
    public final void clear() {
        lazySet (32);
        this.value = null;
    }
    public void cancel() {
        set (4);
        this.value = null;
    }
    public final boolean isCancelled() {
        return 4 == this.get();
    }
    public final boolean tryCancel() {
        return 4 != this.getAndSet(4);
    }
}
