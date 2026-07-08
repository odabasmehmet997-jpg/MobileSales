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
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableAmb<T> extends Observable<T> {
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    public ObservableAmb(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable) {
        this.sources = observableSourceArr;
        this.sourcesIterable = iterable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.sources;
        if (null == observableSourceArr) {
            observableSourceArr = new ObservableSource[8];
            try {
                length = 0;
                for (ObservableSource<? extends T> observableSource : this.sourcesIterable) {
                    if (null == observableSource) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), observer);
                        return;
                    }
                    if (length == observableSourceArr.length) {
                        ObservableSource<? extends T>[] observableSourceArr2 = new ObservableSource[(length >> 2) + length];
                        System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                        observableSourceArr = observableSourceArr2;
                    }
                    int i2 = length + 1;
                    observableSourceArr[length] = observableSource;
                    length = i2;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return;
            }
        } else {
            length = observableSourceArr.length;
        }
        if (0 == length) {
            EmptyDisposable.complete(observer);
        } else if (1 == length) {
            observableSourceArr[0].subscribe(new Observer<DataResponse<ItemSlip>>() {
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
        } else {
            new AmbCoordinator(observer, length).subscribe(observableSourceArr);
        }
    }

    static final class AmbCoordinator<T> implements Disposable {
        final Observer<? super T> downstream;
        final AmbInnerObserver<T>[] observers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(Observer<? super T> observer, int i2) {
            this.downstream = observer;
            this.observers = new AmbInnerObserver[i2];
        }

        public void subscribe(ObservableSource<? extends T>[] observableSourceArr) {
            AmbInnerObserver<T>[] ambInnerObserverArr = this.observers;
            int length = ambInnerObserverArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2 + 1;
                ambInnerObserverArr[i2] = new AmbInnerObserver<>(this, i3, this.downstream);
                i2 = i3;
            }
            this.winner.lazySet(0);
            this.downstream.onSubscribe(this);
            for (int i4 = 0; i4 < length && 0 == winner.get (); i4++) {
                observableSourceArr[i4].subscribe(new Observer<DataResponse<ItemSlip>>() {
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

        public boolean win(int i2) {
            int i3 = this.winner.get();
            int i4 = 0;
            if (0 != i3) {
                return i3 == i2;
            }
            if (!this.winner.compareAndSet(0, i2)) {
                return false;
            }
            AmbInnerObserver<T>[] ambInnerObserverArr = this.observers;
            int length = ambInnerObserverArr.length;
            while (i4 < length) {
                int i5 = i4 + 1;
                if (i5 != i2) {
                    ambInnerObserverArr[i4].dispose();
                }
                i4 = i5;
            }
            return true;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (-1 != winner.get ()) {
                this.winner.lazySet(-1);
                for (AmbInnerObserver<T> ambInnerObserver : this.observers) {
                    ambInnerObserver.dispose();
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return -1 == winner.get ();
        }
    }

    static final class AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> downstream;
        final int index;
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerObserver(AmbCoordinator<T> ambCoordinator, int i2, Observer<? super T> observer) {
            this.parent = ambCoordinator;
            this.index = i2;
            this.downstream = observer;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().dispose();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
