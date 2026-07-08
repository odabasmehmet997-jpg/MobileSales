package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


class FieldDetail {
    private final Field field;
    private final Annotation[] list;
    private final String name;

    public FieldDetail(final Field field) {
        list = field.getDeclaredAnnotations();
        name = field.getName();
        this.field = field;
    }

    public Annotation[] getAnnotations() {
        return list;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }
}
