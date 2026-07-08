package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

import java.lang.annotation.Annotation;

class CacheLabel implements Label {
    private final Annotation annotation;
    private final boolean attribute;
    private final boolean collection;
    private final Contact contact;
    private final boolean data;
    private final Decorator decorator;
    private final Type depend;
    private final String entry;
    private final Expression expression;
    private final boolean inline;
    private final Object key;
    private final Label label;
    private final boolean list;
    private final String name;
    private final String[] names;
    private final String override;
    private final String path;
    private final String[] paths;
    private final boolean required;
    private final boolean text;
    private final Class type;
    private final boolean union;

    public CacheLabel(final Label label) throws Exception {
        annotation = label.getAnnotation();
        expression = label.getExpression();
        decorator = label.getDecorator();
        attribute = label.isAttribute();
        collection = label.isCollection();
        contact = label.getContact();
        depend = label.getDependent();
        required = label.isRequired();
        override = label.getOverride();
        list = label.isTextList();
        inline = label.isInline();
        union = label.isUnion();
        names = label.getNames();
        paths = label.getPaths();
        path = label.getPath();
        type = label.getType();
        name = label.getName();
        entry = label.getEntry();
        data = label.isData();
        text = label.isText();
        key = label.getKey();
        this.label = label;
    }
    public Type getType(final Class cls) throws Exception {
        return label.getType(cls);
    }
    public Label getLabel(final Class cls) throws Exception {
        return label.getLabel(cls);
    }
    public String[] getNames() throws Exception {
        return names;
    }
    public String[] getPaths() throws Exception {
        return paths;
    }
    public Annotation getAnnotation() {
        return annotation;
    }
    public Contact getContact() {
        return contact;
    }
    public Decorator getDecorator() throws Exception {
        return decorator;
    }
    public Converter getConverter(final Context context) throws Exception {
        return label.getConverter(context);
    }
    public Object getEmpty(final Context context) throws Exception {
        return label.getEmpty(context);
    }
    public Type getDependent() throws Exception {
        return depend;
    }
    public Object getKey() throws Exception {
        return key;
    }
    public String getEntry() throws Exception {
        return entry;
    }
    public String getName() throws Exception {
        return name;
    }
    public String getPath() throws Exception {
        return path;
    }
    public Expression getExpression() throws Exception {
        return expression;
    }
    public String getOverride() {
        return override;
    }
    public Class getType() {
        return type;
    }
    public boolean isData() {
        return data;
    }
    public boolean isText() {
        return text;
    }
    public boolean isTextList() {
        return list;
    }
    public boolean isInline() {
        return inline;
    }
    public boolean isAttribute() {
        return attribute;
    }
    public boolean isCollection() {
        return collection;
    }
    public boolean isRequired() {
        return required;
    }
    public boolean isUnion() {
        return union;
    }
    public String toString() {
        return label.toString();
    }
}
