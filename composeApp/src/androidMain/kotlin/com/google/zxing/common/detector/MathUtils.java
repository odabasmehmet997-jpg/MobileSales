package com.google.zxing.common.detector;

public enum MathUtils {
    ;

    public static int round(final float f2) {
        return (int) (f2 + (0.0f > f2 ? -0.5f : 0.5f));
    }

    public static float distance(final float f2, final float f3, final float f4, final float f5) {
        final float f6 = f2 - f4;
        final float f7 = f3 - f5;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    public static float distance(final int i2, final int i3, final int i4, final int i5) {
        final int i6 = i2 - i4;
        final int i7 = i3 - i5;
        return (float) Math.sqrt((i6 * i6) + (i7 * i7));
    }

    public static int sum(final int[] iArr) {
        int i2 = 0;
        for (final int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }
}
