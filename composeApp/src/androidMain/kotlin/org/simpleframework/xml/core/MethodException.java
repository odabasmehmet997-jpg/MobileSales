package org.simpleframework.xml.core;


public class MethodException extends PersistenceException {
    public MethodException(final String str, final Object... objArr) {
        super(str, objArr);
    }

    public MethodException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
