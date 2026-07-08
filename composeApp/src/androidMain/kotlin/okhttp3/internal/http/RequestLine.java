package okhttp3.internal.http;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.net.Proxy;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class RequestLine {
    public static final RequestLine INSTANCE = new RequestLine();
    private RequestLine() {
    }
    public String get(final Request request, final Proxy.Type proxyType) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(proxyType, "proxyType");
        final StringBuilder sb = new StringBuilder();
        sb.append(request.method());
        sb.append(' ');
        final RequestLine requestLine = RequestLine.INSTANCE;
        if (requestLine.includeAuthorityInRequestLine(request, proxyType)) {
            sb.append(request.url());
        } else {
            sb.append(requestLine.requestPath(request.url()));
        }
        sb.append(" HTTP/1.1");
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
    private boolean includeAuthorityInRequestLine(final Request request, final Proxy.Type type) {
        return !request.isHttps() && Proxy.Type.HTTP == type;
    }
    public String requestPath(final HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        final String strEncodedPath = url.encodedPath();
        final String strEncodedQuery = url.encodedQuery();
        if (null == strEncodedQuery) {
            return strEncodedPath;
        }
        return strEncodedPath + '?' + strEncodedQuery;
    }
}
