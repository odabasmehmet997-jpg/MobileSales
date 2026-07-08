package org.simpleframework.xml.stream;


class InputElement implements InputNode {
    private final InputNodeMap map;
    private final EventNode node;
    private final InputNode parent;
    private final NodeReader reader;
    public boolean isElement() {
        return true;
    }
    public InputElement(final InputNode inputNode, final NodeReader nodeReader, final EventNode eventNode) {
        map = new InputNodeMap(this, eventNode);
        reader = nodeReader;
        parent = inputNode;
        node = eventNode;
    }
    public Object getSource() {
        return node.getSource();
    }
    public InputNode getParent() {
        return parent;
    }
    public Position getPosition() {
        return new InputPosition(node);
    }

    
    public String getName() {
        return node.getName();
    }

    
    public String getPrefix() {
        return node.getPrefix();
    }

    
    public String getReference() {
        return node.getReference();
    }

    
    public boolean isRoot() {
        return reader.isRoot(this);
    }

    
    public InputNode getAttribute(final String str) {
        return map.get(str);
    }

    
    public NodeMap<InputNode> getAttributes() {
        return map;
    }

    
    public String getValue() throws Exception {
        return reader.readValue(this);
    }

    
    public InputNode getNext() throws Exception {
        return reader.readElement(this);
    }

    
    public InputNode getNext(final String str) throws Exception {
        return reader.readElement(this, str);
    }

    
    public void skip() throws Exception {
        reader.skipElement(this);
    }

    
    public boolean isEmpty() throws Exception {
        if (map.isEmpty()) {
            return reader.isEmpty(this);
        }
        return false;
    }

    public String toString() {
        return String.format("element %s", this.getName());
    }
}
