package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

import java.util.Map;


class CompositeInlineMap implements Repeater {
    private final Entry entry;
    private final MapFactory factory;
    private final Converter key;
    private final Style style;
    private final Converter value;

    public CompositeInlineMap(final Context context, final Entry entry, final Type type) throws Exception {
        factory = new MapFactory(context, type);
        value = entry.getValue(context);
        key = entry.getKey(context);
        style = context.getStyle();
        this.entry = entry;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Map map = (Map) factory.getInstance();
        if (null != map) {
            return this.read(inputNode, map);
        }
        return null;
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Map map = (Map) obj;
        if (null != map) {
            return this.read(inputNode, map);
        }
        return this.read(inputNode);
    }

    private Object read(InputNode inputNode, final Map map) throws Exception {
        final InputNode parent = inputNode.getParent();
        final String name = inputNode.getName();
        while (null != inputNode) {
            final Object read = key.read(inputNode);
            final Object read2 = value.read(inputNode);
            if (null != map) {
                map.put(read, read2);
            }
            inputNode = parent.getNext(name);
        }
        return map;
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        final InputNode parent = inputNode.getParent();
        final String name = inputNode.getName();
        while (null != inputNode) {
            if (!key.validate(inputNode) || !value.validate(inputNode)) {
                return false;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final OutputNode parent = outputNode.getParent();
        final Mode mode = outputNode.getMode();
        final Map map = (Map) obj;
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        this.write(parent, map, mode);
    }

    private void write(final OutputNode outputNode, final Map map, final Mode mode) throws Exception {
        final String element = style.getElement(entry.getEntry());
        for (final Object obj : map.keySet()) {
            final OutputNode child = outputNode.getChild(element);
            final Object obj2 = map.get(obj);
            child.setMode(mode);
            key.write(child, obj);
            value.write(child, obj2);
        }
    }
}
