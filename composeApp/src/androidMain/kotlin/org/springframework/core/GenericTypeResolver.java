package org.springframework.core;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;

public abstract class GenericTypeResolver {
    private static final Map<Class, Map<TypeVariable, Type>> typeVariableCache = new ConcurrentReferenceHashMap();
    public static Type getTargetType(MethodParameter methodParameter) {
        Assert.notNull(methodParameter, "MethodParameter must not be null");
        if (methodParameter.getConstructor() != null) {
            return methodParameter.getConstructor().getGenericParameterTypes()[methodParameter.getParameterIndex()];
        }
        if (methodParameter.getParameterIndex() >= 0) {
            return methodParameter.getMethod().getGenericParameterTypes()[methodParameter.getParameterIndex()];
        }
        return methodParameter.getMethod().getGenericReturnType();
    }
}
