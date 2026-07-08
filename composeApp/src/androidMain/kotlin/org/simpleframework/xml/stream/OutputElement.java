package org.simpleframework.xml.stream;


class OutputElement implements OutputNode {
    private String comment;
    private String name;
    private final OutputNode parent;
    private String reference;
    private final NamespaceMap scope;
    private String value;
    private final NodeWriter writer;
    private final OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    public OutputElement(final OutputNode outputNode, final NodeWriter nodeWriter, final String str) {
        scope = new PrefixResolver(outputNode);
        writer = nodeWriter;
        parent = outputNode;
        name = str;
    }

    
    public String getPrefix() {
        return this.getPrefix(true);
    }

    
    public String getPrefix(final boolean z) {
        final String prefix = scope.getPrefix(reference);
        return (z && null == prefix) ? parent.getPrefix() : prefix;
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

    @Override // org.simpleframework.xml.stream.Node
    public OutputNode getParent() {
        return parent;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return name;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() {
        return value;
    }

    
    public String getComment() {
        return comment;
    }

    
    public boolean isRoot() {
        return writer.isRoot(this);
    }

    
    public Mode getMode() {
        return mode;
    }

    
    public void setMode(final Mode mode) {
        this.mode = mode;
    }

    
    public OutputNodeMap getAttributes() {
        return table;
    }

    
    public void setComment(final String str) {
        comment = str;
    }

    
    public void setValue(final String str) {
        value = str;
    }

    
    public void setName(final String str) {
        name = str;
    }

    
    public void setData(final boolean z) {
        if (z) {
            mode = Mode.DATA;
        } else {
            mode = Mode.ESCAPE;
        }
    }

    
    public OutputNode setAttribute(final String str, final String str2) {
        return table.put(str, str2);
    }

    
    public OutputNode getChild(final String str) throws Exception {
        return writer.writeElement(this, str);
    }

    
    public void remove() throws Exception {
        writer.remove(this);
    }

    
    public void commit() throws Exception {
        writer.commit(this);
    }

    
    public boolean isCommitted() {
        return writer.isCommitted(this);
    }

    public String toString() {
        return String.format("element %s", name);
    }
}
