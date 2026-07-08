package org.greenrobot.eventbus;

import android.os.Looper;
import org.greenrobot.eventbus.android.AndroidLogger;

import java.io.PrintStream;
import java.util.logging.Level;

public interface Logger {
    void log(Level level, String str);
    void log(Level level, String str, Throwable th);
    class SystemOutLogger implements Logger {
        public void log(final Level level, final String str) {
            System.out.println("[" + level + "] " + str);
        }
        public void log(final Level level, final String str, final Throwable th) {
            final PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(printStream);
        }
    }
    enum Default {
        ;

        public static Logger get() {
            if (AndroidLogger.isAndroidLogAvailable() && null != getAndroidMainLooperOrNull()) {
                return new AndroidLogger("EventBus");
            }
            return new SystemOutLogger();
        }

        static Object getAndroidMainLooperOrNull() {
            try {
                return Looper.getMainLooper();
            } catch (final RuntimeException unused) {
                return null;
            }
        }
    }
}
