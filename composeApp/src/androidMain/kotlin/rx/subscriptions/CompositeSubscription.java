package rx.subscriptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }
    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(4);
                    }
                    this.subscriptions.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }
    public void remove(Subscription subscription) {
        Set<Subscription> set;
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (!this.unsubscribed && (set = this.subscriptions) != null) {
                boolean zRemove = set.remove(subscription);
                if (zRemove) {
                    subscription.unsubscribe();
                }
            }
        }
    }
    public void unsubscribe() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            try {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                Set<Subscription> set = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(set);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    private static void unsubscribeFromAll(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        Iterator<Subscription> it = collection.iterator();
        ArrayList<Throwable> exceptions = null;
        while (it.hasNext()) {
            try {
                it.next().unsubscribe();
            } catch (Throwable th) {
                if (exceptions == null) {
                    exceptions = new ArrayList<>();
                }
                exceptions.add(th);
            }
        }
        Exceptions.throwIfAny(exceptions);
    }
}
