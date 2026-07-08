package org.simpleframework.xml.core;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

class AttributeParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;
    public boolean isAttribute() {
        return true;
    }
    public AttributeParameter(final Constructor constructor, final Attribute attribute, final Format format, final int i2) throws Exception {
        final Contact contact = new Contact(attribute, constructor, i2);
        this.contact = contact;
        final AttributeLabel attributeLabel = new AttributeLabel(contact, attribute, format);
        label = attributeLabel;
        expression = attributeLabel.getExpression();
        path = attributeLabel.getPath();
        type = attributeLabel.getType();
        name = attributeLabel.getName();
        key = attributeLabel.getKey();
        index = i2;
    }
    public Object getKey() {
        return key;
    }
    public String getPath() {
        return path;
    }
    public String getName() {
        return name;
    }
    public Expression getExpression() {
        return expression;
    }
    public Class getType() {
        return type;
    }
    public Annotation getAnnotation() {
        return contact.getAnnotation();
    }
    public int getIndex() {
        return index;
    }
    public boolean isRequired() {
        return label.isRequired();
    }
    public boolean isPrimitive() {
        return type.isPrimitive();
    }
    public String toString() {
        return contact.toString();
    }
    private static class Contact extends ParameterContact<Attribute> {
        public Contact(final Attribute attribute, final Constructor constructor, final int i2) {
            super(attribute, constructor, i2);
        }
        public String getName() {
            return label.name();
        }
    }
}
