package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Collection;

class PrimitiveInlineList implements Repeater {
    private final Type entry;
    private final CollectionFactory factory;
    private final String parent;
    private final Primitive root;
    public PrimitiveInlineList(final Context context, final Type type, final Type type2, final String str) {
        factory = new CollectionFactory(context, type);
        root = new Primitive(context, type2);
        parent = str;
        entry = type2;
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Collection collection = (Collection) factory.getInstance();
        if (null != collection) {
            return this.read(inputNode, collection);
        }
        return null;
    }
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
            final Object read = root.read(inputNode);
            if (null != read) {
                collection.add(read);
            }
            inputNode = parent.getNext(name);
        }
        return collection;
    }
    public boolean validate(InputNode inputNode) throws Exception {
        final InputNode parent = inputNode.getParent();
        final String name = inputNode.getName();
        while (null != inputNode) {
            if (!root.validate(inputNode)) {
                return false;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final OutputNode parent = outputNode.getParent();
        final Mode mode = outputNode.getMode();
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        this.write(parent, obj, mode);
    }
    private void write(final OutputNode outputNode, final Object obj, final Mode mode) throws Exception {
        for (final Object obj2 : (Collection) obj) {
            if (null != obj2) {
                final OutputNode child = outputNode.getChild(parent);
                if (!this.isOverridden(child, obj2)) {
                    child.setMode(mode);
                    root.write(child, obj2);
                }
            }
        }
    }
    private boolean isOverridden(final OutputNode outputNode, final Object obj) throws Exception {
        return factory.setOverride(entry, obj, outputNode);
    }
}
