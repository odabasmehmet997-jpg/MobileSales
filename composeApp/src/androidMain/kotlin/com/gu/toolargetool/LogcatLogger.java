package com.gu.toolargetool;

import android.util.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LogcatLogger implements Logger {
    private int priority = 0;
    private String tag = "";

    public LogcatLogger() {
        Intrinsics.checkNotNullParameter(tag, "tag");
        priority = i2;
        this.tag = tag;
    }
    public  LogcatLogger(final int i2, final String str, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
    public void log(final String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Log.println(priority, tag, msg);
    }
    public void logException(final Exception e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        Log.w(tag, e2.getMessage(), e2);
    }
}
