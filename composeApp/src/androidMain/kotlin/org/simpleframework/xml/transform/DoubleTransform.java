package org.simpleframework.xml.transform;


class DoubleTransform implements Transform<Double> {
    DoubleTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Double read(final String str) {
        return Double.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Double d2) {
        return d2.toString();
    }
}
