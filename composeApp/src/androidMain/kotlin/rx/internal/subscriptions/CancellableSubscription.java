package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Cancellable;
import rx.plugins.RxJavaHooks;

public final class CancellableSubscription extends AtomicReference<Cancellable> implements Subscription {
    private static final long serialVersionUID = 5718521705281392066L;
    public CancellableSubscription(Cancellable cancellable) {
        super(cancellable);
    }
    public boolean isUnsubscribed() {
        return get() == null;
    }
    public void unsubscribe() {
        Cancellable andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        try {
            andSet.cancel();
        } catch (Exception e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaHooks.onError(e2);
        }
    }
}
