package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class MaybeConcatIterableConcatMaybeObserver<T> extends AtomicInteger implements MaybeObserver<T>, Subscription {
    private static final long serialVersionUID = 3520831347801429610L;
    final Subscriber<? super T> downstream;
    long produced;
    final Iterator<? extends MaybeSource<? extends T>> sources;
    final AtomicLong requested = new AtomicLong();
    final SequentialDisposable disposables = new SequentialDisposable();
    final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);

    MaybeConcatIterableConcatMaybeObserver(Subscriber<? super T> subscriber, Iterator<? extends MaybeSource<? extends T>> it) {
        this.downstream = subscriber;
        this.sources = it;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.disposables.dispose();
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        this.disposables.replace(disposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        this.current.lazySet(t);
        drain();
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.current.lazySet(NotificationLite.COMPLETE);
        drain();
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        AtomicReference<Object> atomicReference = this.current;
        Subscriber<? super T> subscriber = this.downstream;
        SequentialDisposable sequentialDisposable = this.disposables;
        while (!sequentialDisposable.isDisposed()) {
            Object obj = atomicReference.get();
            if (null != obj) {
                if (NotificationLite.COMPLETE != obj) {
                    long j2 = this.produced;
                    if (j2 != this.requested.get()) {
                        this.produced = j2 + 1;
                        atomicReference.lazySet(null);
                        subscriber.onNext(obj);
                    }
                } else {
                    atomicReference.lazySet(null);
                }
                if (!sequentialDisposable.isDisposed()) {
                    try {
                        if (this.sources.hasNext()) {
                            try {
                                ObjectHelper.requireNonNull(this.sources.next(), "The source Iterator returned a null MaybeSource").subscribe(this);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                subscriber.onError(th);
                                return;
                            }
                        } else {
                            subscriber.onComplete();
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        subscriber.onError(th2);
                        return;
                    }
                }
            }
            if (0 == this.decrementAndGet()) {
                return;
            }
        }
        atomicReference.lazySet(null);
    }
}
