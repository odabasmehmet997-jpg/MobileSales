package io.reactivex.internal.operators.observable;

import android.util.Log;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableWindowBoundarySelector<T, B, V> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final Function<? super B, ? extends ObservableSource<V>> close;
    final ObservableSource<B> open;

    public ObservableWindowBoundarySelector(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Function<? super B, ? extends ObservableSource<V>> function, int i2) {
        super(observableSource);
        this.open = observableSource2;
        this.close = function;
        this.bufferSize = i2;
    }
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

    static final class WindowBoundaryMainObserver<T, B, V> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        final AtomicReference<Disposable> boundary;
        final int bufferSize;
        final Function<? super B, ? extends ObservableSource<V>> close;
        final ObservableSource<B> open;
        final CompositeDisposable resources;
        final AtomicBoolean stopWindows;
        Disposable upstream;
        final AtomicLong windows;
        final List<UnicastSubject<T>> ws;

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        public void accept(Observer<? super Observable<T>> observer, Object obj) {
        }

        WindowBoundaryMainObserver(Observer<? super Observable<T>> observer, ObservableSource<B> observableSource, Function<? super B, ? extends ObservableSource<V>> function, int i2) {
            super(observer, new MpscLinkedQueue());
            this.boundary = new AtomicReference<>();
            AtomicLong atomicLong = new AtomicLong();
            this.windows = atomicLong;
            this.stopWindows = new AtomicBoolean();
            this.open = observableSource;
            this.close = function;
            this.bufferSize = i2;
            this.resources = new CompositeDisposable();
            this.ws = new ArrayList();
            atomicLong.lazySet(1L);
        }

        
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                downstream.onSubscribe(this);
                if (this.stopWindows.get()) {
                    return;
                }
                OperatorWindowBoundaryOpenObserver operatorWindowBoundaryOpenObserver = new OperatorWindowBoundaryOpenObserver(this);
                if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.boundary, null, operatorWindowBoundaryOpenObserver)) {
                    this.open.subscribe(new Observer<DataResponse<ItemSlip>>() {
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
            }
        }

        
        public void onNext(Object t) {
            if (this.fastEnter()) {
                Iterator<UnicastSubject<T>> it = this.ws.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t);
                }
                if (0 == this.leave(-1)) {
                    return;
                }
            } else {
                queue.offer(NotificationLite.next(t));
                if (!this.enter()) {
                    return;
                }
            }
            drainLoop();
        }

        
        public void onError(Throwable th) {
            if (done) {
                RxJavaPlugins.onError(th);
                return;
            }
            error = th;
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            if (0 == windows.decrementAndGet ()) {
                this.resources.dispose();
            }
            downstream.onError(th);
        }

        
        public void onComplete() {
            if (done) {
                return;
            }
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            if (0 == windows.decrementAndGet ()) {
                this.resources.dispose();
            }
            downstream.onComplete();
        }

        void error(Throwable th) {
            this.upstream.dispose();
            this.resources.dispose();
            onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.boundary);
                if (0 == windows.decrementAndGet ()) {
                    this.upstream.dispose();
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        void disposeBoundary() {
            this.resources.dispose();
            DisposableHelper.dispose(this.boundary);
        }
        void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) queue;
            Observer<? super V> observer = downstream;
            List<UnicastSubject<T>> list = this.ws;
            int i2 = 1;
            while (true) {
                boolean z = done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    disposeBoundary();
                    Throwable th = error;
                    if (null != th) {
                        Iterator<UnicastSubject<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<UnicastSubject<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    list.clear();
                    return;
                }
                if (!z2) {
                    if (poll instanceof WindowOperation windowOperation) {
                        UnicastSubject<T> unicastSubject = windowOperation.w;
                        if (null != unicastSubject) {
                            if (list.remove(unicastSubject)) {
                                windowOperation.w.onComplete();
                                if (0 == windows.decrementAndGet ()) {
                                    disposeBoundary();
                                    return;
                                }
                            } else {
                                continue;
                            }
                        } else if (!this.stopWindows.get()) {
                            UnicastSubject<T> create = UnicastSubject.create(this.bufferSize);
                            list.add(create);
                            observer.onNext(create);
                            try {
                                ObservableSource observableSource = ObjectHelper.requireNonNull(this.close.apply(windowOperation.open), "The ObservableSource supplied is null");
                                OperatorWindowBoundaryCloseObserver operatorWindowBoundaryCloseObserver = new OperatorWindowBoundaryCloseObserver(this, create);
                                if (this.resources.add(operatorWindowBoundaryCloseObserver)) {
                                    this.windows.getAndIncrement();
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
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                this.stopWindows.set(true);
                                observer.onError(th2);
                            }
                        }
                    } else {
                        Iterator<UnicastSubject<T>> it3 = list.iterator();
                        while (it3.hasNext()) {
                            it3.next().onNext(NotificationLite.getValue(poll));
                        }
                    }
                } else {
                    i2 = this.leave(-i2);
                    if (0 == i2) {
                        return;
                    }
                }
            }
        }

        void open(B b2) {
            queue.offer(new WindowOperation(null, b2));
            if (this.enter()) {
                drainLoop();
            }
        }

        void close(OperatorWindowBoundaryCloseObserver<T, V> operatorWindowBoundaryCloseObserver) {
            this.resources.delete(operatorWindowBoundaryCloseObserver);
            queue.offer(new WindowOperation(operatorWindowBoundaryCloseObserver.w, null));
            if (this.enter()) {
                drainLoop();
            }
        }
    }

    class WindowOperation<T, B>(UnicastSubject<T> w, B open) {
    }

    static final class OperatorWindowBoundaryOpenObserver<T, B> extends DisposableObserver<B> {
        final WindowBoundaryMainObserver<T, B, ?> parent;

        OperatorWindowBoundaryOpenObserver(WindowBoundaryMainObserver<T, B, ?> windowBoundaryMainObserver) {
            this.parent = windowBoundaryMainObserver;
        }

        
        public void onNext(Object b2) {
            this.parent.open((B) b2);
        }

        
        public void onError(Throwable th) {
            this.parent.error(th);
        }

        
        public void onComplete() {
            this.parent.onComplete();
        }
    }

    static final class OperatorWindowBoundaryCloseObserver<T, V> extends DisposableObserver<V> {
        boolean done;
        final WindowBoundaryMainObserver<T, ?, V> parent;
        final UnicastSubject<T> w;

        OperatorWindowBoundaryCloseObserver(WindowBoundaryMainObserver<T, ?, V> windowBoundaryMainObserver, UnicastSubject<T> unicastSubject) {
            this.parent = windowBoundaryMainObserver;
            this.w = unicastSubject;
        }

        
        public void onNext(Object v) {
            this.dispose();
            onComplete();
        }

        
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.parent.error(th);
            }
        }

        
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.close(this);
        }
    }
}
