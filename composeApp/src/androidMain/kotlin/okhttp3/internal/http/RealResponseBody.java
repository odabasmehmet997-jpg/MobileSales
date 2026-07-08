package okhttp3.internal.http;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    private final long contentLength;
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(final String str, final long j2, final BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        contentTypeString = str;
        contentLength = j2;
        this.source = source;
    }
    public long contentLength() {
        return contentLength;
    }
    public MediaType contentType() {
        final String str = contentTypeString;
        if (null == str) {
            return null;
        }
        return MediaType.Companion.parse(str);
    }
    public BufferedSource source() {
        return source;
    }
}
