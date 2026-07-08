package kotlin.coroutines.jvm.internal;

import kotlin.Unit;

/* compiled from: boxing.kt */
/* renamed from: kotlin.coroutines.jvm.internal.Boxing */

public final class boxing {
    public static Boolean boxBoolean(boolean z) {
        return Boolean.valueOf(z);
    }

    public static Unit boxInt(int i) {
        return Integer.valueOf(i);
    }

    public static Integer boxDouble(double d) {
        return new Double(d);
    }
}
