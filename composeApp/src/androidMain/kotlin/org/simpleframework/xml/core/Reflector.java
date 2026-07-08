package org.simpleframework.xml.core;

import java.lang.reflect.*;

final class Reflector {
    Reflector() {
    }
    public static Class getDependent(final Field field) {
        final ParameterizedType type = Reflector.getType(field);
        if (null != type) {
            return Reflector.getClass(type);
        }
        return Object.class;
    }
    public static Class[] getDependents(final Field field) {
        final ParameterizedType type = Reflector.getType(field);
        if (null != type) {
            return Reflector.getClasses(type);
        }
        return new Class[0];
    }
    private static ParameterizedType getType(final Field field) {
        final Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            return (ParameterizedType) genericType;
        }
        return null;
    }
    public static Class getReturnDependent(final Method method) {
        final ParameterizedType returnType = Reflector.getReturnType(method);
        if (null != returnType) {
            return Reflector.getClass(returnType);
        }
        return Object.class;
    }
    public static Class[] getReturnDependents(final Method method) {
        final ParameterizedType returnType = Reflector.getReturnType(method);
        if (null != returnType) {
            return Reflector.getClasses(returnType);
        }
        return new Class[0];
    }
    private static ParameterizedType getReturnType(final Method method) {
        final Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            return (ParameterizedType) genericReturnType;
        }
        return null;
    }
    public static Class getParameterDependent(final Method method, final int i2) {
        final ParameterizedType parameterType = Reflector.getParameterType(method, i2);
        if (null != parameterType) {
            return Reflector.getClass(parameterType);
        }
        return Object.class;
    }
    public static Class[] getParameterDependents(final Method method, final int i2) {
        final ParameterizedType parameterType = Reflector.getParameterType(method, i2);
        if (null != parameterType) {
            return Reflector.getClasses(parameterType);
        }
        return new Class[0];
    }
    public static Class getParameterDependent(final Constructor constructor, final int i2) {
        final ParameterizedType parameterType = Reflector.getParameterType(constructor, i2);
        if (null != parameterType) {
            return Reflector.getClass(parameterType);
        }
        return Object.class;
    }
    public static Class[] getParameterDependents(final Constructor constructor, final int i2) {
        final ParameterizedType parameterType = Reflector.getParameterType(constructor, i2);
        if (null != parameterType) {
            return Reflector.getClasses(parameterType);
        }
        return new Class[0];
    }
    private static ParameterizedType getParameterType(final Method method, final int i2) {
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length <= i2) {
            return null;
        }
        final Type type = genericParameterTypes[i2];
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        return null;
    }
    private static ParameterizedType getParameterType(final Constructor constructor, final int i2) {
        final Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        if (genericParameterTypes.length <= i2) {
            return null;
        }
        final Type type = genericParameterTypes[i2];
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        return null;
    }
    private static Class getClass(final ParameterizedType parameterizedType) {
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (0 < actualTypeArguments.length) {
            return Reflector.getClass(actualTypeArguments[0]);
        }
        return null;
    }
    private static Class[] getClasses(final ParameterizedType parameterizedType) {
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        final Class[] clsArr = new Class[actualTypeArguments.length];
        for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
            clsArr[i2] = Reflector.getClass(actualTypeArguments[i2]);
        }
        return clsArr;
    }
    private static Class getClass(final Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return Reflector.getGenericClass(type);
    }
    private static Class getGenericClass(final Type type) {
        if (type instanceof GenericArrayType) {
            return Reflector.getArrayClass(type);
        }
        return Object.class;
    }
    private static Class getArrayClass(final Type type) {
        final Class cls = Reflector.getClass(((GenericArrayType) type).getGenericComponentType());
        if (null != cls) {
            return Array.newInstance((Class<?>) cls, 0).getClass();
        }
        return null;
    }
    public static String getName(final String str) {
        if (0 >= str.length()) {
            return str;
        }
        final char[] charArray = str.toCharArray();
        final char c2 = charArray[0];
        if (!Reflector.isAcronym(charArray)) {
            charArray[0] = Reflector.toLowerCase(c2);
        }
        return new String(charArray);
    }
    private static boolean isAcronym(final char[] cArr) {
        if (2 <= cArr.length && Reflector.isUpperCase(cArr[0])) {
            return Reflector.isUpperCase(cArr[1]);
        }
        return false;
    }
    private static char toLowerCase(final char c2) {
        return Character.toLowerCase(c2);
    }
    private static boolean isUpperCase(final char c2) {
        return Character.isUpperCase(c2);
    }
}
