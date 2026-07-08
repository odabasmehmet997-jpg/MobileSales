package okhttp3;

import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public abstract class RequestBody {
    public static final Companion Companion = new Companion(null);
    public static RequestBody create(File file, MediaType mediaType) {
        return Companion.create(file, mediaType);
    }
    public static RequestBody create(String str, MediaType mediaType) {
        return Companion.create(str, mediaType);
    }
    public static RequestBody create(MediaType mediaType, File file) {
        return Companion.create(mediaType, file);
    }
    public static RequestBody create(MediaType mediaType, String str) {
        return Companion.create(mediaType, str);
    }
    public static RequestBody create(MediaType mediaType, ByteString byteString) {
        return Companion.create(mediaType, byteString);
    }
    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return Companion.create(mediaType, bArr);
    }
    public static RequestBody create(MediaType mediaType, byte[] bArr, int i2) {
        return Companion.create(mediaType, bArr, i2);
    }
    public static RequestBody create(MediaType mediaType, byte[] bArr, int i2, int i3) {
        return Companion.create(mediaType, bArr, i2, i3);
    }
    public static RequestBody create(ByteString byteString, MediaType mediaType) {
        return Companion.create(byteString, mediaType);
    }
    public static RequestBody create(byte[] bArr) {
        return Companion.create(bArr);
    }
    public static RequestBody create(byte[] bArr, MediaType mediaType) {
        return Companion.create(bArr, mediaType);
    }
    public static RequestBody create(byte[] bArr, MediaType mediaType, int i2) {
        return Companion.create(bArr, mediaType, i2);
    }
    public static RequestBody create(byte[] bArr, MediaType mediaType, int i2, int i3) {
        return Companion.create(bArr, mediaType, i2, i3);
    }
    public long contentLength() throws IOException {
        return -1L;
    }
    public abstract MediaType contentType();
    public boolean isDuplex() {
        return false;
    }
    public boolean isOneShot() {
        return false;
    }
    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public static RequestBody createdefault(Companion companion, String str, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(str, mediaType);
        }
        public static RequestBody createdefault(Companion companion, ByteString byteString, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }
        public static RequestBody createdefault(Companion companion, byte[] bArr, MediaType mediaType, int i2, int i3, int i4, Object obj) {
            if (0 != (i4 & 1)) {
                mediaType = null;
            }
            if (0 != (i4 & 2)) {
                i2 = 0;
            }
            if (0 != (i4 & 4)) {
                i3 = bArr.length;
            }
            return companion.create(bArr, mediaType, i2, i3);
        }
        public static RequestBody createdefault(Companion companion, File file, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(file, mediaType);
        }
        public static RequestBody createdefault(Companion companion, MediaType mediaType, byte[] bArr, int i2, int i3, int i4, Object obj) {
            if (0 != (i4 & 4)) {
                i2 = 0;
            }
            if (0 != (i4 & 8)) {
                i3 = bArr.length;
            }
            return companion.create(mediaType, bArr, i2, i3);
        }
        public RequestBody create(MediaType mediaType, byte[] content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return createdefault(this, mediaType, content, 0, 0, 12, null);
        }
        public RequestBody create(MediaType mediaType, byte[] content, int i2) {
            Intrinsics.checkNotNullParameter(content, "content");
            return createdefault(this, mediaType, content, i2, 0, 8, null);
        }
        public RequestBody create(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return createdefault(this, bArr, null, 0, 0, 7, null);
        }
        public RequestBody create(byte[] bArr, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return createdefault(this, bArr, mediaType, 0, 0, 6, null);
        }
        public RequestBody create(byte[] bArr, MediaType mediaType, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return createdefault(this, bArr, mediaType, i2, 0, 4, null);
        }
        public RequestBody create(String str, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Charset charset = Charsets.UTF_8;
            if (null != mediaType) {
                Charset charsetCharsetdefault = MediaType.charsetdefault(mediaType, null, 1, null);
                if (null == charsetCharsetdefault) {
                    mediaType = MediaType.Companion.parse(mediaType + "; charset=utf-8");
                } else {
                    charset = charsetCharsetdefault;
                }
            }
            byte[] bytes = str.getBytes(charset);
            checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return create(bytes, mediaType, 0, bytes.length);
        }
        public RequestBody create(final ByteString byteString, final MediaType mediaType) {
            Intrinsics.checkNotNullParameter(byteString, "<this>");
            return new RequestBody() {
                public MediaType contentType() {
                    return mediaType;
                }
                public long contentLength() {
                    return byteString.size();
                }
                public void writeTo(BufferedSink sink) throws IOException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    sink.write(byteString);
                }
            };
        }
        public RequestBody create(final byte[] bArr, final MediaType mediaType, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            Util.checkOffsetAndCount(bArr.length, i2, i3);
            return new RequestBody() {
                public MediaType contentType() {
                    return mediaType;
                }
                public long contentLength() {
                    return i3;
                }
                public void writeTo(BufferedSink sink) throws IOException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    sink.write(bArr, i2, i3);
                }
            };
        }
        public RequestBody create(final File file, final MediaType mediaType) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            return new RequestBody() {
                public MediaType contentType() {
                    return mediaType;
                }
                public long contentLength() {
                    return file.length();
                }
                public void writeTo(BufferedSink sink) throws IOException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    Source source = Okio.source(file);
                    sink.writeAll(source);
                    CloseableKt.closeFinally(source, null);
                }
            };
        }
        public RequestBody create(MediaType mediaType, String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType);
        }
        public RequestBody create(MediaType mediaType, ByteString content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType);
        }
        public RequestBody create(MediaType mediaType, byte[] content, int i2, int i3) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType, i2, i3);
        }
        public RequestBody create(MediaType mediaType, File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return create(file, mediaType);
        }
    }
}
