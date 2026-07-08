package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = defaultWithBackgroundLstar(50.0d);
    private final double aw;

    /*  INFO: renamed from: c, reason: collision with root package name */
    private final double f16c;
    private final double fl;
    private final double flRoot;
    private final double n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;
    private final double z;

    public double getAw() {
        return this.aw;
    }

    public double getN() {
        return this.n;
    }

    public double getNbb() {
        return this.nbb;
    }

    double getNcb() {
        return this.ncb;
    }

    double getC() {
        return this.f16c;
    }

    double getNc() {
        return this.nc;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    double getZ() {
        return this.z;
    }

    public static ViewingConditions make(double[] dArr, double d2, double d3, double d4, boolean z) {
        double dLerp;
        double dMax = Math.max(0.1d, d3);
        double[][] dArr2 = Cam16.XYZ_TO_CAM16RGB;
        double d5 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d6 = dArr3[0] * d5;
        double d7 = dArr[1];
        double d8 = d6 + (dArr3[1] * d7);
        double d9 = dArr[2];
        double d10 = d8 + (dArr3[2] * d9);
        double[] dArr4 = dArr2[1];
        double d11 = (dArr4[0] * d5) + (dArr4[1] * d7) + (dArr4[2] * d9);
        double[] dArr5 = dArr2[2];
        double d12 = (d5 * dArr5[0]) + (d7 * dArr5[1]) + (d9 * dArr5[2]);
        double d13 = (d4 / 10.0d) + 0.8d;
        if (d13 >= 0.9d) {
            dLerp = MathUtils.lerp(0.59d, 0.69d, (d13 - 0.9d) * 10.0d);
        } else {
            dLerp = MathUtils.lerp(0.525d, 0.59d, (d13 - 0.8d) * 10.0d);
        }
        double d14 = dLerp;
        double dClampDouble = MathUtils.clampDouble(0.0d, 1.0d, z ? 1.0d : (1.0d - (Math.exp(((-d2) - 42.0d) / 92.0d) * 0.2777777777777778d)) * d13);
        double[] dArr6 = {(((100.0d / d10) * dClampDouble) + 1.0d) - dClampDouble, (((100.0d / d11) * dClampDouble) + 1.0d) - dClampDouble, (((100.0d / d12) * dClampDouble) + 1.0d) - dClampDouble};
        double d15 = 5.0d * d2;
        double d16 = 1.0d / (d15 + 1.0d);
        double d17 = d16 * d16 * d16 * d16;
        double d18 = 1.0d - d17;
        double dCbrt = (d17 * d2) + (0.1d * d18 * d18 * Math.cbrt(d15));
        double dYFromLstar = ColorUtils.yFromLstar(dMax) / dArr[1];
        double dSqrt = Math.sqrt(dYFromLstar) + 1.48d;
        double dPow = 0.725d / Math.pow(dYFromLstar, 0.2d);
        double[] dArr7 = {Math.pow(((dArr6[0] * dCbrt) * d10) / 100.0d, 0.42d), Math.pow(((dArr6[1] * dCbrt) * d11) / 100.0d, 0.42d), Math.pow(((dArr6[2] * dCbrt) * d12) / 100.0d, 0.42d)};
        double d19 = dArr7[0];
        double d20 = (d19 * 400.0d) / (d19 + 27.13d);
        double d21 = dArr7[1];
        double d22 = (d21 * 400.0d) / (d21 + 27.13d);
        double d23 = dArr7[2];
        double[] dArr8 = {d20, d22, (400.0d * d23) / (d23 + 27.13d)};
        return new ViewingConditions(dYFromLstar, ((dArr8[0] * 2.0d) + dArr8[1] + (dArr8[2] * 0.05d)) * dPow, dPow, dPow, d14, d13, dArr6, dCbrt, Math.pow(dCbrt, 0.25d), dSqrt);
    }

    public static ViewingConditions defaultWithBackgroundLstar(double d2) {
        return make(ColorUtils.whitePointD65(), (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, d2, 2.0d, false);
    }

    private ViewingConditions(double d2, double d3, double d4, double d5, double d6, double d7, double[] dArr, double d8, double d9, double d10) {
        this.n = d2;
        this.aw = d3;
        this.nbb = d4;
        this.ncb = d5;
        this.f16c = d6;
        this.nc = d7;
        this.rgbD = dArr;
        this.fl = d8;
        this.flRoot = d9;
        this.z = d10;
    }
}
