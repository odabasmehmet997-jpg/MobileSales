package org.simpleframework.xml.stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;


class DocumentReader implements EventReader {
    private static final String RESERVED = "xml";
    private EventNode peek;
    private final NodeExtractor queue;
    private final NodeStack stack;

    public DocumentReader(final Document document) {
        queue = new NodeExtractor(document);
        final NodeStack nodeStack = new NodeStack();
        stack = nodeStack;
        nodeStack.push(document);
    }

    @Override // org.simpleframework.xml.stream.EventReader
    public EventNode peek() throws Exception {
        if (null == this.peek) {
            peek = this.next();
        }
        return peek;
    }

    @Override // org.simpleframework.xml.stream.EventReader
    public EventNode next() throws Exception {
        final EventNode eventNode = peek;
        if (null == eventNode) {
            return this.read();
        }
        peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        final org.w3c.dom.Node peek = queue.peek();
        if (null == peek) {
            return this.end();
        }
        return this.read(peek);
    }

    private EventNode read(final org.w3c.dom.Node node) throws Exception {
        final org.w3c.dom.Node parentNode = node.getParentNode();
        final org.w3c.dom.Node pVar = stack.top();
        if (parentNode != pVar) {
            if (null != pVar) {
                stack.pop();
            }
            return this.end();
        }
        queue.poll();
        return this.convert(node);
    }

    private EventNode convert(final org.w3c.dom.Node node) throws Exception {
        if (1 == node.getNodeType()) {
            stack.push(node);
            return this.start(node);
        }
        return this.text(node);
    }

    private Start start(final org.w3c.dom.Node node) {
        final Start start = new Start(node);
        return start.isEmpty() ? this.build(start) : start;
    }

    private Start build(final Start start) {
        final NamedNodeMap attributes = start.getAttributes();
        final int length = attributes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            final Entry attribute = this.attribute(attributes.item(i2));
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(final org.w3c.dom.Node node) {
        return new Entry(node);
    }

    private Text text(final org.w3c.dom.Node node) {
        return new Text(node);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final org.w3c.dom.Node node;

        public Entry(final org.w3c.dom.Node node) {
            this.node = node;
        }
        public String getName() {
            return node.getLocalName();
        }
        public String getValue() {
            return node.getNodeValue();
        }
        public String getPrefix() {
            return node.getPrefix();
        }
        public String getReference() {
            return node.getNamespaceURI();
        }
        public boolean isReserved() {
            final String prefix = this.getPrefix();
            final String name = this.getName();
            if (null != prefix) {
                return prefix.startsWith(RESERVED);
            }
            return name.startsWith(RESERVED);
        }
        public Object getSource() {
            return node;
        }
    }

    private static class Start extends EventElement {
        private final Element element;

        public Start(final org.w3c.dom.Node node) {
            element = (Element) node;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getName() {
            return element.getLocalName();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getPrefix() {
            return element.getPrefix();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getReference() {
            return element.getNamespaceURI();
        }

        public NamedNodeMap getAttributes() {
            return element.getAttributes();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return element;
        }
    }

    private static class Text extends EventToken {
        private final org.w3c.dom.Node node;

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isText() {
            return true;
        }

        public Text(final org.w3c.dom.Node node) {
            this.node = node;
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public String getValue() {
            return node.getNodeValue();
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return node;
        }
    }

    private static class End extends EventToken {
        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
