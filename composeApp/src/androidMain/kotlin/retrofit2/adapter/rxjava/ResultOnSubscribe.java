package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

/*  INFO: loaded from: classes2.dex */
final class ResultOnSubscribe<T> implements Observable.OnSubscribe<Result<T>> {
    private final Observable.OnSubscribe<Response<T>> upstream;

    ResultOnSubscribe(Observable.OnSubscribe<Response<T>> onSubscribe) {
        this.upstream = onSubscribe;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Result<T>> subscriber) {
        this.upstream.call((Response<T>) new ResultSubscriber(subscriber));
    }

    private final record ResultSubscriber<R>(Subscriber<? super Result<R>> subscriber) extends Subscriber<Response<R>> {

        @Override // rx.Observer
            public void onNext(Object response) {
                this.subscriber.onNext(Result.response(response));
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                try {
                    this.subscriber.onNext(Result.error(th));
                    this.subscriber.onCompleted();
                } catch (Throwable th2) {
                    try {
                        this.subscriber.onError(th2);
                    } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e2) {
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(e2);
                    } catch (Throwable th3) {
                        Exceptions.throwIfFatal(th3);
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th2, th3));
                    }
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.subscriber.onCompleted();
            }
        }
}
