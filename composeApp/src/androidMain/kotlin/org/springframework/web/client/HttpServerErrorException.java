package org.springframework.web.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;

public class HttpServerErrorException extends HttpStatusCodeException {
    private static final long serialVersionUID = -2915754006618138282L;
    public HttpServerErrorException(final HttpStatus httpStatus) {
        super(httpStatus);
    }
    public HttpServerErrorException(final HttpStatus httpStatus, final String str) {
        super(httpStatus, str);
    }
    public HttpServerErrorException(final HttpStatus httpStatus, final String str, final byte[] bArr, final Charset charset) {
        super(httpStatus, str, bArr, charset);
    }
    public HttpServerErrorException(final HttpStatus httpStatus, final String str, final HttpHeaders httpHeaders, final byte[] bArr, final Charset charset) {
        super(httpStatus, str, httpHeaders, bArr, charset);
    }
}
