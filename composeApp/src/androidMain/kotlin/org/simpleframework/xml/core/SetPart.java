package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


class SetPart implements MethodPart {
    private final Cache<Annotation> cache = new ConcurrentCache();
    private final Annotation label;
    private final Annotation[] list;
    private final Method method;
    private final String name;
    private final MethodType type;
    public SetPart(final MethodName methodName, final Annotation annotation, final Annotation[] annotationArr) {
        method = methodName.method();
        name = methodName.name();
        type = methodName.type();
        label = annotation;
        list = annotationArr;
    }
    public String getName() {
        return name;
    }
    public Class getType() {
        return method.getParameterTypes()[0];
    }
    public Class getDependent() {
        return Reflector.getParameterDependent(method, 0);
    }
    public Class[] getDependents() {
        return Reflector.getParameterDependents(method, 0);
    }
    public Class getDeclaringClass() {
        return method.getDeclaringClass();
    }
    public Annotation getAnnotation() {
        return label;
    }
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        if (cache.isEmpty()) {
            for (final Annotation annotation : list) {
                cache.cache(annotation.annotationType(), annotation);
            }
        }
        return (T) cache.fetch(cls);
    }
    public MethodType getMethodType() {
        return type;
    }
    public Method getMethod() {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }
    public String toString() {
        return method.toGenericString();
    }
}
