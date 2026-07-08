package retrofit2;

import java.io.IOException;
import java.util.Objects;
import com.proje.mobilesales.R;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.connection.RealCall;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Timeout;

final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private final Factory callFactory;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private Call<R> rawCall;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, T> responseConverter;
    OkHttpCall(RequestFactory requestFactory, Object[] objArr, Factory factory, Converter<ResponseBody, T> converter) {
        this.requestFactory = requestFactory;
        this.args = objArr;
        this.callFactory = factory;
        this.responseConverter = converter;
    }
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }
    public synchronized Request request() {
        try {
            return getRawCall().request();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized Timeout timeout() {
        try {
            return getRawCall().timeout();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Call<R> getRawCall() throws IOException {
        Call<R> call = this.rawCall;
        if (call != null) {
            return call;
        }
        Throwable th = this.creationFailure;
        if (th != null) {
            if (th instanceof IOException) {
                throw (IOException) th;
            }
            if (th instanceof RuntimeException) {
                throw (RuntimeException) th;
            }
            throw (Error) th;
        }
        try {
            Call<R> callCreateRawCall = createRawCall();
            this.rawCall = callCreateRawCall;
            return callCreateRawCall;
        } catch (IOException | Error | RuntimeException e2) {
            Utils.throwIfFatal(e2);
            this.creationFailure = e2;
            throw e2;
        }
    }
    public void enqueue(final Callback<com.proje.mobilesales.features.sales.view.newadd.T> callback) {
        Call<R> call;
        Throwable th;
        Objects.requireNonNull(callback, "callback == null");
        synchronized (this) {
            try {
                if (this.executed) {
                    throw new IllegalStateException("Already executed.");
                }
                this.executed = true;
                call = this.rawCall;
                th = this.creationFailure;
                if (call == null && th == null) {
                    try {
                        Call<R> callCreateRawCall = createRawCall();
                        this.rawCall = callCreateRawCall;
                        call = callCreateRawCall;
                    } catch (Throwable th2) {
                        th = th2;
                        Utils.throwIfFatal(th);
                        this.creationFailure = th;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new okhttp3.Callback() {
            public void onResponse(RealCall call2, okhttp3.Response response) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, (Response<com.proje.mobilesales.features.sales.view.newadd.T>) OkHttpCall.this.parseResponse(response));
                    } catch (Throwable th4) {
                        Utils.throwIfFatal(th4);
                        th4.printStackTrace();
                    }
                } catch (Throwable th5) {
                    Utils.throwIfFatal(th5);
                    callFailure(th5);
                }
            }
            public void onFailure(RealCall call2, IOException iOException) {
                callFailure(iOException);
            }
            private void callFailure(Throwable th4) {
                try {
                    callback.onFailure(OkHttpCall.this, th4);
                } catch (Throwable th5) {
                    Utils.throwIfFatal(th5);
                    th5.printStackTrace();
                }
            }
        });
    }
    public void enqueue(okhttp3.Callback callback) {
        try {
            getRawCall().enqueue(callback);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized boolean isExecuted() {
        return this.executed;
    }
    public okhttp3.Response execute() throws IOException {
        Call<R> rawCall;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            rawCall = getRawCall();
        }
        if (this.canceled) {
            rawCall.cancel();
        }
        return (Response<com.proje.mobilesales.features.sales.view.newadd.T>) parseResponse(rawCall.execute().raw());
    }
    private Call<R> createRawCall() throws IOException {
        Call<R> callNewCall = this.callFactory.newCall(this.requestFactory.create(this.args));
        if (callNewCall != null) {
            return callNewCall;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }
    Response<T> parseResponse(okhttp3.Response response) throws IOException {
        ResponseBody responseBodyBody = response.body();
        okhttp3.Response responseBuild = response.newBuilder().body(new NoContentResponseBody(responseBodyBody.contentType(), responseBodyBody.contentLength())).build();
        int iCode = responseBuild.code();
        if (iCode < 200 || iCode >= 300) {
            try {
                return Response.error(Utils.buffer(responseBodyBody), responseBuild);
            } finally {
                responseBodyBody.close();
            }
        }
        if (iCode == 204 || iCode == 205) {
            responseBodyBody.close();
            return (Response<T>) Response.success(null, responseBuild);
        }
        ExceptionCatchingResponseBody exceptionCatchingResponseBody = new ExceptionCatchingResponseBody(responseBodyBody);
        try {
            return Response.success(this.responseConverter.convert(exceptionCatchingResponseBody), responseBuild);
        } catch (RuntimeException e2) {
            exceptionCatchingResponseBody.throwIfCaught();
            throw e2;
        }
    }
    public void cancel() {
        Call<R> call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            Call<R> call = this.rawCall;
            if (call == null || !call.isCanceled()) {
                z = false;
            }
        }
        return z;
    }
    static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;
        private final MediaType contentType;

        NoContentResponseBody(MediaType mediaType, long j2) {
            this.contentType = mediaType;
            this.contentLength = j2;
        }
        public MediaType contentType() {
            return this.contentType;
        }
        public long contentLength() {
            return this.contentLength;
        }
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }
    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;
        IOException thrownException;
        ExceptionCatchingResponseBody(ResponseBody responseBody) {
            this.delegate = responseBody;
            DiskLruCache diskLruCache = null;
            this.delegateSource = Okio.buffer(new ForwardingSource(diskLruCache, this) {
                public long read(Buffer buffer, long j2) throws IOException {
                    try {
                        return super.read(buffer, j2);
                    } catch (IOException e2) {
                        this.source = (okio.Source) e2;
                        throw e2;
                    }
                }
            });
        }
        public MediaType contentType() {
            return this.delegate.contentType();
        }
        public long contentLength() {
            return this.delegate.contentLength();
        }
        public BufferedSource source() {
            return this.delegateSource;
        }
        public void close() {
            this.delegate.close();
        }
        void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }
}
