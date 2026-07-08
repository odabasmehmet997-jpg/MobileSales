package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableTimerTimerSubscriber extends AtomicReference<Disposable> implements Subscription, Runnable {
    private static final long serialVersionUID = -2809475196591179431L;
    final Subscriber<? super Long> downstream;
    volatile boolean requested;

    FlowableTimerTimerSubscriber(Subscriber<? super Long> subscriber) {
        this.downstream = subscriber;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            this.requested = true;
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        DisposableHelper.dispose(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (DisposableHelper.DISPOSED != this.get()) {
            if (this.requested) {
                this.downstream.onNext(0L);
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
            } else {
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onError(new MissingBackpressureException("Can't deliver value due to lack of requests"));
            }
        }
    }

    public void setResource(Disposable disposable) {
        DisposableHelper.trySet(this, disposable);
    }
}
