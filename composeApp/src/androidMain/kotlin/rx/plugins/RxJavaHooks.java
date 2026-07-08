package rx.plugins;

import java.util.concurrent.ScheduledExecutorService;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.SingleFromObservable;
import rx.internal.operators.SingleToObservable;


public final class RxJavaHooks {
    static volatile Func1<Completable.OnSubscribe, Completable.OnSubscribe> onCompletableCreate;
    static volatile Func1<Completable.Operator, Completable.Operator> onCompletableLift;
    static volatile Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Action1<Throwable> onError;
    static volatile Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService;
    static volatile Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Single.OnSubscribe, Single.OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;
    static {
        init();
    }
    private RxJavaHooks() {
        throw new IllegalStateException("No instances!");
    }
    static void init() {
        onError = new Action1<Throwable>() { // from class: rx.plugins.RxJavaHooks.1
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        };
        onObservableStart = new Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.2
            @Override // rx.functions.Func2
            public Observable.OnSubscribe call(Observable observable, Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(observable, onSubscribe);
            }
        };
        onObservableReturn = new Func1<Subscription, Subscription>() { // from class: rx.plugins.RxJavaHooks.3
            @Override // rx.functions.Func1
            public Subscription call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onSingleStart = new Func2<Single, Single.OnSubscribe, Single.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.4
            @Override // rx.functions.Func2
            public Single.OnSubscribe call(Single single, Single.OnSubscribe onSubscribe) {
                RxJavaSingleExecutionHook singleExecutionHook = RxJavaPlugins.getInstance().getSingleExecutionHook();
                return singleExecutionHook == RxJavaSingleExecutionHookDefault.getInstance() ? onSubscribe : new SingleFromObservable(singleExecutionHook.onSubscribeStart(single, new SingleToObservable(onSubscribe)));
            }
        };
        onSingleReturn = new Func1<Subscription, Subscription>() { // from class: rx.plugins.RxJavaHooks.5
            @Override // rx.functions.Func1
            public Subscription call(Subscription subscription) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(subscription);
            }
        };
        onCompletableStart = new Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.6
            @Override // rx.functions.Func2
            public Completable.OnSubscribe call(Completable completable, Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(completable, onSubscribe);
            }
        };
        onScheduleAction = new Func1<Action0, Action0>() { // from class: rx.plugins.RxJavaHooks.7
            @Override // rx.functions.Func1
            public Action0 call(Action0 action0) {
                return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(action0);
            }
        };
        onObservableSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.8
            @Override // rx.functions.Func1
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(th);
            }
        };
        onObservableLift = new Func1<Observable.Operator, Observable.Operator>() { // from class: rx.plugins.RxJavaHooks.9
            @Override // rx.functions.Func1
            public Observable.Operator call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(operator);
            }
        };
        onSingleSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.10
            @Override // rx.functions.Func1
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(th);
            }
        };
        onSingleLift = new Func1<Observable.Operator, Observable.Operator>() { // from class: rx.plugins.RxJavaHooks.11
            @Override // rx.functions.Func1
            public Observable.Operator call(Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(operator);
            }
        };
        onCompletableSubscribeError = new Func1<Throwable, Throwable>() { // from class: rx.plugins.RxJavaHooks.12
            @Override // rx.functions.Func1
            public Throwable call(Throwable th) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(th);
            }
        };
        onCompletableLift = new Func1<Completable.Operator, Completable.Operator>() { // from class: rx.plugins.RxJavaHooks.13
            @Override // rx.functions.Func1
            public Completable.Operator call(Completable.Operator operator) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(operator);
            }
        };
        initCreate();
    }
    static void initCreate() {
        onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.14
            @Override // rx.functions.Func1
            public Observable.OnSubscribe call(Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(onSubscribe);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.15
            @Override // rx.functions.Func1
            public Single.OnSubscribe call(Single.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(onSubscribe);
            }
        };
        onCompletableCreate = new Func1<Completable.OnSubscribe, Completable.OnSubscribe>() { // from class: rx.plugins.RxJavaHooks.16
            @Override // rx.functions.Func1
            public Completable.OnSubscribe call(Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(onSubscribe);
            }
        };
    }
    public static void onError(Throwable th) {
        Action1<Throwable> action1 = onError;
        if (action1 != null) {
            try {
                action1.call(th);
                return;
            } catch (Throwable th2) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                signalUncaught(th2);
            }
        }
        signalUncaught(th);
    }
    static void signalUncaught(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
    public static <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> onSubscribe) {
        Func1<Observable.OnSubscribe, Observable.OnSubscribe> func1 = onObservableCreate;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }
    public static <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        Func1<Single.OnSubscribe, Single.OnSubscribe> func1 = onSingleCreate;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }
    public static Completable.OnSubscribe onCreate(Completable.OnSubscribe onSubscribe) {
        Func1<Completable.OnSubscribe, Completable.OnSubscribe> func1 = onCompletableCreate;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }
    public static Action0 onScheduledAction(Action0 action0) {
        Func1<Action0, Action0> func1 = onScheduleAction;
        return func1 != null ? func1.call(action0) : action0;
    }
    public static <T> Observable.OnSubscribe<T> onObservableStart(Observable<T> observable, Observable.OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2 = onObservableStart;
        return func2 != null ? func2.call(observable, onSubscribe) : onSubscribe;
    }
    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = onObservableReturn;
        return func1 != null ? func1.call(subscription) : subscription;
    }
    public static Throwable onObservableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onObservableSubscribeError;
        return func1 != null ? func1.call(th) : th;
    }
    public static <T, R> Observable.Operator<R, T> onObservableLift(Observable.Operator<R, T> operator) {
        Func1<Observable.Operator, Observable.Operator> func1 = onObservableLift;
        return func1 != null ? func1.call(operator) : operator;
    }
    public static <T> Single.OnSubscribe<T> onSingleStart(Single<T> single, Single.OnSubscribe<T> onSubscribe) {
        Func2<Single, Single.OnSubscribe, Single.OnSubscribe> func2 = onSingleStart;
        return func2 != null ? func2.call(single, onSubscribe) : onSubscribe;
    }
    public static Subscription onSingleReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = onSingleReturn;
        return func1 != null ? func1.call(subscription) : subscription;
    }
    public static Throwable onSingleError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onSingleSubscribeError;
        return func1 != null ? func1.call(th) : th;
    }
    public static <T> Completable.OnSubscribe onCompletableStart(Completable completable, Completable.OnSubscribe onSubscribe) {
        Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> func2 = onCompletableStart;
        return func2 != null ? func2.call(completable, onSubscribe) : onSubscribe;
    }
    public static Throwable onCompletableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = onCompletableSubscribeError;
        return func1 != null ? func1.call(th) : th;
    }
    public static Func0<? extends ScheduledExecutorService> getOnGenericScheduledExecutorService() {
        return onGenericScheduledExecutorService;
    }
}
