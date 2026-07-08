package io.reactivex.internal.operators.flowable;

import io.reactivex.Notification;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;



final class FlowableMaterializeMaterializeSubscriber<T> extends SinglePostCompleteSubscriber<T, Notification<T>> {
    private static final long serialVersionUID = -3740826063558713822L;

    FlowableMaterializeMaterializeSubscriber(Subscriber<? super Notification<T>> subscriber) {
        super(subscriber);
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onNext(Object t) {
        produced++;
        downstream.onNext(Notification.createOnNext(t));
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.complete(Notification.createOnError(th));
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber, org.reactivestreams.Subscriber
    public void onComplete() {
        this.complete(Notification.createOnComplete());
    }

    @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber
    protected void onDrop(Notification<T> notification) {
        if (notification.isOnError()) {
            RxJavaPlugins.onError(notification.getError());
        }
    }
}
