package okhttp3.internal.http;

import kotlin.jvm.internal.Intrinsics;

import static kotlin.jvm.internal.Intrinsics.areEqual;

public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }
    public static boolean requiresRequestBody(final String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return areEqual(method, "POST") || areEqual(method, "PUT") || areEqual(method, "PATCH") || areEqual(method, "PROPPATCH") || areEqual(method, "REPORT");
    }
    public static boolean permitsRequestBody(final String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return !areEqual(method, "GET") && !areEqual(method, "HEAD");
    }
    public boolean invalidatesCache(final String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return areEqual(method, "POST") || areEqual(method, "PATCH") || areEqual(method, "PUT") || areEqual(method, "DELETE") || areEqual(method, "MOVE");
    }
    public boolean redirectsWithBody(final String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return areEqual(method, "PROPFIND");
    }
    public boolean redirectsToGet(final String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return !areEqual(method, "PROPFIND");
    }
}
