package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;


class DetailScanner implements Detail {
    private DefaultType access;
    private NamespaceList declaration;
    private final List<FieldDetail> fields;
    private final Annotation[] labels;
    private final List<MethodDetail> methods;
    private String name;
    private Namespace namespace;
    private Order order;
    private final DefaultType override;
    private boolean required;
    private Root root;
    private boolean strict;
    private final Class type;
    public DetailScanner(final Class cls) {
        this(cls, null);
    }
    public DetailScanner(final Class cls, final DefaultType defaultType) {
        methods = new LinkedList();
        fields = new LinkedList();
        labels = cls.getDeclaredAnnotations();
        override = defaultType;
        strict = true;
        type = cls;
        this.scan(cls);
    }
    public boolean isRequired() {
        return required;
    }
    public boolean isStrict() {
        return strict;
    }
    public boolean isPrimitive() {
        return type.isPrimitive();
    }
    public boolean isInstantiable() {
        if (Modifier.isStatic(type.getModifiers())) {
            return true;
        }
        return !type.isMemberClass();
    }
    public Root getRoot() {
        return root;
    }
    public String getName() {
        return name;
    }
    public Class getType() {
        return type;
    }
    public Order getOrder() {
        return order;
    }
    public DefaultType getOverride() {
        return override;
    }
    public DefaultType getAccess() {
        final DefaultType defaultType = override;
        return null != defaultType ? defaultType : access;
    }
    public Namespace getNamespace() {
        return namespace;
    }
    public NamespaceList getNamespaceList() {
        return declaration;
    }
    public List<MethodDetail> getMethods() {
        return methods;
    }
    public List<FieldDetail> getFields() {
        return fields;
    }
    public Annotation[] getAnnotations() {
        return labels;
    }
    public Constructor[] getConstructors() {
        return type.getDeclaredConstructors();
    }
    public Class getSuper() {
        final Class superclass = type.getSuperclass();
        if (Object.class == superclass) {
            return null;
        }
        return superclass;
    }
    private void scan(final Class cls) {
        this.methods(cls);
        this.fields(cls);
        this.extract(cls);
    }
    private void extract(final Class cls) {
        for (final Annotation annotation : labels) {
            if (annotation instanceof Namespace) {
                this.namespace(annotation);
            }
            if (annotation instanceof NamespaceList) {
                this.scope(annotation);
            }
            if (annotation instanceof Root) {
                this.root(annotation);
            }
            if (annotation instanceof Order) {
                this.order(annotation);
            }
            if (annotation instanceof Default) {
                this.access(annotation);
            }
        }
    }
    private void methods(final Class cls) {
        for (final Method method : cls.getDeclaredMethods()) {
            methods.add(new MethodDetail(method));
        }
    }
    private void fields(final Class cls) {
        for (final Field field : cls.getDeclaredFields()) {
            fields.add(new FieldDetail(field));
        }
    }
    private void root(final Annotation annotation) {
        if (null != annotation) {
            final Root root = (Root) annotation;
            final String simpleName = type.getSimpleName();
            String name = root.name();
            if (this.isEmpty(name)) {
                name = Reflector.getName(simpleName);
            }
            strict = root.strict();
            this.root = root;
            this.name = name;
        }
    }
    private boolean isEmpty(final String str) {
        return 0 == str.length();
    }
    private void order(final Annotation annotation) {
        if (null != annotation) {
            order = (Order) annotation;
        }
    }
    private void access(final Annotation annotation) {
        if (null != annotation) {
            final Default r2 = (Default) annotation;
            required = r2.required();
            access = r2.value();
        }
    }
    private void namespace(final Annotation annotation) {
        if (null != annotation) {
            namespace = (Namespace) annotation;
        }
    }
    private void scope(final Annotation annotation) {
        if (null != annotation) {
            declaration = (NamespaceList) annotation;
        }
    }
    public String toString() {
        return type.toString();
    }
}
