package org.simpleframework.xml.core;

public class TextException extends PersistenceException {
    public TextException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public TextException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
