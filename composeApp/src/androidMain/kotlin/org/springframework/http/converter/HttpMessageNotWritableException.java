package org.springframework.http.converter;


/* loaded from: classes.dex */
public class HttpMessageNotWritableException extends HttpMessageConversionException {
    public HttpMessageNotWritableException(final String str) {
        super(str);
    }

    public HttpMessageNotWritableException(final String str, final Throwable th) {
        super(str, th);
    }
}
