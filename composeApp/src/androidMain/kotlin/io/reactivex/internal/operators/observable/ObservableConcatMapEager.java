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
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.InnerQueuedObserver;
import io.reactivex.internal.observers.InnerQueuedObserverSupport;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;



public final class ObservableConcatMapEager<T, R> extends AbstractObservableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    public ObservableConcatMapEager(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<? extends R>> function, ErrorMode errorMode, int i2, int i3) {
        super(observableSource);
        this.mapper = function;
        this.errorMode = errorMode;
        this.maxConcurrency = i2;
        this.prefetch = i3;
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

    static final class ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable, InnerQueuedObserverSupport<R> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        volatile boolean done;
        final Observer<? super R> downstream;
        final ErrorMode errorMode;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        SimpleQueue<T> queue;
        int sourceMode;
        Disposable upstream;
        final AtomicThrowable error = new AtomicThrowable();
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque<>();

        ConcatMapEagerMainObserver(Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> function, int i2, int i3, ErrorMode errorMode) {
            this.downstream = observer;
            this.mapper = function;
            this.maxConcurrency = i2;
            this.prefetch = i3;
            this.errorMode = errorMode;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                if (disposable instanceof QueueDisposable queueDisposable) {
                    int requestFusion = queueDisposable.requestFusion(3);
                    if (1 == requestFusion) {
                        this.sourceMode = 1;
                        this.queue = queueDisposable;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    }
                    if (2 == requestFusion) {
                        this.sourceMode = 2;
                        this.queue = queueDisposable;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(Object t) {
            if (0 == sourceMode) {
                this.queue.offer(t);
            }
            drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.upstream.dispose();
            drainAndDispose();
        }

        void drainAndDispose() {
            if (0 == this.getAndIncrement()) {
                do {
                    this.queue.clear();
                    disposeAll();
                } while (0 != this.decrementAndGet());
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeAll() {
            InnerQueuedObserver<R> innerQueuedObserver = this.current;
            if (null != innerQueuedObserver) {
                innerQueuedObserver.dispose();
            }
            while (true) {
                InnerQueuedObserver<R> poll = this.observers.poll();
                if (null == poll) {
                    return;
                } else {
                    poll.dispose();
                }
            }
        }

        @Override // io.reactivex.internal.observers.InnerQueuedObserverSupport
        public void innerNext(InnerQueuedObserver<R> innerQueuedObserver, R r) {
            innerQueuedObserver.queue().offer(r);
            drain();
        }

        @Override // io.reactivex.internal.observers.InnerQueuedObserverSupport
        public void innerError(InnerQueuedObserver<R> innerQueuedObserver, Throwable th) {
            if (this.error.addThrowable(th)) {
                if (ErrorMode.IMMEDIATE == errorMode) {
                    this.upstream.dispose();
                }
                innerQueuedObserver.setDone();
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.internal.observers.InnerQueuedObserverSupport
        public void innerComplete(InnerQueuedObserver<R> innerQueuedObserver) {
            innerQueuedObserver.setDone();
            drain();
        }

        @Override // io.reactivex.internal.observers.InnerQueuedObserverSupport
        public void drain() {
            R poll;
            boolean z;
            if (0 != this.getAndIncrement()) {
                return;
            }
            SimpleQueue<T> simpleQueue = this.queue;
            ArrayDeque<InnerQueuedObserver<R>> arrayDeque = this.observers;
            Observer<? super R> observer = this.downstream;
            ErrorMode errorMode = this.errorMode;
            int i2 = 1;
            while (true) {
                int i3 = this.activeCount;
                while (i3 != this.maxConcurrency) {
                    if (this.cancelled) {
                        simpleQueue.clear();
                        disposeAll();
                        return;
                    }
                    if (ErrorMode.IMMEDIATE == errorMode && null != error.get ()) {
                        simpleQueue.clear();
                        disposeAll();
                        observer.onError(this.error.terminate());
                        return;
                    }
                    try {
                        T poll2 = simpleQueue.poll();
                        if (null == poll2) {
                            break;
                        }
                        ObservableSource observableSource = ObjectHelper.requireNonNull(this.mapper.apply(poll2), "The mapper returned a null ObservableSource");
                        InnerQueuedObserver<R> innerQueuedObserver = new InnerQueuedObserver<>(this, this.prefetch);
                        arrayDeque.offer(innerQueuedObserver);
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
                        i3++;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.upstream.dispose();
                        simpleQueue.clear();
                        disposeAll();
                        this.error.addThrowable(th);
                        observer.onError(this.error.terminate());
                        return;
                    }
                }
                this.activeCount = i3;
                if (this.cancelled) {
                    simpleQueue.clear();
                    disposeAll();
                    return;
                }
                if (ErrorMode.IMMEDIATE == errorMode && null != error.get ()) {
                    simpleQueue.clear();
                    disposeAll();
                    observer.onError(this.error.terminate());
                    return;
                }
                InnerQueuedObserver<R> innerQueuedObserver2 = this.current;
                if (null == innerQueuedObserver2) {
                    if (ErrorMode.BOUNDARY == errorMode && null != error.get ()) {
                        simpleQueue.clear();
                        disposeAll();
                        observer.onError(this.error.terminate());
                        return;
                    }
                    boolean z2 = this.done;
                    InnerQueuedObserver<R> poll3 = arrayDeque.poll();
                    boolean z3 = null == poll3;
                    if (z2 && z3) {
                        if (null != error.get ()) {
                            simpleQueue.clear();
                            disposeAll();
                            observer.onError(this.error.terminate());
                            return;
                        }
                        observer.onComplete();
                        return;
                    }
                    if (!z3) {
                        this.current = poll3;
                    }
                    innerQueuedObserver2 = poll3;
                }
                if (null != innerQueuedObserver2) {
                    SimpleQueue<R> queue = innerQueuedObserver2.queue();
                    while (!this.cancelled) {
                        boolean isDone = innerQueuedObserver2.isDone();
                        if (ErrorMode.IMMEDIATE == errorMode && null != error.get ()) {
                            simpleQueue.clear();
                            disposeAll();
                            observer.onError(this.error.terminate());
                            return;
                        }
                        try {
                            poll = queue.poll();
                            z = null == poll;
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            this.error.addThrowable(th2);
                            this.current = null;
                            this.activeCount--;
                        }
                        if (isDone && z) {
                            this.current = null;
                            this.activeCount--;
                        } else if (!z) {
                            observer.onNext(poll);
                        }
                    }
                    simpleQueue.clear();
                    disposeAll();
                    return;
                }
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
    }
}
