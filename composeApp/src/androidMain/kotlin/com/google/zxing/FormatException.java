package com.google.zxing;

public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE;
    static {
        final FormatException formatException = new FormatException();
        INSTANCE = formatException;
        formatException.setStackTrace(NO_TRACE);
    }
    private FormatException() {
    }
    private FormatException(final Throwable th) {
        super(th);
    }
    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : FormatException.INSTANCE;
    }
    public static FormatException getFormatInstance(final Throwable th) {
        return isStackTrace ? new FormatException(th) : FormatException.INSTANCE;
    }
}
