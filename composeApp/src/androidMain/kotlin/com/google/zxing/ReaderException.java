package com.google.zxing;

public abstract class ReaderException extends Exception {
    protected static final StackTraceElement[] NO_TRACE = new StackTraceElement[0];
    protected static final boolean isStackTrace = (null != System.getProperty("surefire.test.class.path"));
    ReaderException() {
    }
    ReaderException(final Throwable th) {
        super(th);
    }
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
