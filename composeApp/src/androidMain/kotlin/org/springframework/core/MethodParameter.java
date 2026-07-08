package org.springframework.core;

import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class MethodParameter {
    private final Constructor<?> constructor;
    private final Type genericParameterType;
    private final Method method;
    private int nestingLevel;
    private Annotation[] parameterAnnotations;
    private final int parameterIndex;
    private final String parameterName;
    private Class<?> parameterType;
    Map<Integer, Integer> typeIndexesPerLevel;
    Map<TypeVariable, Type> typeVariableMap;
    public MethodParameter(final MethodParameter methodParameter) {
        nestingLevel = 1;
        Assert.notNull(methodParameter, "Original must not be null");
        method = methodParameter.method;
        constructor = methodParameter.constructor;
        parameterIndex = methodParameter.parameterIndex;
        parameterType = methodParameter.parameterType;
        genericParameterType = methodParameter.genericParameterType;
        parameterAnnotations = methodParameter.parameterAnnotations;
        parameterName = methodParameter.parameterName;
        nestingLevel = methodParameter.nestingLevel;
        typeIndexesPerLevel = methodParameter.typeIndexesPerLevel;
        typeVariableMap = methodParameter.typeVariableMap;
    }
    public Method getMethod() {
        return method;
    }
    public Constructor<?> getConstructor() {
        return constructor;
    }
    private Member getMember() {
        final Method method = this.method;
        return null != method ? method : constructor;
    }
    private AnnotatedElement getAnnotatedElement() {
        final Method method = this.method;
        return null != method ? method : constructor;
    }
    public int getParameterIndex() {
        return parameterIndex;
    }
    public Class<?> getParameterType() {
        if (null == this.parameterType) {
            if (0 > this.parameterIndex) {
                final Method method = this.method;
                parameterType = null != method ? method.getReturnType() : null;
            } else {
                final Method method2 = method;
                parameterType = null != method2 ? method2.getParameterTypes()[parameterIndex] : constructor.getParameterTypes()[parameterIndex];
            }
        }
        return parameterType;
    }
    public Annotation[] getMethodAnnotations() {
        return this.getAnnotatedElement().getAnnotations();
    }
    public Annotation[] getParameterAnnotations() {
        if (null == this.parameterAnnotations) {
            final Method method = this.method;
            final Annotation[][] parameterAnnotations = null != method ? method.getParameterAnnotations() : constructor.getParameterAnnotations();
            final int i2 = parameterIndex;
            if (0 <= i2 && i2 < parameterAnnotations.length) {
                this.parameterAnnotations = parameterAnnotations[i2];
            } else {
                this.parameterAnnotations = new Annotation[0];
            }
        }
        return parameterAnnotations;
    }
    public void increaseNestingLevel() {
        nestingLevel++;
    }
    public int getNestingLevel() {
        return nestingLevel;
    }
    public void setTypeIndexForCurrentLevel(final int i2) {
        this.getTypeIndexesPerLevel().put(Integer.valueOf(nestingLevel), Integer.valueOf(i2));
    }
    private Map<Integer, Integer> getTypeIndexesPerLevel() {
        if (null == this.typeIndexesPerLevel) {
            typeIndexesPerLevel = new HashMap<>(4);
        }
        return typeIndexesPerLevel;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || !(obj instanceof MethodParameter methodParameter)) {
            return false;
        }
        return parameterIndex == methodParameter.parameterIndex && this.getMember().equals(methodParameter.getMember());
    }
    public int hashCode() {
        return (this.getMember().hashCode() * 31) + parameterIndex;
    }
}
