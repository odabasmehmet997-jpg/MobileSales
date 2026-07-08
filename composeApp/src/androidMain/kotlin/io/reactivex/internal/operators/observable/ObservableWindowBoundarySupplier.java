package io.reactivex.internal.operators.observable;

import android.util.Log;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.UnicastSubject;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableWindowBoundarySupplier<T, B> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int capacityHint;
    final Callable<? extends ObservableSource<B>> other;

    public ObservableWindowBoundarySupplier(ObservableSource<T> observableSource, Callable<? extends ObservableSource<B>> callable, int i2) {
        super(observableSource);
        this.other = callable;
        this.capacityHint = i2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        this.source.subscribe(new Observer<DataResponse<ItemSlip>>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(DataResponse<ItemSlip> dataResponse) {
                if (null != dataResponse && dataResponse.isSuccessful()) {
                    NetsisRestAsyncTask.itemSlipToSales(sales, dataResponse.getData());
                    final String str2 = NetsisRestAsyncTask.TAG;
                    Log.d(str2, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
                    return;
                }
                final String str3 = NetsisRestAsyncTask.TAG;
                Log.e(str3, " onNext : error : " + (null != dataResponse.getErrorDesc() ? dataResponse.getErrorDesc() : ""));
                StringBuilder sb2 = sb;
                sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
            }

            public void onError(Throwable th) {
                final String str2 = NetsisRestAsyncTask.TAG;
                Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
                sb.append(th.getMessage());
            }

            @Override
            public void onNext(Object t) {
            }

            public void onComplete() {
                if (0 < sb.length()) {
                    responseListener.onError(sb.toString());
                } else {
                    responseListener.onResponse(sales);
                }
            }
        });
    }

    static final class WindowBoundaryMainObserver<T, B> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        static final WindowBoundaryInnerObserver<Object, Object> BOUNDARY_DISPOSED = new WindowBoundaryInnerObserver<>(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final int capacityHint;
        volatile boolean done;
        final Observer<? super Observable<T>> downstream;
        final Callable<? extends ObservableSource<B>> other;
        Disposable upstream;
        UnicastSubject<T> window;
        final AtomicReference<WindowBoundaryInnerObserver<T, B>> boundaryObserver = new AtomicReference<>();
        final AtomicInteger windows = new AtomicInteger(1);
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicBoolean stopWindows = new AtomicBoolean();

        WindowBoundaryMainObserver(Observer<? super Observable<T>> observer, int i2, Callable<? extends ObservableSource<B>> callable) {
            this.downstream = observer;
            this.capacityHint = i2;
            this.other = callable;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (0 == windows.decrementAndGet ()) {
                    this.upstream.dispose();
                }
            }
        }

        void disposeBoundary() {
            AtomicReference<WindowBoundaryInnerObserver<T, B>> atomicReference = this.boundaryObserver;
            WindowBoundaryInnerObserver<Object, Object> windowBoundaryInnerObserver = BOUNDARY_DISPOSED;
            Disposable disposable = atomicReference.getAndSet(windowBoundaryInnerObserver);
            if (null == disposable || disposable == windowBoundaryInnerObserver) {
                return;
            }
            disposable.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (0 == windows.decrementAndGet ()) {
                this.upstream.dispose();
            }
        }

        void innerNext(WindowBoundaryInnerObserver<T, B> windowBoundaryInnerObserver) {
            LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.boundaryObserver, windowBoundaryInnerObserver, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        void innerError(Throwable th) {
            this.upstream.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        void innerComplete() {
            this.upstream.dispose();
            this.done = true;
            drain();
        }

        void drain() {
            if (0 != this.getAndIncrement()) {
                return;
            }
            Observer<? super Observable<T>> observer = this.downstream;
            MpscLinkedQueue<Object> mpscLinkedQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            int i2 = 1;
            while (0 != windows.get ()) {
                UnicastSubject<T> unicastSubject = this.window;
                boolean z = this.done;
                if (z && null != atomicThrowable.get ()) {
                    mpscLinkedQueue.clear();
                    Throwable terminate = atomicThrowable.terminate();
                    if (0 != unicastSubject) {
                        this.window = null;
                        unicastSubject.onError(terminate);
                    }
                    observer.onError(terminate);
                    return;
                }
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    Throwable terminate2 = atomicThrowable.terminate();
                    if (null == terminate2) {
                        if (0 != unicastSubject) {
                            this.window = null;
                            unicastSubject.onComplete();
                        }
                        observer.onComplete();
                        return;
                    }
                    if (0 != unicastSubject) {
                        this.window = null;
                        unicastSubject.onError(terminate2);
                    }
                    observer.onError(terminate2);
                    return;
                }
                if (!z2) {
                    if (poll != NEXT_WINDOW) {
                        unicastSubject.onNext(poll);
                    } else {
                        if (0 != unicastSubject) {
                            this.window = null;
                            unicastSubject.onComplete();
                        }
                        if (!this.stopWindows.get()) {
                            UnicastSubject<T> create = UnicastSubject.create(this.capacityHint, this);
                            this.window = create;
                            this.windows.getAndIncrement();
                            try {
                                ObservableSource observableSource = ObjectHelper.requireNonNull(this.other.call(), "The other Callable returned a null ObservableSource");
                                WindowBoundaryInnerObserver windowBoundaryInnerObserver = new WindowBoundaryInnerObserver(this);
                                if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.boundaryObserver, null, windowBoundaryInnerObserver)) {
                                    observableSource.subscribe(new Observer<DataResponse<ItemSlip>>() {
                                        public void onSubscribe(Disposable disposable) {
                                        }

                                        public void onNext(DataResponse<ItemSlip> dataResponse) {
                                            if (null != dataResponse && dataResponse.isSuccessful()) {
                                                NetsisRestAsyncTask.itemSlipToSales(sales, dataResponse.getData());
                                                final String str2 = NetsisRestAsyncTask.TAG;
                                                Log.d(str2, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
                                                return;
                                            }
                                            final String str3 = NetsisRestAsyncTask.TAG;
                                            Log.e(str3, " onNext : error : " + (null != dataResponse.getErrorDesc() ? dataResponse.getErrorDesc() : ""));
                                            StringBuilder sb2 = sb;
                                            sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
                                        }

                                        public void onError(Throwable th) {
                                            final String str2 = NetsisRestAsyncTask.TAG;
                                            Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
                                            sb.append(th.getMessage());
                                        }

                                        @Override
                                        public void onNext(Object t) {
                                        }

                                        public void onComplete() {
                                            if (0 < sb.length()) {
                                                responseListener.onError(sb.toString());
                                            } else {
                                                responseListener.onResponse(sales);
                                            }
                                        }
                                    });
                                    observer.onNext(create);
                                }
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                atomicThrowable.addThrowable(th);
                                this.done = true;
                            }
                        }
                    }
                } else {
                    i2 = addAndGet(-i2);
                    if (0 == i2) {
                        return;
                    }
                }
            }
            mpscLinkedQueue.clear();
            this.window = null;
        }
    }

    static final class WindowBoundaryInnerObserver<T, B> extends DisposableObserver<B> {
        boolean done;
        final WindowBoundaryMainObserver<T, B> parent;

        WindowBoundaryInnerObserver(WindowBoundaryMainObserver<T, B> windowBoundaryMainObserver) {
            this.parent = windowBoundaryMainObserver;
        }

        @Override // io.reactivex.Observer
        public void onNext(Object b2) {
            if (this.done) {
                return;
            }
            this.done = true;
            this.dispose();
            this.parent.innerNext(this);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.parent.innerError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.innerComplete();
        }
    }
}
