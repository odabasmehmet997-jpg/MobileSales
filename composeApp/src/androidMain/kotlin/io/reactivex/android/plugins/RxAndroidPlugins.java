package io.reactivex.android.plugins;

import io.reactivex.Scheduler;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;


public final class RxAndroidPlugins {
    private static volatile Function<Callable<Scheduler>, Scheduler> onInitMainThreadHandler;
    private static volatile Function<Scheduler, Scheduler> onMainThreadHandler;
    public static Scheduler initMainThreadScheduler(Callable<Scheduler> callable) {
        if (null == callable) {
            throw new NullPointerException("scheduler == null");
        }
        Function<Callable<Scheduler>, Scheduler> function = onInitMainThreadHandler;
        if (null == function) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }
    public static Scheduler onMainThreadScheduler(Scheduler scheduler) {
        if (null == scheduler) {
            throw new NullPointerException("scheduler == null");
        }
        Function<Scheduler, Scheduler> function = onMainThreadHandler;
        return null == function ? scheduler : apply(function, scheduler);
    }
    static Scheduler callRequireNonNull(Callable<Scheduler> callable) {
        try {
            Scheduler call = callable.call();
            if (null != call) {
                return call;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }
    static Scheduler applyRequireNonNull(Function<Callable<Scheduler>, Scheduler> function, Callable<Scheduler> callable) {
        Scheduler scheduler = apply(function, callable);
        if (null != scheduler) {
            return scheduler;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }
    static <T, R> R apply(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }
    private RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }
}
