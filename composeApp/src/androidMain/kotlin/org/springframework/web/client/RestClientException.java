package org.springframework.web.client;

import org.springframework.core.NestedRuntimeException;

public class RestClientException extends NestedRuntimeException {
    private static final long serialVersionUID = -4084444984163796577L;
    public RestClientException(final String str) {
        super(str);
    }
    public RestClientException(final String str, final Throwable th) {
        super(str, th);
    }
}
