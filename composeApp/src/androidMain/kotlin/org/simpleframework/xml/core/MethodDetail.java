package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


class MethodDetail {
    private final Annotation[] list;
    private final Method method;
    private final String name;

    public MethodDetail(final Method method) {
        list = method.getDeclaredAnnotations();
        name = method.getName();
        this.method = method;
    }

    public Annotation[] getAnnotations() {
        return list;
    }

    public Method getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }
}
