package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


class GetPart implements MethodPart {
    private final Cache<Annotation> cache = new ConcurrentCache();
    private final Annotation label;
    private final Annotation[] list;
    private final Method method;
    private final String name;
    private final MethodType type;

    public GetPart(final MethodName methodName, final Annotation annotation, final Annotation[] annotationArr) {
        method = methodName.method();
        name = methodName.name();
        type = methodName.type();
        label = annotation;
        list = annotationArr;
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Class getType() {
        return method.getReturnType();
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Class getDependent() {
        return Reflector.getReturnDependent(method);
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Class[] getDependents() {
        return Reflector.getReturnDependents(method);
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Class getDeclaringClass() {
        return method.getDeclaringClass();
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Annotation getAnnotation() {
        return label;
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        if (cache.isEmpty()) {
            for (final Annotation annotation : list) {
                cache.cache(annotation.annotationType(), annotation);
            }
        }
        return (T) cache.fetch(cls);
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public MethodType getMethodType() {
        return type;
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public Method getMethod() {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }

    @Override // org.simpleframework.xml.core.MethodPart
    public String toString() {
        return method.toGenericString();
    }
}
