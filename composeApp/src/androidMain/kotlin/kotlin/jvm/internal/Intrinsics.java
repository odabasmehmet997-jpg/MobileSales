package kotlin.jvm.internal;

import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;
import java.util.Arrays;

public class Intrinsics {

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
    public static int compare(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }
    private Intrinsics() {
    }
    public static String stringPlus(String str, Object obj) {
        return str + obj;
    }
    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throwJavaNpe();
        }
    }
    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throwJavaNpe(str);
        }
    }
    public static void throwNpe() {
        throw sanitizeStackTrace(new KotlinNullPointerException());
    }
    public static void throwJavaNpe() {
        throw sanitizeStackTrace(new NullPointerException());
    }
    public static void throwJavaNpe(String str) {
        throw sanitizeStackTrace(new NullPointerException(str));
    }
    public static void throwUninitializedProperty(String str) {
        throw sanitizeStackTrace(new UninitializedPropertyAccessException(str));
    }
    public static void throwUninitializedPropertyAccessException(String str) {
        throwUninitializedProperty("lateinit property " + str + " has not been initialized");
    }
    public static void checkExpressionValueIsNotNull(Object obj, String str) {
        if (obj == null) {
            throw sanitizeStackTrace(new IllegalStateException(str + " must not be null"));
        }
    }
    public static void checkNotNullExpressionValue(Object obj, String str) {
        if (obj == null) {
            throw sanitizeStackTrace(new NullPointerException(str + " must not be null"));
        }
    }
    public static void checkParameterIsNotNull(Object obj, String str) {
        if (obj == null) {
            throwParameterIsNullIAE(str);
        }
    }
    public static void checkNotNullParameter(Object obj, String str) {
        if (obj == null) {
            throwParameterIsNullNPE(str);
        }
    }
    private static void throwParameterIsNullIAE(String str) {
        throw sanitizeStackTrace(new IllegalArgumentException(createParameterIsNullExceptionMessage(str)));
    }
    private static void throwParameterIsNullNPE(String str) {
        throw sanitizeStackTrace(new NullPointerException(createParameterIsNullExceptionMessage(str)));
    }
    private static String createParameterIsNullExceptionMessage(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = Intrinsics.class.getName();
        int i = 0;
        while (!stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        while (stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        StackTraceElement stackTraceElement = stackTrace[i];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        return "Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + str;
    }
    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
    public static boolean areEqual(Double d, double d2) {
        return d != null && d.doubleValue() == d2;
    }
    public static void throwUndefinedForReified() {
        throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }
    public static void throwUndefinedForReified(String str) {
        throw new UnsupportedOperationException(str);
    }
    public static void reifiedOperationMarker(int i, String str) {
        throwUndefinedForReified();
    }
    public static void needClassReification() {
        throwUndefinedForReified();
    }
    private static <T extends Throwable> T sanitizeStackTrace(T t) {
        return sanitizeStackTrace(t, Intrinsics.class.getName());
    }
    public static <T extends Throwable> T sanitizeStackTrace(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace(Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }
    public static boolean equals(String code, String string) {
        return false;
    }
    public static class Kotlin {
        private Kotlin() {
        }
    }
}
