package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;

class VersionLabel extends TemplateLabel {
    private final Decorator decorator;
    private final Introspector detail;
    private final Format format;
    private final Version label;
    private final String name;
    private Expression path;
    private final boolean required;
    private final Class type;
    public String getEmpty(final Context context) {
        return null;
    }
    public boolean isAttribute() {
        return true;
    }
    public boolean isData() {
        return false;
    }
    public VersionLabel(final Contact contact, final Version version, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        required = version.required();
        type = contact.type();
        name = version.name();
        this.format = format;
        label = version;
    }
    public Decorator getDecorator() throws Exception {
        return decorator;
    }
    public Converter getConverter(final Context context) throws Exception {
        final String empty = this.getEmpty(context);
        final Contact contact = this.getContact();
        if (!context.isFloat(contact)) {
            throw new AttributeException("Cannot use %s to represent %s", label, contact);
        }
        return new Primitive(context, contact, empty);
    }
    public String getName() throws Exception {
        return format.style().getAttribute(detail.getName());
    }
    public String getPath() throws Exception {
        return this.getExpression().getAttribute(this.getName());
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
    public String getOverride() {
        return name;
    }
    public Contact getContact() {
        return detail.getContact();
    }
    public Class getType() {
        return type;
    }
    public boolean isRequired() {
        return required;
    }
    public String toString() {
        return detail.toString();
    }
}
