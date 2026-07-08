package rx.observables;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;


public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(Action1<? super Subscription> action1);

    protected ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }
}
