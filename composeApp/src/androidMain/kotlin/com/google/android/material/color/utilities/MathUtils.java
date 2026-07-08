package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MathUtils {
    public static double clampDouble(double d2, double d3, double d4) {
        return d4 < d2 ? d2 : d4 > d3 ? d3 : d4;
    }

    public static int clampInt(int i2, int i3, int i4) {
        return i4 < i2 ? i2 : i4 > i3 ? i3 : i4;
    }

    public static double lerp(double d2, double d3, double d4) {
        return ((1.0d - d4) * d2) + (d4 * d3);
    }

    public static double sanitizeDegreesDouble(double d2) {
        double d3 = d2 % 360.0d;
        return d3 < 0.0d ? d3 + 360.0d : d3;
    }

    public static int signum(double d2) {
        if (d2 < 0.0d) {
            return -1;
        }
        return d2 == 0.0d ? 0 : 1;
    }

    private MathUtils() {
    }

    public static int sanitizeDegreesInt(int i2) {
        int i3 = i2 % 360;
        return i3 < 0 ? i3 + 360 : i3;
    }

    public static double rotationDirection(double d2, double d3) {
        return sanitizeDegreesDouble(d3 - d2) <= 180.0d ? 1.0d : -1.0d;
    }

    public static double differenceDegrees(double d2, double d3) {
        return 180.0d - Math.abs(Math.abs(d2 - d3) - 180.0d);
    }

    public static double[] matrixMultiply(double[] dArr, double[][] dArr2) {
        double d2 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d3 = dArr3[0] * d2;
        double d4 = dArr[1];
        double d5 = d3 + (dArr3[1] * d4);
        double d6 = dArr[2];
        double d7 = d5 + (dArr3[2] * d6);
        double[] dArr4 = dArr2[1];
        double d8 = (dArr4[0] * d2) + (dArr4[1] * d4) + (dArr4[2] * d6);
        double[] dArr5 = dArr2[2];
        return new double[]{d7, d8, (d2 * dArr5[0]) + (d4 * dArr5[1]) + (d6 * dArr5[2])};
    }
}
