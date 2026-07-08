package org.springframework.core.convert;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;

class BeanPropertyDescriptor extends AbstractDescriptor {
    private final Annotation[] annotations;
    private final MethodParameter methodParameter;
    public BeanPropertyDescriptor(final Property property) {
        super(property.getClass());
        throw null;
    }
    public Annotation[] getAnnotations() {
        return annotations;
    }
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionParameterType(methodParameter);
    }
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyParameterType(methodParameter);
    }
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueParameterType(methodParameter);
    }
    protected AbstractDescriptor nested(final Class<?> cls, final int i2) {
        final MethodParameter methodParameter = new MethodParameter(this.methodParameter);
        methodParameter.increaseNestingLevel();
        methodParameter.setTypeIndexForCurrentLevel(i2);
        return new BeanPropertyDescriptor(cls, null, methodParameter, annotations);
    }
    private BeanPropertyDescriptor(final Class<?> cls, final Property property, final MethodParameter methodParameter, final Annotation[] annotationArr) {
        super(cls);
        this.methodParameter = methodParameter;
        annotations = annotationArr;
    }
}
