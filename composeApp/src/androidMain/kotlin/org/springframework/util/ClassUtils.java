package org.springframework.util;

import okhttp3.HttpUrl;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ClassUtils {
    private static final Map<String, Class<?>> commonClassCache;
    private static final Map<String, Class<?>> primitiveTypeNameMap;
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap;
    static {
        final HashMap hashMap = new HashMap(8);
        primitiveWrapperTypeMap = hashMap;
        primitiveTypeToWrapperMap = new HashMap(8);
        primitiveTypeNameMap = new HashMap(32);
        commonClassCache = new HashMap(32);
        hashMap.put(Boolean.class, Boolean.TYPE);
        hashMap.put(Byte.class, Byte.TYPE);
        hashMap.put(Character.class, Character.TYPE);
        hashMap.put(Double.class, Double.TYPE);
        hashMap.put(Float.class, Float.TYPE);
        hashMap.put(Integer.class, Integer.TYPE);
        hashMap.put(Long.class, Long.TYPE);
        hashMap.put(Short.class, Short.TYPE);
        for (final Object entry : hashMap.entrySet()) {
            final Class<?> put = ClassUtils.primitiveTypeToWrapperMap.put(entry.getClass(), (Class<?>) entry);
            ClassUtils.registerCommonClasses((Class<?>) entry);
        }
        final HashSet<Class<?>> hashSet = new HashSet(32);
        hashSet.addAll(ClassUtils.primitiveWrapperTypeMap.values());
        hashSet.addAll(Arrays.asList(boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class));
        hashSet.add(Void.TYPE);
        for (final Class<?> cls : hashSet) {
            ClassUtils.primitiveTypeNameMap.put(cls.getName(), cls);
        }
        ClassUtils.registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class);
        ClassUtils.registerCommonClasses(Number.class, Number[].class, String.class, String[].class, Object.class, Object[].class, Class.class, Class[].class);
        ClassUtils.registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class);
    }
    private static void registerCommonClasses(final Class<?>... clsArr) {
        for (final Class<?> cls : clsArr) {
            ClassUtils.commonClassCache.put(cls.getName(), cls);
        }
    }
    public static String getQualifiedName(final Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        if (cls.isArray()) {
            return ClassUtils.getQualifiedNameForArray(cls);
        }
        return cls.getName();
    }
    private static String getQualifiedNameForArray(Class<?> cls) {
        final StringBuilder sb = new StringBuilder();
        while (cls.isArray()) {
            cls = cls.getComponentType();
            sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        sb.insert(0, cls.getName());
        return sb.toString();
    }
    public static String getDescriptiveType(final Object obj) {
        if (null == obj) {
            return null;
        }
        final Class<?> cls = obj.getClass();
        if (Proxy.isProxyClass(cls)) {
            final StringBuilder sb = new StringBuilder(cls.getName());
            sb.append(" implementing ");
            final Class<?>[] interfaces = cls.getInterfaces();
            for (int i2 = 0; i2 < interfaces.length; i2++) {
                sb.append(interfaces[i2].getName());
                if (i2 < interfaces.length - 1) {
                    sb.append(',');
                }
            }
            return sb.toString();
        }
        if (cls.isArray()) {
            return ClassUtils.getQualifiedNameForArray(cls);
        }
        return cls.getName();
    }
    public static Class<?> resolvePrimitiveIfNecessary(final Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return (!cls.isPrimitive() || cls == Void.TYPE) ? cls : ClassUtils.primitiveTypeToWrapperMap.get(cls);
    }
}
