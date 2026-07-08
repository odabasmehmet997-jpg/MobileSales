package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;

public abstract class Scheduler {
    public abstract Worker createWorker();
    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j2, TimeUnit timeUnit);

        public long now() {
            return System.currentTimeMillis();
        }
    }
    public long now() {
        return System.currentTimeMillis();
    }
}
