package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import org.reactivestreams.Subscription;


enum TestSubscriberEmptySubscriber implements FlowableSubscriber<Object> {
    INSTANCE;
    public void onComplete() {}
    public void onError(Throwable th) {}
    public void onNext(Object obj) {}
    public void onSubscribe(Subscription subscription) {}
}
