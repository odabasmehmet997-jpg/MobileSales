package io.reactivex.internal.subscriptions;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class SubscriptionArbiter extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = -2189523197179400958L;
    Subscription actual;
    final boolean cancelOnReplace;
    volatile boolean cancelled;
    long requested;
    protected boolean unbounded;
    final AtomicReference<Subscription> missedSubscription = new AtomicReference<>();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicLong missedProduced = new AtomicLong();
    public SubscriptionArbiter(boolean z) {
        this.cancelOnReplace = z;
    }
    public final void setSubscription(Subscription subscription) {
        if (this.cancelled) {
            subscription.cancel();
            return;
        }
        ObjectHelper.requireNonNull(subscription, "s is null");
        if (0 == this.get() && compareAndSet (0, 1)) {
            Subscription subscription2 = this.actual;
            if (null != subscription2 && this.cancelOnReplace) {
                subscription2.cancel();
            }
            this.actual = subscription;
            long j2 = this.requested;
            if (0 != this.decrementAndGet()) {
                drainLoop ();
            }
            if (0 != j2) {
                subscription.request(j2);
                return;
            }
            return;
        }
        Subscription andSet = this.missedSubscription.getAndSet(subscription);
        if (null != andSet && this.cancelOnReplace) {
            andSet.cancel();
        }
        drain ();
    }
    public final void request(long j2) {
        if (!SubscriptionHelper.validate(j2) || this.unbounded) {
            return;
        }
        if (0 == this.get() && compareAndSet (0, 1)) {
            long j3 = this.requested;
            if (LocationRequestCompat.PASSIVE_INTERVAL != j3) {
                long addCap = BackpressureHelper.addCap(j3, j2);
                this.requested = addCap;
                if (LocationRequestCompat.PASSIVE_INTERVAL == addCap) {
                    this.unbounded = true;
                }
            }
            Subscription subscription = this.actual;
            if (0 != this.decrementAndGet()) {
                drainLoop ();
            }
            if (null != subscription) {
                subscription.request(j2);
                return;
            }
            return;
        }
        BackpressureHelper.add(this.missedRequested, j2);
        drain ();
    }
    public final void produced(long j2) {
        if (this.unbounded) {
            return;
        }
        if (0 == this.get() && compareAndSet (0, 1)) {
            long j3 = this.requested;
            if (LocationRequestCompat.PASSIVE_INTERVAL != j3) {
                long j4 = j3 - j2;
                if (0 > j4) {
                    SubscriptionHelper.reportMoreProduced(j4);
                    j4 = 0;
                }
                this.requested = j4;
            }
            if (0 == this.decrementAndGet()) {
                return;
            }
            drainLoop ();
            return;
        }
        BackpressureHelper.add(this.missedProduced, j2);
        drain ();
    }
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        drain ();
    }
    final void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        drainLoop ();
    }
    final void drainLoop() {
        int i2 = 1;
        long j2 = 0;
        Subscription subscription = null;
        do {
            Subscription subscription2 = this.missedSubscription.get();
            if (null != subscription2) {
                subscription2 = this.missedSubscription.getAndSet(null);
            }
            long j3 = this.missedRequested.get();
            if (0 != j3) {
                j3 = this.missedRequested.getAndSet(0L);
            }
            long j4 = this.missedProduced.get();
            if (0 != j4) {
                j4 = this.missedProduced.getAndSet(0L);
            }
            Subscription subscription3 = this.actual;
            if (this.cancelled) {
                if (null != subscription3) {
                    subscription3.cancel();
                    this.actual = null;
                }
                if (null != subscription2) {
                    subscription2.cancel();
                }
            } else {
                long j5 = this.requested;
                if (LocationRequestCompat.PASSIVE_INTERVAL != j5) {
                    j5 = BackpressureHelper.addCap(j5, j3);
                    if (LocationRequestCompat.PASSIVE_INTERVAL != j5) {
                        j5 -= j4;
                        if (0 > j5) {
                            SubscriptionHelper.reportMoreProduced(j5);
                            j5 = 0;
                        }
                    }
                    this.requested = j5;
                }
                if (null != subscription2) {
                    if (null != subscription3 && this.cancelOnReplace) {
                        subscription3.cancel();
                    }
                    this.actual = subscription2;
                    if (0 != j5) {
                        j2 = BackpressureHelper.addCap(j2, j5);
                        subscription = subscription2;
                    }
                } else if (null != subscription3 && 0 != j3) {
                    j2 = BackpressureHelper.addCap(j2, j3);
                    subscription = subscription3;
                }
            }
            i2 = addAndGet (-i2);
        } while (0 != i2);
        if (0 != j2) {
            subscription.request(j2);
        }
    }
    public final boolean isUnbounded() {
        return this.unbounded;
    }
    public final boolean isCancelled() {
        return this.cancelled;
    }
}
