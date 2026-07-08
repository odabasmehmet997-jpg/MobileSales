package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableIntervalRangeIntervalRangeSubscriber extends AtomicLong implements Subscription, Runnable {
    private static final long serialVersionUID = -2809475196591179431L;
    long count;
    final Subscriber<? super Long> downstream;
    final long end;
    final AtomicReference<Disposable> resource = new AtomicReference<>();

    FlowableIntervalRangeIntervalRangeSubscriber(Subscriber<? super Long> subscriber, long j2, long j3) {
        this.downstream = subscriber;
        this.count = j2;
        this.end = j3;
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
        Disposable disposable = this.resource.get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable) {
            long j2 = get();
            if (0 != j2) {
                long j3 = this.count;
                this.downstream.onNext(Long.valueOf(j3));
                if (j3 == this.end) {
                    if (disposableHelper != resource.get()) {
                        this.downstream.onComplete();
                    }
                    DisposableHelper.dispose(this.resource);
                    return;
                } else {
                    this.count = j3 + 1;
                    if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                        decrementAndGet();
                        return;
                    }
                    return;
                }
            }
            this.downstream.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
            DisposableHelper.dispose(this.resource);
        }
    }

    public void setResource(Disposable disposable) {
        DisposableHelper.setOnce(this.resource, disposable);
    }
}
