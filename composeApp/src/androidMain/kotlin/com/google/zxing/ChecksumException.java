package com.google.zxing;

public final class ChecksumException extends ReaderException {
    private static final ChecksumException INSTANCE;
    static {
        final ChecksumException checksumException = new ChecksumException();
        INSTANCE = checksumException;
        checksumException.setStackTrace(NO_TRACE);
    }
    private ChecksumException() {
    }
    private ChecksumException(final Throwable th) {
        super(th);
    }
    public static ChecksumException getChecksumInstance() {
        return isStackTrace ? new ChecksumException() : ChecksumException.INSTANCE;
    }
    public static ChecksumException getChecksumInstance(final Throwable th) {
        return isStackTrace ? new ChecksumException(th) : ChecksumException.INSTANCE;
    }
}
