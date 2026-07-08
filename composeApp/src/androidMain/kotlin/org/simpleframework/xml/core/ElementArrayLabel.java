package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

import java.lang.annotation.Annotation;


class ElementArrayLabel extends TemplateLabel {
    private final boolean data;
    private final Decorator decorator;
    private final Introspector detail;
    private String entry;
    private final Format format;
    private final ElementArray label;
    private final String name;
    private Expression path;
    private final boolean required;
    private final Class type;

    public ElementArrayLabel(final Contact contact, final ElementArray elementArray, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        required = elementArray.required();
        type = contact.type();
        entry = elementArray.entry();
        data = elementArray.data();
        name = elementArray.name();
        this.format = format;
        label = elementArray;
    }

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return decorator;
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(final Context context) throws Exception {
        final Contact contact = this.getContact();
        final String entry = this.getEntry();
        if (!type.isArray()) {
            throw new InstantiationException("Type is not an array %s for %s", type, contact);
        }
        return this.getConverter(context, entry);
    }

    private Converter getConverter(final Context context, final String str) throws Exception {
        final Type dependent = this.getDependent();
        final Contact contact = this.getContact();
        if (!context.isPrimitive(dependent)) {
            return new CompositeArray(context, contact, dependent, str);
        }
        return new PrimitiveArray(context, contact, dependent, str);
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getEmpty(final Context context) throws Exception {
        final ArrayFactory arrayFactory = new ArrayFactory(context, new ClassType(type));
        if (label.empty()) {
            return null;
        }
        return arrayFactory.getInstance();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String getEntry() throws Exception {
        final Style style = format.style();
        if (detail.isEmpty(entry)) {
            entry = detail.getEntry();
        }
        return style.getElement(entry);
    }

    @Override // org.simpleframework.xml.core.Label
    public String getName() throws Exception {
        return format.style().getElement(detail.getName());
    }

    @Override // org.simpleframework.xml.core.Label
    public String getPath() throws Exception {
        return this.getExpression().getElement(this.getName());
    }

    @Override // org.simpleframework.xml.core.Label
    public Expression getExpression() throws Exception {
        if (null == this.path) {
            path = detail.getExpression();
        }
        return path;
    }

    @Override // org.simpleframework.xml.core.Label
    public Annotation getAnnotation() {
        return label;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getDependent() {
        final Class<?> componentType = type.getComponentType();
        if (null == componentType) {
            return new ClassType(type);
        }
        return new ClassType(componentType);
    }

    @Override // org.simpleframework.xml.core.Label
    public Class getType() {
        return type;
    }

    @Override // org.simpleframework.xml.core.Label
    public Contact getContact() {
        return detail.getContact();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getOverride() {
        return name;
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
