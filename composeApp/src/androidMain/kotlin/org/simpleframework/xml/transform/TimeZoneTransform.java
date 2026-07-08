package org.simpleframework.xml.transform;

import java.util.TimeZone;


class TimeZoneTransform implements Transform<TimeZone> {
    TimeZoneTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public TimeZone read(final String str) {
        return TimeZone.getTimeZone(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final TimeZone timeZone) {
        return timeZone.getID();
    }
}
