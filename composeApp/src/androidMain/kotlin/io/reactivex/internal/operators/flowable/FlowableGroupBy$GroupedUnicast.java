package io.reactivex.internal.operators.flowable;

import io.reactivex.flowables.GroupedFlowable;
import org.reactivestreams.Subscriber;



final class FlowableGroupByGroupedUnicast<K, T> extends GroupedFlowable<K, T> {
    final FlowableGroupByState<T, K> state;

    public static <T, K> FlowableGroupByGroupedUnicast<K, T> createWith(K k2, int i2, FlowableGroupByGroupBySubscriber<?, K, T> flowableGroupByGroupBySubscriber, boolean z) {
        return new FlowableGroupByGroupedUnicast<>(k2, new FlowableGroupByState(i2, flowableGroupByGroupBySubscriber, k2, z));
    }

    private FlowableGroupByGroupedUnicast(K k2, FlowableGroupByState<T, K> flowableGroupByState) {
        super(k2);
        this.state = flowableGroupByState;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.state.subscribe(subscriber);
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
