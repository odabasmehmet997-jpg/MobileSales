package io.reactivex.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

final class HandlerScheduler extends Scheduler {
    private final boolean async;
    private final Handler handler;
    HandlerScheduler(Handler handler, boolean z) {
        this.handler = handler;
        this.async = z;
    }
    public Disposable scheduleDirect(Runnable runnable, long j2, TimeUnit timeUnit) {
        if (null == runnable) {
            throw new NullPointerException("run == null");
        }
        if (null == timeUnit) {
            throw new NullPointerException("unit == null");
        }
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(this.handler, RxJavaPlugins.onSchedule(runnable));
        Message obtain = Message.obtain(this.handler, scheduledRunnable);
        if (this.async) {
            obtain.setAsynchronous(true);
        }
        this.handler.sendMessageDelayed(obtain, timeUnit.toMillis(j2));
        return scheduledRunnable;
    }
    public Scheduler.Worker createWorker() {
        return new HandlerWorker(handler, async);
    }
    private static final class HandlerWorker extends Scheduler.Worker {
        private final boolean async;
        private volatile boolean disposed;
        private final Handler handler;

        HandlerWorker(Handler handler, boolean z) {
            this.handler = handler;
            this.async = z;
        }

        @SuppressLint("NewApi")
        public Disposable schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
            if (null == runnable) {
                throw new NullPointerException("run == null");
            }
            if (null == timeUnit) {
                throw new NullPointerException("unit == null");
            }
            if (this.disposed) {
                return Disposables.disposed();
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(this.handler, RxJavaPlugins.onSchedule(runnable));
            Message obtain = Message.obtain(this.handler, scheduledRunnable);
            obtain.obj = this;
            if (this.async) {
                obtain.setAsynchronous(true);
            }
            this.handler.sendMessageDelayed(obtain, timeUnit.toMillis(j2));
            if (!this.disposed) {
                return scheduledRunnable;
            }
            this.handler.removeCallbacks(scheduledRunnable);
            return Disposables.disposed();
        }
        public void dispose() {
            this.disposed = true;
            this.handler.removeCallbacksAndMessages(this);
        }
        public boolean isDisposed() {
            return this.disposed;
        }

        public void onError(Throwable th) {

        }
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }
    private static final class ScheduledRunnable implements Runnable, Disposable {
        private final Runnable delegate;
        private volatile boolean disposed;
        private final Handler handler;

        ScheduledRunnable(Handler handler, Runnable runnable) {
            this.handler = handler;
            this.delegate = runnable;
        }

        public void run() {
            try {
                this.delegate.run();
            } catch (Throwable th) {
                RxJavaPlugins.onError(th);
            }
        }
        public void dispose() {
            this.handler.removeCallbacks(this);
            this.disposed = true;
        }
        public boolean isDisposed() {
            return this.disposed;
        }

        public void onError(Throwable th) {

        }
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }
}
