package org.simpleframework.xml.strategy;

import org.simpleframework.xml.core.PersistenceException;


public class CycleException extends PersistenceException {
    public CycleException(final String str, final Object... objArr) {
        super(str, objArr);
    }

    public CycleException(final Throwable th, final String str, final Object... objArr) {
        super(th, str, objArr);
    }
}
