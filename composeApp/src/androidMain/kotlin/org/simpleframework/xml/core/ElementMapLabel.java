package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

import java.lang.annotation.Annotation;


class ElementMapLabel extends TemplateLabel {
    private Expression cache;
    private final boolean data;
    private final Decorator decorator;
    private final Introspector detail;
    private final Entry entry;
    private final Format format;
    private final boolean inline;
    private Class[] items;
    private final ElementMap label;
    private String name;
    private final String override;
    private String parent;
    private String path;
    private final boolean required;
    private final Class type;

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return true;
    }

    public ElementMapLabel(final Contact contact, final ElementMap elementMap, final Format format) {
        detail = new Introspector(contact, this, format);
        decorator = new Qualifier(contact);
        entry = new Entry(contact, elementMap);
        required = elementMap.required();
        type = contact.type();
        inline = elementMap.inline();
        override = elementMap.name();
        data = elementMap.data();
        this.format = format;
        label = elementMap;
    }

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return decorator;
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(final Context context) throws Exception {
        final Type map = this.getMap();
        if (!label.inline()) {
            return new CompositeMap(context, entry, map);
        }
        return new CompositeInlineMap(context, entry, map);
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getEmpty(final Context context) throws Exception {
        final MapFactory mapFactory = new MapFactory(context, new ClassType(type));
        if (label.empty()) {
            return null;
        }
        return mapFactory.getInstance();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getDependent() throws Exception {
        final Contact contact = this.getContact();
        if (null == this.items) {
            items = contact.getDependents();
        }
        final Class[] clsArr = items;
        if (null == clsArr) {
            throw new ElementException("Unable to determine type for %s", contact);
        }
        if (0 == clsArr.length) {
            return new ClassType(Object.class);
        }
        return new ClassType(clsArr[0]);
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String getEntry() throws Exception {
        final Style style = format.style();
        if (detail.isEmpty(parent)) {
            parent = detail.getEntry();
        }
        return style.getElement(parent);
    }

    @Override // org.simpleframework.xml.core.Label
    public String getName() throws Exception {
        if (null == this.name) {
            final Style style = format.style();
            String entry = this.entry.getEntry();
            if (!label.inline()) {
                entry = detail.getName();
            }
            name = style.getElement(entry);
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

    private Type getMap() {
        return new ClassType(type);
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
