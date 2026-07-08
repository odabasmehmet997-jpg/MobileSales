package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class JacksonException extends IOException {
    private static final long serialVersionUID = 123;
    public abstract JsonLocation getLocation();
    public abstract String getOriginalMessage();
    public abstract Object getProcessor();
    protected JacksonException(final String str) {
        super(str);
    }
    protected JacksonException(final Throwable th) {
        super(th);
    }
    protected JacksonException(final String str, final Throwable th) {
        super(str, th);
    }
}
