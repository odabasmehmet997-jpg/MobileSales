package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodPartFactory {
    private final AnnotationFactory factory;
    public MethodPartFactory(final Detail detail, final Support support) {
        factory = new AnnotationFactory(detail, support);
    }
    public MethodPart getInstance(final Method method, final Annotation[] annotationArr) throws Exception {
        final Annotation annotation = this.getAnnotation(method);
        if (null != annotation) {
            return this.getInstance(method, annotation, annotationArr);
        }
        return null;
    }
    public MethodPart getInstance(final Method method, final Annotation annotation, final Annotation[] annotationArr) throws Exception {
        final MethodName name = this.getName(method, annotation);
        if (MethodType.SET == name.type()) {
            return new SetPart(name, annotation, annotationArr);
        }
        return new GetPart(name, annotation, annotationArr);
    }
    private MethodName getName(final Method method, final Annotation annotation) throws Exception {
        final MethodType methodType = this.getMethodType(method);
        if (MethodType.GET == methodType) {
            return this.getRead(method, MethodType.GET);
        }
        if (MethodType.IS == methodType) {
            return this.getRead(method, MethodType.IS);
        }
        if (MethodType.SET == methodType) {
            return this.getWrite(method, MethodType.SET);
        }
        throw new MethodException("Annotation %s must mark a set or get method", annotation);
    }
    private MethodType getMethodType(final Method method) {
        final String name = method.getName();
        if (name.startsWith("get")) {
            return MethodType.GET;
        }
        if (name.startsWith("is")) {
            return MethodType.IS;
        }
        if (name.startsWith("set")) {
            return MethodType.SET;
        }
        return MethodType.NONE;
    }
    private Annotation getAnnotation(final Method method) throws Exception {
        final Class[] dependents = this.getDependents(method);
        final Class type = this.getType(method);
        if (null != type) {
            return factory.getInstance(type, dependents);
        }
        return null;
    }
    private Class[] getDependents(final Method method) throws Exception {
        final MethodType methodType = this.getMethodType(method);
        if (MethodType.SET == methodType) {
            return Reflector.getParameterDependents(method, 0);
        }
        if (MethodType.GET == methodType) {
            return Reflector.getReturnDependents(method);
        }
        if (MethodType.IS == methodType) {
            return Reflector.getReturnDependents(method);
        }
        return null;
    }
    public Class getType(final Method method) throws Exception {
        final MethodType methodType = this.getMethodType(method);
        if (MethodType.SET == methodType) {
            return this.getParameterType(method);
        }
        if (MethodType.GET == methodType) {
            return this.getReturnType(method);
        }
        if (MethodType.IS == methodType) {
            return this.getReturnType(method);
        }
        return null;
    }
    private Class getParameterType(final Method method) throws Exception {
        if (1 == method.getParameterTypes().length) {
            return method.getParameterTypes()[0];
        }
        return null;
    }
    private Class getReturnType(final Method method) throws Exception {
        if (0 == method.getParameterTypes().length) {
            return method.getReturnType();
        }
        return null;
    }
    private MethodName getRead(final Method method, final MethodType methodType) throws Exception {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final String name = method.getName();
        if (0 != parameterTypes.length) {
            throw new MethodException("Get method %s is not a valid property", method);
        }
        final String typeName = this.getTypeName(name, methodType);
        if (null == typeName) {
            throw new MethodException("Could not get name for %s", method);
        }
        return new MethodName(method, methodType, typeName);
    }
    private MethodName getWrite(final Method method, final MethodType methodType) throws Exception {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final String name = method.getName();
        if (1 != parameterTypes.length) {
            throw new MethodException("Set method %s is not a valid property", method);
        }
        final String typeName = this.getTypeName(name, methodType);
        if (null == typeName) {
            throw new MethodException("Could not get name for %s", method);
        }
        return new MethodName(method, methodType, typeName);
    }
    private String getTypeName(String str, final MethodType methodType) {
        final int prefix = methodType.getPrefix();
        final int length = str.length();
        if (length > prefix) {
            str = str.substring(prefix, length);
        }
        return Reflector.getName(str);
    }
}
