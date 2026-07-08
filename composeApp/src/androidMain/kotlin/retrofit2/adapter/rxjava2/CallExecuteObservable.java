package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

final class CallExecuteObservable<T> extends Observable<Response<T>> {
    private final Call<T> originalCall;
    CallExecuteObservable(Call<T> call) {
        this.originalCall = call;
    }
    protected void subscribeActual(Observer<? super Response<T>> observer) {
        Call<T> callClone = this.originalCall.clone();
        CallDisposable callDisposable = new CallDisposable(callClone);
        observer.onSubscribe(callDisposable);
        if (callDisposable.isDisposed()) {
            return;
        }
        boolean z = false;
        try {
            Response<T> responseExecute = callClone.execute();
            if (!callDisposable.isDisposed()) {
                observer.onNext(responseExecute);
            }
            if (callDisposable.isDisposed()) {
                return;
            }
            try {
                observer.onComplete();
            } catch (Throwable th) {
                th = th;
                z = true;
                Exceptions.throwIfFatal(th);
                if (z) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                if (callDisposable.isDisposed()) {
                    return;
                }
                try {
                    observer.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, th2));
                }
            }
        } catch (Throwable th3) {
            Throwable th = th3;
        }
    }
    private static final class CallDisposable implements Disposable {
        private final Call<?> call;
        private volatile boolean disposed;
        CallDisposable(Call<?> call) {
            this.call = call;
        }
        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }
        public boolean isDisposed() {
            return this.disposed;
        }
        public void onError(Throwable th) {
        }
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }
}
