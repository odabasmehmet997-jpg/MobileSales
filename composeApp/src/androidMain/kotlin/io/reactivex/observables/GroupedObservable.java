package io.reactivex.observables;

import io.reactivex.Observable;

public abstract class GroupedObservable<K, T> extends Observable<T> {
    K key = null;
    protected GroupedObservable(K k2) {
        this.key = k2;
    }

    protected GroupedObservable() {
    }
}
