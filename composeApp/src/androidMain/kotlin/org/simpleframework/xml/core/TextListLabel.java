package org.simpleframework.xml.core;

import org.simpleframework.xml.Text;
import org.simpleframework.xml.strategy.Type;

import java.lang.annotation.Annotation;


class TextListLabel extends TemplateLabel {
    private final String empty;
    private final Label label;
    private final Text text;
    public Decorator getDecorator() throws Exception {
        return null;
    }
    public boolean isCollection() {
        return true;
    }
    public boolean isTextList() {
        return true;
    }

    public TextListLabel(final Label label, final Text text) {
        empty = text.empty();
        this.label = label;
        this.text = text;
    }
    public String[] getNames() throws Exception {
        return label.getNames();
    }
    public String[] getPaths() throws Exception {
        return label.getPaths();
    }
    public String getEmpty(final Context context) throws Exception {
        return empty;
    }
    public Converter getConverter(final Context context) throws Exception {
        final Contact contact = this.getContact();
        if (!label.isCollection()) {
            throw new TextException("Cannot use %s to represent %s", contact, label);
        }
        return new TextList(context, contact, label);
    }
    public String getName() throws Exception {
        return label.getName();
    }
    public String getPath() throws Exception {
        return label.getPath();
    }
    public Expression getExpression() throws Exception {
        return label.getExpression();
    }
    public Type getDependent() throws Exception {
        return label.getDependent();
    }
    public String getEntry() throws Exception {
        return label.getEntry();
    }
    public Object getKey() throws Exception {
        return label.getKey();
    }
    public Annotation getAnnotation() {
        return label.getAnnotation();
    }

    public Contact getContact() {
        return label.getContact();
    }
    public Class getType() {
        return label.getType();
    }

    public String getOverride() {
        return label.getOverride();
    }

    public boolean isData() {
        return label.isData();
    }

    public boolean isRequired() {
        return label.isRequired();
    }
    public boolean isInline() {
        return label.isInline();
    }
    public String toString() {
        return String.format("%s %s", text, label);
    }
}
