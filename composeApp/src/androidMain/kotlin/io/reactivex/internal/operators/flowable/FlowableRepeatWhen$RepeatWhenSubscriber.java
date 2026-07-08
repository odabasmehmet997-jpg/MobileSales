package io.reactivex.internal.operators.flowable;

import io.reactivex.processors.FlowableProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;



final class FlowableRepeatWhenRepeatWhenSubscriber<T> extends FlowableRepeatWhenWhenSourceSubscriber<T, Object> {
    private static final long serialVersionUID = -2680129890138081029L;

    FlowableRepeatWhenRepeatWhenSubscriber(Subscriber<? super T> subscriber, FlowableProcessor<Object> flowableProcessor, Subscription subscription) {
        super(subscriber, flowableProcessor, subscription);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRepeatWhenWhenSourceSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.receiver.cancel();
        this.downstream.onError(th);
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRepeatWhenWhenSourceSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        again(0);
    }
}
