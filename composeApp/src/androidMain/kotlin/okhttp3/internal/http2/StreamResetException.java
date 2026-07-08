package okhttp3.internal.http2;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;

import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;
    public StreamResetException(final ErrorCode errorCode) {
        super(stringPlus("stream was reset: ", errorCode));
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
