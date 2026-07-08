package org.springframework.core;

public abstract class NestedRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 5439915454935047936L;
    protected NestedRuntimeException(final String str) {
        super(str);
    }
    protected NestedRuntimeException(final String str, final Throwable th) {
        super(str, th);
    }
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), this.getCause());
    }
    public Throwable getRootCause() {
        Throwable th = null;
        for (Throwable cause = this.getCause(); null != cause && cause != th; cause = cause.getCause()) {
            th = cause;
        }
        return th;
    }
    public Throwable getMostSpecificCause() {
        final Throwable rootCause = this.getRootCause();
        return null != rootCause ? rootCause : this;
    }
    public boolean contains(final Class<?>   cls) {
        if (null == cls) {
            return false;
        }
        if (cls.isInstance(this)) {
            return true;
        }
        Throwable cause = this.getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof NestedRuntimeException) {
            return ((NestedRuntimeException) cause).contains(cls);
        }
        while (null != cause) {
            if (cls.isInstance(cause)) {
                return true;
            }
            if (cause.getCause() == cause) {
                break;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
