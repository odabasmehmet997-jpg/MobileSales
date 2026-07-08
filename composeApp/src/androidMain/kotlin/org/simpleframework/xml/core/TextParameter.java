package org.simpleframework.xml.core;

import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


class TextParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    @Override // org.simpleframework.xml.core.TemplateParameter, org.simpleframework.xml.core.Parameter
    public boolean isText() {
        return true;
    }

    public TextParameter(final Constructor constructor, final Text text, final Format format, final int i2) throws Exception {
        final Contact contact = new Contact(text, constructor, i2);
        this.contact = contact;
        final TextLabel textLabel = new TextLabel(contact, text, format);
        label = textLabel;
        expression = textLabel.getExpression();
        path = textLabel.getPath();
        type = textLabel.getType();
        name = textLabel.getName();
        key = textLabel.getKey();
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

    public String getPath(final Context context) {
        return this.path;
    }

    public String getName(final Context context) {
        return this.name;
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

    private static class Contact extends ParameterContact<Text> {
        public Contact(final Text text, final Constructor constructor, final int i2) {
            super(text, constructor, i2);
        }

        @Override // org.simpleframework.xml.core.ParameterContact, org.simpleframework.xml.core.Contact
        public String getName() {
            return "";
        }
    }
}
