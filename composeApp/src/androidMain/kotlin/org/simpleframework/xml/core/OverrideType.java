package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

import java.lang.annotation.Annotation;


class OverrideType implements Type {
    private final Class override;
    private final Type type;

    public OverrideType(final Type type, final Class cls) {
        override = cls;
        this.type = type;
    }

    @Override // org.simpleframework.xml.strategy.Type
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        return type.getAnnotation(cls);
    }

    @Override // org.simpleframework.xml.strategy.Type
    public Class type() {
        return override;
    }

    @Override // org.simpleframework.xml.strategy.Type
    public String toString() {
        return type.toString();
    }
}
