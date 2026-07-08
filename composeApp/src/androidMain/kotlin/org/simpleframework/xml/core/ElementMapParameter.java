package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


class ElementMapParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementMapParameter(final Constructor constructor, final ElementMap elementMap, final Format format, final int i2) throws Exception {
        final Contact contact = new Contact(elementMap, constructor, i2);
        this.contact = contact;
        final ElementMapLabel elementMapLabel = new ElementMapLabel(contact, elementMap, format);
        label = elementMapLabel;
        expression = elementMapLabel.getExpression();
        path = elementMapLabel.getPath();
        type = elementMapLabel.getType();
        name = elementMapLabel.getName();
        key = elementMapLabel.getKey();
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

    private static class Contact extends ParameterContact<ElementMap> {
        public Contact(final ElementMap elementMap, final Constructor constructor, final int i2) {
            super(elementMap, constructor, i2);
        }

        @Override // org.simpleframework.xml.core.ParameterContact, org.simpleframework.xml.core.Contact
        public String getName() {
            return label.name();
        }
    }
}
