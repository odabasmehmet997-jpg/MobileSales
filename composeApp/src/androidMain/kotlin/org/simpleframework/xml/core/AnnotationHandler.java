package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class AnnotationHandler implements InvocationHandler {
    private static final String ATTRIBUTE = "attribute";
    private static final String CLASS = "annotationType";
    private static final String EQUAL = "equals";
    private static final String REQUIRED = "required";
    private static final String STRING = "toString";
    private final boolean attribute;
    private final Comparer comparer;
    private final boolean required;
    private final Class type;
    public AnnotationHandler(final Class cls) {
        this(cls, true);
    }
    public AnnotationHandler(final Class cls, final boolean z) {
        this(cls, z, false);
    }
    public AnnotationHandler(final Class cls, final boolean z, final boolean z2) {
        comparer = new Comparer();
        attribute = z2;
        required = z;
        type = cls;
    }
    public Object invoke(final Object obj, final Method method, final Object[] objArr) throws Throwable {
        final String name = method.getName();
        if (name.equals(AnnotationHandler.STRING)) {
            return this.toString();
        }
        if (name.equals(AnnotationHandler.EQUAL)) {
            return Boolean.valueOf(this.equals(obj, objArr));
        }
        if (name.equals(AnnotationHandler.CLASS)) {
            return type;
        }
        if (name.equals(AnnotationHandler.REQUIRED)) {
            return Boolean.valueOf(required);
        }
        if (name.equals(AnnotationHandler.ATTRIBUTE)) {
            return Boolean.valueOf(attribute);
        }
        return method.getDefaultValue();
    }
    private boolean equals(final Object obj, final Object[] objArr) throws Throwable {
        final Annotation annotation = (Annotation) obj;
        final Annotation annotation2 = (Annotation) objArr[0];
        if (annotation.annotationType() != annotation2.annotationType()) {
            throw new PersistenceException("Annotation %s is not the same as %s", annotation, annotation2);
        }
        return comparer.equals(annotation, annotation2);
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (null != this.type) {
            this.name(sb);
            this.attributes(sb);
        }
        return sb.toString();
    }
    private void name(final StringBuilder sb) {
        final String name = type.getName();
        sb.append('@');
        sb.append(name);
        sb.append('(');
    }
    private void attributes(final StringBuilder sb) {
        final Method[] declaredMethods = type.getDeclaredMethods();
        for (int i2 = 0; i2 < declaredMethods.length; i2++) {
            final String name = declaredMethods[i2].getName();
            final Object value = this.value(declaredMethods[i2]);
            if (0 < i2) {
                sb.append(',');
                sb.append(' ');
            }
            sb.append(name);
            sb.append('=');
            sb.append(value);
        }
        sb.append(')');
    }
    private Object value(final Method method) {
        final String name = method.getName();
        if (name.equals(AnnotationHandler.REQUIRED)) {
            return Boolean.valueOf(required);
        }
        if (name.equals(AnnotationHandler.ATTRIBUTE)) {
            return Boolean.valueOf(attribute);
        }
        return method.getDefaultValue();
    }
}
