package io.reactivex.internal.subscriptions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReferenceArray;



public final class ArrayCompositeSubscription extends AtomicReferenceArray<Subscription> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;
    public ArrayCompositeSubscription(int i2) {
        super(i2);
    }
    public boolean setResource(int i2, Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = get (i2);
            if (SubscriptionHelper.CANCELLED == subscription2) {
                if (null == subscription) {
                    return false;
                }
                subscription.cancel();
                return false;
            }
        } while (!compareAndSet (i2, subscription2, subscription));
        if (null == subscription2) {
            return true;
        }
        subscription2.cancel();
        return true;
    }
    public Subscription replaceResource(int i2, Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = get (i2);
            if (SubscriptionHelper.CANCELLED == subscription2) {
                if (null == subscription) {
                    return null;
                }
                subscription.cancel();
                return null;
            }
        } while (!compareAndSet (i2, subscription2, subscription));
        return subscription2;
    }
    public void dispose() {
        Subscription andSet;
        if (SubscriptionHelper.CANCELLED != this.get(0)) {
            int length = length ();
            for (int i2 = 0; i2 < length; i2++) {
                Subscription subscription = get (i2);
                final SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
                if (subscriptionHelper != subscription && subscriptionHelper != (andSet = this.getAndSet(i2, subscriptionHelper)) && null != andSet) {
                    andSet.cancel();
                }
            }
        }
    }
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == this.get(0);
    }
    public void onError(Throwable th) {

    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
}
