package okhttp3.internal.platform.android;

import android.util.Log;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class AndroidLog {
    public static final AndroidLog INSTANCE = new AndroidLog();
    private static final int MAX_LOG_LENGTH = 4000;
    private static final Map<String, String> knownLoggers;
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();
    static {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final Package r2 = OkHttpClient.class.getPackage();
        final String name = null == r2 ? null : r2.getName();
        if (null != name) {
            linkedHashMap.put(name, "OkHttp");
        }
        final String name2 = OkHttpClient.class.getName();
        checkNotNullExpressionValue(name2, "OkHttpClient::class.java.name");
        linkedHashMap.put(name2, "okhttp.OkHttpClient");
        final String name3 = Http2.class.getName();
        checkNotNullExpressionValue(name3, "Http2::class.java.name");
        linkedHashMap.put(name3, "okhttp.Http2");
        final String name4 = TaskRunner.class.getName();
        checkNotNullExpressionValue(name4, "TaskRunner::class.java.name");
        linkedHashMap.put(name4, "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.toMap(linkedHashMap);
    }

    private AndroidLog() {
    }

    public void androidLogokhttp(final String loggerName, final int i2, String message, final Throwable th) {
        int iMin;
        Intrinsics.checkNotNullParameter(loggerName, "loggerName");
        Intrinsics.checkNotNullParameter(message, "message");
        final String strLoggerTag = this.loggerTag(loggerName);
        if (Log.isLoggable(strLoggerTag, i2)) {
            if (null != th) {
                message = message + '\n' + Log.getStackTraceString(th);
            }
            final int length = message.length();
            int i3 = 0;
            while (i3 < length) {
                int iIndexOfdefault = StringsKt.indexOf(message, '\n', i3, false);
                if (-1 == iIndexOfdefault) {
                    iIndexOfdefault = length;
                }
                while (true) {
                    iMin = Math.min(iIndexOfdefault, i3 + AndroidLog.MAX_LOG_LENGTH);
                    final String strSubstring = message.substring(i3, iMin);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    Log.println(i2, strLoggerTag, strSubstring);
                    if (iMin >= iIndexOfdefault) {
                        break;
                    } else {
                        i3 = iMin;
                    }
                }
                i3 = iMin + 1;
            }
        }
    }
    private String loggerTag(final String str) {
        final String str2 = AndroidLog.knownLoggers.get(str);
        return null == str2 ? StringsKt.take(str, 23) : str2;
    }

    public void enable() {
        for (final Map.Entry<String, String> entry : AndroidLog.knownLoggers.entrySet()) {
            this.enableLogging(entry.getKey(), entry.getValue());
        }
    }

    private void enableLogging(final String str, final String str2) throws SecurityException {
        final Level level;
        final Logger logger = Logger.getLogger(str);
        if (AndroidLog.configuredLoggers.add(logger)) {
            logger.setUseParentHandlers(false);
            if (Log.isLoggable(str2, Log.DEBUG)) {
                level = Level.FINE;
            } else {
                level = Log.isLoggable(str2, Log.INFO) ? Level.INFO : Level.WARNING;
            }
            logger.setLevel(level);
            logger.addHandler(AndroidLog2.INSTANCE);
        }
    }
}
