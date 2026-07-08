package org.springframework.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Map;

public abstract class GenericCollectionTypeResolver {
    public static Class<?> getCollectionType(Class<? extends Collection> cls) {
        return extractTypeFromClass(cls, Collection.class, 0);
    }
    public static Class<?> getMapKeyType(Class<? extends Map> cls) {
        return extractTypeFromClass(cls, Map.class, 0);
    }
    public static Class<?> getMapValueType(Class<? extends Map> cls) {
        return extractTypeFromClass(cls, Map.class, 1);
    }
    public static Class<?> getCollectionFieldType(Field field, int i2, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Collection.class, 0, map, i2);
    }
    public static Class<?> getMapKeyFieldType(Field field, int i2, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Map.class, 0, map, i2);
    }
    public static Class<?> getMapValueFieldType(Field field, int i2, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Map.class, 1, map, i2);
    }
    public static Class<?> getCollectionParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Collection.class, 0);
    }
    public static Class<?> getMapKeyParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Map.class, 0);
    }
    public static Class<?> getMapValueParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Map.class, 1);
    }
    private static Class<?> getGenericParameterType(MethodParameter methodParameter, Class<?> cls, int i2) {
        return extractType(GenericTypeResolver.getTargetType(methodParameter), cls, i2, methodParameter.typeVariableMap, methodParameter.typeIndexesPerLevel, methodParameter.getNestingLevel(), 1);
    }
    private static Class<?> getGenericFieldType(Field field, Class<?> cls, int i2, Map<Integer, Integer> map, int i3) {
        return extractType(field.getGenericType(), cls, i2, null, map, i3, 1);
    }
    private static Class<?> extractType(Type type, Class<?> cls, int i2, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i3, int i4) {
        Type type2;
        if ((type instanceof TypeVariable) && map != null && (type2 = map.get(type)) != null) {
            type = type2;
        }
        if (type instanceof ParameterizedType) {
            return extractTypeFromParameterizedType((ParameterizedType) type, cls, i2, map, map2, i3, i4);
        }
        if (type instanceof Class) {
            return extractTypeFromClass((Class) type, cls, i2, map, map2, i3, i4);
        }
        if (type instanceof GenericArrayType) {
            return extractType(((GenericArrayType) type).getGenericComponentType(), cls, i2, map, map2, i3, i4 + 1);
        }
        return null;
    }
    private static Class<?> extractTypeFromParameterizedType(ParameterizedType parameterizedType, Class<?> cls, int i2, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i3, int i4) {
        Type type;
        if (!(parameterizedType.getRawType() instanceof Class)) {
            return null;
        }
        Class<?> cls2 = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i3 - i4 > 0) {
            int i5 = i4 + 1;
            Integer num = map2 != null ? map2.get(Integer.valueOf(i5)) : null;
            return extractType(actualTypeArguments[num != null ? num.intValue() : actualTypeArguments.length - 1], cls, i2, map, map2, i3, i5);
        }
        if (cls != null && !cls.isAssignableFrom(cls2)) {
            return null;
        }
        Class<?> clsExtractTypeFromClass = extractTypeFromClass(cls2, cls, i2, map, map2, i3, i4);
        if (clsExtractTypeFromClass != null) {
            return clsExtractTypeFromClass;
        }
        if (actualTypeArguments != null && i2 < actualTypeArguments.length) {
            Type rawType = actualTypeArguments[i2];
            if ((rawType instanceof TypeVariable) && map != null && (type = map.get(rawType)) != null) {
                rawType = type;
            }
            if (rawType instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) rawType;
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds != null && upperBounds.length > 0 && !Object.class.equals(upperBounds[0])) {
                    rawType = upperBounds[0];
                } else {
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    if (lowerBounds != null && lowerBounds.length > 0 && !Object.class.equals(lowerBounds[0])) {
                        rawType = lowerBounds[0];
                    }
                }
            }
            if (rawType instanceof ParameterizedType) {
                rawType = ((ParameterizedType) rawType).getRawType();
            }
            if (rawType instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) rawType).getGenericComponentType();
                if (genericComponentType instanceof Class) {
                    return Array.newInstance((Class<?>) genericComponentType, 0).getClass();
                }
            } else if (rawType instanceof Class) {
                return (Class) rawType;
            }
        }
        return null;
    }
    private static Class<?> extractTypeFromClass(Class<?> cls, Class<?> cls2, int i2) {
        return extractTypeFromClass(cls, cls2, i2, null, null, 1, 1);
    }
    private static Class<?> extractTypeFromClass(Class<?> cls, Class<?> cls2, int i2, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i3, int i4) {
        if (cls.getName().startsWith("java.util.")) {
            return null;
        }
        if (cls.getSuperclass() != null && isIntrospectionCandidate(cls.getSuperclass())) {
            try {
                return extractType(cls.getGenericSuperclass(), cls2, i2, map, map2, i3, i4);
            } catch (MalformedParameterizedTypeException unused) {
            }
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type type : genericInterfaces) {
                Type rawType = type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type;
                if ((rawType instanceof Class) && isIntrospectionCandidate((Class) rawType)) {
                    return extractType(type, cls2, i2, map, map2, i3, i4);
                }
            }
        }
        return null;
    }
    private static boolean isIntrospectionCandidate(Class<?>     cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }
}
