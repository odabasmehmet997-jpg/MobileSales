package org.simpleframework.xml.stream;

import org.xmlpull.v1.XmlPullParser;


class PullReader implements EventReader {
    private final XmlPullParser parser;
    private EventNode peek;

    public PullReader(final XmlPullParser xmlPullParser) {
        parser = xmlPullParser;
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
        final int next = parser.next();
        if (1 == next) {
            return null;
        }
        if (2 == next) {
            return this.start();
        }
        if (4 == next) {
            return this.text();
        }
        if (3 == next) {
            return this.end();
        }
        return this.read();
    }

    private Text text() throws Exception {
        return new Text(parser);
    }

    private Start start() throws Exception {
        final Start start = new Start(parser);
        return start.isEmpty() ? this.build(start) : start;
    }

    private Start build(final Start start) throws Exception {
        final int attributeCount = parser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            final Entry attribute = this.attribute(i2);
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(final int i2) throws Exception {
        return new Entry(parser, i2);
    }

    private End end() throws Exception {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;
        private final String value;

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public boolean isReserved() {
            return false;
        }

        public Entry(final XmlPullParser xmlPullParser, final int i2) {
            reference = xmlPullParser.getAttributeNamespace(i2);
            prefix = xmlPullParser.getAttributePrefix(i2);
            value = xmlPullParser.getAttributeValue(i2);
            name = xmlPullParser.getAttributeName(i2);
            source = xmlPullParser;
        }
        public String getName() {
            return name;
        }
        public String getValue() {
            return value;
        }
        public String getReference() {
            return reference;
        }
        public String getPrefix() {
            return prefix;
        }
        public Object getSource() {
            return source;
        }
    }

    private static class Start extends EventElement {
        private final int line;
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;

        public Start(final XmlPullParser xmlPullParser) {
            reference = xmlPullParser.getNamespace();
            line = xmlPullParser.getLineNumber();
            prefix = xmlPullParser.getPrefix();
            name = xmlPullParser.getName();
            source = xmlPullParser;
        }

        @Override // org.simpleframework.xml.stream.EventElement, org.simpleframework.xml.stream.EventNode
        public int getLine() {
            return line;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getName() {
            return name;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getReference() {
            return reference;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getPrefix() {
            return prefix;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return source;
        }
    }

    private static class Text extends EventToken {
        private final XmlPullParser source;
        private final String text;

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isText() {
            return true;
        }

        public Text(final XmlPullParser xmlPullParser) {
            text = xmlPullParser.getText();
            source = xmlPullParser;
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public String getValue() {
            return text;
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return source;
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
