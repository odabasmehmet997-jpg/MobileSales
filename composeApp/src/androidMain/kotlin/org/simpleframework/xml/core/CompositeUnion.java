package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;


class CompositeUnion implements Converter {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Type type;

    public CompositeUnion(final Context context, final Group group, final Expression expression, final Type type) throws Exception {
        elements = group.getElements();
        this.context = context;
        this.group = group;
        this.type = type;
        path = expression;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode, obj);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).validate(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Class<?> cls = obj.getClass();
        final Label label = group.getLabel(cls);
        if (null == label) {
            throw new UnionException("Value of %s not declared in %s with annotation %s", cls, type, group);
        }
        this.write(outputNode, obj, label);
    }

    private void write(final OutputNode outputNode, final Object obj, final Label label) throws Exception {
        label.getConverter(context).write(outputNode, obj);
    }
}
