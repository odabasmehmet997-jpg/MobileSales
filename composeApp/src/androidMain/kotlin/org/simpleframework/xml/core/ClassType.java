package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

import java.lang.annotation.Annotation;


record ClassType(Class type) implements Type {
    @Override // org.simpleframework.xml.strategy.Type
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        return null;
    }

    @Override // org.simpleframework.xml.strategy.Type
    public String toString() {
        return type.toString();
    }
}
