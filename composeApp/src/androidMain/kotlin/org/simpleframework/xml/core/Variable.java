package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

import java.lang.annotation.Annotation;

class Variable implements Label {
    private final Label label;
    private final Object value;
    public Label getLabel(final Class cls) {
        return this;
    }
    public Variable(final Label label, final Object obj) {
        this.label = label;
        value = obj;
    }
    public Type getType(final Class cls) throws Exception {
        return label.getType(cls);
    }
    public String[] getNames() throws Exception {
        return label.getNames();
    }
    public String[] getPaths() throws Exception {
        return label.getPaths();
    }
    public Object getValue() {
        return value;
    }
    public Decorator getDecorator() throws Exception {
        return label.getDecorator();
    }
    public Converter getConverter(final Context context) throws Exception {
        final Converter converter = label.getConverter(context);
        return converter instanceof Adapter ? converter : new Adapter(converter, label, value);
    }
    public Object getEmpty(final Context context) throws Exception {
        return label.getEmpty(context);
    }
    public Contact getContact() {
        return label.getContact();
    }
    public Type getDependent() throws Exception {
        return label.getDependent();
    }
    public Object getKey() throws Exception {
        return label.getKey();
    }
    public String getEntry() throws Exception {
        return label.getEntry();
    }
    public String getName() throws Exception {
        return label.getName();
    }
    public Annotation getAnnotation() {
        return label.getAnnotation();
    }
    public String getPath() throws Exception {
        return label.getPath();
    }
    public Expression getExpression() throws Exception {
        return label.getExpression();
    }
    public String getOverride() {
        return label.getOverride();
    }
    public Class getType() {
        return label.getType();
    }
    public boolean isData() {
        return label.isData();
    }
    public boolean isInline() {
        return label.isInline();
    }
    public boolean isAttribute() {
        return label.isAttribute();
    }
    public boolean isCollection() {
        return label.isCollection();
    }
    public boolean isRequired() {
        return label.isRequired();
    }
    public boolean isText() {
        return label.isText();
    }
    public boolean isTextList() {
        return label.isTextList();
    }
    public boolean isUnion() {
        return label.isUnion();
    }
    public String toString() {
        return label.toString();
    }
    private record Adapter(Converter reader, Label label, Object value) implements Repeater {

        
            public Object read(final InputNode inputNode) throws Exception {
                return this.read(inputNode, value);
            }
            public Object read(final InputNode inputNode, final Object obj) throws Exception {
                final Position position = inputNode.getPosition();
                final String name = inputNode.getName();
                final Converter converter = reader;
                if (converter instanceof Repeater) {
                    return converter.read(inputNode, obj);
                }
                throw new PersistenceException("Element '%s' is already used with %s at %s", name, label, position);
            }
            public boolean validate(final InputNode inputNode) throws Exception {
                final Position position = inputNode.getPosition();
                final String name = inputNode.getName();
                final Converter converter = reader;
                if (converter instanceof Repeater) {
                    return converter.validate(inputNode);
                }
                throw new PersistenceException("Element '%s' declared twice at %s", name, position);
            }

            
            public void write(final OutputNode outputNode, final Object obj) throws Exception {
                this.write(outputNode, obj);
            }
        }
}
