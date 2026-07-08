package io.reactivex.flowables;

import io.reactivex.Flowable;

public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;
    protected GroupedFlowable(final K key) {
        this.key = key;
    }
}
