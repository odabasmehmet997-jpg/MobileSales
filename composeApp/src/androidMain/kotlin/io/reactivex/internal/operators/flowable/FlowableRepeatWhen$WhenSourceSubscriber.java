package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.processors.FlowableProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


abstract class FlowableRepeatWhenWhenSourceSubscriber<T, U> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -5604623027276966720L;
    protected final Subscriber<? super T> downstream;
    protected final FlowableProcessor<U> processor;
    private long produced;
    protected final Subscription receiver;

    public abstract void onComplete();

    public abstract void onError(Throwable th);

    FlowableRepeatWhenWhenSourceSubscriber(Subscriber<? super T> subscriber, FlowableProcessor<U> flowableProcessor, Subscription subscription) {
        super(false);
        this.downstream = subscriber;
        this.processor = flowableProcessor;
        this.receiver = subscription;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        this.setSubscription(subscription);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onNext(Object t) {
        this.produced++;
        this.downstream.onNext(t);
    }

    protected final void again(U u) {
        this.setSubscription(EmptySubscription.INSTANCE);
        long j2 = this.produced;
        if (0 != j2) {
            this.produced = 0L;
            this.produced(j2);
        }
        this.receiver.request(1L);
        this.processor.onNext(u);
    }

    @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter, org.reactivestreams.Subscription
    public final void cancel() {
        super.cancel();
        this.receiver.cancel();
    }
}
