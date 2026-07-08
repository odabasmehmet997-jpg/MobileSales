package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;



final class FlowableSamplePublisherSampleMainNoLast<T> extends FlowableSamplePublisherSamplePublisherSubscriber<T> {
    private static final long serialVersionUID = -3029755663834015785L;

    FlowableSamplePublisherSampleMainNoLast(Subscriber<? super T> subscriber, Publisher<?> publisher) {
        super(subscriber, publisher);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisherSamplePublisherSubscriber
    void completion() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisherSamplePublisherSubscriber
    void run() {
        emit();
    }
}
