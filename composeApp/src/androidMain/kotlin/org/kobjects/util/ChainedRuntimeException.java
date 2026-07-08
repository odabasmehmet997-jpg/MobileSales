package org.kobjects.util;

public class ChainedRuntimeException extends RuntimeException {
    Exception chain;
    public static ChainedRuntimeException create(final Exception exc, final String str) {
        try {
            return ((ChainedRuntimeException) Class.forName("org.kobjects.util.ChainedRuntimeExceptionSE").newInstance())._create(exc, str);
        } catch (final Exception unused) {
            return new ChainedRuntimeException(exc, str);
        }
    }
    ChainedRuntimeException(final Exception exc, final String str) {
        final StringBuilder sb = new StringBuilder();
        sb.append(null == str ? "rethrown" : str);
        sb.append(": ");
        sb.append(exc.toString());
        chain = exc;
    }
    ChainedRuntimeException _create(final Exception exc, final String str) {
        throw new RuntimeException("ERR!");
    }
    public Exception getChained() {
        return chain;
    }
    public void printStackTrace() {
        super.printStackTrace();
        final Exception exc = chain;
        if (null != exc) {
            exc.printStackTrace();
        }
    }
}
