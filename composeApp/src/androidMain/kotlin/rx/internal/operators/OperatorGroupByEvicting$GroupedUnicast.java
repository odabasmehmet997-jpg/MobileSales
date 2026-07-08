package rx.internal.operators;

import rx.observables.GroupedObservable;


final class OperatorGroupByEvicting$GroupedUnicast<K, T> extends GroupedObservable<K, T> {
    final OperatorGroupByEvicting$State<T, K> state;

    public static <T, K> OperatorGroupByEvicting$GroupedUnicast<K, T> createWith(K k2, int i2, OperatorGroupByEvicting$GroupBySubscriber<?, K, T> operatorGroupByEvicting$GroupBySubscriber, boolean z) {
        return new OperatorGroupByEvicting$GroupedUnicast<>(k2, new OperatorGroupByEvicting$State(i2, operatorGroupByEvicting$GroupBySubscriber, k2, z));
    }

    private OperatorGroupByEvicting$GroupedUnicast(K k2, OperatorGroupByEvicting$State<T, K> operatorGroupByEvicting$State) {
        super(k2, operatorGroupByEvicting$State);
        this.state = operatorGroupByEvicting$State;
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
