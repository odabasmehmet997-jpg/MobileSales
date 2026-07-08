package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

final class CompletableFutureCallAdapterFactory extends CallAdapter.Factory {
    static final CallAdapter.Factory INSTANCE = new CompletableFutureCallAdapterFactory();
    CompletableFutureCallAdapterFactory() {
    }
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != CompletableFuture.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
        if (CallAdapter.Factory.getRawType(parameterUpperBound) != Response.class) {
            return new BodyCallAdapter(parameterUpperBound);
        }
        if (!(parameterUpperBound instanceof ParameterizedType)) {
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        }
        return new ResponseCallAdapter(CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound));
    }
    private record BodyCallAdapter<R>(Type responseType) implements CallAdapter<R, CompletableFuture<R>> {
            public CompletableFuture<R> adapt(Call<R> call) {
                CallCancelCompletableFuture callCancelCompletableFuture = new CallCancelCompletableFuture(call);
                call.enqueue(new BodyCallback(callCancelCompletableFuture));
                return callCancelCompletableFuture;
            }

            private class BodyCallback implements Callback<R> {
                private final CompletableFuture<R> future;

                public BodyCallback(CompletableFuture<R> completableFuture) {
                    this.future = completableFuture;
                }
                public void onResponse(Call<R> call, Response<R> response) {
                    if (response.isSuccessful()) {
                        this.future.complete(response.body());
                    } else {
                        this.future.completeExceptionally(new HttpException(response));
                    }
                }
                public void onFailure(Call<R> call, Throwable th) {
                    this.future.completeExceptionally(th);
                }
            }
        }
    private record ResponseCallAdapter<R>(Type responseType) implements CallAdapter<R, CompletableFuture<Response<R>>> {
            public CompletableFuture<Response<R>> adapt(Call<R> call) {
                CallCancelCompletableFuture callCancelCompletableFuture = new CallCancelCompletableFuture(call);
                call.enqueue(new ResponseCallback(callCancelCompletableFuture));
                return callCancelCompletableFuture;
            }
            private class ResponseCallback implements Callback<R> {
                private final CompletableFuture<Response<R>> future;

                public ResponseCallback(CompletableFuture<Response<R>> completableFuture) {
                    this.future = completableFuture;
                }
                public void onResponse(Call<R> call, Response<R> response) {
                    this.future.complete(response);
                }
                public void onFailure(Call<R> call, Throwable th) {
                    this.future.completeExceptionally(th);
                }
            }
        }
    private static final class CallCancelCompletableFuture<T> extends CompletableFuture<T> {
        private final Call<?> call;

        CallCancelCompletableFuture(Call<?> call) {
            this.call = call;
        }
        public boolean cancel(boolean z) {
            if (z) {
                this.call.cancel();
            }
            return super.cancel(z);
        }
    }
}
