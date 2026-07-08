package org.simpleframework.xml.transform;


class LongTransform implements Transform<Long> {
    LongTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Long read(final String str) {
        return Long.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Long l) {
        return l.toString();
    }
}
