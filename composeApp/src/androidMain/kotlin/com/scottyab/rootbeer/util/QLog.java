package com.scottyab.rootbeer.util;

import android.util.Log;

public class QLog {
    public static int LOGGING_LEVEL = 5;
    public static void m583e(final Object obj) {
        if (QLog.isELoggable()) {
            Log.e ("RootBeer", QLog.getTrace() + obj);
            Log.e ("QLog", QLog.getTrace() + obj);
        }
    }
    public static void m582e(final Exception exc) {
        if (QLog.isELoggable()) {
            exc.printStackTrace ();
        }
    }
    public static void m584v(final Object obj) {
        if (QLog.isVLoggable()) {
            Log.v ("RootBeer", QLog.getTrace() + obj);
        }
    }
    public static boolean isVLoggable() {
        return 4 < LOGGING_LEVEL;
    }
    public static boolean isELoggable() {
        return 0 < LOGGING_LEVEL;
    }
    private static String getTrace() {
        final StackTraceElement[] stackTrace = new Throwable ().getStackTrace ();
        final String methodName = stackTrace[2].getMethodName ();
        final String className = stackTrace[2].getClassName ();
        final int lineNumber = stackTrace[2].getLineNumber ();
        return className.substring (className.lastIndexOf (46) + 1) + ": " + methodName + "() [" + lineNumber + "] - ";
    }
}
