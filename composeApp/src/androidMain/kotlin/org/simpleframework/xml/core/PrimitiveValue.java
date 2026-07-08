package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveValue implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;
    public PrimitiveValue(final Context context, final Entry entry, final Type type) {
        factory = new PrimitiveFactory(context, type);
        root = new Primitive(context, type);
        style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Class type = this.type.type();
        String value = entry.getValue();
        if (!entry.isInline()) {
            if (null == value) {
                value = context.getName(type);
            }
            return this.readElement(inputNode, value);
        }
        return this.readAttribute(inputNode, value);
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        if (null != obj) {
            throw new PersistenceException("Can not read value of %s for %s", type, entry);
        }
        return this.read(inputNode);
    }
    private Object readElement(final InputNode inputNode, final String str) throws Exception {
        final InputNode next = inputNode.getNext(style.getAttribute(str));
        if (null == next) {
            return null;
        }
        return root.read(next);
    }
    private Object readAttribute(InputNode inputNode, final String str) throws Exception {
        if (null != str) {
            inputNode = inputNode.getAttribute(style.getAttribute(str));
        }
        if (null == inputNode) {
            return null;
        }
        return root.read(inputNode);
    }
    public boolean validate(final InputNode inputNode) throws Exception {
        final Class type = this.type.type();
        String value = entry.getValue();
        if (!entry.isInline()) {
            if (null == value) {
                value = context.getName(type);
            }
            return this.validateElement(inputNode, value);
        }
        return this.validateAttribute(inputNode, value);
    }
    private boolean validateElement(final InputNode inputNode, final String str) throws Exception {
        if (null == inputNode.getNext(this.style.getAttribute(str))) {
            return true;
        }
        return root.validate(inputNode);
    }
    private boolean validateAttribute(InputNode inputNode, final String str) throws Exception {
        if (null != str) {
            inputNode = inputNode.getNext(style.getAttribute(str));
        }
        if (null == inputNode) {
            return true;
        }
        return root.validate(inputNode);
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        String value = entry.getValue();
        if (!entry.isInline()) {
            if (null == value) {
                value = context.getName(type);
            }
            this.writeElement(outputNode, obj, value);
            return;
        }
        this.writeAttribute(outputNode, obj, value);
    }
    private void writeElement(final OutputNode outputNode, final Object obj, final String str) throws Exception {
        final OutputNode child = outputNode.getChild(style.getAttribute(str));
        if (null == obj || this.isOverridden(child, obj)) {
            return;
        }
        root.write(child, obj);
    }
    private void writeAttribute(OutputNode outputNode, final Object obj, final String str) throws Exception {
        if (null != obj) {
            if (null != str) {
                outputNode = outputNode.setAttribute(style.getAttribute(str), null);
            }
            root.write(outputNode, obj);
        }
    }
    private boolean isOverridden(final OutputNode outputNode, final Object obj) throws Exception {
        return factory.setOverride(type, obj, outputNode);
    }
}
