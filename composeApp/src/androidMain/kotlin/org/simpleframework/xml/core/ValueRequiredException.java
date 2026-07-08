package org.simpleframework.xml.core;

public class ValueRequiredException extends PersistenceException {
    public ValueRequiredException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public ValueRequiredException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
