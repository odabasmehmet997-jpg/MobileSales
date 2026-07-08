package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ContrastCurve {
    private final double high;
    private final double low;
    private final double medium;
    private final double normal;

    public ContrastCurve(double d2, double d3, double d4, double d5) {
        this.low = d2;
        this.normal = d3;
        this.medium = d4;
        this.high = d5;
    }

    public double getContrast(double d2) {
        if (d2 <= -1.0d) {
            return this.low;
        }
        if (d2 < 0.0d) {
            return MathUtils.lerp(this.low, this.normal, (d2 - (-1.0d)));
        }
        if (d2 < 0.5d) {
            return MathUtils.lerp(this.normal, this.medium, (d2 - 0.0d) / 0.5d);
        }
        if (d2 < 1.0d) {
            return MathUtils.lerp(this.medium, this.high, (d2 - 0.5d) / 0.5d);
        }
        return this.high;
    }
}
