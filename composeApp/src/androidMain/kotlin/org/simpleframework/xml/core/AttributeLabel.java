package org.simpleframework.xml.core;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.stream.Format;
import java.lang.annotation.Annotation;

class AttributeLabel extends TemplateLabel {
    private final Decorator decorator;
    private final Introspector detail;
    private final String empty;
    private final Format format;
    private final Attribute label;
    private final String name;
    private Expression path;
    private final boolean required;
    private final Class type;
    public boolean isAttribute() {
        return true;
    }
    public boolean isData() {
        return false;
    }
    public AttributeLabel(final Contact contact, final Attribute attribute, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        required = attribute.required();
        type = contact.type();
        empty = attribute.empty();
        name = attribute.name();
        this.format = format;
        label = attribute;
    }
    public Decorator getDecorator() throws Exception {
        return decorator;
    }
    public Converter getConverter(final Context context) throws Exception {
        return new Primitive(context, this.getContact(), this.getEmpty(context));
    }
    public String getEmpty(final Context context) {
        if (detail.isEmpty(empty)) {
            return null;
        }
        return empty;
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
