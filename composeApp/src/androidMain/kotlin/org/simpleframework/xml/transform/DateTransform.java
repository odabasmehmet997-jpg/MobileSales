package org.simpleframework.xml.transform;

import java.util.Date;


class DateTransform<T extends Date> implements Transform<T> {
    private final DateFactory<T> factory;

    public DateTransform(final Class<T> cls) throws Exception {
        factory = new DateFactory<>(cls);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized T read(final String str) throws Exception {
        return factory.getInstance(Long.valueOf(DateType.getDate(str).getTime()));
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized String write(final T t) throws Exception {
        return DateType.getText(t);
    }
}
