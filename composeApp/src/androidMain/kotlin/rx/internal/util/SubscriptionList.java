package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class SubscriptionList implements Subscription {
    private List<Subscription> subscriptions;
    private volatile boolean unsubscribed;
    public SubscriptionList() {
    }
    public SubscriptionList(Subscription... subscriptionArr) {
        this.subscriptions = new LinkedList(Arrays.asList(subscriptionArr));
    }
    public SubscriptionList(Subscription subscription) {
        LinkedList linkedList = new LinkedList();
        this.subscriptions = linkedList;
        linkedList.add(subscription);
    }
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
                    List linkedList = this.subscriptions;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.subscriptions = linkedList;
                    }
                    linkedList.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }
    public void remove(Subscription subscription) {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            List<Subscription> list = this.subscriptions;
            if (!this.unsubscribed && list != null) {
                boolean zRemove = list.remove(subscription);
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
                List<Subscription> list = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(list);
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
