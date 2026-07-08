package org.springframework.web.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public abstract class HttpStatusCodeException extends RestClientException {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final long serialVersionUID = -5807494703720513267L;
    private final byte[] responseBody;
    private final String responseCharset;
    private final HttpHeaders responseHeaders;
    private final HttpStatus statusCode;
    private final String statusText;

    protected HttpStatusCodeException(final HttpStatus httpStatus) {
        this(httpStatus, httpStatus.name(), null, null, null);
    }

    protected HttpStatusCodeException(final HttpStatus httpStatus, final String str) {
        this(httpStatus, str, null, null, null);
    }

    protected HttpStatusCodeException(final HttpStatus httpStatus, final String str, final byte[] bArr, final Charset charset) {
        this(httpStatus, str, null, bArr, charset);
    }

    protected HttpStatusCodeException(final HttpStatus httpStatus, final String str, final HttpHeaders httpHeaders, final byte[] bArr, final Charset charset) {
        super(httpStatus.value() + " " + str);
        statusCode = httpStatus;
        statusText = str;
        responseHeaders = httpHeaders;
        responseBody = null == bArr ? new byte[0] : bArr;
        responseCharset = null != charset ? charset.name() : HttpStatusCodeException.DEFAULT_CHARSET;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public byte[] getResponseBodyAsByteArray() {
        return responseBody;
    }

    public String getResponseBodyAsString() {
        try {
            return new String(responseBody, responseCharset);
        } catch (final UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2);
        }
    }
}
