package org.springframework.core;

import java.io.IOException;

public class NestedIOException extends IOException {
    public NestedIOException(final String str) {
        super(str);
    }
    public NestedIOException(final String str, final Throwable th) {
        super(str);
        this.initCause(th);
    }
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), this.getCause());
    }
}
