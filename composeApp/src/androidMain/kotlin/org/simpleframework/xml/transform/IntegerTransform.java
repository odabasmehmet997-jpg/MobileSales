package org.simpleframework.xml.transform;


class IntegerTransform implements Transform<Integer> {
    IntegerTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Integer read(final String str) {
        return Integer.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Integer num) {
        return num.toString();
    }
}
