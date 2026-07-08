package io.reactivex.internal.operators.flowable;

import io.reactivex.processors.FlowableProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class FlowableRetryWhenRetryWhenSubscriber<T> extends FlowableRepeatWhenWhenSourceSubscriber<T, Throwable> {
    private static final long serialVersionUID = -2680129890138081029L;

    FlowableRetryWhenRetryWhenSubscriber(Subscriber<? super T> subscriber, FlowableProcessor<Throwable> flowableProcessor, Subscription subscription) {
        super(subscriber, flowableProcessor, subscription);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRepeatWhenWhenSourceSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        again(th);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRepeatWhenWhenSourceSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        this.receiver.cancel();
        this.downstream.onComplete();
    }
}
