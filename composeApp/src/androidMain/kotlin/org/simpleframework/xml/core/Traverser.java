package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;


class Traverser {
    private final Context context;
    private final Style style;

    public Traverser(final Context context) {
        style = context.getStyle();
        this.context = context;
    }

    private Decorator getDecorator(final Class cls) throws Exception {
        return context.getDecorator(cls);
    }

    public Object read(final InputNode inputNode, final Class cls) throws Exception {
        final Object read = this.getComposite(cls).read(inputNode);
        if (null != read) {
            return this.read(inputNode, read.getClass(), read);
        }
        return null;
    }

    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Class<?> cls = obj.getClass();
        return this.read(inputNode, cls, this.getComposite(cls).read(inputNode, obj));
    }

    private Object read(final InputNode inputNode, final Class cls, final Object obj) throws Exception {
        if (null != getName(cls)) {
            return obj;
        }
        throw new RootException("Root annotation required for %s", cls);
    }

    public boolean validate(final InputNode inputNode, final Class cls) throws Exception {
        final Composite composite = this.getComposite(cls);
        if (null == getName(cls)) {
            throw new RootException("Root annotation required for %s", cls);
        }
        return composite.validate(inputNode);
    }

    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        this.write(outputNode, obj, obj.getClass());
    }

    public void write(final OutputNode outputNode, final Object obj, final Class cls) throws Exception {
        final Class<?> cls2 = obj.getClass();
        final String name = this.getName(cls2);
        if (null == name) {
            throw new RootException("Root annotation required for %s", cls2);
        }
        this.write(outputNode, obj, cls, name);
    }

    public void write(final OutputNode outputNode, final Object obj, final Class cls, final String str) throws Exception {
        final OutputNode child = outputNode.getChild(str);
        final Type type = this.getType(cls);
        if (null != obj) {
            final Class<?> cls2 = obj.getClass();
            final Decorator decorator = this.getDecorator(cls2);
            if (null != decorator) {
                decorator.decorate(child);
            }
            if (!context.setOverride(type, obj, child)) {
                this.getComposite(cls2).write(child, obj);
            }
        }
        child.commit();
    }

    private Composite getComposite(final Class cls) throws Exception {
        final Type type = this.getType(cls);
        if (null == cls) {
            throw new RootException("Can not instantiate null class");
        }
        return new Composite(context, type);
    }

    private Type getType(final Class cls) {
        return new ClassType(cls);
    }

    protected String getName(final Class cls) throws Exception {
        return style.getElement(context.getName(cls));
    }
}
