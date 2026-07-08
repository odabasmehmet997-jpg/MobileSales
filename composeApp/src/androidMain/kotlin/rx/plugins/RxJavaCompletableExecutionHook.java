package rx.plugins;

import rx.Completable;

public abstract class RxJavaCompletableExecutionHook {
    public Completable.OnSubscribe onCreate(Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }
    public Completable.Operator onLift(Completable.Operator operator) {
        return operator;
    }
    public Throwable onSubscribeError(Throwable th) {
        return th;
    }
    public Completable.OnSubscribe onSubscribeStart(Completable completable, Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }
    public String toString() {
        return "RxJavaCompletableExecutionHook";
    }
}
