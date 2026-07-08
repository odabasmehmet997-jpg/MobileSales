package org.simpleframework.xml.transform;

import org.simpleframework.xml.core.PersistenceException;


public class TransformException extends PersistenceException {
    public TransformException(final String str, final Object... objArr) {
        super(String.format(str, objArr));
    }

    public TransformException(final Throwable th, final String str, final Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
