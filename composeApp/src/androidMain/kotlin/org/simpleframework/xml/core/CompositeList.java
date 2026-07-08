package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Collection;

class CompositeList implements Converter {
    private final Type entry;
    private final CollectionFactory factory;
    private final String name;
    private final Traverser root;
    private final Type type;
    public CompositeList(final Context context, final Type type, final Type type2, final String str) {
        factory = new CollectionFactory(context, type);
        root = new Traverser(context);
        entry = type2;
        this.type = type;
        name = str;
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
            final Class type = entry.type();
            if (null == next) {
                return collection;
            }
            collection.add(root.read(next, type));
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
            final Class type = entry.type();
            if (null == next) {
                return true;
            }
            root.validate(next, type);
        }
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        for (final Object obj2 : (Collection) obj) {
            if (null != obj2) {
                final Class type = entry.type();
                final Class<?> cls = obj2.getClass();
                if (!type.isAssignableFrom(cls)) {
                    throw new PersistenceException("Entry %s does not match %s for %s", cls, entry, this.type);
                }
                root.write(outputNode, obj2, type, name);
            }
        }
    }
}
