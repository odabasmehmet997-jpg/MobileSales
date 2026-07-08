package io.reactivex.internal.operators.observable;

import android.util.Log;
import androidx.core.location.LocationRequestCompat;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;



public final class ObservableRepeat<T> extends AbstractObservableWithUpstream<T, T> {
    final long count;

    public ObservableRepeat(Observable<T> observable, long j2) {
        super(observable);
        this.count = j2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        long j2 = this.count;
        long j3 = LocationRequestCompat.PASSIVE_INTERVAL;
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            j3 = j2 - 1;
        }
        new RepeatObserver(observer, j3, sequentialDisposable, this.source).subscribeNext();
    }

    static final class RepeatObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        long remaining;
        final SequentialDisposable sd;
        final ObservableSource<? extends T> source;

        RepeatObserver(Observer<? super T> observer, long j2, SequentialDisposable sequentialDisposable, ObservableSource<? extends T> observableSource) {
            this.downstream = observer;
            this.sd = sequentialDisposable;
            this.source = observableSource;
            this.remaining = j2;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.sd.replace(disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            long j2 = this.remaining;
            if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                this.remaining = j2 - 1;
            }
            if (0 != j2) {
                subscribeNext();
            } else {
                this.downstream.onComplete();
            }
        }

        void subscribeNext() {
            if (0 == this.getAndIncrement()) {
                int i2 = 1;
                while (!this.sd.isDisposed()) {
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
