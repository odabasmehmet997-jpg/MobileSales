package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;


public final class SingleToObservable<T> implements Observable.OnSubscribe<T> {
    final Single.OnSubscribe<T> source;

    public SingleToObservable(Single.OnSubscribe<T> onSubscribe) {
        this.source = onSubscribe;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super T> subscriber) {
        SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>(subscriber) { // from class: rx.internal.operators.SingleLiftObservableOperator$WrapSubscriberIntoSingle
            final Subscriber<? super T> actual;

            {
                this.actual = subscriber;
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                this.actual.setProducer(new SingleProducer(this.actual, t));
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.actual.onError(th);
            }
        };
        subscriber.add(singleSubscriber);
        this.source.call(singleSubscriber);
    }
}
