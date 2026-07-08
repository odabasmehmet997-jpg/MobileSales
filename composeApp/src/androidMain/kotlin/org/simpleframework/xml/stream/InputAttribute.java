package org.simpleframework.xml.stream;


class InputAttribute implements InputNode {
    private final String name;
    private final InputNode parent;
    private String prefix;
    private String reference;
    private Object source;
    private final String value;

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getAttribute(final String str) {
        return null;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getNext() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public InputNode getNext(final String str) {
        return null;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isElement() {
        return false;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isEmpty() {
        return false;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public boolean isRoot() {
        return false;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public void skip() {
    }

    public InputAttribute(final InputNode inputNode, final String str, final String str2) {
        parent = inputNode;
        value = str2;
        name = str;
    }

    public InputAttribute(final InputNode inputNode, final Attribute attribute) {
        reference = attribute.getReference();
        prefix = attribute.getPrefix();
        source = attribute.getSource();
        value = attribute.getValue();
        name = attribute.getName();
        parent = inputNode;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public Object getSource() {
        return source;
    }

    @Override // org.simpleframework.xml.stream.Node
    public InputNode getParent() {
        return parent;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public Position getPosition() {
        return parent.getPosition();
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public String getPrefix() {
        return prefix;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public String getReference() {
        return reference;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() {
        return value;
    }

    @Override // org.simpleframework.xml.stream.InputNode
    public NodeMap<InputNode> getAttributes() {
        return new InputNodeMap(this);
    }

    public String toString() {
        return String.format("attribute %s='%s'", name, value);
    }
}
