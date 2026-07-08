package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;


class OutputNodeMap extends LinkedHashMap<String, OutputNode> implements NodeMap<OutputNode> {
    private final OutputNode source;

    public OutputNodeMap(final OutputNode outputNode) {
        source = outputNode;
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public OutputNode getNode() {
        return source;
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public String getName() {
        return source.getName();
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public OutputNode put(final String str, final String str2) {
        final OutputAttribute outputAttribute = new OutputAttribute(source, str, str2);
        if (null != this.source) {
            put((OutputNodeMap) str, (String) outputAttribute);
        }
        return outputAttribute;
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public OutputNode remove(final String str) {
        return remove(str);
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public OutputNode get(final String str) {
        return get(str);
    }

    @Override // org.simpleframework.xml.stream.NodeMap, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.keySet().iterator();
    }
}
