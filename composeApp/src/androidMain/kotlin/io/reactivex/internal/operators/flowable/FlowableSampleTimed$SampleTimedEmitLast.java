package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;



final class FlowableSampleTimedSampleTimedEmitLast<T> extends FlowableSampleTimedSampleTimedSubscriber<T> {
    private static final long serialVersionUID = -7139995637533111443L;
    final AtomicInteger wip;

    FlowableSampleTimedSampleTimedEmitLast(Subscriber<? super T> subscriber, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        super(subscriber, j2, timeUnit, scheduler);
        this.wip = new AtomicInteger(1);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableSampleTimedSampleTimedSubscriber
    void complete() {
        emit();
        if (0 == wip.decrementAndGet ()) {
            this.downstream.onComplete();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (2 == wip.incrementAndGet ()) {
            emit();
            if (0 == wip.decrementAndGet ()) {
                this.downstream.onComplete();
            }
        }
    }
}
