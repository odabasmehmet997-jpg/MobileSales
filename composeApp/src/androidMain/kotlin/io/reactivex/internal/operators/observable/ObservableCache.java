package io.reactivex.internal.operators.observable;

import android.util.Log;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableCache<T> extends AbstractObservableWithUpstream<T, T> implements Observer<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    final int capacityHint;
    volatile boolean done;
    Throwable error;
    final Node<T> head;
    final AtomicReference<CacheDisposable<T>[]> observers;
    final AtomicBoolean once;
    volatile long size;
    Node<T> tail;
    int tailOffset;

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
    }

    public ObservableCache(Observable<T> observable, int i2) {
        super(observable);
        this.capacityHint = i2;
        this.once = new AtomicBoolean();
        Node<T> node = new Node<>(i2);
        this.head = node;
        this.tail = node;
        this.observers = new AtomicReference<>(EMPTY);
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        CacheDisposable<T> cacheDisposable = new CacheDisposable<>(observer, this);
        observer.onSubscribe(cacheDisposable);
        add(cacheDisposable);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
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
        } else {
            replay(cacheDisposable);
        }
    }

    void add(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.observers.get();
            if (cacheDisposableArr == TERMINATED) {
                return;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[length + 1];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
    }

    void remove(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.observers.get();
            int length = cacheDisposableArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (cacheDisposableArr[i2] == cacheDisposable) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                cacheDisposableArr2 = EMPTY;
            } else {
                CacheDisposable[] cacheDisposableArr3 = new CacheDisposable[length - 1];
                System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr3, 0, i2);
                System.arraycopy(cacheDisposableArr, i2 + 1, cacheDisposableArr3, i2, (length - i2) - 1);
                cacheDisposableArr2 = cacheDisposableArr3;
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
    }

    void replay(CacheDisposable<T> cacheDisposable) {
        if (0 != cacheDisposable.getAndIncrement ()) {
            return;
        }
        long j2 = cacheDisposable.index;
        int i2 = cacheDisposable.offset;
        Node<T> node = cacheDisposable.node;
        Observer<? super T> observer = cacheDisposable.downstream;
        int i3 = this.capacityHint;
        int i4 = 1;
        while (!cacheDisposable.disposed) {
            boolean z = this.done;
            boolean z2 = this.size == j2;
            if (z && z2) {
                cacheDisposable.node = null;
                Throwable th = this.error;
                if (null != th) {
                    observer.onError(th);
                    return;
                } else {
                    observer.onComplete();
                    return;
                }
            }
            if (!z2) {
                if (i2 == i3) {
                    node = node.next;
                    i2 = 0;
                }
                observer.onNext(node.values[i2]);
                i2++;
                j2++;
            } else {
                cacheDisposable.index = j2;
                cacheDisposable.offset = i2;
                cacheDisposable.node = node;
                i4 = cacheDisposable.addAndGet(-i4);
                if (0 == i4) {
                    return;
                }
            }
        }
        cacheDisposable.node = null;
    }

    @Override // io.reactivex.Observer
    public void onNext(Object t) {
        int i2 = this.tailOffset;
        if (i2 == this.capacityHint) {
            Node<T> node = new Node<>(i2);
            node.values[0] = t;
            this.tailOffset = 1;
            this.tail.next = node;
            this.tail = node;
        } else {
            this.tail.values[i2] = t;
            this.tailOffset = i2 + 1;
        }
        this.size++;
        for (CacheDisposable<T> cacheDisposable : this.observers.get()) {
            replay(cacheDisposable);
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        for (CacheDisposable<T> cacheDisposable : this.observers.getAndSet(TERMINATED)) {
            replay(cacheDisposable);
        }
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        this.done = true;
        for (CacheDisposable<T> cacheDisposable : this.observers.getAndSet(TERMINATED)) {
            replay(cacheDisposable);
        }
    }

    static final class CacheDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 6770240836423125754L;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        long index;
        Node<T> node;
        int offset;
        final ObservableCache<T> parent;

        CacheDisposable(Observer<? super T> observer, ObservableCache<T> observableCache) {
            this.downstream = observer;
            this.parent = observableCache;
            this.node = observableCache.head;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.disposed) {
                return;
            }
            this.disposed = true;
            this.parent.remove(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }

    static final class Node<T> {
        volatile Node<T> next;
        final T[] values;

        Node(int i2) {
            this.values = (T[]) new Object[i2];
        }
    }
}
