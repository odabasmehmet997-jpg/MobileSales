package rx;

import java.util.concurrent.TimeUnit;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.operators.EmptyObservableHolder;
import rx.internal.operators.OnSubscribeLift;
import rx.internal.operators.OnSubscribeMap;
import rx.internal.operators.OnSubscribeSingle;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorReplay;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;

public class Observable<T> {
    OnSubscribe<T> onSubscribe = null;
    public Observable() {
    }
    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }
    public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    }
    protected Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }
    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<>(RxJavaHooks.onCreate(onSubscribe));
    }
    public static <T> Observable<T> unsafeCreate(OnSubscribe<T> onSubscribe) {
        return new Observable<>(RxJavaHooks.onCreate(onSubscribe));
    }
    public final <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
        return unsafeCreate(new OnSubscribeLift(this.onSubscribe, operator));
    }
    public Single<T> toSingle() {
        return new Single<>(OnSubscribeSingle.create(this));
    }
    public Completable toCompletable() {
        return Completable.fromObservable(this);
    }
    public static <T> Observable<T> empty() {
        return EmptyObservableHolder.instance();
    }
    public final <R> Observable<R> map(Func1<? super T, ? extends R> func1) {
        return unsafeCreate(new OnSubscribeMap(this, func1));
    }
    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, RxRingBuffer.SIZE);
    }
    public final Observable<T> observeOn(Scheduler scheduler, int i2) {
        return observeOn(scheduler, false, i2);
    }
    public final Observable<T> observeOn(Scheduler scheduler, boolean z, int i2) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return (Observable<T>) lift(new OperatorObserveOn(scheduler, z, i2));
    }
    public final ConnectableObservable<T> replay() {
        return OperatorReplay.create(this);
    }
    public final ConnectableObservable<T> replay(int i2) {
        return OperatorReplay.create(this, i2);
    }
    public final ConnectableObservable<T> replay(int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        if (i2 < 0) {
            throw new IllegalArgumentException("bufferSize < 0");
        }
        return OperatorReplay.create(this, j2, timeUnit, scheduler, i2);
    }
    public final ConnectableObservable<T> replay(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return OperatorReplay.create(this, j2, timeUnit, scheduler);
    }
    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onObservableStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            try {
                subscriber.onError(RxJavaHooks.onObservableError(th));
                return Subscriptions.unsubscribed();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                RxJavaHooks.onObservableError(onErrorFailedException);
                throw onErrorFailedException;
            }
        }
    }
    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        return subscribe(subscriber, this);
    }
    static <T> Subscription subscribe(Subscriber<? super T> subscriber, Observable<T> observable) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        }
        if (observable.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        subscriber.onStart();
        if (!(subscriber instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber(subscriber);
        }
        try {
            RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (subscriber.isUnsubscribed()) {
                RxJavaHooks.onError(RxJavaHooks.onObservableError(th));
            } else {
                try {
                    subscriber.onError(RxJavaHooks.onObservableError(th));
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                    RxJavaHooks.onObservableError(onErrorFailedException);
                    throw onErrorFailedException;
                }
            }
            return Subscriptions.unsubscribed();
        }
    }
    public final Observable<T> subscribeOn(Scheduler scheduler) {
        return subscribeOn(scheduler, true);
    }
    public final Observable<T> subscribeOn(Scheduler scheduler, boolean z) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return unsafeCreate(new OperatorSubscribeOn(this, scheduler, z));
    }
}
