package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ColorUtils {
    static final double[][] SRGB_TO_XYZ = {new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};
    static final double[][] XYZ_TO_SRGB = {new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};
    static final double[] WHITE_POINT_D65 = {95.047d, 100.0d, 108.883d};

    public static int alphaFromArgb(int i2) {
        return (i2 >> 24) & 255;
    }

    public static int argbFromRgb(int i2, int i3, int i4) {
        return ((i2 & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((i3 & 255) << 8) | (i4 & 255);
    }

    public static int blueFromArgb(int i2) {
        return i2 & 255;
    }

    public static int greenFromArgb(int i2) {
        return (i2 >> 8) & 255;
    }

    static double labInvf(double d2) {
        double d3 = d2 * d2 * d2;
        return d3 > 0.008856451679035631d ? d3 : ((d2 * 116.0d) - 16.0d) / 903.2962962962963d;
    }

    public static int redFromArgb(int i2) {
        return (i2 >> 16) & 255;
    }

    private ColorUtils() {
    }

    public static int argbFromLinrgb(double[] dArr) {
        return argbFromRgb(delinearized(dArr[0]), delinearized(dArr[1]), delinearized(dArr[2]));
    }

    public static boolean isOpaque(int i2) {
        return alphaFromArgb(i2) >= 255;
    }

    public static int argbFromXyz(double d2, double d3, double d4) {
        double[][] dArr = XYZ_TO_SRGB;
        double[] dArr2 = dArr[0];
        double d5 = (dArr2[0] * d2) + (dArr2[1] * d3) + (dArr2[2] * d4);
        double[] dArr3 = dArr[1];
        double d6 = (dArr3[0] * d2) + (dArr3[1] * d3) + (dArr3[2] * d4);
        double[] dArr4 = dArr[2];
        return argbFromRgb(delinearized(d5), delinearized(d6), delinearized((dArr4[0] * d2) + (dArr4[1] * d3) + (dArr4[2] * d4)));
    }

    public static double[] xyzFromArgb(int i2) {
        return MathUtils.matrixMultiply(new double[]{linearized(redFromArgb(i2)), linearized(greenFromArgb(i2)), linearized(blueFromArgb(i2))}, SRGB_TO_XYZ);
    }

    public static int argbFromLab(double d2, double d3, double d4) {
        double[] dArr = WHITE_POINT_D65;
        double d5 = (d2 + 16.0d) / 116.0d;
        double d6 = d5 - (d4 / 200.0d);
        return argbFromXyz(labInvf((d3 / 500.0d) + d5) * dArr[0], labInvf(d5) * dArr[1], labInvf(d6) * dArr[2]);
    }

    public static double[] labFromArgb(int i2) {
        double dLinearized = linearized(redFromArgb(i2));
        double dLinearized2 = linearized(greenFromArgb(i2));
        double dLinearized3 = linearized(blueFromArgb(i2));
        double[][] dArr = SRGB_TO_XYZ;
        double[] dArr2 = dArr[0];
        double d2 = (dArr2[0] * dLinearized) + (dArr2[1] * dLinearized2) + (dArr2[2] * dLinearized3);
        double[] dArr3 = dArr[1];
        double d3 = (dArr3[0] * dLinearized) + (dArr3[1] * dLinearized2) + (dArr3[2] * dLinearized3);
        double[] dArr4 = dArr[2];
        double d4 = (dArr4[0] * dLinearized) + (dArr4[1] * dLinearized2) + (dArr4[2] * dLinearized3);
        double[] dArr5 = WHITE_POINT_D65;
        double d5 = d2 / dArr5[0];
        double d6 = d3 / dArr5[1];
        double d7 = d4 / dArr5[2];
        double dLabF = labF(d5);
        double dLabF2 = labF(d6);
        return new double[]{(116.0d * dLabF2) - 16.0d, (dLabF - dLabF2) * 500.0d, (dLabF2 - labF(d7)) * 200.0d};
    }

    public static int argbFromLstar(double d2) {
        int iDelinearized = delinearized(yFromLstar(d2));
        return argbFromRgb(iDelinearized, iDelinearized, iDelinearized);
    }

    public static double lstarFromArgb(int i2) {
        return (labF(xyzFromArgb(i2)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    public static double yFromLstar(double d2) {
        return labInvf((d2 + 16.0d) / 116.0d) * 100.0d;
    }

    public static double lstarFromY(double d2) {
        return (labF(d2 / 100.0d) * 116.0d) - 16.0d;
    }

    public static double linearized(int i2) {
        double d2 = ((double) i2) / 255.0d;
        return (d2 <= 0.040449936d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d)) * 100.0d;
    }

    public static int delinearized(double d2) {
        double d3 = d2 / 100.0d;
        return MathUtils.clampInt(0, 255, (int) Math.round((d3 <= 0.0031308d ? d3 * 12.92d : (Math.pow(d3, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d));
    }

    public static double[] whitePointD65() {
        return WHITE_POINT_D65;
    }

    static double labF(double d2) {
        return d2 > 0.008856451679035631d ? Math.pow(d2, 0.3333333333333333d) : ((d2 * 903.2962962962963d) + 16.0d) / 116.0d;
    }
}
