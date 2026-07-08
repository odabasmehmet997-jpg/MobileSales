package com.gu.toolargetool;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class TooLargeTool {
    public static final TooLargeTool INSTANCE;
    private static ActivitySavedStateLogger activityLogger;
    static {
        TooLargeTool tooLargeTool = new TooLargeTool();
        INSTANCE = tooLargeTool;
    }
    private float KB(int paramInt) {
        return paramInt / 1000.0F;
    }
    public static String bundleBreakdown(Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramBundle, "bundle");
        SizeTree sizeTree = TooLargeToolKt.sizeTreeFromBundle(paramBundle);
        String str2 = sizeTree.component1();
        int i = sizeTree.component2();
        List list = sizeTree.component3();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.UK;
        int j = list.size();
        Integer integer = Integer.valueOf(j);
        TooLargeTool tooLargeTool = INSTANCE;
        Float float_ = Float.valueOf(tooLargeTool.KB(i));
        Object[] arrayOfObject = { str2, integer, float_ };
        i = 3;
        arrayOfObject = Arrays.copyOf(arrayOfObject, i);
        String str1 = String.format(locale, "%s contains %d keys and measures %,.1f KB when serialized as a Parcel", arrayOfObject);
        final String str3 = "java.lang.String.format(locale, format, *args)";
        checkNotNullExpressionValue(str1, str3);
        Iterator<SizeTree> iterator = list.iterator();
        while (true) {
            String str = "";
            boolean bool = iterator.hasNext();
            if (bool) {
                SizeTree sizeTree1 = iterator.next();
                String str4 = sizeTree1.component1();
                int k = sizeTree1.component2();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str1);
                StringCompanionObject stringCompanionObject1 = StringCompanionObject.INSTANCE;
                Locale locale1 = Locale.UK;
                TooLargeTool tooLargeTool1 = INSTANCE;
                float f = tooLargeTool1.KB(k);
                Float float_1 = Float.valueOf(f);
                Object[] arrayOfObject1 = { str4, float_1 };
                j = 2;
                arrayOfObject1 = Arrays.copyOf(arrayOfObject1, j);
                str4 = "\n* %s = %,.1f KB";
                str = String.format(locale1, str4, arrayOfObject1);
                checkNotNullExpressionValue(str, str3);
                stringBuilder.append(str);
                str = stringBuilder.toString();
                continue;
            }
            return str;
        }
    }
    public static boolean isLogging() {
        ActivitySavedStateLogger activitySavedStateLogger = activityLogger;
        checkNotNull(activitySavedStateLogger);
        return activitySavedStateLogger.isLogging();
    }
    public static void logBundleBreakdown(String paramString, int paramInt, Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramString, "tag");
        Intrinsics.checkNotNullParameter(paramBundle, "bundle");
        String str = bundleBreakdown(paramBundle);
        Log.println(paramInt, paramString, str);
    }
    public static void logBundleBreakdown(String paramString, Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramString, "tag");
        Intrinsics.checkNotNullParameter(paramBundle, "bundle");
        String str = bundleBreakdown(paramBundle);
        Log.println(Log.DEBUG, paramString, str);
    }
    public static void startLogging(Application paramApplication, final int i, final Object o, final int i1, final Object object) {
        TooLargeTool.startLogging(paramApplication, 0, null, 6, null);
    }
    public static void startLogging(Application paramApplication, int paramInt) {
        TooLargeTool.startLogging(paramApplication, paramInt, null, 4, null);
    }
    public static void startLogging(Application paramApplication, int paramInt, String paramString) {
        Intrinsics.checkNotNullParameter(paramApplication, "application");
        Intrinsics.checkNotNullParameter(paramString, "tag");
        DefaultFormatter defaultFormatter = new DefaultFormatter();

        LogcatLogger logcatLogger = new LogcatLogger();
        startLogging(paramApplication, defaultFormatter, logcatLogger);
    }
    public static void startLogging(Application paramApplication, Formatter paramFormatter, Logger paramLogger) {
        Intrinsics.checkNotNullParameter(paramApplication, "application");
        Intrinsics.checkNotNullParameter(paramFormatter, "formatter");
        Intrinsics.checkNotNullParameter(paramLogger, "logger");
        ActivitySavedStateLogger activitySavedStateLogger2 = activityLogger;
        if (null == activitySavedStateLogger2) {
            activitySavedStateLogger2 = new ActivitySavedStateLogger();
            final boolean bool1 = true;
           // this(paramFormatter, paramLogger, bool1);
            activityLogger = activitySavedStateLogger2;
        }
        ActivitySavedStateLogger activitySavedStateLogger1 = activityLogger;
        checkNotNull(activitySavedStateLogger1);
        boolean bool = activitySavedStateLogger1.isLogging();
        if (bool)
            return;
        activitySavedStateLogger1 = activityLogger;
        checkNotNull(activitySavedStateLogger1);
        activitySavedStateLogger1.startLogging();
        activitySavedStateLogger1 = activityLogger;
        paramApplication.registerActivityLifecycleCallbacks(activitySavedStateLogger1);
    }
    public static void stopLogging(Application paramApplication) {
        Intrinsics.checkNotNullParameter(paramApplication, "application");
        ActivitySavedStateLogger activitySavedStateLogger = activityLogger;
        checkNotNull(activitySavedStateLogger);
        boolean bool = activitySavedStateLogger.isLogging();
        if (!bool)
            return;
        activitySavedStateLogger = activityLogger;
        checkNotNull(activitySavedStateLogger);
        activitySavedStateLogger.stopLogging();
        activitySavedStateLogger = activityLogger;
        paramApplication.unregisterActivityLifecycleCallbacks(activitySavedStateLogger);
    }
}
