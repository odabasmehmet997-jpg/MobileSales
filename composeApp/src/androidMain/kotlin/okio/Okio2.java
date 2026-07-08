package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.EOFException;

final class Okio2 implements Sink {
    public void close() {
    }
    public void flush() {
    }
    public void write(final Buffer source, final long j2) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        source.skip(j2);
    }
    public Timeout timeout() {
        return Timeout.NONE;
    }
}
