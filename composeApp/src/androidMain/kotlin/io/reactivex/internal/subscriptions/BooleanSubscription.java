package io.reactivex.internal.subscriptions;

import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;

public final class BooleanSubscription extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = -8127758972444290902L;
    public void request(long j2) {
        SubscriptionHelper.validate(j2);
    }
    public void cancel() {
        lazySet (true);
    }
    public boolean isCancelled() {
        return get ();
    }
    public String toString() {
        return "BooleanSubscription(cancelled=" + get () + ")";
    }
}
