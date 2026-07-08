package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


class CompositeListUnion implements Repeater {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Style style;
    private final Type type;

    public CompositeListUnion(final Context context, final Group group, final Expression expression, final Type type) throws Exception {
        elements = group.getElements();
        style = context.getStyle();
        this.context = context;
        this.group = group;
        this.type = type;
        path = expression;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        if (null == this.group.getText()) {
            return this.readElement(inputNode);
        }
        return this.readText(inputNode);
    }

    private Object readElement(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode);
    }

    private Object readText(final InputNode inputNode) throws Exception {
        return group.getText().getConverter(context).read(inputNode);
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        return null != this.group.getText() ? this.readText(inputNode, obj) : this.readElement(inputNode, obj);
    }

    private Object readElement(final InputNode inputNode, final Object obj) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode, obj);
    }

    private Object readText(final InputNode inputNode, final Object obj) throws Exception {
        return group.getText().getConverter(context).read(inputNode.getParent(), obj);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).validate(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Collection collection = (Collection) obj;
        if (group.isInline()) {
            if (!collection.isEmpty()) {
                this.write(outputNode, collection);
                return;
            } else {
                if (outputNode.isCommitted()) {
                    return;
                }
                outputNode.remove();
                return;
            }
        }
        this.write(outputNode, collection);
    }

    private void write(final OutputNode outputNode, final Collection collection) throws Exception {
        for (final Object obj : collection) {
            if (null != obj) {
                final Class<?> cls = obj.getClass();
                final Label label = group.getLabel(cls);
                if (null == label) {
                    throw new UnionException("Entry of %s not declared in %s with annotation %s", cls, type, group);
                }
                this.write(outputNode, obj, label);
            }
        }
    }

    private void write(final OutputNode outputNode, final Object obj, final Label label) throws Exception {
        final Converter converter = label.getConverter(context);
        final Set singleton = Collections.singleton(obj);
        if (!label.isInline()) {
            final String element = style.getElement(label.getName());
            if (!outputNode.isCommitted()) {
                outputNode.setName(element);
            }
        }
        converter.write(outputNode, singleton);
    }
}
