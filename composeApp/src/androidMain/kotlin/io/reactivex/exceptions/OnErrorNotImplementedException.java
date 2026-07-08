package io.reactivex.exceptions;



public final class OnErrorNotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public OnErrorNotImplementedException(final String str, final Throwable th) {
        super(str, null == th ? new NullPointerException() : th);
    }

    public OnErrorNotImplementedException(final Throwable th) {
        this("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + th, th);
    }
}
