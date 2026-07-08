package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;


public enum NodeBuilder {
    ;
    private static final Provider PROVIDER = ProviderFactory.getInstance();

    public static InputNode read(final InputStream inputStream) throws Exception {
        return NodeBuilder.read(NodeBuilder.PROVIDER.provide(inputStream));
    }

    public static InputNode read(final Reader reader) throws Exception {
        return NodeBuilder.read(NodeBuilder.PROVIDER.provide(reader));
    }

    private static InputNode read(final EventReader eventReader) throws Exception {
        return new NodeReader(eventReader).readRoot();
    }

    public static OutputNode write(final Writer writer) throws Exception {
        return NodeBuilder.write(writer, new Format());
    }

    public static OutputNode write(final Writer writer, final Format format) throws Exception {
        return new NodeWriter(writer, format).writeRoot();
    }
}
