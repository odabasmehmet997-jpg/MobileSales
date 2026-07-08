package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;



final class FlowableSampleTimedSampleTimedNoLast<T> extends FlowableSampleTimedSampleTimedSubscriber<T> {
    private static final long serialVersionUID = -7139995637533111443L;

    FlowableSampleTimedSampleTimedNoLast(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        super(subscriber, j2, timeUnit, scheduler);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSampleTimedSampleTimedSubscriber
    void complete() {
        this.downstream.onComplete();
    }

    @Override // java.lang.Runnable
    public void run() {
        emit();
    }
}
