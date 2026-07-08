package com.google.zxing;

public final class NotFoundException extends ReaderException {
    private static final NotFoundException INSTANCE;
    static {
        final NotFoundException notFoundException = new NotFoundException();
        INSTANCE = notFoundException;
        notFoundException.setStackTrace(NO_TRACE);
    }
    private NotFoundException() {
    }
    public static NotFoundException getNotFoundInstance() {
        return NotFoundException.INSTANCE;
    }
}
