package org.simpleframework.xml.stream;


class InputStack extends Stack<InputNode> {
    public InputStack() {
        super(6);
    }

    public boolean isRelevant(final InputNode inputNode) {
        return this.contains(inputNode) || this.isEmpty();
    }
}
