package org.springframework.http.converter;

import org.springframework.core.NestedRuntimeException;


/* loaded from: classes.dex */
public class HttpMessageConversionException extends NestedRuntimeException {
    public HttpMessageConversionException(final String str) {
        super(str);
    }

    public HttpMessageConversionException(final String str, final Throwable th) {
        super(str, th);
    }
}
