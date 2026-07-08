package org.simpleframework.xml.transform;


class ShortTransform implements Transform<Short> {
    ShortTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Short read(final String str) {
        return Short.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Short sh) {
        return sh.toString();
    }
}
