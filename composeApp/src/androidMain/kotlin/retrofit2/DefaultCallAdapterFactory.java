package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import okhttp3.Request;
import okio.Timeout;
 
final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    private final Executor callbackExecutor;
    DefaultCallAdapterFactory(Executor executor) {
        this.callbackExecutor = executor;
    }
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != Call.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        final Type parameterUpperBound = Utils.getParameterUpperBound(0, (ParameterizedType) type);
        final Executor executor = Utils.isAnnotationPresent(annotationArr, SkipCallbackExecutor.class) ? null : this.callbackExecutor;
        return new CallAdapter<Object, Call<?>>() {  
            public Type responseType() {
                return parameterUpperBound;
            } 
            public Call<?> adapt(Call<Object> call) {
                Executor executor2 = executor;
                return executor2 == null ? call : new ExecutorCallbackCall(executor2, call);
            }
        };
    }
    record ExecutorCallbackCall<T>(Executor callbackExecutor, Call<T> delegate) implements Call<T> {
 
            public void enqueue(Callback<T> callback) {
                Objects.requireNonNull(callback, "callback == null");
                this.delegate.enqueue(new AnonymousClass1(callback));
            } 
            class AnonymousClass1 implements Callback<T> {
                final  Callback valcallback;

                AnonymousClass1(Callback callback) {
                    this.valcallback = callback;
                } 
                public void onResponse(Call<T> call, final Response<T> response) {
                    Executor executor = ExecutorCallbackCall.this.callbackExecutor;
                    final Callback callback = this.valcallback;
                    executor.execute(new Runnable() {
                        private ExecutorCallbackCall<Object>.AnonymousClass1 f0;

                        public void run() {
                            this.f0.lambdaonResponse0(callback, response);
                        }
                    });
                }

                private   void lambdaonResponse0(Callback callback, Response response) {
                    if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                        callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                    } else {
                        callback.onResponse(ExecutorCallbackCall.this, response);
                    }
                } 
                public  void lambdaonFailure1(Callback callback, Throwable th) {
                    callback.onFailure(ExecutorCallbackCall.this, th);
                } 
                public void onFailure(Call<T> call, final Throwable th) {
                    Executor executor = ExecutorCallbackCall.this.callbackExecutor;
                    final Callback callback = this.valcallback;
                    executor.execute(new Runnable() {
                        private ExecutorCallbackCall<Object>.AnonymousClass1 f0;

                        public void run() {
                            this.f0.lambdaonFailure1(callback, th);
                        }
                    });
                }
            }

            
            public boolean isExecuted() {
                return this.delegate.isExecuted();
            }

            
            public Response<T> execute() throws IOException {
                return this.delegate.execute();
            }

            
            public void cancel() {
                this.delegate.cancel();
            }

            
            public boolean isCanceled() {
                return this.delegate.isCanceled();
            }

            
            public Call<T> clone() {
                return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
            }

            
            public Request request() {
                return this.delegate.request();
            }

            
            public Timeout timeout() {
                return this.delegate.timeout();
            }
        }
}
