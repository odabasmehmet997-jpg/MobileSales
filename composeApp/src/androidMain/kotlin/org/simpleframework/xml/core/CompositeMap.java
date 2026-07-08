package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

import java.util.Map;


class CompositeMap implements Converter {
    private final Entry entry;
    private final MapFactory factory;
    private final Converter key;
    private final Style style;
    private final Converter value;

    public CompositeMap(final Context context, final Entry entry, final Type type) throws Exception {
        factory = new MapFactory(context, type);
        value = entry.getValue(context);
        key = entry.getKey(context);
        style = context.getStyle();
        this.entry = entry;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Instance mapFactory = factory.getInstance(inputNode);
        final Object instance = mapFactory.getInstance();
        return !mapFactory.isReference() ? this.populate(inputNode, instance) : instance;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Instance mapFactory = factory.getInstance(inputNode);
        if (mapFactory.isReference()) {
            return mapFactory.getInstance();
        }
        mapFactory.setInstance(obj);
        return null != obj ? this.populate(inputNode, obj) : obj;
    }

    private Object populate(final InputNode inputNode, final Object obj) throws Exception {
        final Map map = (Map) obj;
        while (true) {
            final InputNode next = inputNode.getNext();
            if (null == next) {
                return map;
            }
            map.put(key.read(next), value.read(next));
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        final Instance mapFactory = factory.getInstance(inputNode);
        if (mapFactory.isReference()) {
            return true;
        }
        mapFactory.setInstance(null);
        return this.validate(inputNode, mapFactory.getType());
    }

    private boolean validate(final InputNode inputNode, final Class cls) throws Exception {
        InputNode next;
        do {
            next = inputNode.getNext();
            if (null == next) {
                return true;
            }
            if (!key.validate(next)) {
                return false;
            }
        } while (value.validate(next));
        return false;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Map map = (Map) obj;
        for (final Object obj2 : map.keySet()) {
            final OutputNode child = outputNode.getChild(style.getElement(entry.getEntry()));
            final Object obj3 = map.get(obj2);
            key.write(child, obj2);
            value.write(child, obj3);
        }
    }
}
