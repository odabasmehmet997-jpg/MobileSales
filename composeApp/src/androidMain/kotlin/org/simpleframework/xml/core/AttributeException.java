package org.simpleframework.xml.core;

public class AttributeException extends PersistenceException {
    public AttributeException(final String str, final Object... objArr) {
        super(str, objArr);
    }
    public AttributeException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
