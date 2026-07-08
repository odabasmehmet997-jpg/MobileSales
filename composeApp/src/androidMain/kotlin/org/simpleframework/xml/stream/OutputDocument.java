package org.simpleframework.xml.stream;


class OutputDocument implements OutputNode {
    private String comment;
    private String name;
    private String reference;
    private final OutputStack stack;
    private String value;
    private final NodeWriter writer;
    private final OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    @Override // org.simpleframework.xml.stream.Node
    public String getName() {
        return null;
    }

    
    public NamespaceMap getNamespaces() {
        return null;
    }

    @Override // org.simpleframework.xml.stream.Node
    public OutputNode getParent() {
        return null;
    }

    
    public String getPrefix() {
        return null;
    }

    
    public String getPrefix(final boolean z) {
        return null;
    }

    
    public boolean isRoot() {
        return true;
    }

    public OutputDocument(final NodeWriter nodeWriter, final OutputStack outputStack) {
        writer = nodeWriter;
        stack = outputStack;
    }

    
    public String getReference() {
        return reference;
    }

    
    public void setReference(final String str) {
        reference = str;
    }

    @Override // org.simpleframework.xml.stream.Node
    public String getValue() throws Exception {
        return value;
    }

    
    public String getComment() {
        return comment;
    }

    
    public Mode getMode() {
        return mode;
    }

    
    public void setMode(final Mode mode) {
        this.mode = mode;
    }

    
    public OutputNode setAttribute(final String str, final String str2) {
        return table.put(str, str2);
    }

    
    public NodeMap<OutputNode> getAttributes() {
        return table;
    }

    
    public void setName(final String str) {
        name = str;
    }

    
    public void setValue(final String str) {
        value = str;
    }

    
    public void setComment(final String str) {
        comment = str;
    }

    
    public void setData(final boolean z) {
        if (z) {
            mode = Mode.DATA;
        } else {
            mode = Mode.ESCAPE;
        }
    }

    
    public OutputNode getChild(final String str) throws Exception {
        return writer.writeElement(this, str);
    }

    
    public void remove() throws Exception {
        if (stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        stack.bottom().remove();
    }

    
    public void commit() throws Exception {
        if (stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        stack.bottom().commit();
    }

    
    public boolean isCommitted() {
        return stack.isEmpty();
    }
}
