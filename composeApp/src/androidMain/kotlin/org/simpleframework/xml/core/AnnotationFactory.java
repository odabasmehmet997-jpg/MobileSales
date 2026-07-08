package org.simpleframework.xml.core;

import org.simpleframework.xml.*;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Verbosity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;

class AnnotationFactory {
    private final Format format;
    private final boolean required;
    public AnnotationFactory(final Detail detail, final Support support) {
        required = detail.isRequired();
        format = support.getFormat();
    }
    public Annotation getInstance(final Class<?> cls, final Class<?>[] clsArr) throws Exception {
        final ClassLoader classLoader = this.getClassLoader();
        if (Map.class.isAssignableFrom(cls)) {
            if (this.isPrimitiveKey(clsArr) && this.isAttribute()) {
                return this.getInstance(classLoader, ElementMap.class, true);
            }
            return this.getInstance(classLoader, ElementMap.class);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return this.getInstance(classLoader, ElementList.class);
        }
        return this.getInstance(cls);
    }
    private Annotation getInstance(final Class cls) throws Exception {
        final ClassLoader classLoader = this.getClassLoader();
        final Class<?> componentType = cls.getComponentType();
        if (cls.isArray()) {
            if (this.isPrimitive(componentType)) {
                return this.getInstance(classLoader, Element.class);
            }
            return this.getInstance(classLoader, ElementArray.class);
        }
        if (this.isPrimitive(cls) && this.isAttribute()) {
            return this.getInstance(classLoader, Attribute.class);
        }
        return this.getInstance(classLoader, Element.class);
    }
    private Annotation getInstance(final ClassLoader classLoader, final Class cls) throws Exception {
        return this.getInstance(classLoader, cls, false);
    }
    private Annotation getInstance(final ClassLoader classLoader, final Class cls, final boolean z) throws Exception {
        return (Annotation) Proxy.newProxyInstance(classLoader, new Class[]{cls}, new AnnotationHandler(cls, required, z));
    }
    private ClassLoader getClassLoader() throws Exception {
        return AnnotationFactory.class.getClassLoader();
    }
    private boolean isPrimitiveKey(final Class[] clsArr) {
        if (null == clsArr || 0 >= clsArr.length) {
            return false;
        }
        final Class superclass = clsArr[0].getSuperclass();
        final Class cls = clsArr[0];
        if (null == superclass || !(superclass.isEnum() || cls.isEnum())) {
            return this.isPrimitive(cls);
        }
        return true;
    }
    private boolean isPrimitive(final Class cls) {
        if (Number.class.isAssignableFrom(cls) || Boolean.class == cls || Character.class == cls) {
            return true;
        }
        return cls.isPrimitive();
    }
    private boolean isAttribute() {
        final Verbosity verbosity = format.verbosity();
        return null != verbosity && Verbosity.LOW == verbosity;
    }
}
