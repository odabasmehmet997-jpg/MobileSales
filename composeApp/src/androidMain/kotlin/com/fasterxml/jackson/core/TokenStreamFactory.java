package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.DataOutputAsStream;

import java.io.*;
import java.net.URL;

public abstract class TokenStreamFactory implements Serializable {
    private static final long serialVersionUID = 2;
    public abstract boolean canHandleBinaryNatively();
    public abstract boolean canParseAsync();
    public abstract boolean canUseSchema(FormatSchema formatSchema);
    public abstract JsonGenerator createGenerator(DataOutput dataOutput) throws IOException;
    public abstract JsonGenerator createGenerator(DataOutput dataOutput, JsonEncoding jsonEncoding) throws IOException;
    public abstract JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException;
    public abstract JsonGenerator createGenerator(OutputStream outputStream) throws IOException;
    public abstract JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException;
    public abstract JsonGenerator createGenerator(Writer writer) throws IOException;
    public abstract JsonParser createNonBlockingByteArrayParser() throws IOException;
    public abstract JsonParser createParser(DataInput dataInput) throws IOException;
    public abstract JsonParser createParser(File file) throws IOException;
    public abstract JsonParser createParser(InputStream inputStream) throws IOException;
    public abstract JsonParser createParser(Reader reader) throws IOException;
    public abstract JsonParser createParser(String str) throws IOException;
    public abstract JsonParser createParser(URL url) throws IOException;
    public abstract JsonParser createParser(byte[] bArr) throws IOException;
    public abstract JsonParser createParser(byte[] bArr, int i2, int i3) throws IOException;
    public abstract JsonParser createParser(char[] cArr) throws IOException;
    public abstract JsonParser createParser(char[] cArr, int i2, int i3) throws IOException;
    public abstract int getFormatGeneratorFeatures();
    public abstract String getFormatName();
    public abstract int getFormatParserFeatures();
    public abstract Class<? extends FormatFeature> getFormatReadFeatureType();
    public abstract Class<? extends FormatFeature> getFormatWriteFeatureType();
    public abstract int getGeneratorFeatures();
    public abstract int getParserFeatures();
    public abstract boolean isEnabled(JsonGenerator.Feature feature);
    public abstract boolean isEnabled(JsonParser.Feature feature);
    public abstract boolean requiresPropertyOrdering();
    public abstract Version version();
    protected OutputStream _createDataOutputWrapper(final DataOutput dataOutput) {
        return new DataOutputAsStream(dataOutput);
    }
    protected InputStream _optimizedStreamFromURL(final URL url) throws IOException {
        final String host;
        if ("file".equals(url.getProtocol()) && ((null == (host = url.getHost()) || 0 == host.length()) && 0 > url.getPath().indexOf(37))) {
            return new FileInputStream(url.getPath());
        }
        return url.openStream();
    }
}
