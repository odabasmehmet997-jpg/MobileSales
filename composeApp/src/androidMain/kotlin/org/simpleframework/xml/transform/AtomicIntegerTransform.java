package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicInteger;


class AtomicIntegerTransform implements Transform<AtomicInteger> {
    AtomicIntegerTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public AtomicInteger read(final String str) {
        return new AtomicInteger(Integer.valueOf(str).intValue());
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final AtomicInteger atomicInteger) {
        return atomicInteger.toString();
    }
}
