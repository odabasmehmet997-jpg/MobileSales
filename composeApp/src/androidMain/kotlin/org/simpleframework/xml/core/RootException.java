package org.simpleframework.xml.core;

public class RootException extends PersistenceException {
    public RootException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public RootException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
