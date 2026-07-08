package org.simpleframework.xml.core;


public class PersistenceException extends Exception {
    public PersistenceException(final String str, final Object... objArr) {
        super(String.format(str, objArr));
    }
    public PersistenceException(final Throwable th, final String str, final Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
