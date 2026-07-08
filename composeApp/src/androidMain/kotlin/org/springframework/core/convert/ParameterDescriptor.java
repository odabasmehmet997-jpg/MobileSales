package org.springframework.core.convert;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;


/* loaded from: classes.dex */
class ParameterDescriptor extends AbstractDescriptor {
    private final MethodParameter methodParameter;

    public ParameterDescriptor(final MethodParameter methodParameter) {
        super(methodParameter.getParameterType());
        if (1 != methodParameter.getNestingLevel()) {
            throw new IllegalArgumentException("MethodParameter argument must have its nestingLevel set to 1");
        }
        this.methodParameter = methodParameter;
    }

    private ParameterDescriptor(final Class<?> cls, final MethodParameter methodParameter) {
        super(cls);
        this.methodParameter = methodParameter;
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        if (-1 == this.methodParameter.getParameterIndex()) {
            return TypeDescriptor.nullSafeAnnotations(methodParameter.getMethodAnnotations());
        }
        return TypeDescriptor.nullSafeAnnotations(methodParameter.getParameterAnnotations());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionParameterType(methodParameter);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyParameterType(methodParameter);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueParameterType(methodParameter);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(final Class<?> cls, final int i2) {
        final MethodParameter methodParameter = new MethodParameter(this.methodParameter);
        methodParameter.increaseNestingLevel();
        methodParameter.setTypeIndexForCurrentLevel(i2);
        return new ParameterDescriptor(cls, methodParameter);
    }
}
