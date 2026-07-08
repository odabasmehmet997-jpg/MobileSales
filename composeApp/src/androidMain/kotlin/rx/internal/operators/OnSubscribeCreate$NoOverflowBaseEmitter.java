package rx.internal.operators;

import rx.Subscriber;


abstract class OnSubscribeCreate$NoOverflowBaseEmitter<T> extends OnSubscribeCreate$BaseEmitter<T> {
    private static final long serialVersionUID = 4127754106204442833L;

    abstract void onOverflow();

    public OnSubscribeCreate$NoOverflowBaseEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onNext(T t) {
        if (this.actual.isUnsubscribed()) {
            return;
        }
        if (get() != 0) {
            this.actual.onNext(t);
            BackpressureUtils.produced(this, 1L);
        } else {
            onOverflow();
        }
    }
}
