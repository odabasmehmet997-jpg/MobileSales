package okio;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.DataFormatException;

public interface Source extends Closeable {
    void close() throws IOException;
    long read(Buffer buffer, long j2) throws IOException, DataFormatException;
    Timeout timeout();
}
