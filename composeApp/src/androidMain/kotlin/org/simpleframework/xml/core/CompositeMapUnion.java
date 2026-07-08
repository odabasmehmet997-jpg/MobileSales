package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

import java.util.Collections;
import java.util.Map;


class CompositeMapUnion implements Repeater {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Style style;
    private final Type type;

    public CompositeMapUnion(final Context context, final Group group, final Expression expression, final Type type) throws Exception {
        elements = group.getElements();
        style = context.getStyle();
        this.context = context;
        this.group = group;
        this.type = type;
        path = expression;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode);
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).read(inputNode, obj);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        return elements.get(path.getElement(inputNode.getName())).getConverter(context).validate(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Map map = (Map) obj;
        if (group.isInline()) {
            if (!map.isEmpty()) {
                this.write(outputNode, map);
                return;
            } else {
                if (outputNode.isCommitted()) {
                    return;
                }
                outputNode.remove();
                return;
            }
        }
        this.write(outputNode, map);
    }

    private void write(final OutputNode outputNode, final Map map) throws Exception {
        for (final Object obj : map.keySet()) {
            final Object obj2 = map.get(obj);
            if (null != obj2) {
                final Class<?> cls = obj2.getClass();
                final Label label = group.getLabel(cls);
                if (null == label) {
                    throw new UnionException("Value of %s not declared in %s with annotation %s", cls, type, group);
                }
                this.write(outputNode, obj, obj2, label);
            }
        }
    }

    private void write(final OutputNode outputNode, final Object obj, final Object obj2, final Label label) throws Exception {
        final Converter converter = label.getConverter(context);
        final Map singletonMap = Collections.singletonMap(obj, obj2);
        if (!label.isInline()) {
            final String element = style.getElement(label.getName());
            if (!outputNode.isCommitted()) {
                outputNode.setName(element);
            }
        }
        converter.write(outputNode, singletonMap);
    }
}
