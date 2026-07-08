package org.simpleframework.xml.stream;

import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Iterator;


class StreamReader implements EventReader {
    private EventNode peek;
    private final XMLEventReader reader;

    public StreamReader(final XMLEventReader xMLEventReader) {
        reader = xMLEventReader;
    }
    public EventNode peek() throws Exception {
        if (null == this.peek) {
            peek = this.next();
        }
        return peek;
    }
    public EventNode next() throws Exception {
        final EventNode eventNode = peek;
        if (null == eventNode) {
            return this.read();
        }
        peek = null;
        return eventNode;
    }
    private EventNode read() throws Exception {
        final XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isEndDocument()) {
            return null;
        }
        if (nextEvent.isStartElement()) {
            return this.start(nextEvent);
        }
        if (nextEvent.isCharacters()) {
            return this.text(nextEvent);
        }
        if (nextEvent.isEndElement()) {
            return this.end();
        }
        return this.read();
    }

    private Start start(final XMLEvent xMLEvent) {
        final Start start = new Start(xMLEvent);
        return start.isEmpty() ? this.build(start) : start;
    }

    private Start build(final Start start) {
        final Iterator<javax.xml.stream.events.Attribute> attributes = start.getAttributes();
        while (attributes.hasNext()) {
            final Entry attribute = this.attribute(attributes.next());
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(final javax.xml.stream.events.Attribute attribute) {
        return new Entry(attribute);
    }

    private Text text(final XMLEvent xMLEvent) {
        return new Text(xMLEvent);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final javax.xml.stream.events.Attribute entry;
        public boolean isReserved() {
            return false;
        }

        public Entry(final javax.xml.stream.events.Attribute attribute) {
            entry = attribute;
        }
        public String getName() {
            return entry.getName().getLocalPart();
        }
        public String getPrefix() {
            return entry.getName().getPrefix();
        }
        public String getReference() {
            return entry.getName().getNamespaceURI();
        }
        public String getValue() {
            return entry.getValue();
        }
        public Object getSource() {
            return entry;
        }
    }

    private static class Start extends EventElement {
        private final StartElement element;
        private final Location location;

        public Start(final XMLEvent xMLEvent) {
            element = xMLEvent.asStartElement();
            location = xMLEvent.getLocation();
        }
        public int getLine() {
            return location.getLineNumber();
        }
        public String getName() {
            return element.getName().getLocalPart();
        }
        public String getPrefix() {
            return element.getName().getPrefix();
        }
        public String getReference() {
            return element.getName().getNamespaceURI();
        }
        public Iterator<javax.xml.stream.events.Attribute> getAttributes() {
            return element.getAttributes();
        }
        public Object getSource() {
            return element;
        }
    }

    private static class Text extends EventToken {
        private final Characters text;
        public boolean isText() {
            return true;
        }
        public Text(final XMLEvent xMLEvent) {
            text = xMLEvent.asCharacters();
        }
        public String getValue() {
            return text.getData();
        }
        public Object getSource() {
            return text;
        }
    }

    private static class End extends EventToken {
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
