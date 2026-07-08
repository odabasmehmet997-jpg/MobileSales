package io.reactivex.internal.operators.observable;

import _COROUTINE.ArtificialStackFrames;
import android.util.Log;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;



public final class ObservableFlatMapMaybe<T, R> extends AbstractObservableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

    public ObservableFlatMapMaybe(ObservableSource<T> observableSource, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z) {
        super(observableSource);
        this.mapper = function;
        this.delayErrors = z;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super R> observer) {
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

    static final class FlatMapMaybeObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8600231336733376951L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        Disposable upstream;
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        FlatMapMaybeObserver(Observer<? super R> observer, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z) {
            this.downstream = observer;
            this.mapper = function;
            this.delayErrors = z;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            try {
                MaybeSource maybeSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (this.cancelled || !this.set.add(innerObserver)) {
                    return;
                }
                maybeSource.subscribe(innerObserver);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerSuccess(InnerObserver innerObserver, R r) {
            this.set.delete(innerObserver);
            if (0 == this.get()) {
                if (compareAndSet(0, 1)) {
                    this.downstream.onNext(r);
                    boolean z = 0 == active.decrementAndGet ();
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
                    if (z && (null == spscLinkedArrayQueue || spscLinkedArrayQueue.isEmpty())) {
                        Throwable terminate = this.errors.terminate();
                        if (null != terminate) {
                            this.downstream.onError(terminate);
                            return;
                        } else {
                            this.downstream.onComplete();
                            return;
                        }
                    }
                    if (0 == this.decrementAndGet()) {
                        return;
                    }
                    drainLoop();
                }
            }
            SpscLinkedArrayQueue<R> orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (0 != this.getAndIncrement()) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue;
            do {
                SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = this.queue.get();
                if (null != spscLinkedArrayQueue2) {
                    return spscLinkedArrayQueue2;
                }
                spscLinkedArrayQueue = new SpscLinkedArrayQueue<>(bufferSize());
            } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.queue, null, spscLinkedArrayQueue));
            return spscLinkedArrayQueue;
        }

        void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.delete(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        void innerComplete(InnerObserver innerObserver) {
            this.set.delete(innerObserver);
            if (0 == this.get()) {
                if (compareAndSet(0, 1)) {
                    boolean z = 0 == active.decrementAndGet ();
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
                    if (z && (null == spscLinkedArrayQueue || spscLinkedArrayQueue.isEmpty())) {
                        Throwable terminate = this.errors.terminate();
                        if (null != terminate) {
                            this.downstream.onError(terminate);
                            return;
                        } else {
                            this.downstream.onComplete();
                            return;
                        }
                    }
                    if (0 == this.decrementAndGet()) {
                        return;
                    }
                    drainLoop();
                    return;
                }
            }
            this.active.decrementAndGet();
            drain();
        }

        void drain() {
            if (0 == this.getAndIncrement()) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
            if (null != spscLinkedArrayQueue) {
                spscLinkedArrayQueue.clear();
            }
        }

        void drainLoop() {
            Observer<? super R> observer = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
            int i2 = 1;
            while (!this.cancelled) {
                if (!this.delayErrors && null != errors.get ()) {
                    Throwable terminate = this.errors.terminate();
                    clear();
                    observer.onError(terminate);
                    return;
                }
                boolean z = 0 == atomicInteger.get ();
                SpscLinkedArrayQueue<R> spscLinkedArrayQueue = atomicReference.get();
                ArtificialStackFrames poll = null != spscLinkedArrayQueue ? spscLinkedArrayQueue.poll() : null;
                boolean z2 = null == poll;
                if (z && z2) {
                    Throwable terminate2 = this.errors.terminate();
                    if (null != terminate2) {
                        observer.onError(terminate2);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                }
                if (!z2) {
                    observer.onNext(poll);
                } else {
                    i2 = addAndGet(-i2);
                    if (0 == i2) {
                        return;
                    }
                }
            }
            clear();
        }

        final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.MaybeObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onSuccess(R r) {
                FlatMapMaybeObserver.this.innerSuccess(this, r);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                FlatMapMaybeObserver.this.innerError(this, th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                FlatMapMaybeObserver.this.innerComplete(this);
            }

            @Override // io.reactivex.disposables.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
