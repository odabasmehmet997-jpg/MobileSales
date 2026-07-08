package io.reactivex.internal.operators.observable;

import android.util.Log;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableBufferBoundarySupplier<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {
    final Callable<? extends ObservableSource<B>> boundarySupplier;
    final Callable<U> bufferSupplier;

    public ObservableBufferBoundarySupplier(ObservableSource<T> observableSource, Callable<? extends ObservableSource<B>> callable, Callable<U> callable2) {
        super(observableSource);
        this.boundarySupplier = callable;
        this.bufferSupplier = callable2;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super U> observer) {
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

    static final class BufferBoundarySupplierObserver<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Disposable {
        final Callable<? extends ObservableSource<B>> boundarySupplier;
        U buffer;
        final Callable<U> bufferSupplier;
        final AtomicReference<Disposable> other;
        Disposable upstream;

        public   void accept(Observer observer, Object obj) {
            this.accept((Observer<? super Observer>) observer, obj);
        }

        BufferBoundarySupplierObserver(Observer<? super U> observer, Callable<U> callable, Callable<? extends ObservableSource<B>> callable2) {
            super(observer, new MpscLinkedQueue());
            this.other = new AtomicReference<>();
            this.bufferSupplier = callable;
            this.boundarySupplier = callable2;
        }

        public <V> void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                Observer<? super V> observer = downstream;
                try {
                    this.buffer = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    try {
                        ObservableSource observableSource = ObjectHelper.requireNonNull(this.boundarySupplier.call(), "The boundary ObservableSource supplied is null");
                        BufferBoundaryObserver bufferBoundaryObserver = new BufferBoundaryObserver(this);
                        this.other.set(bufferBoundaryObserver);
                        observer.onSubscribe(this);
                        if (cancelled) {
                            return;
                        }
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
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        cancelled = true;
                        disposable.dispose();
                        EmptyDisposable.error(th, observer);
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    cancelled = true;
                    disposable.dispose();
                    EmptyDisposable.error(th2, observer);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            synchronized (this) {
                try {
                    U u = this.buffer;
                    if (null == u) {
                        return;
                    }
                    u.add(t);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            dispose();
            downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            synchronized (this) {
                try {
                    U u = this.buffer;
                    if (null == u) {
                        return;
                    }
                    this.buffer = null;
                    queue.offer(u);
                    done = true;
                    if (this.enter()) {
                        QueueDrainHelper.drainLoop(queue, downstream, false, this, this);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            this.upstream.dispose();
            disposeOther();
            if (this.enter()) {
                queue.clear();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return cancelled;
        }

        void disposeOther() {
            DisposableHelper.dispose(this.other);
        }

        void next() {
            try {
                U u = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                try {
                    ObservableSource observableSource = ObjectHelper.requireNonNull(this.boundarySupplier.call(), "The boundary ObservableSource supplied is null");
                    BufferBoundaryObserver bufferBoundaryObserver = new BufferBoundaryObserver(this);
                    if (DisposableHelper.replace(this.other, bufferBoundaryObserver)) {
                        synchronized (this) {
                            try {
                                U u2 = this.buffer;
                                if (null == u2) {
                                    return;
                                }
                                this.buffer = u;
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
                                this.fastPathEmit(u2, false, this);
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    cancelled = true;
                    this.upstream.dispose();
                    downstream.onError(th2);
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                dispose();
                downstream.onError(th3);
            }
        }

        public void accept(Observer<? super U> observer, U u) {
            downstream.onNext(u);
        }
    }

    static final class BufferBoundaryObserver<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        boolean once;
        final BufferBoundarySupplierObserver<T, U, B> parent;

        BufferBoundaryObserver(BufferBoundarySupplierObserver<T, U, B> bufferBoundarySupplierObserver) {
            this.parent = bufferBoundarySupplierObserver;
        }

        @Override // io.reactivex.Observer
        public void onNext(Object b2) {
            if (this.once) {
                return;
            }
            this.once = true;
            this.dispose();
            this.parent.next();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.once) {
                RxJavaPlugins.onError(th);
            } else {
                this.once = true;
                this.parent.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.once) {
                return;
            }
            this.once = true;
            this.parent.next();
        }
    }
}
