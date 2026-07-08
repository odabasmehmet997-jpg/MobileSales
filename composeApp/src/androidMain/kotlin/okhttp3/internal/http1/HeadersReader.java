package okhttp3.internal.http1;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okio.BufferedSource;

import java.io.IOException;

public final class HeadersReader {
    public static final Companion Companion = new Companion(null);
    private static final int HEADER_LIMIT = 262144;
    private final BufferedSource source;
    private long headerLimit;

    public HeadersReader(final BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        headerLimit = 262144L;
    }

    public BufferedSource getSource() {
        return source;
    }

    public String readLine() throws IOException {
        final String utf8LineStrict = source.readUtf8LineStrict(headerLimit);
        headerLimit -= utf8LineStrict.length();
        return utf8LineStrict;
    }

    public Headers readHeaders() throws IOException {
        final Headers.Builder builder = new Headers.Builder();
        while (true) {
            final String line = this.readLine();
            if (0 != line.length()) {
                builder.addLenientokhttp(line);
            } else {
                return builder.build();
            }
        }
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
