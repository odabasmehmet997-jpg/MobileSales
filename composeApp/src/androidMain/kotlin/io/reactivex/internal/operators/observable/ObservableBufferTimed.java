package io.reactivex.internal.operators.observable;

import android.util.Log;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    public ObservableBufferTimed(ObservableSource<T> observableSource, long j2, long j3, TimeUnit timeUnit, Scheduler scheduler, Callable<U> callable, int i2, boolean z) {
        super(observableSource);
        this.timespan = j2;
        this.timeskip = j3;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.bufferSupplier = callable;
        this.maxSize = i2;
        this.restartTimerOnMaxSize = z;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super U> observer) {
        if (this.timespan == this.timeskip && Integer.MAX_VALUE == maxSize) {
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
            return;
        }
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
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
    }

    static final class BufferExactUnboundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;

        public void accept(Observer observer, Object obj) {
            this.accept((Observer<? super Observer>) observer, obj);
        }

        BufferExactUnboundedObserver(Observer<? super U> observer, Callable<U> callable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
            super(observer, new MpscLinkedQueue());
            this.timer = new AtomicReference<>();
            this.bufferSupplier = callable;
            this.timespan = j2;
            this.unit = timeUnit;
            this.scheduler = scheduler;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                try {
                    this.buffer = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    downstream.onSubscribe(this);
                    if (cancelled) {
                        return;
                    }
                    Scheduler scheduler = this.scheduler;
                    long j2 = this.timespan;
                    Disposable schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(this, j2, j2, this.unit);
                    if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.timer, null, schedulePeriodicallyDirect)) {
                        return;
                    }
                    schedulePeriodicallyDirect.dispose();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    dispose();
                    EmptyDisposable.error(th, downstream);
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
            synchronized (this) {
                this.buffer = null;
            }
            downstream.onError(th);
            DisposableHelper.dispose(this.timer);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.buffer;
                this.buffer = null;
            }
            if (null != u) {
                queue.offer(u);
                done = true;
                if (this.enter()) {
                    QueueDrainHelper.drainLoop(queue, downstream, false, null, this);
                }
            }
            DisposableHelper.dispose(this.timer);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.timer);
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.DISPOSED == timer.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            U u;
            try {
                U u2 = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    try {
                        u = this.buffer;
                        if (null != u) {
                            this.buffer = u2;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (null == u) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    this.fastPathEmit(u, false, this);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                downstream.onError(th2);
                dispose();
            }
        }

        public void accept(Observer<? super U> observer, U u) {
            downstream.onNext(u);
        }
    }

    static final class BufferSkipBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        final Callable<U> bufferSupplier;
        final List<U> buffers;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker w;

        public void accept(Observer observer, Object obj) {
            this.accept((Observer<? super Observer>) observer, obj);
        }

        BufferSkipBoundedObserver(Observer<? super U> observer, Callable<U> callable, long j2, long j3, TimeUnit timeUnit, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j2;
            this.timeskip = j3;
            this.unit = timeUnit;
            this.w = worker;
            this.buffers = new LinkedList();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                try {
                    Collection collection = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.buffers.add(collection);
                    downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.w;
                    long j2 = this.timeskip;
                    worker.schedulePeriodically(this, j2, j2, this.unit);
                    this.w.schedule(new RemoveFromBufferEmit(collection), this.timespan, this.unit);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    EmptyDisposable.error(th, downstream);
                    this.w.dispose();
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            synchronized (this) {
                try {
                    Iterator<U> it = this.buffers.iterator();
                    while (it.hasNext()) {
                        it.next().add(t);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            done = true;
            clear();
            downstream.onError(th);
            this.w.dispose();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            ArrayList arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.buffers);
                this.buffers.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                queue.offer((Collection) it.next());
            }
            done = true;
            if (this.enter()) {
                QueueDrainHelper.drainLoop(queue, downstream, false, this.w, this);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            clear();
            this.upstream.dispose();
            this.w.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return cancelled;
        }

        void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cancelled) {
                return;
            }
            try {
                Collection collection = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    try {
                        if (cancelled) {
                            return;
                        }
                        this.buffers.add(collection);
                        this.w.schedule(new RemoveFromBuffer(collection), this.timespan, this.unit);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                downstream.onError(th2);
                dispose();
            }
        }
        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        final class RemoveFromBuffer implements Runnable {

            private final U f39b;

            RemoveFromBuffer(U u) {
                this.f39b = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.f39b);
                }
                BufferSkipBoundedObserver bufferSkipBoundedObserver = BufferSkipBoundedObserver.this;
                bufferSkipBoundedObserver.fastPathOrderedEmit(this.f39b, false, bufferSkipBoundedObserver.w);
            }
        }

        final class RemoveFromBufferEmit implements Runnable {
            private final U buffer;

            RemoveFromBufferEmit(U u) {
                this.buffer = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedObserver bufferSkipBoundedObserver = BufferSkipBoundedObserver.this;
                bufferSkipBoundedObserver.fastPathOrderedEmit(this.buffer, false, bufferSkipBoundedObserver.w);
            }
        }
    }

    static final class BufferExactBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker w;

        public void accept(Observer observer, Object obj) {
            this.accept((Observer<? super Observer>) observer, obj);
        }

        BufferExactBoundedObserver(Observer<? super U> observer, Callable<U> callable, long j2, TimeUnit timeUnit, int i2, boolean z, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j2;
            this.unit = timeUnit;
            this.maxSize = i2;
            this.restartTimerOnMaxSize = z;
            this.w = worker;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                try {
                    this.buffer = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.w;
                    long j2 = this.timespan;
                    this.timer = worker.schedulePeriodically(this, j2, j2, this.unit);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    EmptyDisposable.error(th, downstream);
                    this.w.dispose();
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
                    if (u.size() < this.maxSize) {
                        return;
                    }
                    this.buffer = null;
                    this.producerIndex++;
                    if (this.restartTimerOnMaxSize) {
                        this.timer.dispose();
                    }
                    this.fastPathOrderedEmit(u, false, this);
                    try {
                        U u2 = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                        synchronized (this) {
                            this.buffer = u2;
                            this.consumerIndex++;
                        }
                        if (this.restartTimerOnMaxSize) {
                            Scheduler.Worker worker = this.w;
                            long j2 = this.timespan;
                            this.timer = worker.schedulePeriodically(this, j2, j2, this.unit);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        downstream.onError(th);
                        dispose();
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.buffer = null;
            }
            downstream.onError(th);
            this.w.dispose();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            U u;
            this.w.dispose();
            synchronized (this) {
                u = this.buffer;
                this.buffer = null;
            }
            if (null != u) {
                queue.offer(u);
                done = true;
                if (this.enter()) {
                    QueueDrainHelper.drainLoop(queue, downstream, false, this, this);
                }
            }
        }

        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            this.upstream.dispose();
            this.w.dispose();
            synchronized (this) {
                this.buffer = null;
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    U u2 = this.buffer;
                    if (null != u2 && this.producerIndex == this.consumerIndex) {
                        this.buffer = u;
                        this.fastPathOrderedEmit(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                downstream.onError(th);
            }
        }
    }
}
