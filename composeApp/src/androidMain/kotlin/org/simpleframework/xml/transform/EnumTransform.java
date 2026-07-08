package org.simpleframework.xml.transform;


class EnumTransform implements Transform<Enum> {
    private final Class type;

    public EnumTransform(final Class cls) {
        type = cls;
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Enum read(final String str) throws Exception {
        return Enum.valueOf(type, str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Enum r1) throws Exception {
        return r1.name();
    }
}
