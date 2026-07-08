package org.springframework.core.convert;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

class ClassDescriptor extends AbstractDescriptor {
    ClassDescriptor(final Class<?> cls) {
        super(cls);
    }
    public Annotation[] getAnnotations() {
        return TypeDescriptor.EMPTY_ANNOTATION_ARRAY;
    }
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionType((Class<? extends Collection>) this.getType());
    }
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyType((Class<? extends Map>) this.getType());
    }
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueType((Class<? extends Map>) this.getType());
    }
    protected AbstractDescriptor nested(final Class<?> cls, final int i2) {
        return new ClassDescriptor(cls);
    }
}
