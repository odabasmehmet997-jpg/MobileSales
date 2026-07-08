package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {
    private final Observable<Response<T>> upstream;
    BodyObservable(Observable<Response<T>> observable) {
        this.upstream = observable;
    }
    protected void subscribeActual(Observer<? super T> observer) {
        this.upstream.subscribe((Consumer<? super Response<T>>) new BodyObserver(observer));
    }
    private static class BodyObserver<R> implements Consumer<Response<R>> {
        private final Observer<? super R> observer;
        private boolean terminated;

        BodyObserver(Observer<? super R> observer) {
            this.observer = observer;
        }
        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.body());
                return;
            }
            this.terminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.observer.onError(httpException);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(httpException, th));
            }
        }
        public void onComplete() {
            if (this.terminated) {
                return;
            }
            this.observer.onComplete();
        }
        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.", th);
            assertionError.initCause(th);
            RxJavaPlugins.onError(assertionError);
        }
        public void accept(Response<R> t) throws Exception {
            onNext(t);
        }
        public Object invoke(Object obj) {
            return null;
        }
    }
}
