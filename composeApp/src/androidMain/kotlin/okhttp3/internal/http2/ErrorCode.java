package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;

public enum ErrorCode {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    SETTINGS_TIMEOUT(4),
    STREAM_CLOSED(5),
    FRAME_SIZE_ERROR(6),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);

    public static final Companion Companion = new Companion(null);
    private final int httpCode;

    ErrorCode(int i2) {
        this.httpCode = i2;
    }

    public final int getHttpCode() {
        return this.httpCode;
    }

    public void readConnectionPreface(final Http2Connection.ReaderRunnable readerRunnable) {
    }

    public boolean nextFrame(final boolean b, final Http2Connection.ReaderRunnable readerRunnable) {
        return b;
    }

    public void close() {
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ErrorCode fromHttp2(int i2) {
            for (ErrorCode errorCode : ErrorCode.values()) {
                if (errorCode.getHttpCode() == i2) {
                    return errorCode;
                }
            }
            return null;
        }
    }
}
