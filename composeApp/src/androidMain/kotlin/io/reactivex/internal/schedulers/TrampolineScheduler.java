package io.reactivex.internal.schedulers;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class TrampolineScheduler extends Scheduler {
    private static final TrampolineScheduler INSTANCE = new TrampolineScheduler();
    public static TrampolineScheduler instance() {
        return INSTANCE;
    }
    public Scheduler.Worker createWorker() {
        return new TrampolineWorker();
    }
    TrampolineScheduler() {
    }
    public Disposable scheduleDirect(final Runnable runnable) {
        RxJavaPlugins.onSchedule(runnable).run();
        return EmptyDisposable.INSTANCE;
    }
    public Disposable scheduleDirect(final Runnable runnable, final long j2, final TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j2);
            RxJavaPlugins.onSchedule(runnable).run();
        } catch (final InterruptedException e2) {
            Thread.currentThread().interrupt();
            RxJavaPlugins.onError(e2);
        }
        return EmptyDisposable.INSTANCE;
    }
    static final class TrampolineWorker extends Scheduler.Worker {
        volatile boolean disposed;
        final PriorityBlockingQueue<TimedRunnable> queue = new PriorityBlockingQueue<>();
        private final AtomicInteger wip = new AtomicInteger();
        final AtomicInteger counter = new AtomicInteger();

        TrampolineWorker() {
        }

        @Override // io.reactivex.Scheduler.Worker
        public Disposable schedule(Runnable runnable) {
            return enqueue (runnable, this.now(TimeUnit.MILLISECONDS));
        }

        @Override // io.reactivex.Scheduler.Worker
        public Disposable schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
            long now = this.now(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j2);
            return enqueue (new SleepingRunnable(runnable, this, now), now);
        }

        Disposable enqueue(Runnable runnable, long j2) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TimedRunnable timedRunnable = new TimedRunnable(runnable, Long.valueOf(j2), this.counter.incrementAndGet());
            this.queue.add(timedRunnable);
            if (0 == wip.getAndIncrement ()) {
                int i2 = 1;
                while (!this.disposed) {
                    TimedRunnable poll = this.queue.poll();
                    if (null != poll) {
                        if (!poll.disposed) {
                            poll.run.run();
                        }
                    } else {
                        i2 = this.wip.addAndGet(-i2);
                        if (0 == i2) {
                            return EmptyDisposable.INSTANCE;
                        }
                    }
                }
                this.queue.clear();
                return EmptyDisposable.INSTANCE;
            }
            return Disposables.fromRunnable(new AppendToQueueTask(timedRunnable));
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.disposables.Disposable
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

        final class AppendToQueueTask implements Runnable {
            final TimedRunnable timedRunnable;

            AppendToQueueTask(TimedRunnable timedRunnable) {
                this.timedRunnable = timedRunnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.timedRunnable.disposed = true;
                TrampolineWorker.this.queue.remove(this.timedRunnable);
            }
        }
    }
    static final class TimedRunnable implements Comparable<TimedRunnable> {
        final int count;
        volatile boolean disposed;
        final long execTime;
        final Runnable run;

        TimedRunnable(Runnable runnable, Long l, int i2) {
            this.run = runnable;
            this.execTime = l.longValue();
            this.count = i2;
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable timedRunnable) {
            int compare = ObjectHelper.compare(this.execTime, timedRunnable.execTime);
            return 0 == compare ? ObjectHelper.compare(this.count, timedRunnable.count) : compare;
        }
    }
    static final class SleepingRunnable implements Runnable {
        private final long execTime;
        private final Runnable run;
        private final TrampolineWorker worker;

        SleepingRunnable(Runnable runnable, TrampolineWorker trampolineWorker, long j2) {
            this.run = runnable;
            this.worker = trampolineWorker;
            this.execTime = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.worker.disposed) {
                return;
            }
            long now = this.worker.now(TimeUnit.MILLISECONDS);
            long j2 = this.execTime;
            if (j2 > now) {
                try {
                    Thread.sleep(j2 - now);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    RxJavaPlugins.onError(e2);
                    return;
                }
            }
            if (this.worker.disposed) {
                return;
            }
            this.run.run();
        }
    }
}
