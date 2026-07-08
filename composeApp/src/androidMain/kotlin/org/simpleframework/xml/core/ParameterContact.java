package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

abstract class ParameterContact<T extends Annotation> implements Contact {
    protected final Constructor factory;
    protected final int index;
    protected final T label;
    protected final Annotation[] labels;
    protected final Class owner;
    public Object get(final Object obj) {
        return null;
    }
    public abstract String getName();
    public boolean isReadOnly() {
        return false;
    }
    public void set(final Object obj, final Object obj2) {
    }
    protected ParameterContact(final T t, final Constructor constructor, final int i2) {
        labels = constructor.getParameterAnnotations()[i2];
        owner = constructor.getDeclaringClass();
        factory = constructor;
        index = i2;
        label = t;
    }
    public Annotation getAnnotation() {
        return label;
    }
    public Class type() {
        return factory.getParameterTypes()[index];
    }
    public Class getDependent() {
        return Reflector.getParameterDependent(factory, index);
    }
    public Class[] getDependents() {
        return Reflector.getParameterDependents(factory, index);
    }
    public Class getDeclaringClass() {
        return owner;
    }
    public <A extends Annotation> A getAnnotation(final Class<A> cls) {
        for (final Annotation annotation : labels) {
            final A a2 = (A) annotation;
            if (a2.annotationType().equals(cls)) {
                return a2;
            }
        }
        return null;
    }
    public String toString() {
        return String.format("parameter %s of constructor %s", Integer.valueOf(index), factory);
    }
}
