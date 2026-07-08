package io.reactivex.internal.operators.parallel;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class ParallelReduceFullParallelReduceFullInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -7954444275102466525L;
    boolean done;
    final ParallelReduceFullParallelReduceFullMainSubscriber<T> parent;
    final BiFunction<T, T, T> reducer;
    T value;

    ParallelReduceFullParallelReduceFullInnerSubscriber(ParallelReduceFullParallelReduceFullMainSubscriber<T> parallelReduceFullParallelReduceFullMainSubscriber, BiFunction<T, T, T> biFunction) {
        this.parent = parallelReduceFullParallelReduceFullMainSubscriber;
        this.reducer = biFunction;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        T t2 = this.value;
        if (null == t2) {
            this.value = t;
            return;
        }
        try {
            this.value = ObjectHelper.requireNonNull(this.reducer.apply(t2, t), "The reducer returned a null value");
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            get().cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
        } else {
            this.done = true;
            this.parent.innerError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.parent.innerComplete(this.value);
    }

    void cancel() {
        SubscriptionHelper.cancel(this);
    }
}
