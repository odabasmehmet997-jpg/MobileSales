package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;


class InputNodeMap extends LinkedHashMap<String, InputNode> implements NodeMap<InputNode> {
    private final InputNode source;

    protected InputNodeMap(final InputNode inputNode) {
        source = inputNode;
    }

    public InputNodeMap(final InputNode inputNode, final EventNode eventNode) {
        source = inputNode;
        this.build(eventNode);
    }

    private void build(final EventNode eventNode) {
        for (final Attribute attribute : eventNode) {
            final InputAttribute inputAttribute = new InputAttribute(source, attribute);
            if (!attribute.isReserved()) {
                this.put(inputAttribute.getName(), inputAttribute);
            }
        }
    }
    public InputNode getNode() {
        return source;
    }
    public String getName() {
        return source.getName();
    }
    public InputNode put(final String str, final String str2) {
        final InputAttribute inputAttribute = new InputAttribute(source, str, str2);
        if (null != str) {
            this.put(str, inputAttribute);
        }
        return inputAttribute;
    }
    public InputNode remove(final String str) {
        return remove(str);
    }
    public InputNode get(final String str) {
        return get(str);
    }
    public Iterator<String> iterator() {
        return this.keySet().iterator();
    }
}
