package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Hct {
    private int argb;
    private double chroma;
    private double hue;
    private double tone;

    public static Hct from(double d2, double d3, double d4) {
        return new Hct(HctSolver.solveToInt(d2, d3, d4));
    }

    public static Hct fromInt(int i2) {
        return new Hct(i2);
    }

    private Hct(int i2) {
        setInternalState(i2);
    }

    public double getHue() {
        return this.hue;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getTone() {
        return this.tone;
    }

    public int toInt() {
        return this.argb;
    }

    public void setHue(double d2) {
        setInternalState(HctSolver.solveToInt(d2, this.chroma, this.tone));
    }

    public void setChroma(double d2) {
        setInternalState(HctSolver.solveToInt(this.hue, d2, this.tone));
    }

    public void setTone(double d2) {
        setInternalState(HctSolver.solveToInt(this.hue, this.chroma, d2));
    }

    public Hct inViewingConditions(ViewingConditions viewingConditions) {
        double[] dArrXyzInViewingConditions = Cam16.fromInt(toInt()).xyzInViewingConditions(viewingConditions, null);
        Cam16 cam16FromXyzInViewingConditions = Cam16.fromXyzInViewingConditions(dArrXyzInViewingConditions[0], dArrXyzInViewingConditions[1], dArrXyzInViewingConditions[2], ViewingConditions.DEFAULT);
        return from(cam16FromXyzInViewingConditions.getHue(), cam16FromXyzInViewingConditions.getChroma(), ColorUtils.lstarFromY(dArrXyzInViewingConditions[1]));
    }

    private void setInternalState(int i2) {
        this.argb = i2;
        Cam16 cam16FromInt = Cam16.fromInt(i2);
        this.hue = cam16FromInt.getHue();
        this.chroma = cam16FromInt.getChroma();
        this.tone = ColorUtils.lstarFromArgb(i2);
    }
}
