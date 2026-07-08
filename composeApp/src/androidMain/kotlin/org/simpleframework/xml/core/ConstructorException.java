package org.simpleframework.xml.core;

public class ConstructorException extends PersistenceException {
    public ConstructorException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public ConstructorException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
