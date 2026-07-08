package okio;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface BufferedSink extends Sink, WritableByteChannel {
    BufferedSink emit() throws IOException;
    BufferedSink emitCompleteSegments() throws IOException;
    void flush() throws IOException;
    Buffer getBuffer();
    BufferedSink write(ByteString byteString) throws IOException;
    BufferedSink write(byte[] bArr) throws IOException;
    BufferedSink write(byte[] bArr, int i2, int i3) throws IOException;
    long writeAll(Source source) throws IOException;
    BufferedSink writeByte(int i2) throws IOException;
    BufferedSink writeDecimalLong(long j2) throws IOException;
    BufferedSink writeHexadecimalUnsignedLong(long j2) throws IOException;
    BufferedSink writeInt(int i2) throws IOException;
    BufferedSink writeShort(int i2) throws IOException;
    BufferedSink writeUtf8(String str) throws IOException;
}
