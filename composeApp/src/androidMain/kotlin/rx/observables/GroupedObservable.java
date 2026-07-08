package rx.observables;

import rx.Observable;


public class GroupedObservable<K, T> extends Observable<T> {
    private final K key;

    protected GroupedObservable(K k2, Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
        this.key = k2;
    }
}
