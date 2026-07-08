package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


class Comparer {
    private static final String NAME = "name";
    private final String[] ignore;

    public Comparer() {
        this(Comparer.NAME);
    }

    public Comparer(final String... strArr) {
        ignore = strArr;
    }

    public boolean equals(final Annotation annotation, final Annotation annotation2) throws Exception {
        final Class<? extends Annotation> annotationType = annotation.annotationType();
        final Class<? extends Annotation> annotationType2 = annotation2.annotationType();
        final Method[] declaredMethods = annotationType.getDeclaredMethods();
        if (!annotationType.equals(annotationType2)) {
            return false;
        }
        for (final Method method : declaredMethods) {
            if (!this.isIgnore(method) && !method.invoke(annotation, null).equals(method.invoke(annotation2, null))) {
                return false;
            }
        }
        return true;
    }

    private boolean isIgnore(final Method method) {
        final String name = method.getName();
        final String[] strArr = ignore;
        if (null != strArr) {
            for (final String str : strArr) {
                if (name.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
