package rx.internal.operators;

import rx.observables.GroupedObservable;


final class OperatorGroupBy$GroupedUnicast<K, T> extends GroupedObservable<K, T> {
    final OperatorGroupBy$State<T, K> state;

    public static <T, K> OperatorGroupBy$GroupedUnicast<K, T> createWith(K k2, int i2, OperatorGroupBy$GroupBySubscriber<?, K, T> operatorGroupBy$GroupBySubscriber, boolean z) {
        return new OperatorGroupBy$GroupedUnicast<>(k2, new OperatorGroupBy$State(i2, operatorGroupBy$GroupBySubscriber, k2, z));
    }

    private OperatorGroupBy$GroupedUnicast(K k2, OperatorGroupBy$State<T, K> operatorGroupBy$State) {
        super(k2, operatorGroupBy$State);
        this.state = operatorGroupBy$State;
    }

    public void onNext(T t) {
        this.state.onNext(t);
    }

    public void onError(Throwable th) {
        this.state.onError(th);
    }

    public void onComplete() {
        this.state.onComplete();
    }
}
