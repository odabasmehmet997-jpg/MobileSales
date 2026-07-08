package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

import java.lang.reflect.Array;


class CompositeArray implements Converter {
    private final Type entry;
    private final ArrayFactory factory;
    private final String parent;
    private final Traverser root;
    private final Type type;

    public CompositeArray(final Context context, final Type type, final Type type2, final String str) {
        factory = new ArrayFactory(context, type);
        root = new Traverser(context);
        parent = str;
        entry = type2;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Instance arrayFactory = factory.getInstance(inputNode);
        final Object instance = arrayFactory.getInstance();
        return !arrayFactory.isReference() ? this.read(inputNode, instance) : instance;
    }

    @Override // org.simpleframework.xml.core.Converter
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
            this.read(next, obj, i2);
            i2++;
        }
    }

    private void read(final InputNode inputNode, final Object obj, final int i2) throws Exception {
        Array.set(obj, i2, !inputNode.isEmpty() ? root.read(inputNode, entry.type()) : null);
    }

    @Override // org.simpleframework.xml.core.Converter
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
            if (!next.isEmpty()) {
                root.validate(next, cls);
            }
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            root.write(outputNode, Array.get(obj, i2), entry.type(), parent);
        }
        outputNode.commit();
    }
}
