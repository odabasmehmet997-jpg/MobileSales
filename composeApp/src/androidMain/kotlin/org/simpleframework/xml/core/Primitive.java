package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class Primitive implements Converter {
    private final Context context;
    private final String empty;
    private final Class expect;
    private final PrimitiveFactory factory;
    private final Type type;
    public Primitive(final Context context, final Type type) {
        this(context, type, null);
    }
    public Primitive(final Context context, final Type type, final String str) {
        factory = new PrimitiveFactory(context, type);
        expect = type.type();
        this.context = context;
        empty = str;
        this.type = type;
    }
    public Object read(final InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            return this.readElement(inputNode);
        }
        return this.read(inputNode, expect);
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        if (null != obj) {
            throw new PersistenceException("Can not read existing %s for %s", expect, type);
        }
        return this.read(inputNode);
    }
    public Object read(final InputNode inputNode, final Class cls) throws Exception {
        final String value = inputNode.getValue();
        if (null == value) {
            return null;
        }
        final String str = empty;
        if (value.equals(str)) {
            return empty;
        }
        return this.readTemplate(value, cls);
    }
    private Object readElement(final InputNode inputNode) throws Exception {
        final Instance primitiveFactory = factory.getInstance(inputNode);
        if (!primitiveFactory.isReference()) {
            return this.readElement(inputNode, primitiveFactory);
        }
        return primitiveFactory.getInstance();
    }
    private Object readElement(final InputNode inputNode, final Instance instance) throws Exception {
        final Object read = this.read(inputNode, expect);
        if (null != instance) {
            instance.setInstance(read);
        }
        return read;
    }
    private Object readTemplate(final String str, final Class cls) throws Exception {
        final String property = context.getProperty(str);
        if (null != property) {
            return factory.getInstance(property, cls);
        }
        return null;
    }
    public boolean validate(final InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            this.validateElement(inputNode);
            return true;
        }
        inputNode.getValue();
        return true;
    }
    private boolean validateElement(final InputNode inputNode) throws Exception {
        final Instance primitiveFactory = factory.getInstance(inputNode);
        if (primitiveFactory.isReference()) {
            return true;
        }
        primitiveFactory.setInstance(null);
        return true;
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final String text = factory.getText(obj);
        if (null != text) {
            outputNode.setValue(text);
        }
    }
}
