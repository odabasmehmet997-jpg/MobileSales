package io.reactivex.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;


public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.setOnce(this.upstream, subscription, getClass())) {
            onStart();
        }
    }
    protected void onStart() {
        this.upstream.get().request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
    protected final void cancel() {
        dispose();
    }
    public final boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == upstream.get();
    }
    public final void dispose() {
        SubscriptionHelper.cancel(this.upstream);
    }
}
