package org.simpleframework.xml.stream;


class NodeReader {
    private final EventReader reader;
    private final StringBuilder text = new StringBuilder();
    private final InputStack stack = new InputStack();

    public NodeReader(final EventReader eventReader) {
        reader = eventReader;
    }

    public boolean isRoot(final InputNode inputNode) {
        return stack.bottom() == inputNode;
    }

    public InputNode readRoot() throws Exception {
        if (!stack.isEmpty()) {
            return null;
        }
        final InputNode readElement = this.readElement(null);
        if (null != readElement) {
            return readElement;
        }
        throw new NodeException("Document has no root element");
    }

    public InputNode readElement(final InputNode inputNode) throws Exception {
        if (!stack.isRelevant(inputNode)) {
            return null;
        }
        EventNode next = reader.next();
        while (null != next) {
            if (next.isEnd()) {
                if (stack.pop() == inputNode) {
                    return null;
                }
            } else if (next.isStart()) {
                return this.readStart(inputNode, next);
            }
            next = reader.next();
        }
        return null;
    }

    public InputNode readElement(final InputNode inputNode, final String str) throws Exception {
        if (!stack.isRelevant(inputNode)) {
            return null;
        }
        EventNode peek = reader.peek();
        while (true) {
            if (null == peek) {
                break;
            }
            if (peek.isText()) {
                this.fillText(inputNode);
            } else if (peek.isEnd()) {
                if (stack.top() == inputNode) {
                    return null;
                }
                stack.pop();
            } else if (peek.isStart()) {
                if (this.isName(peek, str)) {
                    return this.readElement(inputNode);
                }
            }
            reader.next();
            peek = reader.peek();
        }
        return null;
    }

    private InputNode readStart(final InputNode inputNode, final EventNode eventNode) throws Exception {
        final InputElement inputElement = new InputElement(inputNode, this, eventNode);
        if (0 < this.text.length()) {
            text.setLength(0);
        }
        return eventNode.isStart() ? stack.push(inputElement) : inputElement;
    }

    private boolean isName(final EventNode eventNode, final String str) {
        final String name = eventNode.getName();
        if (null == name) {
            return false;
        }
        return name.equals(str);
    }

    public String readValue(final InputNode inputNode) throws Exception {
        if (!stack.isRelevant(inputNode)) {
            return null;
        }
        if (0 >= this.text.length() && reader.peek().isEnd()) {
            if (stack.top() == inputNode) {
                return null;
            }
            stack.pop();
            reader.next();
        }
        return this.readText(inputNode);
    }

    private String readText(final InputNode inputNode) throws Exception {
        EventNode peek = reader.peek();
        while (stack.top() == inputNode && peek.isText()) {
            this.fillText(inputNode);
            reader.next();
            peek = reader.peek();
        }
        return this.readBuffer(inputNode);
    }

    private String readBuffer(final InputNode inputNode) throws Exception {
        if (0 >= this.text.length()) {
            return null;
        }
        final String sb = text.toString();
        text.setLength(0);
        return sb;
    }

    private void fillText(final InputNode inputNode) throws Exception {
        final EventNode peek = reader.peek();
        if (peek.isText()) {
            text.append(peek.getValue());
        }
    }

    public boolean isEmpty(final InputNode inputNode) throws Exception {
        return stack.top() == inputNode && reader.peek().isEnd();
    }

    public void skipElement(final InputNode inputNode) throws Exception {
        while (null != readElement(inputNode)) {
        }
    }
}
