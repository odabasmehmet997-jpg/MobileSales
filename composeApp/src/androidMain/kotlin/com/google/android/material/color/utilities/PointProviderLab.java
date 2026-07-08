package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class PointProviderLab implements PointProvider {
    @Override // com.google.android.material.color.utilities.PointProvider
    public double[] fromInt(int i2) {
        double[] dArrLabFromArgb = ColorUtils.labFromArgb(i2);
        return new double[]{dArrLabFromArgb[0], dArrLabFromArgb[1], dArrLabFromArgb[2]};
    }

    @Override // com.google.android.material.color.utilities.PointProvider
    public int toInt(double[] dArr) {
        return ColorUtils.argbFromLab(dArr[0], dArr[1], dArr[2]);
    }

    @Override // com.google.android.material.color.utilities.PointProvider
    public double distance(double[] dArr, double[] dArr2) {
        double d2 = dArr[0] - dArr2[0];
        double d3 = dArr[1] - dArr2[1];
        double d4 = dArr[2] - dArr2[2];
        return (d2 * d2) + (d3 * d3) + (d4 * d4);
    }
}
