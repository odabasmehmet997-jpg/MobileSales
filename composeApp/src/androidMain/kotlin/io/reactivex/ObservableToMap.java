package io.reactivex;

import io.reactivex.functions.Function;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.internal.util.HashMapSupplier;

public class ObservableToMap extends Completable {
    public <T, K, V> ObservableToMap(Observable<T> tObservable, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, HashMapSupplier callable, ArrayListSupplier arrayListSupplier) {
        super();
    }
    @Override
    protected void subscribeActual(CompletableObserver completableObserver) {

    }
}
