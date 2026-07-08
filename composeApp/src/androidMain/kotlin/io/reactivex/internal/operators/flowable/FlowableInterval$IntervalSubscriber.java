package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableIntervalIntervalSubscriber extends AtomicLong implements Subscription, Runnable {
    private static final long serialVersionUID = -2809475196591179431L;
    long count;
    final Subscriber<? super Long> downstream;
    final AtomicReference<Disposable> resource = new AtomicReference<>();

    FlowableIntervalIntervalSubscriber(Subscriber<? super Long> subscriber) {
        this.downstream = subscriber;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        DisposableHelper.dispose(this.resource);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (DisposableHelper.DISPOSED != resource.get()) {
            if (0 != this.get()) {
                Subscriber<? super Long> subscriber = this.downstream;
                long j2 = this.count;
                this.count = j2 + 1;
                subscriber.onNext(Long.valueOf(j2));
                BackpressureHelper.produced(this, 1L);
                return;
            }
            this.downstream.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
            DisposableHelper.dispose(this.resource);
        }
    }

    public void setResource(Disposable disposable) {
        DisposableHelper.setOnce(this.resource, disposable);
    }
}
