package rx.internal.operators;

import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.plugins.RxJavaHooks;


final class OnSubscribeCreate$ErrorEmitter<T> extends OnSubscribeCreate$NoOverflowBaseEmitter<T> {
    private static final long serialVersionUID = 338953216916120960L;
    private boolean done;

    public OnSubscribeCreate$ErrorEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }

    @Override // rx.internal.operators.OnSubscribeCreate$NoOverflowBaseEmitter, rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onNext(T t) {
        if (this.done) {
            return;
        }
        super.onNext(t);
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        super.onCompleted();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaHooks.onError(th);
        } else {
            this.done = true;
            super.onError(th);
        }
    }

    @Override // rx.internal.operators.OnSubscribeCreate$NoOverflowBaseEmitter
    void onOverflow() {
        onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
    }
}
