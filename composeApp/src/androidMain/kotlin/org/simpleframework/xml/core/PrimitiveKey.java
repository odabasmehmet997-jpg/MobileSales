package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;
    public PrimitiveKey(final Context context, final Entry entry, final Type type) {
        factory = new PrimitiveFactory(context, type);
        root = new Primitive(context, type);
        style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Class type = this.type.type();
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        if (!entry.isAttribute()) {
            return this.readElement(inputNode, key);
        }
        return this.readAttribute(inputNode, key);
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        if (null != obj) {
            throw new PersistenceException("Can not read key of %s for %s", type, entry);
        }
        return this.read(inputNode);
    }
    private Object readAttribute(final InputNode inputNode, final String str) throws Exception {
        final InputNode attribute = inputNode.getAttribute(style.getAttribute(str));
        if (null == attribute) {
            return null;
        }
        return root.read(attribute);
    }
    private Object readElement(final InputNode inputNode, final String str) throws Exception {
        final InputNode next = inputNode.getNext(style.getElement(str));
        if (null == next) {
            return null;
        }
        return root.read(next);
    }
    public boolean validate(final InputNode inputNode) throws Exception {
        final Class type = this.type.type();
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        if (!entry.isAttribute()) {
            return this.validateElement(inputNode, key);
        }
        return this.validateAttribute(inputNode, key);
    }
    private boolean validateAttribute(final InputNode inputNode, final String str) throws Exception {
        final InputNode attribute = inputNode.getAttribute(style.getElement(str));
        if (null == attribute) {
            return true;
        }
        return root.validate(attribute);
    }
    private boolean validateElement(final InputNode inputNode, final String str) throws Exception {
        final InputNode next = inputNode.getNext(style.getElement(str));
        if (null == next) {
            return true;
        }
        return root.validate(next);
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        if (!entry.isAttribute()) {
            this.writeElement(outputNode, obj);
        } else if (null != obj) {
            this.writeAttribute(outputNode, obj);
        }
    }
    private void writeElement(final OutputNode outputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        final OutputNode child = outputNode.getChild(style.getElement(key));
        if (null == obj || this.isOverridden(child, obj)) {
            return;
        }
        root.write(child, obj);
    }
    private void writeAttribute(final OutputNode outputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        final String text = factory.getText(obj);
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        final String attribute = style.getAttribute(key);
        if (null != text) {
            outputNode.setAttribute(attribute, text);
        }
    }
    private boolean isOverridden(final OutputNode outputNode, final Object obj) throws Exception {
        return factory.setOverride(type, obj, outputNode);
    }
}
