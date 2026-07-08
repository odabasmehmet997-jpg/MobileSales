package org.simpleframework.xml.core;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;


class ElementLabel extends TemplateLabel {
    private Expression cache;
    private final boolean data;
    private final Decorator decorator;
    private final Introspector detail;
    private final Class expect;
    private final Format format;
    private final Element label;
    private String name;
    private final String override;
    private String path;
    private final boolean required;
    private final Class type;

    @Override // org.simpleframework.xml.core.Label
    public Object getEmpty(final Context context) {
        return null;
    }

    public ElementLabel(final Contact contact, final Element element, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        required = element.required();
        type = contact.type();
        override = element.name();
        expect = element.type();
        data = element.data();
        this.format = format;
        label = element;
    }

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return decorator;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getType(final Class cls) {
        final Contact contact = this.getContact();
        final Class cls2 = expect;
        return cls2 == Void.TYPE ? contact : new OverrideType(contact, cls2);
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(final Context context) throws Exception {
        final Contact contact = this.getContact();
        if (context.isPrimitive(contact)) {
            return new Primitive(context, contact);
        }
        final Class cls = expect;
        if (cls == Void.TYPE) {
            return new Composite(context, contact);
        }
        return new Composite(context, contact, cls);
    }

    @Override // org.simpleframework.xml.core.Label
    public String getName() throws Exception {
        if (null == this.name) {
            name = format.style().getElement(detail.getName());
        }
        return name;
    }

    @Override // org.simpleframework.xml.core.Label
    public String getPath() throws Exception {
        if (null == this.path) {
            path = this.getExpression().getElement(this.getName());
        }
        return path;
    }

    @Override // org.simpleframework.xml.core.Label
    public Expression getExpression() throws Exception {
        if (null == this.cache) {
            cache = detail.getExpression();
        }
        return cache;
    }

    @Override // org.simpleframework.xml.core.Label
    public Annotation getAnnotation() {
        return label;
    }

    @Override // org.simpleframework.xml.core.Label
    public Contact getContact() {
        return detail.getContact();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getOverride() {
        return override;
    }

    @Override // org.simpleframework.xml.core.Label
    public Class getType() {
        final Class cls = expect;
        return cls == Void.TYPE ? type : cls;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isRequired() {
        return required;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isData() {
        return data;
    }

    @Override // org.simpleframework.xml.core.Label
    public String toString() {
        return detail.toString();
    }
}
