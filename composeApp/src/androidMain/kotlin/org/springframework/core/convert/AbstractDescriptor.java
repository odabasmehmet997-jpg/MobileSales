package org.springframework.core.convert;

import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

abstract class AbstractDescriptor {
    private final Class<?> type;
    public abstract Annotation[] getAnnotations();
    protected abstract AbstractDescriptor nested(Class<?> cls, int i2);
    protected abstract Class<?> resolveCollectionElementType();
    protected abstract Class<?> resolveMapKeyType();
    protected abstract Class<?> resolveMapValueType();
    protected AbstractDescriptor(final Class<?> cls) {
        Assert.notNull(cls, "Type must not be null");
        type = cls;
    }
    public Class<?> getType() {
        return type;
    }
    public TypeDescriptor getElementTypeDescriptor() {
        if (this.isCollection()) {
            final Class<?> resolveCollectionElementType = this.resolveCollectionElementType();
            if (null != resolveCollectionElementType) {
                return new TypeDescriptor(this.nested(resolveCollectionElementType, 0));
            }
            return null;
        }
        if (this.isArray()) {
            return new TypeDescriptor(this.nested(this.type.getComponentType(), 0));
        }
        return null;
    }
    public TypeDescriptor getMapKeyTypeDescriptor() {
        final Class<?> resolveMapKeyType;
        if (!this.isMap() || null == (resolveMapKeyType = resolveMapKeyType())) {
            return null;
        }
        return new TypeDescriptor(this.nested(resolveMapKeyType, 0));
    }
    public TypeDescriptor getMapValueTypeDescriptor() {
        final Class<?> resolveMapValueType;
        if (!this.isMap() || null == (resolveMapValueType = resolveMapValueType())) {
            return null;
        }
        return new TypeDescriptor(this.nested(resolveMapValueType, 1));
    }
    public AbstractDescriptor nested() {
        if (this.isCollection()) {
            final Class<?> resolveCollectionElementType = this.resolveCollectionElementType();
            if (null != resolveCollectionElementType) {
                return this.nested(resolveCollectionElementType, 0);
            }
            return null;
        }
        if (this.isArray()) {
            return this.nested(this.type.getComponentType(), 0);
        }
        if (this.isMap()) {
            final Class<?> resolveMapValueType = this.resolveMapValueType();
            if (null != resolveMapValueType) {
                return this.nested(resolveMapValueType, 1);
            }
            return null;
        }
        if (Object.class.equals(this.type)) {
            return this;
        }
        throw new IllegalStateException("Not a collection, array, or map: cannot resolve nested value types");
    }
    private boolean isCollection() {
        return Collection.class.isAssignableFrom(this.type);
    }
    private boolean isArray() {
        return this.type.isArray();
    }
    private boolean isMap() {
        return Map.class.isAssignableFrom(this.type);
    }
}
