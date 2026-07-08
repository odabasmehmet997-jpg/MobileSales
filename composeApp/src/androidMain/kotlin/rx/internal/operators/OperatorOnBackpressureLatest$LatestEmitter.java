package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;


final class OperatorOnBackpressureLatest$LatestEmitter<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
    static final Object EMPTY = new Object();
    static final long NOT_REQUESTED = -4611686018427387904L;
    private static final long serialVersionUID = -1364393685005146274L;
    final Subscriber<? super T> child;
    volatile boolean done;
    boolean emitting;
    boolean missed;
    OperatorOnBackpressureLatest$LatestSubscriber<? super T> parent;
    Throwable terminal;
    final AtomicReference<Object> value = new AtomicReference<>(EMPTY);

    public OperatorOnBackpressureLatest$LatestEmitter(Subscriber<? super T> subscriber) {
        this.child = subscriber;
        lazySet(NOT_REQUESTED);
    }

    @Override // rx.Producer
    public void request(long j2) throws Throwable {
        long j3;
        long j4;
        if (j2 >= 0) {
            do {
                j3 = get();
                if (j3 == Long.MIN_VALUE) {
                    return;
                }
                if (j3 == NOT_REQUESTED) {
                    j4 = j2;
                } else {
                    j4 = j3 + j2;
                    if (j4 < 0) {
                        j4 = Long.MAX_VALUE;
                    }
                }
            } while (!compareAndSet(j3, j4));
            if (j3 == NOT_REQUESTED) {
                this.parent.requestMore(LocationRequestCompat.PASSIVE_INTERVAL);
            }
            emit();
        }
    }

    long produced(long j2) {
        long j3;
        long j4;
        do {
            j3 = get();
            if (j3 < 0) {
                return j3;
            }
            j4 = j3 - j2;
        } while (!compareAndSet(j3, j4));
        return j4;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get() == Long.MIN_VALUE;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (get() >= 0) {
            getAndSet(Long.MIN_VALUE);
        }
    }

    @Override // rx.Observer
    public void onNext(Object t) throws Throwable {
        this.value.lazySet(t);
        emit();
    }

    @Override // rx.Observer
    public void onError(Throwable th) throws Throwable {
        this.terminal = th;
        this.done = true;
        emit();
    }

    @Override // rx.Observer
    public void onCompleted() throws Throwable {
        this.done = true;
        emit();
    }

    void emit() throws Throwable {
        boolean z;
        Object obj;
        synchronized (this) {
            boolean z2 = true;
            if (this.emitting) {
                this.missed = true;
                return;
            }
            this.emitting = true;
            this.missed = false;
            while (true) {
                try {
                    long j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    Object obj2 = this.value.get();
                    if (j2 > 0 && obj2 != (obj = EMPTY)) {
                        this.child.onNext(obj2);
                        LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.value, obj2, obj);
                        produced(1L);
                        obj2 = obj;
                    }
                    if (obj2 == EMPTY && this.done) {
                        Throwable th = this.terminal;
                        if (th != null) {
                            this.child.onError(th);
                        } else {
                            this.child.onCompleted();
                        }
                    }
                    try {
                        synchronized (this) {
                            try {
                                if (!this.missed) {
                                    this.emitting = false;
                                    return;
                                }
                                this.missed = false;
                            } catch (Throwable th2) {
                                th = th2;
                                z2 = false;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    try {
                        throw th;
                    } catch (Throwable th4) {
                        z = z2;
                        th = th4;
                        if (z) {
                            throw th;
                        }
                        synchronized (this) {
                            this.emitting = false;
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    z = false;
                }
            }
        }
    }
}
