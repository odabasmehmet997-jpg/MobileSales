package org.springframework.web.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;

public class HttpClientErrorException extends HttpStatusCodeException {
    private static final long serialVersionUID = 5177019431887513952L;
    public HttpClientErrorException(final HttpStatus httpStatus) {
        super(httpStatus);
    }
    public HttpClientErrorException(final HttpStatus httpStatus, final String str) {
        super(httpStatus, str);
    }
    public HttpClientErrorException(final HttpStatus httpStatus, final String str, final byte[] bArr, final Charset charset) {
        super(httpStatus, str, bArr, charset);
    }
    public HttpClientErrorException(final HttpStatus httpStatus, final String str, final HttpHeaders httpHeaders, final byte[] bArr, final Charset charset) {
        super(httpStatus, str, httpHeaders, bArr, charset);
    }
}
