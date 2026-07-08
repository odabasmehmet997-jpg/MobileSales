package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeConcatArrayConcatMaybeObserver<T> extends AtomicInteger implements MaybeObserver<T>, Subscription {
    private static final long serialVersionUID = 3520831347801429610L;
    final Subscriber<? super T> downstream;
    int index;
    long produced;
    final MaybeSource<? extends T>[] sources;
    final AtomicLong requested = new AtomicLong();
    final SequentialDisposable disposables = new SequentialDisposable();
    final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);

    MaybeConcatArrayConcatMaybeObserver(final Subscriber<? super T> subscriber, final MaybeSource<? extends T>[] maybeSourceArr) {
        downstream = subscriber;
        sources = maybeSourceArr;
    }

    @Override // org.reactivestreams.Subscription
    public void request(final long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(requested, j2);
            this.drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        disposables.dispose();
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(final Disposable disposable) {
        disposables.replace(disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(final T t) {
        current.lazySet(t);
        this.drain();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(final Throwable th) {
        downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        current.lazySet(NotificationLite.COMPLETE);
        this.drain();
    }

    void drain() {
        if (0 != getAndIncrement()) {
            return;
        }
        final AtomicReference<Object> atomicReference = current;
        final Subscriber<? super T> subscriber = downstream;
        final SequentialDisposable sequentialDisposable = disposables;
        while (!sequentialDisposable.isDisposed()) {
            final Object obj = atomicReference.get();
            if (null != obj) {
                if (NotificationLite.COMPLETE != obj) {
                    final long j2 = produced;
                    if (j2 != requested.get()) {
                        produced = j2 + 1;
                        atomicReference.lazySet(null);
                        subscriber.onNext(obj);
                    }
                } else {
                    atomicReference.lazySet(null);
                }
                if (!sequentialDisposable.isDisposed()) {
                    final int i2 = index;
                    final MaybeSource<? extends T>[] maybeSourceArr = sources;
                    if (i2 == maybeSourceArr.length) {
                        subscriber.onComplete();
                        return;
                    } else {
                        index = i2 + 1;
                        maybeSourceArr[i2].subscribe(this);
                    }
                }
            }
            if (0 == decrementAndGet()) {
                return;
            }
        }
        atomicReference.lazySet(null);
    }
}
