package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

import java.lang.annotation.Annotation;


class ElementListLabel extends TemplateLabel {
    private Expression cache;
    private final boolean data;
    private final Decorator decorator;
    private final Introspector detail;
    private String entry;
    private final Format format;
    private final boolean inline;
    private Class item;
    private final ElementList label;
    private String name;
    private final String override;
    private String path;
    private final boolean required;
    private final Class type;

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return true;
    }

    public ElementListLabel(final Contact contact, final ElementList elementList, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        required = elementList.required();
        type = contact.type();
        override = elementList.name();
        inline = elementList.inline();
        entry = elementList.entry();
        data = elementList.data();
        item = elementList.type();
        this.format = format;
        label = elementList;
    }

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return decorator;
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(final Context context) throws Exception {
        final String entry = this.getEntry();
        if (!label.inline()) {
            return this.getConverter(context, entry);
        }
        return this.getInlineConverter(context, entry);
    }

    private Converter getConverter(final Context context, final String str) throws Exception {
        final Type dependent = this.getDependent();
        final Contact contact = this.getContact();
        if (!context.isPrimitive(dependent)) {
            return new CompositeList(context, contact, dependent, str);
        }
        return new PrimitiveList(context, contact, dependent, str);
    }

    private Converter getInlineConverter(final Context context, final String str) throws Exception {
        final Type dependent = this.getDependent();
        final Contact contact = this.getContact();
        if (!context.isPrimitive(dependent)) {
            return new CompositeInlineList(context, contact, dependent, str);
        }
        return new PrimitiveInlineList(context, contact, dependent, str);
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getEmpty(final Context context) throws Exception {
        final CollectionFactory collectionFactory = new CollectionFactory(context, new ClassType(type));
        if (label.empty()) {
            return null;
        }
        return collectionFactory.getInstance();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getDependent() throws Exception {
        final Contact contact = this.getContact();
        if (item == Void.TYPE) {
            item = contact.getDependent();
        }
        final Class cls = item;
        if (null == cls) {
            throw new ElementException("Unable to determine generic type for %s", contact);
        }
        return new ClassType(cls);
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
    public Class getType() {
        return type;
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
    public boolean isData() {
        return data;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isRequired() {
        return required;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isInline() {
        return inline;
    }

    @Override // org.simpleframework.xml.core.Label
    public String toString() {
        return detail.toString();
    }
}
