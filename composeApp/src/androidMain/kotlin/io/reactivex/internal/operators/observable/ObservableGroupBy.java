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
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.observables.GroupedObservable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupBy<T, K, V> extends AbstractObservableWithUpstream<T, GroupedObservable<K, V>> {
    final int bufferSize;
    final boolean delayError;
    final Function<? super T, ? extends K> keySelector;
    final Function<? super T, ? extends V> valueSelector;

    public ObservableGroupBy(final ObservableSource<T> observableSource, final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, final int i2, final boolean z) {
        super(observableSource);
        keySelector = function;
        valueSelector = function2;
        bufferSize = i2;
        delayError = z;
    }
    public void subscribeActual(final Observer<? super GroupedObservable<K, V>> observer) {
        source.subscribe(new Observer<DataResponse<ItemSlip>>() {
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

    public static final class GroupByObserver<T, K, V> extends AtomicInteger implements Observer<T>, Disposable {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final boolean delayError;
        final Observer<? super GroupedObservable<K, V>> downstream;
        final Function<? super T, ? extends K> keySelector;
        Disposable upstream;
        final Function<? super T, ? extends V> valueSelector;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final Map<Object, GroupedUnicast<K, V>> groups = new ConcurrentHashMap();

        public GroupByObserver(final Observer<? super GroupedObservable<K, V>> observer, final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, final int i2, final boolean z) {
            downstream = observer;
            keySelector = function;
            valueSelector = function2;
            bufferSize = i2;
            delayError = z;
            this.lazySet(1);
        }
        public void onSubscribe(final Disposable disposable) {
            if (DisposableHelper.validate(upstream, disposable)) {
                upstream = disposable;
                downstream.onSubscribe(this);
            }
        }

        public void onNext(Object t) {
            try {
                K apply = keySelector.apply(t);
                Object obj = null != apply ? apply : GroupByObserver.NULL_KEY;
                GroupedUnicast<K, V> groupedUnicast = groups.get(obj);
                r2 = groupedUnicast;
                if (false == groupedUnicast) {
                    if (cancelled.get()) {
                        return;
                    }
                    Object createWith = GroupedUnicast.createWith(apply, bufferSize, this, delayError);
                    groups.put(obj, createWith);
                    this.getAndIncrement();
                    downstream.onNext(createWith);
                    r2 = createWith;
                }
                try {
                    r2.onNext(ObjectHelper.requireNonNull(valueSelector.apply(t), "The value supplied is null"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    upstream.dispose();
                    this.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                upstream.dispose();
                this.onError(th2);
            }
        }
        public void onError(final Throwable th) {
            final ArrayList arrayList = new ArrayList(groups.values());
            groups.clear();
            final Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GroupedUnicast) it.next()).onError(th);
            }
            downstream.onError(th);
        }
        public void onComplete() {
            final ArrayList arrayList = new ArrayList(groups.values());
            groups.clear();
            final Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GroupedUnicast) it.next()).onComplete();
            }
            downstream.onComplete();
        }
        public void dispose() {
            if (cancelled.compareAndSet(false, true) && 0 == decrementAndGet()) {
                upstream.dispose();
            }
        }
        public boolean isDisposed() {
            return cancelled.get();
        }

        public void cancel(K k2) {
            if (null == k2) {
                k2 = (K) GroupByObserver.NULL_KEY;
            }
            groups.remove(k2);
            if (0 == decrementAndGet()) {
                upstream.dispose();
            }
        }
    }

    static final class GroupedUnicast<K, T> extends GroupedObservable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(final K k2, final int i2, final GroupByObserver<?, K, T> groupByObserver, final boolean z) {
            return new GroupedUnicast<>(k2, new State(i2, groupByObserver, k2, z));
        }

        private GroupedUnicast(final K k2, final State<T, K> state) {
            super(k2);
            this.state = state;
        }
        protected void subscribeActual(final Observer<? super T> observer) {
            state.subscribe(new Observer<DataResponse<ItemSlip>>() {
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

        public void onNext(final T t) {
            state.onNext(t);
        }

        public void onError(final Throwable th) {
            state.onError(th);
        }

        public void onComplete() {
            state.onComplete();
        }
    }

    static final class State<T, K> extends AtomicInteger implements Disposable, ObservableSource<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final GroupByObserver<?, K, T> parent;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicReference<Observer<? super T>> actual = new AtomicReference<>();

        State(final int i2, final GroupByObserver<?, K, T> groupByObserver, final K k2, final boolean z) {
            queue = new SpscLinkedArrayQueue<>(i2);
            parent = groupByObserver;
            key = k2;
            delayError = z;
        }
        public void dispose() {
            if (cancelled.compareAndSet(false, true) && 0 == getAndIncrement()) {
                actual.lazySet(null);
                parent.cancel(key);
            }
        }
        public boolean isDisposed() {
            return cancelled.get();
        }
        public Disposable subscribe(Observer<DataResponse<ItemSlip>> observer) {
            if (once.compareAndSet(false, true)) {
                observer.onSubscribe(this);
                actual.lazySet(observer);
                if (cancelled.get()) {
                    actual.lazySet(null);
                    return null;
                } else {
                    this.drain();
                    return null;
                }
            }
            EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), observer);
            return null;
        }

        public void onNext(final T t) {
            queue.offer(t);
            this.drain();
        }

        public void onError(final Throwable th) {
            error = th;
            done = true;
            this.drain();
        }

        public void onComplete() {
            done = true;
            this.drain();
        }

        void drain() {
            if (0 != getAndIncrement()) {
                return;
            }
            final SpscLinkedArrayQueue<T> spscLinkedArrayQueue = queue;
            final boolean z = delayError;
            Observer<? super T> observer = actual.get();
            int i2 = 1;
            while (true) {
                if (null != observer) {
                    while (true) {
                        final boolean z2 = done;
                        final T poll = spscLinkedArrayQueue.poll();
                        final boolean z3 = null == poll;
                        if (this.checkTerminated(z2, z3, observer, z)) {
                            return;
                        }
                        if (z3) {
                            break;
                        } else {
                            observer.onNext(poll);
                        }
                    }
                }
                i2 = this.addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
                if (null == observer) {
                    observer = actual.get();
                }
            }
        }

        boolean checkTerminated(final boolean z, final boolean z2, final Observer<? super T> observer, final boolean z3) {
            if (cancelled.get()) {
                queue.clear();
                parent.cancel(key);
                actual.lazySet(null);
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                final Throwable th = error;
                actual.lazySet(null);
                if (null != th) {
                    observer.onError(th);
                } else {
                    observer.onComplete();
                }
                return true;
            }
            final Throwable th2 = error;
            if (null != th2) {
                queue.clear();
                actual.lazySet(null);
                observer.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            actual.lazySet(null);
            observer.onComplete();
            return true;
        }
    }
}
