package org.springframework.core.convert;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/* loaded from: classes.dex */
class FieldDescriptor extends AbstractDescriptor {
    private final Field field;
    private final int nestingLevel;
    private Map<Integer, Integer> typeIndexesPerLevel;

    public FieldDescriptor(final Field field) {
        super(field.getType());
        this.field = field;
        nestingLevel = 1;
    }

    private FieldDescriptor(final Class<?> cls, final Field field, final int i2, final int i3, final Map<Integer, Integer> map) {
        super(cls);
        this.field = field;
        nestingLevel = i2;
        typeIndexesPerLevel = map;
        map.put(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        return TypeDescriptor.nullSafeAnnotations(field.getAnnotations());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionFieldType(field, nestingLevel, typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyFieldType(field, nestingLevel, typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueFieldType(field, nestingLevel, typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(final Class<?> cls, final int i2) {
        if (null == this.typeIndexesPerLevel) {
            typeIndexesPerLevel = new HashMap(4);
        }
        return new FieldDescriptor(cls, field, nestingLevel + 1, i2, typeIndexesPerLevel);
    }
}
