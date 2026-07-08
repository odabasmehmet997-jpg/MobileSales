package org.ksoap2.transport;
import java.io.IOException;
import java.util.List;
public class HttpResponseException extends IOException {
    private List responseHeaders;
    private final int statusCode;
    public HttpResponseException(final int i2) {
        statusCode = i2;
    }
    public HttpResponseException(final String str, final int i2) {
        super(str);
        statusCode = i2;
    }
    public HttpResponseException(final String str, final int i2, final List list) {
        super(str);
        statusCode = i2;
        responseHeaders = list;
    }
    public HttpResponseException(final String str, final Throwable th, final int i2) {
        super(str, th);
        statusCode = i2;
    }
    public HttpResponseException(final Throwable th, final int i2) {
        super(th);
        statusCode = i2;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public List getResponseHeaders() {
        return responseHeaders;
    }
}
