package io.reactivex;

import io.reactivex.functions.Function;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;

public class ObservableToMultimap extends Completable {
    public <T, K, V> ObservableToMultimap(Observable<T> tObservable, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, Callable<? extends Map<K, Collection<V>>> callable, Function<? super K, ? extends Collection<? super V>> function3) {
        super();
    }

    @Override
    protected void subscribeActual(CompletableObserver completableObserver) {

    }
}
