package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.subscriptions.SequentialSubscription;
import rx.plugins.RxJavaHooks;


final class SingleFromEmitter$SingleEmitterImpl<T> extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = 8082834163465882809L;
    final SingleSubscriber<? super T> actual;
    final SequentialSubscription resource = new SequentialSubscription();

    SingleFromEmitter$SingleEmitterImpl(SingleSubscriber<? super T> singleSubscriber) {
        this.actual = singleSubscriber;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (compareAndSet(false, true)) {
            this.resource.unsubscribe();
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get();
    }

    public void onSuccess(T t) {
        if (compareAndSet(false, true)) {
            try {
                this.actual.onSuccess(t);
            } finally {
                this.resource.unsubscribe();
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        if (compareAndSet(false, true)) {
            try {
                this.actual.onError(th);
                return;
            } finally {
                this.resource.unsubscribe();
            }
        }
        RxJavaHooks.onError(th);
    }

    public void setSubscription(Subscription subscription) {
        this.resource.update(subscription);
    }

    public void setCancellation(Cancellable cancellable) {
        setSubscription(new CancellableSubscription(cancellable));
    }
}
