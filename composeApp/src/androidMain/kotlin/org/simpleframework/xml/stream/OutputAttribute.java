package org.simpleframework.xml.stream;


class OutputAttribute implements OutputNode {
    private String name;
    private String reference;
    private final NamespaceMap scope;
    private final OutputNode source;
    private String value;

    
    public void commit() {
    }

    
    public OutputNode getChild(final String str) {
        return null;
    }

    
    public String getComment() {
        return null;
    }

    
    public boolean isCommitted() {
        return true;
    }

    
    public boolean isRoot() {
        return false;
    }

    
    public void remove() {
    }

    
    public OutputNode setAttribute(final String str, final String str2) {
        return null;
    }

    
    public void setComment(final String str) {
    }

    
    public void setData(final boolean z) {
    }

    
    public void setMode(final Mode mode) {
    }

    public OutputAttribute(final OutputNode outputNode, final String str, final String str2) {
        scope = outputNode.getNamespaces();
        source = outputNode;
        value = str2;
        name = str;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() {
        return value;
    }

    
    public void setValue(final String str) {
        value = str;
    }

    
    public void setName(final String str) {
        name = str;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.stream.Node
    public OutputNode getParent() {
        return source;
    }

    
    public NodeMap<OutputNode> getAttributes() {
        return new OutputNodeMap(this);
    }

    
    public Mode getMode() {
        return Mode.INHERIT;
    }

    
    public String getPrefix() {
        return scope.getPrefix(reference);
    }

    
    public String getPrefix(final boolean z) {
        return scope.getPrefix(reference);
    }

    
    public String getReference() {
        return reference;
    }

    
    public void setReference(final String str) {
        reference = str;
    }

    
    public NamespaceMap getNamespaces() {
        return scope;
    }

    public String toString() {
        return String.format("attribute %s='%s'", name, value);
    }
}
