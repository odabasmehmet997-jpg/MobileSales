package rx.internal.schedulers;

import rx.Scheduler;
import rx.exceptions.Exceptions;
import rx.functions.Action0;

class SleepingAction implements Action0 {
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;
    public SleepingAction(Action0 action0, Scheduler.Worker worker, long j2) {
        this.underlying = action0;
        this.innerScheduler = worker;
        this.execTime = j2;
    }
    public void call() {
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        long jNow = this.execTime - this.innerScheduler.now();
        if (jNow > 0) {
            try {
                Thread.sleep(jNow);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                Exceptions.propagate(e2);
            }
        }
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        this.underlying.call();
    }
}
