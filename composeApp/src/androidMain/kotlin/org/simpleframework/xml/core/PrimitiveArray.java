package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

import java.lang.reflect.Array;

class PrimitiveArray implements Converter {
    private final Type entry;
    private final ArrayFactory factory;
    private final String parent;
    private final Primitive root;
    private final Type type;
    public PrimitiveArray(final Context context, final Type type, final Type type2, final String str) {
        factory = new ArrayFactory(context, type);
        root = new Primitive(context, type2);
        parent = str;
        entry = type2;
        this.type = type;
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Instance arrayFactory = factory.getInstance(inputNode);
        final Object instance = arrayFactory.getInstance();
        return !arrayFactory.isReference() ? this.read(inputNode, instance) : instance;
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final int length = Array.getLength(obj);
        int i2 = 0;
        while (true) {
            final Position position = inputNode.getPosition();
            final InputNode next = inputNode.getNext();
            if (null == next) {
                return obj;
            }
            if (i2 >= length) {
                throw new ElementException("Array length missing or incorrect for %s at %s", type, position);
            }
            Array.set(obj, i2, root.read(next));
            i2++;
        }
    }
    public boolean validate(final InputNode inputNode) throws Exception {
        final Instance arrayFactory = factory.getInstance(inputNode);
        if (arrayFactory.isReference()) {
            return true;
        }
        arrayFactory.setInstance(null);
        return this.validate(inputNode, arrayFactory.getType());
    }
    private boolean validate(final InputNode inputNode, final Class cls) throws Exception {
        while (true) {
            final InputNode next = inputNode.getNext();
            if (null == next) {
                return true;
            }
            root.validate(next);
        }
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            final OutputNode child = outputNode.getChild(parent);
            if (null == child) {
                return;
            }
            this.write(child, obj, i2);
        }
    }
    private void write(final OutputNode outputNode, final Object obj, final int i2) throws Exception {
        final Object obj2 = Array.get(obj, i2);
        if (null == obj2 || this.isOverridden(outputNode, obj2)) {
            return;
        }
        root.write(outputNode, obj2);
    }
    private boolean isOverridden(final OutputNode outputNode, final Object obj) throws Exception {
        return factory.setOverride(entry, obj, outputNode);
    }
}
