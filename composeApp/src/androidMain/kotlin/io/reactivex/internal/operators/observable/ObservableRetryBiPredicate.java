package io.reactivex.internal.operators.observable;

import android.util.Log;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;



public final class ObservableRetryBiPredicate<T> extends AbstractObservableWithUpstream<T, T> {
    final BiPredicate<? super Integer, ? super Throwable> predicate;

    public ObservableRetryBiPredicate(Observable<T> observable, BiPredicate<? super Integer, ? super Throwable> biPredicate) {
        super(observable);
        this.predicate = biPredicate;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        new RetryBiObserver(observer, this.predicate, sequentialDisposable, this.source).subscribeNext();
    }

    static final class RetryBiObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        final BiPredicate<? super Integer, ? super Throwable> predicate;
        int retries;
        final ObservableSource<? extends T> source;
        final SequentialDisposable upstream;

        RetryBiObserver(Observer<? super T> observer, BiPredicate<? super Integer, ? super Throwable> biPredicate, SequentialDisposable sequentialDisposable, ObservableSource<? extends T> observableSource) {
            this.downstream = observer;
            this.upstream = sequentialDisposable;
            this.source = observableSource;
            this.predicate = biPredicate;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.upstream.replace(disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            try {
                BiPredicate<? super Integer, ? super Throwable> biPredicate = this.predicate;
                int i2 = this.retries + 1;
                this.retries = i2;
                if (!biPredicate.test(Integer.valueOf(i2), th)) {
                    this.downstream.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }

        void subscribeNext() {
            if (0 == this.getAndIncrement()) {
                int i2 = 1;
                while (!this.upstream.isDisposed()) {
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
                    i2 = addAndGet(-i2);
                    if (0 == i2) {
                        return;
                    }
                }
            }
        }
    }
}
