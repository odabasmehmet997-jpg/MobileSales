package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


class ElementListUnionParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementListUnionParameter(final Constructor constructor, final ElementListUnion elementListUnion, final ElementList elementList, final Format format, final int i2) throws Exception {
        final Contact contact = new Contact(elementList, constructor, i2);
        this.contact = contact;
        final ElementListUnionLabel elementListUnionLabel = new ElementListUnionLabel(contact, elementListUnion, elementList, format);
        label = elementListUnionLabel;
        expression = elementListUnionLabel.getExpression();
        path = elementListUnionLabel.getPath();
        type = elementListUnionLabel.getType();
        name = elementListUnionLabel.getName();
        key = elementListUnionLabel.getKey();
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

    private static class Contact extends ParameterContact<ElementList> {
        public Contact(final ElementList elementList, final Constructor constructor, final int i2) {
            super(elementList, constructor, i2);
        }

        @Override // org.simpleframework.xml.core.ParameterContact, org.simpleframework.xml.core.Contact
        public String getName() {
            return label.name();
        }
    }
}
