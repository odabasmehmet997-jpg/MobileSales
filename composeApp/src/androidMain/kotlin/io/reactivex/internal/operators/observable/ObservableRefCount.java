package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableRefCount<T> extends Observable<T> {
    RefConnection connection;
    final int n;
    final Scheduler scheduler;
    final ConnectableObservable<T> source;
    final long timeout;
    final TimeUnit unit;

    public ObservableRefCount(ConnectableObservable<T> connectableObservable) {
        this(connectableObservable, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public ObservableRefCount(ConnectableObservable<T> connectableObservable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.source = connectableObservable;
        this.n = i2;
        this.timeout = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        RefConnection refConnection;
        boolean z;
        Disposable disposable;
        synchronized (this) {
            try {
                refConnection = this.connection;
                if (null == refConnection) {
                    refConnection = new RefConnection(this);
                    this.connection = refConnection;
                }
                long j2 = refConnection.subscriberCount;
                if (0 == j2 && null != (disposable = refConnection.timer)) {
                    disposable.dispose();
                }
                long j3 = j2 + 1;
                refConnection.subscriberCount = j3;
                if (refConnection.connected || j3 != this.n) {
                    z = false;
                } else {
                    z = true;
                    refConnection.connected = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.source.subscribe(new RefCountObserver(observer, this, refConnection));
        if (z) {
            this.source.connect(refConnection);
        }
    }

    void cancel(RefConnection refConnection) {
        synchronized (this) {
            final RefConnection refConnection2 = connection;
            if (null != refConnection2 && refConnection2 == refConnection) {
                final long j2 = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j2;
                if (0 == j2 && refConnection.connected) {
                    if (0 == this.timeout) {
                        this.timeout(refConnection);
                        return;
                    }
                    final SequentialDisposable sequentialDisposable = new SequentialDisposable();
                    refConnection.timer = sequentialDisposable;
                    sequentialDisposable.replace(scheduler.scheduleDirect(refConnection, timeout, unit));
                }
            }
        }
    }

    void terminated(RefConnection refConnection) {
        synchronized (this) {
            try {
                if (this.source instanceof ObservablePublishClassic) {
                    RefConnection refConnection2 = this.connection;
                    if (null != refConnection2 && refConnection2 == refConnection) {
                        this.connection = null;
                        clearTimer(refConnection);
                    }
                    long j2 = refConnection.subscriberCount - 1;
                    refConnection.subscriberCount = j2;
                    if (0 == j2) {
                        reset(refConnection);
                    }
                } else {
                    RefConnection refConnection3 = this.connection;
                    if (null != refConnection3 && refConnection3 == refConnection) {
                        clearTimer(refConnection);
                        long j3 = refConnection.subscriberCount - 1;
                        refConnection.subscriberCount = j3;
                        if (0 == j3) {
                            this.connection = null;
                            reset(refConnection);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void clearTimer(RefConnection refConnection) {
        Disposable disposable = refConnection.timer;
        if (null != disposable) {
            disposable.dispose();
            refConnection.timer = null;
        }
    }

    void reset(RefConnection refConnection) {
        ConnectableObservable<T> connectableObservable = this.source;
        if (connectableObservable instanceof Disposable) {
            ((Disposable) connectableObservable).dispose();
        } else if (connectableObservable instanceof ResettableConnectable) {
            ((ResettableConnectable) connectableObservable).resetIf(refConnection.get());
        }
    }

    void timeout(RefConnection refConnection) {
        synchronized (this) {
            try {
                if (0 == refConnection.subscriberCount && refConnection == this.connection) {
                    this.connection = null;
                    Disposable disposable = refConnection.get();
                    DisposableHelper.dispose(refConnection);
                    ConnectableObservable<T> connectableObservable = this.source;
                    if (connectableObservable instanceof Disposable) {
                        ((Disposable) connectableObservable).dispose();
                    } else if (connectableObservable instanceof ResettableConnectable) {
                        if (null == disposable) {
                            refConnection.disconnectedEarly = true;
                        } else {
                            ((ResettableConnectable) connectableObservable).resetIf(disposable);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static final class RefConnection extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final ObservableRefCount<?> parent;
        long subscriberCount;
        Disposable timer;

        RefConnection(ObservableRefCount<?> observableRefCount) {
            this.parent = observableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.timeout(this);
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Disposable disposable) throws Exception {
            DisposableHelper.replace(this, disposable);
            synchronized (this.parent) {
                try {
                    if (this.disconnectedEarly) {
                        ((ResettableConnectable) this.parent.source).resetIf(disposable);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    static final class RefCountObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final Observer<? super T> downstream;
        final ObservableRefCount<T> parent;
        Disposable upstream;

        RefCountObserver(Observer<? super T> observer, ObservableRefCount<T> observableRefCount, RefConnection refConnection) {
            this.downstream = observer;
            this.parent = observableRefCount;
            this.connection = refConnection;
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            if (compareAndSet(false, true)) {
                this.parent.cancel(this.connection);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
