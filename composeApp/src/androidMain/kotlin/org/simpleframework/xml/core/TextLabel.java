package org.simpleframework.xml.core;

import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;

class TextLabel extends TemplateLabel {
    private final Contact contact;
    private final boolean data;
    private final Introspector detail;
    private final String empty;
    private final Text label;
    private Expression path;
    private final boolean required;
    private final Class type;
    public Decorator getDecorator() throws Exception {
        return null;
    }
    public boolean isInline() {
        return true;
    }
    public boolean isText() {
        return true;
    }
    public TextLabel(final Contact contact, final Text text, final Format format) {
        detail = new Introspector(contact, this, format);
        required = text.required();
        type = contact.type();
        empty = text.empty();
        data = text.data();
        this.contact = contact;
        label = text;
    }
    public Converter getConverter(final Context context) throws Exception {
        final String empty = this.getEmpty(context);
        final Contact contact = this.contact;
        if (!context.isPrimitive(contact)) {
            throw new TextException("Cannot use %s to represent %s", contact, label);
        }
        return new Primitive(context, contact, empty);
    }
    public String getEmpty(final Context context) {
        if (detail.isEmpty(empty)) {
            return null;
        }
        return empty;
    }
    public String getPath() throws Exception {
        return this.getExpression().getPath();
    }
    public Expression getExpression() throws Exception {
        if (null == this.path) {
            path = detail.getExpression();
        }
        return path;
    }
    public Annotation getAnnotation() {
        return label;
    }
    public Contact getContact() {
        return contact;
    }
    public String getName() {
        return "";
    }
    public String getOverride() {
        return contact.toString();
    }
    public Class getType() {
        return type;
    }
    public boolean isRequired() {
        return required;
    }
    public boolean isData() {
        return data;
    }
    public String toString() {
        return detail.toString();
    }
}
