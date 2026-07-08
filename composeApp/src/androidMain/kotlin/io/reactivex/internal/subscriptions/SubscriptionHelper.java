package io.reactivex.internal.subscriptions;

import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



public enum SubscriptionHelper implements Subscription {
    CANCELLED;
    public void cancel() {
    }
    public void request(long j2) {
    }
    public static boolean validate(Subscription subscription, Subscription subscription2) {
        if (null == subscription2) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        }
        if (null == subscription) {
            return true;
        }
        subscription2.cancel();
        reportSubscriptionSet ();
        return false;
    }
    public static void reportSubscriptionSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Subscription already set!"));
    }
    public static boolean validate(long j2) {
        if (0 < j2) {
            return true;
        }
        RxJavaPlugins.onError(new IllegalArgumentException("n > 0 required but it was " + j2));
        return false;
    }
    public static void reportMoreProduced(long j2) {
        RxJavaPlugins.onError(new ProtocolViolationException("More produced than requested: " + j2));
    }
    public static boolean set(AtomicReference<Subscription> atomicReference, Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = atomicReference.get();
            if (CANCELLED == subscription2) {
                if (null == subscription) {
                    return false;
                }
                subscription.cancel();
                return false;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, subscription2, subscription));
        if (null == subscription2) {
            return true;
        }
        subscription2.cancel();
        return true;
    }
    public static boolean setOnce(AtomicReference<Subscription> atomicReference, Subscription subscription) {
        ObjectHelper.requireNonNull(subscription, "s is null");
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, null, subscription)) {
            return true;
        }
        subscription.cancel();
        if (CANCELLED == atomicReference.get ()) {
            return false;
        }
        reportSubscriptionSet ();
        return false;
    }
    public static boolean replace(AtomicReference<Subscription> atomicReference, Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = atomicReference.get();
            if (CANCELLED == subscription2) {
                if (null == subscription) {
                    return false;
                }
                subscription.cancel();
                return false;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, subscription2, subscription));
        return true;
    }
    public static boolean cancel(AtomicReference<Subscription> atomicReference) {
        Subscription andSet;
        Subscription subscription = atomicReference.get();
        final SubscriptionHelper subscriptionHelper = CANCELLED;
        if (subscriptionHelper == subscription || subscriptionHelper == (andSet = atomicReference.getAndSet (subscriptionHelper))) {
            return false;
        }
        if (null == andSet) {
            return true;
        }
        andSet.cancel();
        return true;
    }
    public static boolean deferredSetOnce(AtomicReference<Subscription> atomicReference, AtomicLong atomicLong, Subscription subscription) {
        if (!setOnce (atomicReference, subscription)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0L);
        if (0 == andSet) {
            return true;
        }
        subscription.request(andSet);
        return true;
    }
    public static void deferredRequest(AtomicReference<Subscription> atomicReference, AtomicLong atomicLong, long j2) {
        Subscription subscription = atomicReference.get();
        if (null != subscription) {
            subscription.request(j2);
            return;
        }
        if (validate (j2)) {
            BackpressureHelper.add(atomicLong, j2);
            Subscription subscription2 = atomicReference.get();
            if (null != subscription2) {
                long andSet = atomicLong.getAndSet(0L);
                if (0 != andSet) {
                    subscription2.request(andSet);
                }
            }
        }
    }
    public static boolean setOnce(AtomicReference<Subscription> atomicReference, Subscription subscription, long j2) {
        if (!setOnce (atomicReference, subscription)) {
            return false;
        }
        subscription.request(j2);
        return true;
    }
}
