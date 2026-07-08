package org.simpleframework.xml.transform;


class StringTransform implements Transform<String> {
    @Override // org.simpleframework.xml.transform.Transform
    public String read(final String str) {
        return str;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final String str) {
        return str;
    }

    StringTransform() {
    }
}
