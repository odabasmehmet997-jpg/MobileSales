package okhttp3.internal.platform.android;

import kotlin.jvm.internal.Intrinsics;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class AndroidLog2 extends Handler {
    public static final AndroidLog2 INSTANCE = new AndroidLog2();
    private AndroidLog2() {
    }
    public void close() {
    }
    public void flush() {
    }
    public void publish(final LogRecord record) {
        Intrinsics.checkNotNullParameter(record, "record");
        final AndroidLog androidLog = AndroidLog.INSTANCE;
        final String loggerName = record.getLoggerName();
        checkNotNullExpressionValue(loggerName, "record.loggerName");
        final int androidLevel = AndroidLog3.getAndroidLevel(record);
        final String message = record.getMessage();
        checkNotNullExpressionValue(message, "record.message");
        androidLog.androidLogokhttp(loggerName, androidLevel, message, record.getThrown());
    }
}
