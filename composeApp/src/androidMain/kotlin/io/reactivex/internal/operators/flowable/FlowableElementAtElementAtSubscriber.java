package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.NoSuchElementException;

final class FlowableElementAtElementAtSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 4066607327284737757L;
    long count;
    final T defaultValue;
    boolean done;
    final boolean errorOnFewer;
    final long index;
    Subscription upstream;
    FlowableElementAtElementAtSubscriber(Subscriber<? super T> subscriber, long j2, T t, boolean z) {
        super(subscriber);
        this.index = j2;
        this.defaultValue = t;
        this.errorOnFewer = z;
    } 
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    } 
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        long j2 = this.count;
        if (j2 == this.index) {
            this.done = true;
            this.upstream.cancel();
            this.complete((T) t);
            return;
        }
        this.count = j2 + 1;
    } 
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
        } else {
            this.done = true;
            downstream.onError(th);
        }
    } 
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        T t = this.defaultValue;
        if (null == t) {
            if (this.errorOnFewer) {
                downstream.onError(new NoSuchElementException());
                return;
            } else {
                downstream.onComplete();
                return;
            }
        }
        this.complete(t);
    } 
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
