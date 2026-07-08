package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

final class ResultObservable<T> extends Observable<Result<T>> {
    private final Observable<Response<T>> upstream;
    ResultObservable(Observable<Response<T>> observable) {
        this.upstream = observable;
    }
    protected void subscribeActual(Observer<? super Result<T>> observer) {
        this.upstream.subscribe(new ResultObserver(observer));
    }

    private final record ResultObserver<R>(Observer<? super Result<R>> observer) implements Observer<Response<R>> {
        public void onSubscribe(Disposable disposable) {
                this.observer.onSubscribe(disposable);
            }

        public void onNext(Response<R> response) {
                this.observer.onNext(Result.response(response));
            }

        public void onError(Throwable th) {
                try {
                    this.observer.onNext(Result.error(th));
                    this.observer.onComplete();
                } catch (Throwable th2) {
                    try {
                        this.observer.onError(th2);
                    } catch (Throwable th3) {
                        Exceptions.throwIfFatal(th3);
                        RxJavaPlugins.onError(new CompositeException(th2, th3));
                    }
                }
            }

        public void onNext(Object t) {
            }

        public void onComplete() {
                this.observer.onComplete();
            }
        }
}
