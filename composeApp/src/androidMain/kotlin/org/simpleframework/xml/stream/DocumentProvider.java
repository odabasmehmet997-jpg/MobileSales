package org.simpleframework.xml.stream;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.Reader;


class DocumentProvider implements Provider {
    private final DocumentBuilderFactory factory;

    public DocumentProvider() {
        final DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        factory = newInstance;
        newInstance.setNamespaceAware(true);
    }

    
    public EventReader provide(final InputStream inputStream) throws Exception {
        return this.provide(new InputSource(inputStream));
    }

    
    public EventReader provide(final Reader reader) throws Exception {
        return this.provide(new InputSource(reader));
    }

    private EventReader provide(final InputSource inputSource) throws Exception {
        return new DocumentReader(factory.newDocumentBuilder().parse(inputSource));
    }
}
