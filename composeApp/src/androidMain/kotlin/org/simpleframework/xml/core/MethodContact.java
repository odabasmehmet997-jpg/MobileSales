package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;


class MethodContact implements Contact {
    private final MethodPart get;
    private final Class item;
    private final Class[] items;
    private final Annotation label;
    private final String name;
    private final Class owner;
    private final MethodPart set;
    private final Class type;

    public MethodContact(final MethodPart methodPart) {
        this(methodPart, null);
    }

    public MethodContact(final MethodPart methodPart, final MethodPart methodPart2) {
        owner = methodPart.getDeclaringClass();
        label = methodPart.getAnnotation();
        items = methodPart.getDependents();
        item = methodPart.getDependent();
        type = methodPart.getType();
        name = methodPart.getName();
        set = methodPart2;
        get = methodPart;
    }

    @Override // org.simpleframework.xml.core.Contact
    public boolean isReadOnly() {
        return null == this.set;
    }

    public MethodPart getRead() {
        return get;
    }

    public MethodPart getWrite() {
        return set;
    }

    @Override // org.simpleframework.xml.core.Contact
    public Annotation getAnnotation() {
        return label;
    }

    @Override // org.simpleframework.xml.strategy.Type
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        final MethodPart methodPart;
        final T t = get.getAnnotation(cls);
        if (cls == label.annotationType()) {
            return (T) label;
        }
        return (null != t || null == (methodPart = this.set)) ? t : methodPart.getAnnotation(cls);
    }

    @Override // org.simpleframework.xml.strategy.Type
    public Class type() {
        return type;
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class getDependent() {
        return item;
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class[] getDependents() {
        return items;
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class getDeclaringClass() {
        return owner;
    }

    @Override // org.simpleframework.xml.core.Contact
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.core.Contact
    public void set(final Object obj, final Object obj2) throws Exception {
        final Class<?> declaringClass = get.getMethod().getDeclaringClass();
        final MethodPart methodPart = set;
        if (null == methodPart) {
            throw new MethodException("Property '%s' is read only in %s", name, declaringClass);
        }
        methodPart.getMethod().invoke(obj, obj2);
    }

    @Override // org.simpleframework.xml.core.Contact
    public Object get(final Object obj) throws Exception {
        return get.getMethod().invoke(obj, null);
    }

    @Override // org.simpleframework.xml.core.Contact, org.simpleframework.xml.strategy.Type
    public String toString() {
        return String.format("method '%s'", name);
    }
}
