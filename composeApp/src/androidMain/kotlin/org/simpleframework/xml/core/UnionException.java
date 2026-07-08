package org.simpleframework.xml.core;

public class UnionException extends PersistenceException {
    public UnionException(final String str, final Object... objArr) {
        super(String.format(str, objArr));
    }
}
