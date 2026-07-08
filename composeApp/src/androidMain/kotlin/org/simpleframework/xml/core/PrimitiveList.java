package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Collection;

class PrimitiveList implements Converter {
    private final Type entry;
    private final CollectionFactory factory;
    private final String parent;
    private final Primitive root;
    public PrimitiveList(final Context context, final Type type, final Type type2, final String str) {
        factory = new CollectionFactory(context, type);
        root = new Primitive(context, type2);
        parent = str;
        entry = type2;
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Instance collectionFactory = factory.getInstance(inputNode);
        final Object instance = collectionFactory.getInstance();
        return !collectionFactory.isReference() ? this.populate(inputNode, instance) : instance;
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Instance collectionFactory = factory.getInstance(inputNode);
        if (collectionFactory.isReference()) {
            return collectionFactory.getInstance();
        }
        collectionFactory.setInstance(obj);
        return null != obj ? this.populate(inputNode, obj) : obj;
    }
    private Object populate(final InputNode inputNode, final Object obj) throws Exception {
        final Collection collection = (Collection) obj;
        while (true) {
            final InputNode next = inputNode.getNext();
            if (null == next) {
                return collection;
            }
            collection.add(root.read(next));
        }
    }
    public boolean validate(final InputNode inputNode) throws Exception {
        final Instance collectionFactory = factory.getInstance(inputNode);
        if (collectionFactory.isReference()) {
            return true;
        }
        collectionFactory.setInstance(null);
        return this.validate(inputNode, collectionFactory.getType());
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
        for (final Object obj2 : (Collection) obj) {
            if (null != obj2) {
                final OutputNode child = outputNode.getChild(parent);
                if (!this.isOverridden(child, obj2)) {
                    root.write(child, obj2);
                }
            }
        }
    }
    private boolean isOverridden(final OutputNode outputNode, final Object obj) throws Exception {
        return factory.setOverride(entry, obj, outputNode);
    }
}
