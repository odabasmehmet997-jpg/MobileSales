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
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.subjects.UnicastSubject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public final class ObservableWindowTimed<T> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    public ObservableWindowTimed(ObservableSource<T> observableSource, long j2, long j3, TimeUnit timeUnit, Scheduler scheduler, long j4, int i2, boolean z) {
        super(observableSource);
        this.timespan = j2;
        this.timeskip = j3;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.maxSize = j4;
        this.bufferSize = i2;
        this.restartTimerOnMaxSize = z;
    }
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        SerializedObserver serializedObserver = new SerializedObserver(observer);
        long j2 = this.timespan;
        long j3 = this.timeskip;
        if (j2 == j3) {
            long j4 = this.maxSize;
            if (LocationRequestCompat.PASSIVE_INTERVAL == j4) {
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
                return;
            }
        }
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
    static final class WindowExactUnboundedObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable, Runnable {
        static final Object NEXT = new Object();
        final int bufferSize;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        UnicastSubject<T> window;

        WindowExactUnboundedObserver(Observer<? super Observable<T>> observer, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2) {
            super(observer, new MpscLinkedQueue());
            this.timer = new SequentialDisposable();
            this.timespan = j2;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.bufferSize = i2;
        }
        public <V> void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.window = UnicastSubject.create(this.bufferSize);
                Observer<? super V> observer = (Observer<? super V>) downstream;
                observer.onSubscribe(this);
                observer.onNext(this.window);
                if (cancelled) {
                    return;
                }
                Scheduler scheduler = this.scheduler;
                long j2 = this.timespan;
                this.timer.replace(scheduler.schedulePeriodicallyDirect(this, j2, j2, this.unit));
            }
        }

        
        public void onNext(Object t) {
            if (this.terminated) {
                return;
            }
            if (this.fastEnter()) {
                this.window.onNext(t);
                if (0 == this.leave(-1)) {
                    return;
                }
            } else {
                queue.offer(NotificationLite.next(t));
                if (!this.enter()) {
                    return;
                }
            }
            drainLoop();
        }

        
        public void onError(Throwable th) {
            error = th;
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onError(th);
        }

        
        public void onComplete() {
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onComplete();
        }

        
        public void dispose() {
            cancelled = true;
        }

        
        public boolean isDisposed() {
            return cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (cancelled) {
                this.terminated = true;
            }
            queue.offer(NEXT);
            if (this.enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) queue;
            Observer<? super V> observer = downstream;
            UnicastSubject<T> unicastSubject = this.window;
            int i2 = 1;
            while (true) {
                boolean z = this.terminated;
                boolean z2 = done;
                Object poll = mpscLinkedQueue.poll();
                if (!z2 || (null != poll && poll != NEXT)) {
                    if (null != poll) {
                        if (poll == NEXT) {
                            unicastSubject.onComplete();
                            if (!z) {
                                unicastSubject = UnicastSubject.create(this.bufferSize);
                                this.window = unicastSubject;
                                observer.onNext(unicastSubject);
                            } else {
                                this.upstream.dispose();
                            }
                        } else {
                            unicastSubject.onNext(NotificationLite.getValue(poll));
                        }
                    } else {
                        i2 = this.leave(-i2);
                        if (0 == i2) {
                            return;
                        }
                    }
                }
            }
        }

        @Override
        public void accept(Observer<? super Observable<T>> observer, Object o) {
            
        }
    }
    static final class WindowExactBoundedObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        final int bufferSize;
        long count;
        final long maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        UnicastSubject<T> window;
        final Scheduler.Worker worker;

        WindowExactBoundedObserver(Observer<? super Observable<T>> observer, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2, long j3, boolean z) {
            super(observer, new MpscLinkedQueue());
            this.timer = new SequentialDisposable();
            this.timespan = j2;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.bufferSize = i2;
            this.maxSize = j3;
            this.restartTimerOnMaxSize = z;
            if (z) {
                this.worker = scheduler.createWorker();
            } else {
                this.worker = null;
            }
        }

        
        public void onSubscribe(Disposable disposable) {
            Disposable schedulePeriodicallyDirect;
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                Observer<? super V> observer = downstream;
                observer.onSubscribe(this);
                if (cancelled) {
                    return;
                }
                UnicastSubject<T> create = UnicastSubject.create(this.bufferSize);
                this.window = create;
                observer.onNext(create);
                ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                if (this.restartTimerOnMaxSize) {
                    Scheduler.Worker worker = this.worker;
                    long j2 = this.timespan;
                    schedulePeriodicallyDirect = worker.schedulePeriodically(consumerIndexHolder, j2, j2, this.unit);
                } else {
                    Scheduler scheduler = this.scheduler;
                    long j3 = this.timespan;
                    schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(consumerIndexHolder, j3, j3, this.unit);
                }
                this.timer.replace(schedulePeriodicallyDirect);
            }
        }

        
        public void onNext(Object t) {
            if (this.terminated) {
                return;
            }
            if (this.fastEnter()) {
                UnicastSubject<T> unicastSubject = this.window;
                unicastSubject.onNext(t);
                long j2 = this.count + 1;
                if (j2 >= this.maxSize) {
                    this.producerIndex++;
                    this.count = 0L;
                    unicastSubject.onComplete();
                    UnicastSubject<T> create = UnicastSubject.create(this.bufferSize);
                    this.window = create;
                    downstream.onNext(create);
                    if (this.restartTimerOnMaxSize) {
                        this.timer.get().dispose();
                        Scheduler.Worker worker = this.worker;
                        ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                        long j3 = this.timespan;
                        DisposableHelper.replace(this.timer, worker.schedulePeriodically(consumerIndexHolder, j3, j3, this.unit));
                    }
                } else {
                    this.count = j2;
                }
                if (0 == this.leave(-1)) {
                    return;
                }
            } else {
                queue.offer(NotificationLite.next(t));
                if (!this.enter()) {
                    return;
                }
            }
            drainLoop();
        }

        
        public void onError(Throwable th) {
            error = th;
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onError(th);
        }

        
        public void onComplete() {
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onComplete();
        }

        
        public void dispose() {
            cancelled = true;
        }

        
        public boolean isDisposed() {
            return cancelled;
        }

        void disposeTimer() {
            DisposableHelper.dispose(this.timer);
            Scheduler.Worker worker = this.worker;
            if (null != worker) {
                worker.dispose();
            }
        }

        void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) queue;
            Observer<? super V> observer = downstream;
            UnicastSubject<T> unicastSubject = this.window;
            int i2 = 1;
            while (!this.terminated) {
                boolean z = done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = null == poll;
                boolean z3 = poll instanceof ConsumerIndexHolder;
                if (z && (z2 || z3)) {
                    this.window = null;
                    mpscLinkedQueue.clear();
                    Throwable th = error;
                    if (null != th) {
                        unicastSubject.onError(th);
                    } else {
                        unicastSubject.onComplete();
                    }
                    disposeTimer();
                    return;
                }
                if (z2) {
                    i2 = this.leave(-i2);
                    if (0 == i2) {
                        return;
                    }
                } else if (z3) {
                    ConsumerIndexHolder consumerIndexHolder = (ConsumerIndexHolder) poll;
                    if (!this.restartTimerOnMaxSize || this.producerIndex == consumerIndexHolder.index) {
                        unicastSubject.onComplete();
                        this.count = 0L;
                        unicastSubject = UnicastSubject.create(this.bufferSize);
                        this.window = unicastSubject;
                        observer.onNext(unicastSubject);
                    }
                } else {
                    unicastSubject.onNext(NotificationLite.getValue(poll));
                    long j2 = this.count + 1;
                    if (j2 >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0L;
                        unicastSubject.onComplete();
                        unicastSubject = UnicastSubject.create(this.bufferSize);
                        this.window = unicastSubject;
                        downstream.onNext(unicastSubject);
                        if (this.restartTimerOnMaxSize) {
                            Disposable disposable = this.timer.get();
                            disposable.dispose();
                            Scheduler.Worker worker = this.worker;
                            ConsumerIndexHolder consumerIndexHolder2 = new ConsumerIndexHolder(this.producerIndex, this);
                            long j3 = this.timespan;
                            Disposable schedulePeriodically = worker.schedulePeriodically(consumerIndexHolder2, j3, j3, this.unit);
                            if (!this.timer.compareAndSet(disposable, schedulePeriodically)) {
                                schedulePeriodically.dispose();
                            }
                        }
                    } else {
                        this.count = j2;
                    }
                }
            }
            this.upstream.dispose();
            mpscLinkedQueue.clear();
            disposeTimer();
        }

        class ConsumerIndexHolder(long index, WindowExactBoundedObserver<?> parent) implements Runnable {

                    public void run() {
                        WindowExactBoundedObserver<?> windowExactBoundedObserver = this.parent;
                        if (!windowExactBoundedObserver.cancelled) {
                            windowExactBoundedObserver.queue.offer(this);
                        } else {
                            windowExactBoundedObserver.terminated = true;
                        }
                        if (windowExactBoundedObserver.enter()) {
                            windowExactBoundedObserver.drainLoop();
                        }
                    }
                }
    }
    static final class WindowSkipObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable, Runnable {
        final int bufferSize;
        volatile boolean terminated;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        final List<UnicastSubject<T>> windows;
        final Scheduler.Worker worker;

        WindowSkipObserver(Observer<? super Observable<T>> observer, long j2, long j3, TimeUnit timeUnit, Scheduler.Worker worker, int i2) {
            super(observer, new MpscLinkedQueue());
            this.timespan = j2;
            this.timeskip = j3;
            this.unit = timeUnit;
            this.worker = worker;
            this.bufferSize = i2;
            this.windows = new LinkedList();
        }

        
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                downstream.onSubscribe(this);
                if (cancelled) {
                    return;
                }
                UnicastSubject<T> create = UnicastSubject.create(this.bufferSize);
                this.windows.add(create);
                downstream.onNext(create);
                this.worker.schedule(new CompletionTask(create), this.timespan, this.unit);
                Scheduler.Worker worker = this.worker;
                long j2 = this.timeskip;
                worker.schedulePeriodically(this, j2, j2, this.unit);
            }
        }

        
        public void onNext(Object t) {
            if (this.fastEnter()) {
                Iterator<UnicastSubject<T>> it = this.windows.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t);
                }
                if (0 == this.leave(-1)) {
                    return;
                }
            } else {
                queue.offer(t);
                if (!this.enter()) {
                    return;
                }
            }
            drainLoop();
        }

        
        public void onError(Throwable th) {
            error = th;
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onError(th);
        }

        
        public void onComplete() {
            done = true;
            if (this.enter()) {
                drainLoop();
            }
            downstream.onComplete();
        }

        
        public void dispose() {
            cancelled = true;
        }

        
        public boolean isDisposed() {
            return cancelled;
        }

        void complete(UnicastSubject<T> unicastSubject) {
            queue.offer(new SubjectWork(unicastSubject, false));
            if (this.enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) queue;
            Observer<? super V> observer = downstream;
            List<UnicastSubject<T>> list = this.windows;
            int i2 = 1;
            while (!this.terminated) {
                boolean z = done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = null == poll;
                boolean z3 = poll instanceof SubjectWork;
                if (z && (z2 || z3)) {
                    mpscLinkedQueue.clear();
                    Throwable th = error;
                    if (null != th) {
                        Iterator<UnicastSubject<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<UnicastSubject<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    list.clear();
                    this.worker.dispose();
                    return;
                }
                if (z2) {
                    i2 = this.leave(-i2);
                    if (0 == i2) {
                        return;
                    }
                } else if (z3) {
                    SubjectWork subjectWork = (SubjectWork) poll;
                    if (subjectWork.open) {
                        if (!cancelled) {
                            UnicastSubject<T> create = UnicastSubject.create(this.bufferSize);
                            list.add(create);
                            observer.onNext(create);
                            this.worker.schedule(new CompletionTask(create), this.timespan, this.unit);
                        }
                    } else {
                        list.remove(subjectWork.w);
                        subjectWork.w.onComplete();
                        if (list.isEmpty() && cancelled) {
                            this.terminated = true;
                        }
                    }
                } else {
                    Iterator<UnicastSubject<T>> it3 = list.iterator();
                    while (it3.hasNext()) {
                        it3.next().onNext(poll);
                    }
                }
            }
            this.upstream.dispose();
            mpscLinkedQueue.clear();
            list.clear();
            this.worker.dispose();
        }

        @Override // java.lang.Runnable
        public void run() {
            SubjectWork subjectWork = new SubjectWork(UnicastSubject.create(this.bufferSize), true);
            if (!cancelled) {
                queue.offer(subjectWork);
            }
            if (this.enter()) {
                drainLoop();
            }
        }

        class SubjectWork<T>(UnicastSubject<T> w, boolean open) {
        }

        final class CompletionTask implements Runnable {
            private final UnicastSubject<T> w;

            CompletionTask(UnicastSubject<T> unicastSubject) {
                this.w = unicastSubject;
            }

            @Override // java.lang.Runnable
            public void run() {
                WindowSkipObserver.this.complete(this.w);
            }
        }
    }
}
