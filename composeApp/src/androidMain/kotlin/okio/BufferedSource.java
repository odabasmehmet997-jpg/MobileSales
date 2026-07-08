package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public interface BufferedSource extends Source, ReadableByteChannel {
    boolean exhausted() throws IOException;
    Buffer getBuffer();
    InputStream inputStream();
    BufferedSource peek();
    boolean rangeEquals(long j2, ByteString byteString) throws IOException;
    long readAll(Sink sink) throws IOException;
    byte readByte() throws IOException;
    byte[] readByteArray() throws IOException;
    byte[] readByteArray(long j2) throws IOException;
    ByteString readByteString() throws IOException;
    ByteString readByteString(long j2) throws IOException;
    long readDecimalLong() throws IOException;
    void readFully(Buffer buffer, long j2) throws IOException;
    void readFully(byte[] bArr) throws IOException;
    long readHexadecimalUnsignedLong() throws IOException;
    int readInt() throws IOException;
    int readIntLe() throws IOException;
    long readLong() throws IOException;
    long readLongLe() throws IOException;
    short readShort() throws IOException;
    short readShortLe() throws IOException;
    String readString(Charset charset) throws IOException;
    String readUtf8(long j2) throws IOException;
    String readUtf8LineStrict() throws IOException;
    String readUtf8LineStrict(long j2) throws IOException;
    boolean request(long j2) throws IOException;
    void require(long j2) throws IOException;
    int select(Options options) throws IOException;
    void skip(long j2) throws IOException;
}
