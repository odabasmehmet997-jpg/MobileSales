package rx.internal.operators;

import rx.Subscriber;


final class OperatorOnBackpressureLatest$LatestSubscriber<T> extends Subscriber<T> {
    private final OperatorOnBackpressureLatest$LatestEmitter<T> producer;

    @Override // rx.Subscriber
    public void onStart() {
        request(0L);
    }

    @Override // rx.Observer
    public void onNext(Object t) throws Throwable {
        this.producer.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable th) throws Throwable {
        this.producer.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() throws Throwable {
        this.producer.onCompleted();
    }

    void requestMore(long j2) {
        request(j2);
    }
}
