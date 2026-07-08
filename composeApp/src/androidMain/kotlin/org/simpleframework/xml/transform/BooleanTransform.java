package org.simpleframework.xml.transform;


class BooleanTransform implements Transform<Boolean> {
    BooleanTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Boolean read(final String str) {
        return Boolean.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Boolean bool) {
        return bool.toString();
    }
}
