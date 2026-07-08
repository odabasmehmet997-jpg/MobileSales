package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.schedulers.NewThreadWorker;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;


public abstract class Scheduler {
    static boolean IS_DRIFT_USE_NANOTIME = Boolean.getBoolean("rx2.scheduler.use-nanotime");
    static final long CLOCK_DRIFT_TOLERANCE_NANOSECONDS = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());
    public abstract Worker createWorker();
    static long computeNow(TimeUnit timeUnit) {
        if (!IS_DRIFT_USE_NANOTIME) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        return timeUnit.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }
    public long now(TimeUnit timeUnit) {
        return computeNow(timeUnit);
    }
    public Disposable scheduleDirect(Runnable runnable) {
        return scheduleDirect(runnable, 0L, TimeUnit.NANOSECONDS);
    }
    public Disposable scheduleDirect(Runnable runnable, long j2, TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        DisposeTask disposeTask = new DisposeTask(RxJavaPlugins.onSchedule(runnable), createWorker);
        createWorker.schedule(disposeTask, j2, timeUnit);
        return disposeTask;
    }
    public Disposable schedulePeriodicallyDirect(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        PeriodicDirectTask periodicDirectTask = new PeriodicDirectTask(RxJavaPlugins.onSchedule(runnable), createWorker);
        Disposable schedulePeriodically = createWorker.schedulePeriodically(periodicDirectTask, j2, j3, timeUnit);
        return EmptyDisposable.INSTANCE == schedulePeriodically ? schedulePeriodically : periodicDirectTask;
    }
    public static abstract class Worker implements Disposable {
        public abstract Disposable schedule(Runnable runnable, long j2, TimeUnit timeUnit);

        public Disposable schedule(Runnable runnable) {
            return schedule(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public Disposable schedulePeriodically(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable onSchedule = RxJavaPlugins.onSchedule(runnable);
            long nanos = timeUnit.toNanos(j3);
            long now = now(TimeUnit.NANOSECONDS);
            Disposable schedule = schedule(new PeriodicTask(now + timeUnit.toNanos(j2), onSchedule, now, sequentialDisposable2, nanos), j2, timeUnit);
            if (EmptyDisposable.INSTANCE == schedule) {
                return EmptyDisposable.INSTANCE;
            }
            sequentialDisposable.replace(schedule);
            return sequentialDisposable2;
        }

        public long now(TimeUnit timeUnit) {
            return Scheduler.computeNow(timeUnit);
        }

        final class PeriodicTask implements Runnable {
            long count;
            final Runnable decoratedRun;
            long lastNowNanoseconds;
            final long periodInNanoseconds;
            final SequentialDisposable sd;
            long startInNanoseconds;

            PeriodicTask(long j2, Runnable runnable, long j3, SequentialDisposable sequentialDisposable, long j4) {
                this.decoratedRun = runnable;
                this.sd = sequentialDisposable;
                this.periodInNanoseconds = j4;
                this.lastNowNanoseconds = j3;
                this.startInNanoseconds = j2;
            }
            public void run() {
                long j2;
                this.decoratedRun.run();
                if (this.sd.isDisposed()) {
                    return;
                }
                Worker worker = Worker.this;
                final TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                long now = worker.now(timeUnit);
                long j3 = Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS;
                long j4 = now + j3;
                long j5 = this.lastNowNanoseconds;
                if (j4 >= j5) {
                    long j6 = this.periodInNanoseconds;
                    if (now < j5 + j6 + j3) {
                        long j7 = this.startInNanoseconds;
                        long j8 = this.count + 1;
                        this.count = j8;
                        j2 = j7 + (j8 * j6);
                        this.lastNowNanoseconds = now;
                        this.sd.replace(Worker.this.schedule(this, j2 - now, timeUnit));
                    }
                }
                long j9 = this.periodInNanoseconds;
                long j10 = now + j9;
                long j11 = this.count + 1;
                this.count = j11;
                this.startInNanoseconds = j10 - (j9 * j11);
                j2 = j10;
                this.lastNowNanoseconds = now;
                this.sd.replace(Worker.this.schedule(this, j2 - now, timeUnit));
            }
        }
    }
    static final class PeriodicDirectTask implements Disposable, Runnable {
        volatile boolean disposed;
        final Runnable run;
        final Worker worker;

        PeriodicDirectTask(Runnable runnable, Worker worker) {
            this.run = runnable;
            this.worker = worker;
        }
        public void run() {
            if (this.disposed) {
                return;
            }
            try {
                this.run.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.worker.dispose();
                throw ExceptionHelper.wrapOrThrow(th);
            }
        }

        public void dispose() {
            this.disposed = true;
            this.worker.dispose();
        }
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }
    static final class DisposeTask implements Disposable, Runnable {
        final Runnable decoratedRun;
        Thread runner;
        final Worker w;

        DisposeTask(Runnable runnable, Worker worker) {
            this.decoratedRun = runnable;
            this.w = worker;
        }
        public void run() {
            this.runner = Thread.currentThread();
            try {
                this.decoratedRun.run();
            } finally {
                dispose();
                this.runner = null;
            }
        }
        public void dispose() {
            if (this.runner == Thread.currentThread()) {
                Worker worker = this.w;
                if (worker instanceof NewThreadWorker) {
                    ((NewThreadWorker) worker).shutdown();
                    return;
                }
            }
            this.w.dispose();
        }
        public boolean isDisposed() {
            return this.w.isDisposed();
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }
}
