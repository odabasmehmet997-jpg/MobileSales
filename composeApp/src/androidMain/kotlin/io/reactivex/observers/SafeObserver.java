package io.reactivex.observers;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;


public final class SafeObserver<T> implements Observer<T>, Disposable {
    boolean done;
    final Observer<? super T> downstream;
    Disposable upstream;

    public SafeObserver(final Observer<? super T> observer) {
        downstream = observer;
    }

    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.validate(upstream, disposable)) {
            upstream = disposable;
            try {
                downstream.onSubscribe(this);
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                done = true;
                try {
                    disposable.dispose();
                    RxJavaPlugins.onError(th);
                } catch (final Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, th2));
                }
            }
        }
    }
    public void dispose() {
        upstream.dispose();
    }

    public boolean isDisposed() {
        return upstream.isDisposed();
    }

    public void onNext(final Object t) {
        if (done) {
            return;
        }
        if (null == this.upstream) {
            this.onNextNoSubscription();
            return;
        }
        if (null == t) {
            final NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            try {
                upstream.dispose();
                this.onError(nullPointerException);
                return;
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                this.onError(new CompositeException(nullPointerException, th));
                return;
            }
        }
        try {
            downstream.onNext(t);
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            try {
                upstream.dispose();
                this.onError(th2);
            } catch (final Throwable th3) {
                Exceptions.throwIfFatal(th3);
                this.onError(new CompositeException(th2, th3));
            }
        }
    }

    void onNextNoSubscription() {
        done = true;
        final NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            downstream.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                downstream.onError(nullPointerException);
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(nullPointerException, th));
            }
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(nullPointerException, th2));
        }
    }
    public void onError(Throwable th) {
        if (done) {
            RxJavaPlugins.onError(th);
            return;
        }
        done = true;
        if (null == this.upstream) {
            final NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                downstream.onSubscribe(EmptyDisposable.INSTANCE);
                try {
                    downstream.onError(new CompositeException(th, nullPointerException));
                    return;
                } catch (final Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, nullPointerException, th2));
                    return;
                }
            } catch (final Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.onError(new CompositeException(th, nullPointerException, th3));
                return;
            }
        }
        if (null == th) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        try {
            downstream.onError(th);
        } catch (final Throwable th4) {
            Exceptions.throwIfFatal(th4);
            RxJavaPlugins.onError(new CompositeException(th, th4));
        }
    }

    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }

    public void onComplete() {
        if (done) {
            return;
        }
        done = true;
        if (null == this.upstream) {
            this.onCompleteNoSubscription();
            return;
        }
        try {
            downstream.onComplete();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    void onCompleteNoSubscription() {
        final NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            downstream.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                downstream.onError(nullPointerException);
            } catch (final Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(nullPointerException, th));
            }
        } catch (final Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(nullPointerException, th2));
        }
    }
}
