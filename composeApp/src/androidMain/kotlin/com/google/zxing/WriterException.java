package com.google.zxing;

public final class WriterException extends Exception {
    public WriterException() {
    }
    public WriterException(final String str) {
        super(str);
    }
    public WriterException(final Throwable th) {
        super(th);
    }
}
