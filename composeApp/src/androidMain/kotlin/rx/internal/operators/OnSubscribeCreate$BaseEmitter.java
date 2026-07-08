package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.subscriptions.SerialSubscription;


abstract class OnSubscribeCreate$BaseEmitter<T> extends AtomicLong implements Observer, Producer, Subscription {
    private static final long serialVersionUID = 7326289992464377023L;
    final Subscriber<? super T> actual;
    final SerialSubscription serial = new SerialSubscription();

    @Override // rx.Observer
    public abstract /* synthetic */ void onNext(Object obj);

    void onRequested() {
    }

    void onUnsubscribed() {
    }

    public OnSubscribeCreate$BaseEmitter(Subscriber<? super T> subscriber) {
        this.actual = subscriber;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.actual.isUnsubscribed()) {
            return;
        }
        try {
            this.actual.onCompleted();
        } finally {
            this.serial.unsubscribe();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.actual.isUnsubscribed()) {
            return;
        }
        try {
            this.actual.onError(th);
        } finally {
            this.serial.unsubscribe();
        }
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.serial.unsubscribe();
        onUnsubscribed();
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.serial.isUnsubscribed();
    }

    @Override // rx.Producer
    public final void request(long j2) {
        if (BackpressureUtils.validate(j2)) {
            BackpressureUtils.getAndAddRequest(this, j2);
            onRequested();
        }
    }

    public final void setSubscription(Subscription subscription) {
        this.serial.set(subscription);
    }

    public final void setCancellation(Cancellable cancellable) {
        setSubscription(new CancellableSubscription(cancellable));
    }

    public final long requested() {
        return get();
    }
}
