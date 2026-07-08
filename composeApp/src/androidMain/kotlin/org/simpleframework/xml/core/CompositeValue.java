package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;


class CompositeValue implements Converter {
    private final Context context;
    private final Entry entry;
    private final Traverser root;
    private final Style style;
    private final Type type;

    public CompositeValue(final Context context, final Entry entry, final Type type) throws Exception {
        root = new Traverser(context);
        style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final InputNode next = inputNode.getNext();
        final Class type = this.type.type();
        if (null == next || next.isEmpty()) {
            return null;
        }
        return root.read(next, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Class type = this.type.type();
        if (null != obj) {
            throw new PersistenceException("Can not read value of %s for %s", type, entry);
        }
        return this.read(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        final Class type = this.type.type();
        String value = entry.getValue();
        if (null == value) {
            value = context.getName(type);
        }
        return this.validate(inputNode, value);
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
        String value = entry.getValue();
        if (null == value) {
            value = context.getName(type);
        }
        root.write(outputNode, obj, type, style.getElement(value));
    }
}
