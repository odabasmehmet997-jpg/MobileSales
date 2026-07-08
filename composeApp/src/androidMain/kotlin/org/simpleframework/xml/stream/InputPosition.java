package org.simpleframework.xml.stream;


class InputPosition implements Position {
    private final EventNode source;

    public InputPosition(final EventNode eventNode) {
        source = eventNode;
    }

    @Override // org.simpleframework.xml.stream.Position
    public int getLine() {
        return source.getLine();
    }

    @Override // org.simpleframework.xml.stream.Position
    public String toString() {
        return String.format("line %s", Integer.valueOf(this.getLine()));
    }
}
