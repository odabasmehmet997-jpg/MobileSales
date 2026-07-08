package org.springframework.core;


public abstract class NestedCheckedException extends Exception {
    private static final long serialVersionUID = 7100714597678207546L;
    protected NestedCheckedException(final String str) {
        super(str);
    }
    protected NestedCheckedException(final String str, final Throwable th) {
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
    public boolean contains(final Class<? extends Throwable> cls) {
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
        if (cause instanceof NestedCheckedException) {
            return ((NestedCheckedException) cause).contains(cls);
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
