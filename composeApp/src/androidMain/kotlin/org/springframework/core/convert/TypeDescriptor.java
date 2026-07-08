package org.springframework.core.convert;

import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TypeDescriptor implements Serializable {
    static final Annotation[] EMPTY_ANNOTATION_ARRAY = new Annotation[0];
    private static final Map<Class<?>, TypeDescriptor> typeDescriptorCache;
    private final Annotation[] annotations;
    private final TypeDescriptor elementTypeDescriptor;
    private final TypeDescriptor mapKeyTypeDescriptor;
    private final TypeDescriptor mapValueTypeDescriptor;
    private final Class<?> type;

    static {
        final HashMap hashMap = new HashMap();
        typeDescriptorCache = hashMap;
        final Class cls = Boolean.TYPE;
        hashMap.put(cls, new TypeDescriptor((Class<?>) cls));
        hashMap.put(Boolean.class, new TypeDescriptor(Boolean.class));
        final Class cls2 = Byte.TYPE;
        hashMap.put(cls2, new TypeDescriptor((Class<?>) cls2));
        hashMap.put(Byte.class, new TypeDescriptor(Byte.class));
        final Class cls3 = Character.TYPE;
        hashMap.put(cls3, new TypeDescriptor((Class<?>) cls3));
        hashMap.put(Character.class, new TypeDescriptor(Character.class));
        final Class cls4 = Short.TYPE;
        hashMap.put(cls4, new TypeDescriptor((Class<?>) cls4));
        hashMap.put(Short.class, new TypeDescriptor(Short.class));
        final Class cls5 = Integer.TYPE;
        hashMap.put(cls5, new TypeDescriptor((Class<?>) cls5));
        hashMap.put(Integer.class, new TypeDescriptor(Integer.class));
        final Class cls6 = Long.TYPE;
        hashMap.put(cls6, new TypeDescriptor((Class<?>) cls6));
        hashMap.put(Long.class, new TypeDescriptor(Long.class));
        final Class cls7 = Float.TYPE;
        hashMap.put(cls7, new TypeDescriptor((Class<?>) cls7));
        hashMap.put(Float.class, new TypeDescriptor(Float.class));
        final Class cls8 = Double.TYPE;
        hashMap.put(cls8, new TypeDescriptor((Class<?>) cls8));
        hashMap.put(Double.class, new TypeDescriptor(Double.class));
        hashMap.put(String.class, new TypeDescriptor(String.class));
    }

    public TypeDescriptor(final MethodParameter methodParameter) {
        this(new ParameterDescriptor(methodParameter));
    }

    public TypeDescriptor(final Field field) {
        this(new FieldDescriptor(field));
    }

    public TypeDescriptor(final Property property) {
        this(new BeanPropertyDescriptor(property));
    }

    public static TypeDescriptor valueOf(final Class<?> cls) {
        final TypeDescriptor typeDescriptor = TypeDescriptor.typeDescriptorCache.get(cls);
        return null != typeDescriptor ? typeDescriptor : new TypeDescriptor(cls);
    }

    public static TypeDescriptor collection(final Class<?> cls, final TypeDescriptor typeDescriptor) {
        if (!Collection.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("collectionType must be a java.util.Collection");
        }
        return new TypeDescriptor(cls, typeDescriptor);
    }

    public static TypeDescriptor map(final Class<?> cls, final TypeDescriptor typeDescriptor, final TypeDescriptor typeDescriptor2) {
        if (!Map.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("mapType must be a java.util.Map");
        }
        return new TypeDescriptor(cls, typeDescriptor, typeDescriptor2);
    }

    public static TypeDescriptor array(final TypeDescriptor typeDescriptor) {
        if (null == typeDescriptor) {
            return null;
        }
        return new TypeDescriptor(Array.newInstance(typeDescriptor.type, 0).getClass(), typeDescriptor, null, null, typeDescriptor.getAnnotations());
    }

    public static TypeDescriptor nested(final MethodParameter methodParameter, final int i2) {
        if (1 != methodParameter.getNestingLevel()) {
            throw new IllegalArgumentException("methodParameter nesting level must be 1: use the nestingLevel parameter to specify the desired nestingLevel for nested type traversal");
        }
        return TypeDescriptor.nested(new ParameterDescriptor(methodParameter), i2);
    }

    public static TypeDescriptor nested(final Field field, final int i2) {
        return TypeDescriptor.nested(new FieldDescriptor(field), i2);
    }

    public static TypeDescriptor nested(final Property property, final int i2) {
        return TypeDescriptor.nested(new BeanPropertyDescriptor(property), i2);
    }

    public static TypeDescriptor forObject(final Object obj) {
        if (null != obj) {
            return TypeDescriptor.valueOf(obj.getClass());
        }
        return null;
    }

    public Class<?> getType() {
        return type;
    }

    public Class<?> getObjectType() {
        return ClassUtils.resolvePrimitiveIfNecessary(this.type);
    }

    public TypeDescriptor narrow(final Object obj) {
        return null == obj ? this : new TypeDescriptor(obj.getClass(), elementTypeDescriptor, mapKeyTypeDescriptor, mapValueTypeDescriptor, annotations);
    }

    public TypeDescriptor upcast(final Class<?> cls) {
        if (null == cls) {
            return null;
        }
        Assert.isAssignable(cls, this.type);
        return new TypeDescriptor(cls, elementTypeDescriptor, mapKeyTypeDescriptor, mapValueTypeDescriptor, annotations);
    }

    public String getName() {
        return ClassUtils.getQualifiedName(this.type);
    }

    public boolean isPrimitive() {
        return this.type.isPrimitive();
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public boolean hasAnnotation(final Class<? extends Annotation> cls) {
        return null != getAnnotation(cls);
    }

    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        for (final Annotation annotation : annotations) {
            final T t = (T) annotation;
            if (t.annotationType().equals(cls)) {
                return t;
            }
        }
        for (final Annotation annotation2 : annotations) {
            final T t2 = annotation2.annotationType().getAnnotation(cls);
            if (null != t2) {
                return t2;
            }
        }
        return null;
    }

    public boolean isAssignableTo(final TypeDescriptor typeDescriptor) {
        if (!typeDescriptor.getObjectType().isAssignableFrom(this.getObjectType())) {
            return false;
        }
        if (this.isArray() && typeDescriptor.isArray()) {
            return this.getElementTypeDescriptor().isAssignableTo(typeDescriptor.getElementTypeDescriptor());
        }
        if (this.isCollection() && typeDescriptor.isCollection()) {
            return this.isNestedAssignable(this.getElementTypeDescriptor(), typeDescriptor.getElementTypeDescriptor());
        }
        if (this.isMap() && typeDescriptor.isMap()) {
            return this.isNestedAssignable(this.getMapKeyTypeDescriptor(), typeDescriptor.getMapKeyTypeDescriptor()) && this.isNestedAssignable(this.getMapValueTypeDescriptor(), typeDescriptor.getMapValueTypeDescriptor());
        }
        return true;
    }

    public boolean isCollection() {
        return Collection.class.isAssignableFrom(this.type);
    }

    public boolean isArray() {
        return this.type.isArray();
    }

    public TypeDescriptor getElementTypeDescriptor() {
        this.assertCollectionOrArray();
        return elementTypeDescriptor;
    }

    public TypeDescriptor elementTypeDescriptor(final Object obj) {
        return this.narrow(obj, this.getElementTypeDescriptor());
    }

    public boolean isMap() {
        return Map.class.isAssignableFrom(this.type);
    }

    public TypeDescriptor getMapKeyTypeDescriptor() {
        this.assertMap();
        return mapKeyTypeDescriptor;
    }

    public TypeDescriptor getMapKeyTypeDescriptor(final Object obj) {
        return this.narrow(obj, this.getMapKeyTypeDescriptor());
    }

    public TypeDescriptor getMapValueTypeDescriptor() {
        this.assertMap();
        return mapValueTypeDescriptor;
    }

    public TypeDescriptor getMapValueTypeDescriptor(final Object obj) {
        return this.narrow(obj, this.getMapValueTypeDescriptor());
    }

    @Deprecated
    public Class<?> getElementType() {
        return this.getElementTypeDescriptor().type;
    }

    @Deprecated
    public Class<?> getMapKeyType() {
        return this.getMapKeyTypeDescriptor().type;
    }

    @Deprecated
    public Class<?> getMapValueType() {
        return this.getMapValueTypeDescriptor().type;
    }

    TypeDescriptor(final AbstractDescriptor abstractDescriptor) {
        type = abstractDescriptor.getType();
        elementTypeDescriptor = abstractDescriptor.getElementTypeDescriptor();
        mapKeyTypeDescriptor = abstractDescriptor.getMapKeyTypeDescriptor();
        mapValueTypeDescriptor = abstractDescriptor.getMapValueTypeDescriptor();
        annotations = abstractDescriptor.getAnnotations();
    }

    static Annotation[] nullSafeAnnotations(final Annotation[] annotationArr) {
        return null != annotationArr ? annotationArr : TypeDescriptor.EMPTY_ANNOTATION_ARRAY;
    }

    private TypeDescriptor(final Class<?> cls) {
        this(new ClassDescriptor(cls));
    }

    private TypeDescriptor(final Class<?> cls, final TypeDescriptor typeDescriptor) {
        this(cls, typeDescriptor, null, null, TypeDescriptor.EMPTY_ANNOTATION_ARRAY);
    }

    private TypeDescriptor(final Class<?> cls, final TypeDescriptor typeDescriptor, final TypeDescriptor typeDescriptor2) {
        this(cls, null, typeDescriptor, typeDescriptor2, TypeDescriptor.EMPTY_ANNOTATION_ARRAY);
    }

    private TypeDescriptor(final Class<?> cls, final TypeDescriptor typeDescriptor, final TypeDescriptor typeDescriptor2, final TypeDescriptor typeDescriptor3, final Annotation[] annotationArr) {
        type = cls;
        elementTypeDescriptor = typeDescriptor;
        mapKeyTypeDescriptor = typeDescriptor2;
        mapValueTypeDescriptor = typeDescriptor3;
        annotations = annotationArr;
    }

    private static TypeDescriptor nested(AbstractDescriptor abstractDescriptor, final int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            abstractDescriptor = abstractDescriptor.nested();
            if (null == abstractDescriptor) {
                return null;
            }
        }
        return new TypeDescriptor(abstractDescriptor);
    }

    private void assertCollectionOrArray() {
        if (!this.isCollection() && !this.isArray()) {
            throw new IllegalStateException("Not a java.util.Collection or Array");
        }
    }

    private void assertMap() {
        if (!this.isMap()) {
            throw new IllegalStateException("Not a java.util.Map");
        }
    }

    private TypeDescriptor narrow(final Object obj, final TypeDescriptor typeDescriptor) {
        if (null != typeDescriptor) {
            return typeDescriptor.narrow(obj);
        }
        if (null != obj) {
            return new TypeDescriptor(obj.getClass(), null, null, null, annotations);
        }
        return null;
    }

    private boolean isNestedAssignable(final TypeDescriptor typeDescriptor, final TypeDescriptor typeDescriptor2) {
        if (null == typeDescriptor || null == typeDescriptor2) {
            return true;
        }
        return typeDescriptor.isAssignableTo(typeDescriptor2);
    }

    private String wildcard(final TypeDescriptor typeDescriptor) {
        return null != typeDescriptor ? typeDescriptor.toString() : "?";
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeDescriptor typeDescriptor)) {
            return false;
        }
        if (!ObjectUtils.nullSafeEquals(type, typeDescriptor.type)) {
            return false;
        }
        final Annotation[] annotationArr = annotations;
        if (annotationArr.length != typeDescriptor.annotations.length) {
            return false;
        }
        for (final Annotation annotation : annotationArr) {
            if (null == typeDescriptor.getAnnotation(annotation.annotationType())) {
                return false;
            }
        }
        if (this.isCollection() || this.isArray()) {
            return ObjectUtils.nullSafeEquals(elementTypeDescriptor, typeDescriptor.elementTypeDescriptor);
        }
        if (this.isMap()) {
            return ObjectUtils.nullSafeEquals(mapKeyTypeDescriptor, typeDescriptor.mapKeyTypeDescriptor) && ObjectUtils.nullSafeEquals(mapValueTypeDescriptor, typeDescriptor.mapValueTypeDescriptor);
        }
        return true;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Annotation annotation : annotations) {
            sb.append("@");
            sb.append(annotation.annotationType().getName());
            sb.append(' ');
        }
        sb.append(ClassUtils.getQualifiedName(this.type));
        if (this.isMap()) {
            sb.append("<");
            sb.append(this.wildcard(mapKeyTypeDescriptor));
            sb.append(", ");
            sb.append(this.wildcard(mapValueTypeDescriptor));
            sb.append(">");
        } else if (this.isCollection()) {
            sb.append("<");
            sb.append(this.wildcard(elementTypeDescriptor));
            sb.append(">");
        }
        return sb.toString();
    }
}
