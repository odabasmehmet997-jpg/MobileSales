package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class TonalPalette {
    Map<Integer, Integer> cache = new HashMap();
    double chroma;
    double hue;
    Hct keyColor;

    public static TonalPalette fromInt(int i2) {
        return fromHct(Hct.fromInt(i2));
    }

    public static TonalPalette fromHct(Hct hct) {
        return new TonalPalette(hct.getHue(), hct.getChroma(), hct);
    }

    public static TonalPalette fromHueAndChroma(double d2, double d3) {
        return new TonalPalette(d2, d3, createKeyColor(d2, d3));
    }

    private TonalPalette(double d2, double d3, Hct hct) {
        this.hue = d2;
        this.chroma = d3;
        this.keyColor = hct;
    }

    private static Hct createKeyColor(double d2, double d3) {
        Hct hctFrom = Hct.from(d2, d3, 50.0d);
        Hct hct = hctFrom;
        double dAbs = Math.abs(hctFrom.getChroma() - d3);
        for (double d4 = 1.0d; d4 < 50.0d && Math.round(d3) != Math.round(hct.getChroma()); d4 += 1.0d) {
            Hct hctFrom2 = Hct.from(d2, d3, 50.0d + d4);
            double dAbs2 = Math.abs(hctFrom2.getChroma() - d3);
            if (dAbs2 < dAbs) {
                hct = hctFrom2;
                dAbs = dAbs2;
            }
            Hct hctFrom3 = Hct.from(d2, d3, 50.0d - d4);
            double dAbs3 = Math.abs(hctFrom3.getChroma() - d3);
            if (dAbs3 < dAbs) {
                hct = hctFrom3;
                dAbs = dAbs3;
            }
        }
        return hct;
    }

    public int tone(int i2) {
        Integer numValueOf = this.cache.get(Integer.valueOf(i2));
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(Hct.from(this.hue, this.chroma, i2).toInt());
            this.cache.put(Integer.valueOf(i2), numValueOf);
        }
        return numValueOf.intValue();
    }

    public Hct getHct(double d2) {
        return Hct.from(this.hue, this.chroma, d2);
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public Hct getKeyColor() {
        return this.keyColor;
    }
}
