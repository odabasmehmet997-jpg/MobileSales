package org.simpleframework.xml.transform;


class FloatTransform implements Transform<Float> {
    FloatTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Float read(final String str) {
        return Float.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Float f2) {
        return f2.toString();
    }
}
