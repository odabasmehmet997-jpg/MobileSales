package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.subscriptions.SequentialSubscription;
import rx.plugins.RxJavaHooks;
 
final class CompletableFromEmitterFromEmitter extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = 5539301318568668881L;
    final CompletableSubscriber actual;
    final SequentialSubscription resource = new SequentialSubscription();
    public CompletableFromEmitterFromEmitter(CompletableSubscriber completableSubscriber) {
        this.actual = completableSubscriber;
    }
    public void onCompleted() {
        if (compareAndSet(false, true)) {
            try {
                this.actual.onCompleted();
            } finally {
                this.resource.unsubscribe();
            }
        }
    }
    public void onError(Throwable th) {
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
    public void unsubscribe() {
        if (compareAndSet(false, true)) {
            this.resource.unsubscribe();
        }
    } 
    public boolean isUnsubscribed() {
        return get();
    }
}
