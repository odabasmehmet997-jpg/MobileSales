package okhttp3;

import com.fasterxml.jackson.core.JsonFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static kotlin.jvm.internal.Intrinsics.*;

public final class MultipartBody extends RequestBody {
    public static final MediaType ALTERNATIVE;
    public static final Part.Companion Companion = new Part.Companion(null);
    public static final MediaType DIGEST;
    public static final MediaType FORM;
    public static final MediaType MIXED;
    public static final MediaType PARALLEL;
    private static final byte[] COLONSPACE;
    private static final byte[] CRLF;
    private static final byte[] DASHDASH;
    static {
        final MediaType.Companion companion = MediaType.Companion;
        MIXED = companion.get("multipart/mixed");
        ALTERNATIVE = companion.get("multipart/alternative");
        DIGEST = companion.get("multipart/digest");
        PARALLEL = companion.get("multipart/parallel");
        FORM = companion.get("multipart/form-data");
        COLONSPACE = new byte[]{58, 32};
        CRLF = new byte[]{13, 10};
        DASHDASH = new byte[]{45, 45};
    }
    private ByteString boundaryByteString;
    private MediaType contentType;
    private List<Part> parts;
    private MediaType type;
    private long contentLength;
    public MultipartBody(final ByteString boundaryByteString, final MediaType type, final List<Part> parts) {
        Intrinsics.checkNotNullParameter(boundaryByteString, "boundaryByteString");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(parts, "parts");
        this.boundaryByteString = boundaryByteString;
        this.type = type;
        this.parts = parts;
        contentType = MediaType.Companion.get(type + "; boundary=" + this.boundary());
        contentLength = -1L;
    }
    public MultipartBody(FormBody contentTypeOverridingRequestBody, MediaType mediaType) {
        super();
    }
    public MediaType type() {
        return type;
    }
    public List<Part> parts() {
        return parts;
    }
    public String boundary() {
        return boundaryByteString.utf8();
    }
    public int size() {
        return parts.size();
    }
    public Part part(final int i2) {
        return parts.get(i2);
    }
    public MediaType contentType() {
        return contentType;
    }
    public MediaType m1796deprecated_type() {
        return type;
    }
    public String m1793deprecated_boundary() {
        return this.boundary();
    }
    public int m1795deprecated_size() {
        return this.size();
    }
    public List<Part> m1794deprecated_parts() {
        return parts;
    }
    public long contentLength() throws IOException {
        final long j2 = contentLength;
        if (-1 != j2) {
            return j2;
        }
        final long jWriteOrCountBytes = this.writeOrCountBytes(null, true);
        contentLength = jWriteOrCountBytes;
        return jWriteOrCountBytes;
    }
    public void writeTo(final BufferedSink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.writeOrCountBytes(sink, false);
    }
    private long writeOrCountBytes(BufferedSink bufferedSink, final boolean z) throws IOException {
        final Buffer buffer;
        if (z) {
            bufferedSink = new Buffer();
            buffer = bufferedSink.getBuffer();
        } else {
            buffer = null;
        }
        final int size = parts.size();
        long j2 = 0;
        int i2 = 0;
        while (i2 < size) {
            final int i3 = i2 + 1;
            final Part part = parts.get(i2);
            final Headers headers = part.headers();
            final RequestBody requestBodyBody = part.body();
            checkNotNull(bufferedSink);
            bufferedSink.write(MultipartBody.DASHDASH);
            bufferedSink.write(boundaryByteString);
            bufferedSink.write(MultipartBody.CRLF);
            if (null != headers) {
                final int size2 = headers.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    bufferedSink.writeUtf8(headers.name(i4)).write(MultipartBody.COLONSPACE).writeUtf8(headers.value(i4)).write(MultipartBody.CRLF);
                }
            }
            final MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (null != mediaTypeContentType) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(mediaTypeContentType.toString()).write(MultipartBody.CRLF);
            }
            final long jContentLength = requestBodyBody.contentLength();
            if (-1 != jContentLength) {
                bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(jContentLength).write(MultipartBody.CRLF);
            } else if (z) {
                checkNotNull(buffer);
                buffer.clear();
                return -1L;
            }
            final byte[] bArr = MultipartBody.CRLF;
            bufferedSink.write(bArr);
            if (z) {
                j2 += jContentLength;
            } else {
                requestBodyBody.writeTo(bufferedSink);
            }
            bufferedSink.write(bArr);
            i2 = i3;
        }
        checkNotNull(bufferedSink);
        final byte[] bArr2 = MultipartBody.DASHDASH;
        bufferedSink.write(bArr2);
        bufferedSink.write(boundaryByteString);
        bufferedSink.write(bArr2);
        bufferedSink.write(MultipartBody.CRLF);
        if (!z) {
            return j2;
        }
        checkNotNull(buffer);
        final long size3 = j2 + buffer.size();
        buffer.clear();
        return size3;
    }
    public static final class Part {
        public static final Companion Companion = new Companion(null);
        private final RequestBody body;
        private final Headers headers;
        public Part(final Headers headers, final RequestBody requestBody, final DefaultConstructorMarker defaultConstructorMarker) {
            this(headers, requestBody);
        }
        private Part(final Headers headers, final RequestBody requestBody) {
            this.headers = headers;
            body = requestBody;
        }
        public static Part create(final Headers headers, final RequestBody requestBody) {
            return Part.Companion.create(headers, requestBody);
        }
        public static Part create(final RequestBody requestBody) {
            return Part.Companion.create(requestBody);
        }
        public static Part createFormData(final String str, final String str2) {
            return Part.Companion.createFormData(str, str2);
        }
        public static Part createFormData(final String str, final String str2, final RequestBody requestBody) {
            return Part.Companion.createFormData(str, str2, requestBody);
        }
        public Headers headers() {
            return headers;
        }

        public RequestBody body() {
            return body;
        }
        public Headers m1798deprecated_headers() {
            return headers;
        }
        public RequestBody m1797deprecated_body() {
            return body;
        }
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public Part create(final RequestBody body) {
                Intrinsics.checkNotNullParameter(body, "body");
                return this.create(null, body);
            }

            public Part create(final Headers headers, final RequestBody body) {
                Intrinsics.checkNotNullParameter(body, "body");
                if (null != (null == headers ? null : headers.get(HttpHeaders.CONTENT_TYPE))) {
                    throw new IllegalArgumentException("Unexpected header: Content-Type");
                }
                if (null != (null == headers ? null : headers.get(HttpHeaders.CONTENT_LENGTH))) {
                    throw new IllegalArgumentException("Unexpected header: Content-Length");
                }
                return new Part(headers, body, null);
            }

            public Part createFormData(final String name, final String value) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                return this.createFormData(name, null, RequestBody.Companion.createdefault(RequestBody.Companion, value, null, 1, null));
            }

            public Part createFormData(final String name, final String str, final RequestBody body) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(body, "body");
                final StringBuilder sb = new StringBuilder();
                sb.append("form-data; name=");
                final Companion companion = MultipartBody.Companion;
                companion.appendQuotedStringokhttp(sb, name);
                if (null != str) {
                    sb.append("; filename=");
                    companion.appendQuotedStringokhttp(sb, str);
                }
                final String string = sb.toString();
                checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return this.create(new Headers.Builder().addUnsafeNonAscii(HttpHeaders.CONTENT_DISPOSITION, string).build(), body);
            }

            private void appendQuotedStringokhttp(StringBuilder sb, String name) {

            }
        }
    }
    public static final class Builder {
        private final ByteString boundary;
        private final List<Part> parts;
        private MediaType type;

        public Builder() {
            this(null, 1, null);
        }

        public Builder(final String boundary) {
            Intrinsics.checkNotNullParameter(boundary, "boundary");
            this.boundary = ByteString.Companion.encodeUtf8(boundary);
            type = MIXED;
            parts = new ArrayList();
        }
        public Builder(String str, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
            if (0 != (i2 & 1)) {
                str = UUID.randomUUID().toString();
                checkNotNullExpressionValue(str, "randomUUID().toString()");
            }
            this(str);
        }

        public Builder setType(final MediaType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (!areEqual(type.type(), "multipart")) {
                throw new IllegalArgumentException(stringPlus("multipart != ", type));
            }
            this.type = type;
            return this;
        }

        public Builder addPart(final RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.addPart(Part.Companion.create(body));
            return this;
        }

        public Builder addPart(final Headers headers, final RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.addPart(Part.Companion.create(headers, body));
            return this;
        }

        public Builder addFormDataPart(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.addPart(Part.Companion.createFormData(name, value));
            return this;
        }

        public Builder addFormDataPart(final String name, final String str, final RequestBody body) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(body, "body");
            this.addPart(Part.Companion.createFormData(name, str, body));
            return this;
        }

        public Builder addPart(final Part part) {
            Intrinsics.checkNotNullParameter(part, "part");
            parts.add(part);
            return this;
        }

        public MultipartBody build() {
            if (parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(boundary, type, Util.toImmutableList(parts));
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public void appendQuotedStringokhttp(final StringBuilder sb, final String key) {
            Intrinsics.checkNotNullParameter(sb, "<this>");
            Intrinsics.checkNotNullParameter(key, "key");
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            final int length = key.length();
            int i2 = 0;
            while (i2 < length) {
                final int i3 = i2 + 1;
                final char cCharAt = key.charAt(i2);
                if ('\n' == cCharAt) {
                    sb.append("%0A");
                } else if ('\r' == cCharAt) {
                    sb.append("%0D");
                } else if ('\"' == cCharAt) {
                    sb.append("%22");
                } else {
                    sb.append(cCharAt);
                }
                i2 = i3;
            }
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
    }
}
