package org.simpleframework.xml.stream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.InputStream;
import java.io.Reader;


class StreamProvider implements Provider {
    private final XMLInputFactory factory = XMLInputFactory.newInstance();

    
    public EventReader provide(final InputStream inputStream) throws Exception {
        return provide(factory.createXMLEventReader(inputStream));
    }

    
    public EventReader provide(final Reader reader) throws Exception {
        return provide(factory.createXMLEventReader(reader));
    }

    private EventReader provide(final XMLEventReader xMLEventReader) throws Exception {
        return new StreamReader(xMLEventReader);
    }
}
