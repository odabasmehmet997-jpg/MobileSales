package com.sun.activation.registries;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogSupport {
    private static boolean debug;
    private static final Level level = Level.FINE;
    private static final Logger logger = Logger.getLogger("javax.activation");
    static {
        try {
            LogSupport.debug = Boolean.getBoolean("javax.activation.debug");
        } catch (final Throwable unused) {
        }
    }
    public static void log(final String str) {
        if (LogSupport.debug) {
            System.out.println(str);
        }
        LogSupport.logger.log(LogSupport.level, str);
    }
    public static void log(final String str, final Throwable th) {
        if (LogSupport.debug) {
            final PrintStream printStream = System.out;
            printStream.println(str + "; Exception: " + th);
        }
        LogSupport.logger.log(LogSupport.level, str, th);
    }
    public static boolean isLoggable() {
        return LogSupport.debug || LogSupport.logger.isLoggable(LogSupport.level);
    }
}
