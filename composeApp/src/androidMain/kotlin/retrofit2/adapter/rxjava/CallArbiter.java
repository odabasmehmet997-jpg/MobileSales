package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer {
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;
    private volatile boolean unsubscribed;
    CallArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }
    public void unsubscribe() {
        this.unsubscribed = true;
        this.call.cancel();
    }
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }
    public void request(long j2) {
        if (j2 == 0) {
            return;
        }
        while (true) {
            int i2 = get();
            if (i2 != 0) {
                if (i2 == 1) {
                    return;
                }
                if (i2 != 2) {
                    if (i2 == 3) {
                        return;
                    }
                    throw new IllegalStateException("Unknown state: " + i2);
                }
                if (compareAndSet(2, 3)) {
                    deliverResponse(this.response);
                    return;
                }
            } else if (compareAndSet(0, 1)) {
                return;
            }
        }
    }
    void emitResponse(Response<T> response) {
        while (true) {
            int i2 = get();
            if (i2 == 0) {
                this.response = response;
                if (compareAndSet(0, 2)) {
                    return;
                }
            } else {
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        throw new AssertionError();
                    }
                    throw new IllegalStateException("Unknown state: " + i2);
                }
                if (compareAndSet(1, 3)) {
                    deliverResponse(response);
                    return;
                }
            }
        }
    }
    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                if (isUnsubscribed()) {
                    return;
                }
                this.subscriber.onCompleted();
            } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e2) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e3) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e3);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            try {
                this.subscriber.onError(th2);
            } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e4) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e4);
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th2, th3));
            }
        }
    }
    void emitError(Throwable th) {
        set(3);
        if (isUnsubscribed()) {
            return;
        }
        try {
            this.subscriber.onError(th);
        } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e2) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e2);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th, th2));
        }
    }
}
