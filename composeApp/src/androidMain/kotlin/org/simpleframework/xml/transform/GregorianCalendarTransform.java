package org.simpleframework.xml.transform;

import java.util.Date;
import java.util.GregorianCalendar;


class GregorianCalendarTransform implements Transform<GregorianCalendar> {
    private final DateTransform transform;

    public GregorianCalendarTransform() throws Exception {
        this(Date.class);
    }

    public GregorianCalendarTransform(final Class cls) throws Exception {
        transform = new DateTransform(cls);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public GregorianCalendar read(final String str) throws Exception {
        return this.read(transform.read(str));
    }

    private GregorianCalendar read(final Date date) throws Exception {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (null != date) {
            gregorianCalendar.setTime(date);
        }
        return gregorianCalendar;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final GregorianCalendar gregorianCalendar) throws Exception {
        return transform.write((DateTransform) gregorianCalendar.getTime());
    }
}
