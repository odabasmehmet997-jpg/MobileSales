package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicLong;


class AtomicLongTransform implements Transform<AtomicLong> {
    AtomicLongTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public AtomicLong read(final String str) {
        return new AtomicLong(Long.valueOf(str).longValue());
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final AtomicLong atomicLong) {
        return atomicLong.toString();
    }
}
