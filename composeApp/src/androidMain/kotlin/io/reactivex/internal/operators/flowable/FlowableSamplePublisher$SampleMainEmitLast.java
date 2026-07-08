package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;



final class FlowableSamplePublisherSampleMainEmitLast<T> extends FlowableSamplePublisherSamplePublisherSubscriber<T> {
    private static final long serialVersionUID = -3029755663834015785L;
    volatile boolean done;
    final AtomicInteger wip;

    FlowableSamplePublisherSampleMainEmitLast(Subscriber<? super T> subscriber, Publisher<?> publisher) {
        super(subscriber, publisher);
        this.wip = new AtomicInteger();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisherSamplePublisherSubscriber
    void completion() {
        this.done = true;
        if (0 == wip.getAndIncrement ()) {
            emit();
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisherSamplePublisherSubscriber
    void run() {
        if (0 == wip.getAndIncrement ()) {
            do {
                boolean z = this.done;
                emit();
                if (z) {
                    this.downstream.onComplete();
                    return;
                }
            } while (0 != wip.decrementAndGet ());
        }
    }
}
