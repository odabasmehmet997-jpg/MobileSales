package org.greenrobot.eventbus.android;

import android.util.Log;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import org.greenrobot.eventbus.Logger;

import java.util.logging.Level;

public class AndroidLogger implements Logger {
    private static final boolean ANDROID_LOG_AVAILABLE;
    private final String tag;
    static {
        boolean z;
        try {
            Class.forName("android.util.Log");
            z = true;
        } catch (final ClassNotFoundException unused) {
            z = false;
        }
        ANDROID_LOG_AVAILABLE = z;
    }
    public static boolean isAndroidLogAvailable() {
        return AndroidLogger.ANDROID_LOG_AVAILABLE;
    }
    public AndroidLogger(final String str) {
        tag = str;
    }
    public void log(final Level level, final String str) {
        if (level != Level.OFF) {
            Log.println(this.mapLevel(level), tag, str);
        }
    }
    public void log(final Level level, final String str, final Throwable th) {
        if (level != Level.OFF) {
            Log.println(this.mapLevel(level), tag, str + SqlLiteVariable._NEW_LINE + Log.getStackTraceString(th));
        }
    }
    private int mapLevel(final Level level) {
        final int intValue = level.intValue();
        if (800 > intValue) {
            return 500 > intValue ? 2 : 3;
        }
        if (900 > intValue) {
            return 4;
        }
        return 1000 > intValue ? 5 : 6;
    }
}
