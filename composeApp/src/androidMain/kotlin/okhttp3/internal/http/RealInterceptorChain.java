package okhttp3.internal.http;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RealInterceptorChain implements Interceptor.Chain {
    private final RealCall call;
    private final int connectTimeoutMillis;
    private final Exchange exchange;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeoutMillis;
    private final Request request;
    private final int writeTimeoutMillis;
    private int calls;
    public RealInterceptorChain(final RealCall call, final List<? extends Interceptor> interceptors, final int i2, final Exchange exchange, final Request request, final int i3, final int i4, final int i5) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(request, "request");
        this.call = call;
        this.interceptors = (List<Interceptor>) interceptors;
        index = i2;
        this.exchange = exchange;
        this.request = request;
        connectTimeoutMillis = i3;
        readTimeoutMillis = i4;
        writeTimeoutMillis = i5;
    }
    public static RealInterceptorChain copyokhttpdefault(final RealInterceptorChain realInterceptorChain, int i2, Exchange exchange, Request request, int i3, int i4, int i5, final int i6, Object o) {
        if (0 != (i6 & 1)) {
            i2 = realInterceptorChain.index;
        }
        if (0 != (i6 & 2)) {
            exchange = realInterceptorChain.exchange;
        }
        final Exchange exchange2 = exchange;
        if (0 != (i6 & 4)) {
            request = realInterceptorChain.request;
        }
        final Request request2 = request;
        if (0 != (i6 & 8)) {
            i3 = realInterceptorChain.connectTimeoutMillis;
        }
        final int i7 = i3;
        if (0 != (i6 & 16)) {
            i4 = realInterceptorChain.readTimeoutMillis;
        }
        final int i8 = i4;
        if (0 != (i6 & 32)) {
            i5 = realInterceptorChain.writeTimeoutMillis;
        }
        return realInterceptorChain.copyokhttp(i2, exchange2, request2, i7, i8, i5);
    }
    public RealCall getCallokhttp() {
        return call;
    }
    public Exchange getExchangeokhttp() {
        return exchange;
    }
    public Request getRequestokhttp() {
        return request;
    }
    public int getConnectTimeoutMillisokhttp() {
        return connectTimeoutMillis;
    }
    public int getReadTimeoutMillisokhttp() {
        return readTimeoutMillis;
    }
    public long getWriteTimeoutMillisokhttp() {
        return writeTimeoutMillis;
    }
    public RealInterceptorChain copyokhttp(final int i2, final Exchange exchange, final Request request, final int i3, final int i4, final int i5) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new RealInterceptorChain(call, interceptors, i2, exchange, request, i3, i4, i5);
    }
    public Connection connection() {
        final Exchange exchange = this.exchange;
        if (null == exchange) {
            return null;
        }
        return exchange.getConnectionokhttp();
    }
    public int connectTimeoutMillis() {
        return connectTimeoutMillis;
    }
    public Interceptor.Chain withConnectTimeout(final int i2, final TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (null != this.exchange) {
            throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copyokhttpdefault(this, 0, null, null, Util.checkDuration("connectTimeout", i2, unit), 0, 0, 55, null);
    }
    public int readTimeoutMillis() {
        return readTimeoutMillis;
    }
    public Interceptor.Chain withReadTimeout(final int i2, final TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (null != this.exchange) {
            throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copyokhttpdefault(this, 0, null, null, 0, Util.checkDuration("readTimeout", i2, unit), 0, 47, null);
    }
    public int writeTimeoutMillis() {
        return writeTimeoutMillis;
    }
    public Interceptor.Chain withWriteTimeout(final int i2, final TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (null != this.exchange) {
            throw new IllegalStateException("Timeouts can't be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copyokhttpdefault(this, 0, null, null, 0, 0, Util.checkDuration("writeTimeout", i2, unit), 31, null);
    }
    public Call<T> call() {
        return this.call;
    }
    public Request request() {
        return this.request;
    }
    public Response proceed(Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.index >= this.interceptors.size()) {
            throw new IllegalStateException("Check failed.");
        }
        this.calls++;
        Exchange exchange = this.exchange;
        if (exchange != null) {
            if (!exchange.getFinderokhttp().sameHostAndPort(request.url())) {
                throw new IllegalStateException(("network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port"));
            }
            if (this.calls != 1) {
                throw new IllegalStateException(("network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once"));
            }
        }
        RealInterceptorChain realInterceptorChainCopyokhttpdefault = copyokhttpdefault(this, this.index + 1, null, request, 0, 0, 0, 58, null);
        Interceptor interceptor = this.interceptors.get(this.index);
        Response responseIntercept = interceptor.intercept(realInterceptorChainCopyokhttpdefault);
        if (responseIntercept == null) {
            throw new NullPointerException("interceptor " + interceptor + " returned null");
        }
        if (this.exchange != null && this.index + 1 < this.interceptors.size() && realInterceptorChainCopyokhttpdefault.calls != 1) {
            throw new IllegalStateException(("network interceptor " + interceptor + " must call proceed() exactly once"));
        }
        if (responseIntercept.body() != null) {
            return responseIntercept;
        }
        throw new IllegalStateException(("interceptor " + interceptor + " returned a response with no body"));
    }
}
