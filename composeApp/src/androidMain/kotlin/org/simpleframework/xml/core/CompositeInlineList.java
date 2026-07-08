package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Collection;


class CompositeInlineList implements Repeater {
    private final Type entry;
    private final CollectionFactory factory;
    private final String name;
    private final Traverser root;
    private final Type type;

    public CompositeInlineList(final Context context, final Type type, final Type type2, final String str) {
        factory = new CollectionFactory(context, type);
        root = new Traverser(context);
        entry = type2;
        this.type = type;
        name = str;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Collection collection = (Collection) factory.getInstance();
        if (null != collection) {
            return this.read(inputNode, collection);
        }
        return null;
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Collection collection = (Collection) obj;
        if (null != collection) {
            return this.read(inputNode, collection);
        }
        return this.read(inputNode);
    }

    private Object read(InputNode inputNode, final Collection collection) throws Exception {
        final InputNode parent = inputNode.getParent();
        final String name = inputNode.getName();
        while (null != inputNode) {
            final Object read = this.read(inputNode, entry.type());
            if (null != read) {
                collection.add(read);
            }
            inputNode = parent.getNext(name);
        }
        return collection;
    }

    private Object read(final InputNode inputNode, final Class cls) throws Exception {
        final Object read = root.read(inputNode, cls);
        final Class<?> cls2 = read.getClass();
        if (entry.type().isAssignableFrom(cls2)) {
            return read;
        }
        throw new PersistenceException("Entry %s does not match %s for %s", cls2, entry, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        final InputNode parent = inputNode.getParent();
        final Class type = entry.type();
        final String name = inputNode.getName();
        while (null != inputNode) {
            if (!root.validate(inputNode, type)) {
                return false;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Collection collection = (Collection) obj;
        final OutputNode parent = outputNode.getParent();
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        this.write(parent, collection);
    }

    public void write(final OutputNode outputNode, final Collection collection) throws Exception {
        for (final Object obj : collection) {
            if (null != obj) {
                final Class type = entry.type();
                final Class<?> cls = obj.getClass();
                if (!type.isAssignableFrom(cls)) {
                    throw new PersistenceException("Entry %s does not match %s for %s", cls, type, this.type);
                }
                root.write(outputNode, obj, type, name);
            }
        }
    }
}
