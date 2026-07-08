package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;
import org.simpleframework.xml.stream.Style;


class CompositeKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final Traverser root;
    private final Style style;
    private final Type type;

    public CompositeKey(final Context context, final Entry entry, final Type type) throws Exception {
        root = new Traverser(context);
        style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Position position = inputNode.getPosition();
        final Class type = this.type.type();
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        if (entry.isAttribute()) {
            throw new AttributeException("Can not have %s as an attribute for %s at %s", type, entry, position);
        }
        return this.read(inputNode, key);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Position position = inputNode.getPosition();
        final Class type = this.type.type();
        if (null != obj) {
            throw new PersistenceException("Can not read key of %s for %s at %s", type, entry, position);
        }
        return this.read(inputNode);
    }

    private Object read(InputNode inputNode, final String str) throws Exception {
        final String element = style.getElement(str);
        final Class type = this.type.type();
        if (null != element) {
            inputNode = inputNode.getNext(element);
        }
        if (null == inputNode || inputNode.isEmpty()) {
            return null;
        }
        return root.read(inputNode, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        final Position position = inputNode.getPosition();
        final Class type = this.type.type();
        String key = entry.getKey();
        if (null == key) {
            key = context.getName(type);
        }
        if (entry.isAttribute()) {
            throw new ElementException("Can not have %s as an attribute for %s at %s", type, entry, position);
        }
        return this.validate(inputNode, key);
    }

    private boolean validate(final InputNode inputNode, final String str) throws Exception {
        final InputNode next = inputNode.getNext(style.getElement(str));
        final Class type = this.type.type();
        if (null == next || next.isEmpty()) {
            return true;
        }
        return root.validate(next, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        String key = entry.getKey();
        if (entry.isAttribute()) {
            throw new ElementException("Can not have %s as an attribute for %s", type, entry);
        }
        if (null == key) {
            key = context.getName(type);
        }
        root.write(outputNode, obj, type, style.getElement(key));
    }
}
