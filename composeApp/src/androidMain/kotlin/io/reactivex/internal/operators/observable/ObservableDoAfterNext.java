package io.reactivex.internal.operators.observable;

import android.util.Log;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BasicFuseableObserver;



public final class ObservableDoAfterNext<T> extends AbstractObservableWithUpstream<T, T> {
    final Consumer<? super T> onAfterNext;

    public ObservableDoAfterNext(ObservableSource<T> observableSource, Consumer<? super T> consumer) {
        super(observableSource);
        this.onAfterNext = consumer;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
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

    static final class DoAfterObserver<T> extends BasicFuseableObserver<T, T> {
        final Consumer<? super T> onAfterNext;

        DoAfterObserver(Observer<? super T> observer, Consumer<? super T> consumer) {
            super(observer);
            this.onAfterNext = consumer;
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            downstream.onNext(t);
            if (0 == sourceMode) {
                try {
                    this.onAfterNext.accept(t);
                } catch (Throwable th) {
                    this.fail(th);
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i2) {
            return this.transitiveBoundaryFusion(i2);
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public T poll() throws Exception {
            T poll = qd.poll();
            if (null != poll) {
                this.onAfterNext.accept(poll);
            }
            return poll;
        }
    }
}
