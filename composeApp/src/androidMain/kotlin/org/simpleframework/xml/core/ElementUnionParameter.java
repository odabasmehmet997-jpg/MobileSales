package org.simpleframework.xml.core;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


class ElementUnionParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementUnionParameter(final Constructor constructor, final ElementUnion elementUnion, final Element element, final Format format, final int i2) throws Exception {
        final Contact contact = new Contact(element, constructor, i2);
        this.contact = contact;
        final ElementUnionLabel elementUnionLabel = new ElementUnionLabel(contact, elementUnion, element, format);
        label = elementUnionLabel;
        expression = elementUnionLabel.getExpression();
        path = elementUnionLabel.getPath();
        type = elementUnionLabel.getType();
        name = elementUnionLabel.getName();
        key = elementUnionLabel.getKey();
        index = i2;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Object getKey() {
        return key;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getPath() {
        return path;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Expression getExpression() {
        return expression;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Class getType() {
        return type;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public Annotation getAnnotation() {
        return contact.getAnnotation();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public int getIndex() {
        return index;
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isRequired() {
        return label.isRequired();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public boolean isPrimitive() {
        return type.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Parameter
    public String toString() {
        return contact.toString();
    }

    private static class Contact extends ParameterContact<Element> {
        public Contact(final Element element, final Constructor constructor, final int i2) {
            super(element, constructor, i2);
        }

        @Override // org.simpleframework.xml.core.ParameterContact, org.simpleframework.xml.core.Contact
        public String getName() {
            return label.name();
        }
    }
}
