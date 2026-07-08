package io.reactivex.observers;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.internal.util.EndConsumerHelper;

import java.util.concurrent.atomic.AtomicReference;


public class TestObserver<T> extends BaseTestConsumer<T, TestObserver<T>> implements Observer<T>, MaybeObserver<T>, SingleObserver<T>, CompletableObserver {
    private final Observer<? super T> downstream;
    private QueueDisposable<T> qd;
    private final AtomicReference<Disposable> upstream;
    enum EmptyObserver implements Observer<Object> {
        INSTANCE;
        public void onComplete() {
        }
        public void onError(final Throwable th) {
        }
        public void onNext(final Object obj) {
        }
        public void onSubscribe(final Disposable disposable) {
        }
    }
    public TestObserver() {
        this(EmptyObserver.INSTANCE);
    }
    public TestObserver(final Observer<? super T> observer) {
        upstream = new AtomicReference<>();
        downstream = observer;
    }
    public void onSubscribe(final Disposable disposable) {
        lastThread = Thread.currentThread();
        if (null == disposable) {
            errors.add(new NullPointerException("onSubscribe received a null Subscription"));
            return;
        }
        if (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m((CompletableAndThenCompletableSourceObserver) upstream, null, (Throwable) disposable)) {
            disposable.dispose();
            if (DisposableHelper.DISPOSED != this.upstream.get()) {
                errors.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + disposable));
                return;
            }
            return;
        }
        final int i2 = initialFusionMode;
        if (0 != i2 && (disposable instanceof QueueDisposable)) {
            final QueueDisposable<T> queueDisposable = (QueueDisposable) disposable;
            qd = queueDisposable;
            final int requestFusion = queueDisposable.requestFusion(i2);
            establishedFusionMode = requestFusion;
            if (1 == requestFusion) {
                checkSubscriptionOnce = true;
                lastThread = Thread.currentThread();
                while (true) {
                    try {
                        final T poll = qd.poll();
                        if (null != poll) {
                            values.add(poll);
                        } else {
                            completions++;
                            upstream.lazySet(DisposableHelper.DISPOSED);
                            return;
                        }
                    } catch (final Throwable th) {
                        errors.add(th);
                        return;
                    }
                }
            }
        }
        downstream.onSubscribe(disposable);
    }
    public void onNext(final Object t) {
        if (!checkSubscriptionOnce) {
            checkSubscriptionOnce = true;
            if (null == this.upstream.get ()) {
                errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        lastThread = Thread.currentThread();
        if (2 != this.establishedFusionMode) {
            values.add(( T ) t);
            if (null == t) {
                errors.add(new NullPointerException("onNext received a null value"));
            }
            downstream.onNext(t);
            return;
        }
        while (true) {
            try {
                final T poll = qd.poll();
                if (null == poll) {
                    return;
                } else {
                    values.add(poll);
                }
            } catch (final Throwable th) {
                errors.add(th);
                qd.dispose();
                return;
            }
        }
    }
    public void onError(final Throwable th) {
        if (!checkSubscriptionOnce) {
            checkSubscriptionOnce = true;
            if (null == this.upstream.get ()) {
                errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            lastThread = Thread.currentThread();
            if (null == th) {
                errors.add(new NullPointerException("onError received a null Throwable"));
            } else {
                errors.add(th);
            }
            downstream.onError(th);
            done.countDown();
        } catch (final Throwable th2) {
            done.countDown();
            throw th2;
        }
    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public void onComplete() {
        if (!checkSubscriptionOnce) {
            checkSubscriptionOnce = true;
            if (null == this.upstream.get ()) {
                errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            lastThread = Thread.currentThread();
            completions++;
            downstream.onComplete();
        } finally {
            done.countDown();
        }
    }
    public final void dispose() {
        DisposableHelper.dispose(upstream);
    }
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(upstream.get());
    }
    public void onSuccess(final T t) {
        this.onNext(t);
        this.onComplete();
    }
}
