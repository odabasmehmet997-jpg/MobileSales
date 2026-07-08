package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;


public final class SingleFromObservable<T> implements Single.OnSubscribe<T> {
    final Observable.OnSubscribe<T> source;

    public SingleFromObservable(Observable.OnSubscribe<T> onSubscribe) {
        this.source = onSubscribe;
    }

    @Override // rx.functions.Action1
    public void call(SingleSubscriber<? super T> singleSubscriber) {
        WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new WrapSingleIntoSubscriber(singleSubscriber);
        singleSubscriber.add(wrapSingleIntoSubscriber);
        this.source.call(wrapSingleIntoSubscriber);
    }

    static final class WrapSingleIntoSubscriber<T> extends Subscriber<T> {
        final SingleSubscriber<? super T> actual;
        int state;
        T value;

        WrapSingleIntoSubscriber(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
        }

        @Override // rx.Observer
        public void onNext(Object t) {
            int i2 = this.state;
            if (i2 == 0) {
                this.state = 1;
                this.value = t;
            } else if (i2 == 1) {
                this.state = 2;
                this.actual.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.state == 2) {
                RxJavaHooks.onError(th);
            } else {
                this.value = null;
                this.actual.onError(th);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            int i2 = this.state;
            if (i2 == 0) {
                this.actual.onError(new NoSuchElementException());
            } else if (i2 == 1) {
                this.state = 2;
                T t = this.value;
                this.value = null;
                this.actual.onSuccess(t);
            }
        }
    }
}
