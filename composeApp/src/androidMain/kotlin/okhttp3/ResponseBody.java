package okhttp3;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

import java.io.*;
import java.nio.charset.Charset;

import static kotlin.jvm.internal.Intrinsics.stringPlus;

public abstract class ResponseBody implements Closeable {
    public static final Companion Companion = new Companion(null);
    private Reader reader;
    public static ResponseBody create(String str, MediaType mediaType) {
        return Companion.create(str, mediaType);
    }
    public static ResponseBody create(MediaType mediaType, long j2, BufferedSource bufferedSource) {
        return Companion.create(mediaType, j2, bufferedSource);
    }
    public static ResponseBody create(MediaType mediaType, String str) {
        return Companion.create(mediaType, str);
    }
    public static ResponseBody create(MediaType mediaType, ByteString byteString) {
        return Companion.create(mediaType, byteString);
    }
    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        return Companion.create(mediaType, bArr);
    }
    public static ResponseBody create(BufferedSource bufferedSource, MediaType mediaType, long j2) {
        return Companion.create(bufferedSource, mediaType, j2);
    }
    public static ResponseBody create(ByteString byteString, MediaType mediaType) {
        return Companion.create(byteString, mediaType);
    }
    public static ResponseBody create(byte[] bArr, MediaType mediaType) {
        return Companion.create(bArr, mediaType);
    }
    public abstract long contentLength();
    public abstract MediaType contentType();
    public abstract BufferedSource source();
    public final InputStream byteStream() {
        return source().inputStream();
    }
    private <T> T consumeSource(Function1<? super BufferedSource, ? extends T> function1, Function1<? super T, Integer> function12) throws IOException {
        long jContentLength = contentLength();
        if (2147483647L < jContentLength) {
            throw new IOException(stringPlus("Cannot buffer entire body for content length: ", Long.valueOf(jContentLength)));
        }
        BufferedSource bufferedSourceSource = source();
        final T tInvoke = function1.invoke(bufferedSourceSource);
        InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(bufferedSourceSource, null);
        InlineMarker.finallyEnd(1);
        final int iIntValue = function12.invoke(tInvoke).intValue();
        if (-1 == jContentLength || jContentLength == iIntValue) {
            return tInvoke;
        }
        throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + iIntValue + ") disagree");
    }
    public final ByteString byteString() throws IOException {
        long jContentLength = contentLength();
        if (2147483647L < jContentLength) {
            throw new IOException(stringPlus("Cannot buffer entire body for content length: ", Long.valueOf(jContentLength)));
        }
        BufferedSource bufferedSourceSource = source();
        final ByteString byteString = bufferedSourceSource.readByteString();
        kotlin.io.CloseableKt.closeFinally(bufferedSourceSource, null);
        final int size = byteString.size();
        if (-1 == jContentLength || jContentLength == size) {
            return byteString;
        }
        throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + size + ") disagree");
    }
    public final byte[] bytes() throws IOException {
        long jContentLength = contentLength();
        if (2147483647L < jContentLength) {
            throw new IOException(stringPlus("Cannot buffer entire body for content length: ", Long.valueOf(jContentLength)));
        }
        BufferedSource bufferedSourceSource = source();
        final byte[] byteArray = bufferedSourceSource.readByteArray();
        kotlin.io.CloseableKt.closeFinally(bufferedSourceSource, null);
        final int length = byteArray.length;
        if (-1 == jContentLength || jContentLength == length) {
            return byteArray;
        }
        throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + length + ") disagree");
    }
    public final Reader charStream() {
        Reader reader = this.reader;
        if (null != reader) {
            return reader;
        }
        BomAwareReader bomAwareReader = new BomAwareReader(source(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }
    public final String string() throws IOException {
        BufferedSource bufferedSourceSource = source();
        final String string = bufferedSourceSource.readString(Util.readBomAsCharset(bufferedSourceSource, this.charset()));
        kotlin.io.CloseableKt.closeFinally(bufferedSourceSource, null);
        return string;
    }
    private final Charset charset() {
        MediaType mediaTypeContentType = contentType();
        Charset charset = null == mediaTypeContentType ? null : mediaTypeContentType.charset(Charsets.UTF_8);
        return null == charset ? Charsets.UTF_8 : charset;
    }
    public void close() {
        Util.closeQuietly(source());
    }
    public static final class BomAwareReader extends Reader {
        private final Charset charset;
        private final BufferedSource source;
        private boolean closed;
        private Reader delegate;

        public BomAwareReader(BufferedSource source, Charset charset) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(charset, "charset");
            this.source = source;
            this.charset = charset;
        }
        public int read(char[] cbuf, int i2, int i3) throws IOException {
            Intrinsics.checkNotNullParameter(cbuf, "cbuf");
            if (this.closed) {
                throw new IOException("Stream closed");
            }
            Reader inputStreamReader = this.delegate;
            if (null == inputStreamReader) {
                inputStreamReader = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
                this.delegate = inputStreamReader;
            }
            return inputStreamReader.read(cbuf, i2, i3);
        }
        public void close() throws IOException {
            Unit unit;
            this.closed = true;
            Reader reader = this.delegate;
            if (null == reader) {
                unit = null;
            } else {
                reader.close();
                unit = Unit.INSTANCE;
            }
            if (null == unit) {
                this.source.close();
            }
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static ResponseBody createdefault(Companion companion, String str, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(str, mediaType);
        }

        public static ResponseBody createdefault(Companion companion, byte[] bArr, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(bArr, mediaType);
        }

        public static ResponseBody createdefault(Companion companion, ByteString byteString, MediaType mediaType, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        public static ResponseBody createdefault(Companion companion, BufferedSource bufferedSource, MediaType mediaType, long j2, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                mediaType = null;
            }
            if (0 != (i2 & 2)) {
                j2 = -1;
            }
            return companion.create(bufferedSource, mediaType, j2);
        }

        public ResponseBody create(String str, MediaType mediaType) {
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
            Buffer bufferWriteString = new Buffer().writeString(str, charset);
            return create(bufferWriteString, mediaType, bufferWriteString.size());
        }

        public ResponseBody create(byte[] bArr, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return create(new Buffer().write(bArr), mediaType, bArr.length);
        }

        public ResponseBody create(ByteString byteString, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(byteString, "<this>");
            return create(new Buffer().write(byteString), mediaType, byteString.size());
        }

        public ResponseBody create(final BufferedSource bufferedSource, final MediaType mediaType, final long j2) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
            return new ResponseBody() { // from class: okhttp3.ResponseBodyCompanionasResponseBody1
                @Override // okhttp3.ResponseBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // okhttp3.ResponseBody
                public long contentLength() {
                    return j2;
                }

                @Override // okhttp3.ResponseBody
                public BufferedSource source() {
                    return bufferedSource;
                }
            };
        }

        public ResponseBody create(MediaType mediaType, String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType);
        }

        public ResponseBody create(MediaType mediaType, byte[] content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType);
        }

        public ResponseBody create(MediaType mediaType, ByteString content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType);
        }

        public ResponseBody create(MediaType mediaType, long j2, BufferedSource content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, mediaType, j2);
        }
    }
}
