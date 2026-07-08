package org.springframework.web.client;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


/* loaded from: classes.dex */
public class UnknownHttpStatusCodeException extends RestClientException {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final long serialVersionUID = 4702443689088991600L;
    private final int rawStatusCode;
    private final byte[] responseBody;
    private final String responseCharset;
    private final HttpHeaders responseHeaders;
    private final String statusText;

    public UnknownHttpStatusCodeException(final int i2, final String str, final HttpHeaders httpHeaders, final byte[] bArr, final Charset charset) {
        super("Unknown status code [" + i2 + "] " + str);
        rawStatusCode = i2;
        statusText = str;
        responseHeaders = httpHeaders;
        responseBody = null == bArr ? new byte[0] : bArr;
        responseCharset = null != charset ? charset.name() : UnknownHttpStatusCodeException.DEFAULT_CHARSET;
    }

    public int getRawStatusCode() {
        return rawStatusCode;
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
