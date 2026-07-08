package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;


class DefaultDetail implements Detail {
    private final DefaultType access;
    private final Detail detail;

    public DefaultDetail(final Detail detail, final DefaultType defaultType) {
        this.detail = detail;
        access = defaultType;
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isStrict() {
        return detail.isStrict();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isRequired() {
        return detail.isRequired();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isInstantiable() {
        return detail.isInstantiable();
    }

    @Override // org.simpleframework.xml.core.Detail
    public boolean isPrimitive() {
        return detail.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Class getSuper() {
        return detail.getSuper();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Class getType() {
        return detail.getType();
    }

    @Override // org.simpleframework.xml.core.Detail
    public String getName() {
        return detail.getName();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Root getRoot() {
        return detail.getRoot();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Order getOrder() {
        return detail.getOrder();
    }

    @Override // org.simpleframework.xml.core.Detail
    public DefaultType getAccess() {
        return detail.getAccess();
    }

    @Override // org.simpleframework.xml.core.Detail
    public DefaultType getOverride() {
        return access;
    }

    @Override // org.simpleframework.xml.core.Detail
    public Namespace getNamespace() {
        return detail.getNamespace();
    }

    @Override // org.simpleframework.xml.core.Detail
    public NamespaceList getNamespaceList() {
        return detail.getNamespaceList();
    }

    @Override // org.simpleframework.xml.core.Detail
    public List<MethodDetail> getMethods() {
        return detail.getMethods();
    }

    @Override // org.simpleframework.xml.core.Detail
    public List<FieldDetail> getFields() {
        return detail.getFields();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Annotation[] getAnnotations() {
        return detail.getAnnotations();
    }

    @Override // org.simpleframework.xml.core.Detail
    public Constructor[] getConstructors() {
        return detail.getConstructors();
    }

    public String toString() {
        return detail.toString();
    }
}
