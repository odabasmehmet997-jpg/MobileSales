package rx.internal.operators;

import rx.Subscriber;


final class OnSubscribeCreate$NoneEmitter<T> extends OnSubscribeCreate$BaseEmitter<T> {
    private static final long serialVersionUID = 3776720187248809713L;

    public OnSubscribeCreate$NoneEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onNext(T t) {
        long j2;
        if (this.actual.isUnsubscribed()) {
            return;
        }
        this.actual.onNext(t);
        do {
            j2 = get();
            if (j2 == 0) {
                return;
            }
        } while (!compareAndSet(j2, j2 - 1));
    }
}
