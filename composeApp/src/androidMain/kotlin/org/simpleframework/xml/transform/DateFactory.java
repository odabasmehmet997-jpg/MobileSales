package org.simpleframework.xml.transform;

import java.lang.reflect.Constructor;
import java.util.Date;


class DateFactory<T extends Date> {
    private final Constructor<T> factory;

    public DateFactory(final Class<T> cls) throws Exception {
        this(cls, Long.TYPE);
    }

    public DateFactory(final Class<T> cls, final Class... clsArr) throws Exception {
        factory = cls.getDeclaredConstructor(clsArr);
    }

    public T getInstance(final Object... objArr) throws Exception {
        return factory.newInstance(objArr);
    }
}
