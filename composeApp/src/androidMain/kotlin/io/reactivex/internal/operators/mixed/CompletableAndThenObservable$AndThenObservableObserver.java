package io.reactivex.internal.operators.mixed;

import android.util.Log;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;



final class CompletableAndThenObservableAndThenObservableObserver<R> extends AtomicReference<Disposable> implements Observer<R>, CompletableObserver, Disposable {
    private static final long serialVersionUID = -8948264376121066672L;
    final Observer<? super R> downstream;
    ObservableSource<? extends R> other;

    CompletableAndThenObservableAndThenObservableObserver(Observer<? super R> observer, ObservableSource<? extends R> observableSource) {
        this.other = observableSource;
        this.downstream = observer;
    }

    @Override // io.reactivex.Observer
    public void onNext(Object r) {
        this.downstream.onNext(r);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        ObservableSource<? extends R> observableSource = this.other;
        if (null == observableSource) {
            this.downstream.onComplete();
        } else {
            this.other = null;
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
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.replace(this, disposable);
    }
}
