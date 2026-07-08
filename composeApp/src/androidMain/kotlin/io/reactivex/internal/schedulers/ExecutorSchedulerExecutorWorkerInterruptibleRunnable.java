package io.reactivex.internal.schedulers;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import java.util.concurrent.atomic.AtomicInteger;



final class ExecutorSchedulerExecutorWorkerInterruptibleRunnable extends AtomicInteger implements Runnable, Disposable {
    static final int FINISHED = 2;
    static final int INTERRUPTED = 4;
    static final int INTERRUPTING = 3;
    static final int READY = 0;
    static final int RUNNING = 1;
    private static final long serialVersionUID = -3603436687413320876L;
    final Runnable run;
    final DisposableContainer tasks;
    volatile Thread thread;
    ExecutorSchedulerExecutorWorkerInterruptibleRunnable(Runnable runnable, DisposableContainer disposableContainer) {
        this.run = runnable;
        this.tasks = disposableContainer;
    }
    public void run() {
        if (0 == this.get()) {
            this.thread = Thread.currentThread();
            if (compareAndSet (0, 1)) {
                try {
                    this.run.run();
                    this.thread = null;
                    if (compareAndSet (1, 2)) {
                        cleanup ();
                        return;
                    }
                    while (3 == this.get()) {
                        Thread.yield();
                    }
                    Thread.interrupted();
                    return;
                } catch (Throwable th) {
                    this.thread = null;
                    if (compareAndSet (1, 2)) {
                        cleanup ();
                    } else {
                        while (3 == this.get()) {
                            Thread.yield();
                        }
                        Thread.interrupted();
                    }
                    throw th;
                }
            }
            this.thread = null;
        }
    }
    public void dispose() {
        while (true) {
            int i2 = get ();
            if (2 <= i2) {
                return;
            }
            if (0 == i2) {
                if (compareAndSet (0, 4)) {
                    cleanup ();
                    return;
                }
            } else if (compareAndSet (1, 3)) {
                Thread thread = this.thread;
                if (null != thread) {
                    thread.interrupt();
                    this.thread = null;
                }
                set (4);
                cleanup ();
                return;
            }
        }
    }
    void cleanup() {
        DisposableContainer disposableContainer = this.tasks;
        if (null != disposableContainer) {
            disposableContainer.delete(this);
        }
    }
    public boolean isDisposed() {
        return 2 <= this.get();
    }

    @Override
    public void onError(Throwable th) {

    }

    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
}
