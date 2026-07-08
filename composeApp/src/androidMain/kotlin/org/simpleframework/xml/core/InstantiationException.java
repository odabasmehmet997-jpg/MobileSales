package org.simpleframework.xml.core;


public class InstantiationException extends PersistenceException {
    public InstantiationException(final String str, final Object... objArr) {
        super(str, objArr);
    }

    public InstantiationException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
