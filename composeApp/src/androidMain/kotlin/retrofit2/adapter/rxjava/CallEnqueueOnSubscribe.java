package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class CallEnqueueOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
    private final Call<T> originalCall;
    CallEnqueueOnSubscribe(Call<T> call) {
        this.originalCall = call;
    }
    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> callClone = this.originalCall.clone();
        final CallArbiter callArbiter = new CallArbiter(callClone, subscriber);
        subscriber.add(callArbiter);
        subscriber.setProducer(callArbiter);
        callClone.enqueue(new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                callArbiter.emitResponse(response);
            }
            public void onFailure(Call<T> call, Throwable th) {
                Exceptions.throwIfFatal(th);
                callArbiter.emitError(th);
            }
        });
    }
}
