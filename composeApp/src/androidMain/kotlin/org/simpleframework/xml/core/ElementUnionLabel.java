package org.simpleframework.xml.core;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;


class ElementUnionLabel extends TemplateLabel {
    private final Contact contact;
    private final GroupExtractor extractor;
    private final Label label;
    private Expression path;
    private final ElementUnion union;

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isUnion() {
        return true;
    }

    public ElementUnionLabel(final Contact contact, final ElementUnion elementUnion, final Element element, final Format format) throws Exception {
        extractor = new GroupExtractor(contact, elementUnion, format);
        label = new ElementLabel(contact, element, format);
        this.contact = contact;
        union = elementUnion;
    }

    @Override // org.simpleframework.xml.core.Label
    public Contact getContact() {
        return contact;
    }

    @Override // org.simpleframework.xml.core.Label
    public Annotation getAnnotation() {
        return label.getAnnotation();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Label getLabel(final Class cls) throws Exception {
        final Contact contact = this.contact;
        if (!extractor.isValid(cls)) {
            throw new UnionException("No type matches %s in %s for %s", cls, union, contact);
        }
        return extractor.getLabel(cls);
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getType(final Class cls) throws Exception {
        final Contact contact = this.contact;
        if (extractor.isValid(cls)) {
            return extractor.isDeclared(cls) ? new OverrideType(contact, cls) : contact;
        }
        throw new UnionException("No type matches %s in %s for %s", cls, union, contact);
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(final Context context) throws Exception {
        final Expression expression = this.getExpression();
        final Contact contact = this.contact;
        if (null == contact) {
            throw new UnionException("Union %s was not declared on a field or method", label);
        }
        return new CompositeUnion(context, extractor, expression, contact);
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String[] getNames() throws Exception {
        return extractor.getNames();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String[] getPaths() throws Exception {
        return extractor.getPaths();
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getEmpty(final Context context) throws Exception {
        return label.getEmpty(context);
    }

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return label.getDecorator();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getDependent() throws Exception {
        return label.getDependent();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String getEntry() throws Exception {
        return label.getEntry();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getName() throws Exception {
        return label.getName();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getPath() throws Exception {
        return label.getPath();
    }

    @Override // org.simpleframework.xml.core.Label
    public Expression getExpression() throws Exception {
        if (null == this.path) {
            path = label.getExpression();
        }
        return path;
    }

    @Override // org.simpleframework.xml.core.Label
    public String getOverride() {
        return label.getOverride();
    }

    @Override // org.simpleframework.xml.core.Label
    public Class getType() {
        return label.getType();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return label.isCollection();
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isData() {
        return label.isData();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isInline() {
        return label.isInline();
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isRequired() {
        return label.isRequired();
    }

    @Override // org.simpleframework.xml.core.Label
    public String toString() {
        return label.toString();
    }
}
