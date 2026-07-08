package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


class FieldContact implements Contact {
    private final Cache<Annotation> cache = new ConcurrentCache();
    private final Field field;
    private final Annotation label;
    private final Annotation[] list;
    private final int modifier;
    private final String name;

    public FieldContact(final Field field, final Annotation annotation, final Annotation[] annotationArr) {
        modifier = field.getModifiers();
        name = field.getName();
        label = annotation;
        this.field = field;
        list = annotationArr;
    }

    @Override // org.simpleframework.xml.core.Contact
    public boolean isReadOnly() {
        return !this.isStatic() && this.isFinal();
    }

    public boolean isStatic() {
        return Modifier.isStatic(modifier);
    }

    public boolean isFinal() {
        return Modifier.isFinal(modifier);
    }

    @Override // org.simpleframework.xml.strategy.Type
    public Class type() {
        return field.getType();
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class getDependent() {
        return Reflector.getDependent(field);
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class[] getDependents() {
        return Reflector.getDependents(field);
    }

    @Override // org.simpleframework.xml.core.Contact
    public Class getDeclaringClass() {
        return field.getDeclaringClass();
    }

    @Override // org.simpleframework.xml.core.Contact
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.core.Contact
    public Annotation getAnnotation() {
        return label;
    }

    @Override // org.simpleframework.xml.strategy.Type
    public <T extends Annotation> T getAnnotation(final Class<T> cls) {
        if (cls == label.annotationType()) {
            return (T) label;
        }
        return this.getCache(cls);
    }

    private <T extends Annotation> T getCache(final Class<T> cls) {
        if (cache.isEmpty()) {
            for (final Annotation annotation : list) {
                cache.cache(annotation.annotationType(), annotation);
            }
        }
        return (T) cache.fetch(cls);
    }

    @Override // org.simpleframework.xml.core.Contact
    public void set(final Object obj, final Object obj2) throws Exception {
        if (this.isFinal()) {
            return;
        }
        field.set(obj, obj2);
    }

    @Override // org.simpleframework.xml.core.Contact
    public Object get(final Object obj) throws Exception {
        return field.get(obj);
    }

    @Override // org.simpleframework.xml.core.Contact, org.simpleframework.xml.strategy.Type
    public String toString() {
        return String.format("field '%s' %s", this.name, field.toString());
    }
}
