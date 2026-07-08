package org.springframework.http.converter;


/* loaded from: classes.dex */
public class HttpMessageNotReadableException extends HttpMessageConversionException {
    public HttpMessageNotReadableException(final String str) {
        super(str);
    }

    public HttpMessageNotReadableException(final String str, final Throwable th) {
        super(str, th);
    }
}
