package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;


class CacheParameter implements Parameter {
    private final Annotation annotation;
    private final boolean attribute;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final String name;
    private final String path;
    private final boolean primitive;
    private final boolean required;
    private final String string;
    private final boolean text;
    private final Class type;

    public CacheParameter(final Parameter parameter, final Label label) throws Exception {
        annotation = parameter.getAnnotation();
        expression = parameter.getExpression();
        attribute = parameter.isAttribute();
        primitive = parameter.isPrimitive();
        required = label.isRequired();
        string = parameter.toString();
        text = parameter.isText();
        index = parameter.getIndex();
        name = parameter.getName();
        path = parameter.getPath();
        type = parameter.getType();
        key = label.getKey();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Object getKey() {
        return key;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Class getType() {
        return type;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public int getIndex() {
        return index;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Annotation getAnnotation() {
        return annotation;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Expression getExpression() {
        return expression;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getPath() {
        return path;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isRequired() {
        return required;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isPrimitive() {
        return primitive;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isAttribute() {
        return attribute;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isText() {
        return text;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String toString() {
        return string;
    }
}
