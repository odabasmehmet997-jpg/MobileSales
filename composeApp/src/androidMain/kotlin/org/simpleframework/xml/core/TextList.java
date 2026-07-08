package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Collection;
import java.util.Iterator;

class TextList implements Repeater {
    private final CollectionFactory factory;
    private final Primitive primitive;
    private final Type type;
    public boolean validate(final InputNode inputNode) throws Exception {
        return true;
    }
    public TextList(final Context context, final Type type, final Label label) {
        final ClassType classType = new ClassType(String.class);
        this.type = classType;
        factory = new CollectionFactory(context, type);
        primitive = new Primitive(context, classType);
    }
    public Object read(final InputNode inputNode) throws Exception {
        final Instance collectionFactory = factory.getInstance(inputNode);
        final Object instance = collectionFactory.getInstance();
        if (collectionFactory.isReference()) {
            return collectionFactory.getInstance();
        }
        return this.read(inputNode, instance);
    }
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Collection collection = (Collection) obj;
        final Object read = primitive.read(inputNode);
        if (null != read) {
            collection.add(read);
        }
        return obj;
    }
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final OutputNode parent = outputNode.getParent();
        final Iterator it = ((Collection) obj).iterator();
        while (it.hasNext()) {
            primitive.write(parent, it.next());
        }
    }
}
