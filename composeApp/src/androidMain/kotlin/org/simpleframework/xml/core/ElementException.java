package org.simpleframework.xml.core;


public class ElementException extends PersistenceException {
    public ElementException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public ElementException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
