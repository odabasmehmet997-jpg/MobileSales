package org.simpleframework.xml.transform;


class ByteTransform implements Transform<Byte> {
    ByteTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Byte read(final String str) {
        return Byte.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Byte b2) {
        return b2.toString();
    }
}
