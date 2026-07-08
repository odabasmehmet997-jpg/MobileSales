package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Cam16 {
    private final double astar;
    private final double bstar;
    private final double chroma;
    private final double hue;

    /*  INFO: renamed from: j, reason: collision with root package name */
    private final double f15j;
    private final double jstar;
    private final double m;
    private final double q;
    private final double s;
    private final double[] tempArray = {0.0d, 0.0d, 0.0d};
    static final double[][] XYZ_TO_CAM16RGB = {new double[]{0.401288d, 0.650173d, -0.051461d}, new double[]{-0.250268d, 1.204414d, 0.045854d}, new double[]{-0.002079d, 0.048952d, 0.953127d}};
    static final double[][] CAM16RGB_TO_XYZ = {new double[]{1.8620678d, -1.0112547d, 0.14918678d}, new double[]{0.38752654d, 0.62144744d, -0.00897398d}, new double[]{-0.0158415d, -0.03412294d, 1.0499644d}};

    double distance(Cam16 cam16) {
        double jstar = getJstar() - cam16.getJstar();
        double astar = getAstar() - cam16.getAstar();
        double bstar = getBstar() - cam16.getBstar();
        return Math.pow(Math.sqrt((jstar * jstar) + (astar * astar) + (bstar * bstar)), 0.63d) * 1.41d;
    }

    public double getHue() {
        return this.hue;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getJ() {
        return this.f15j;
    }

    public double getQ() {
        return this.q;
    }

    public double getM() {
        return this.m;
    }

    public double getS() {
        return this.s;
    }

    public double getJstar() {
        return this.jstar;
    }

    public double getAstar() {
        return this.astar;
    }

    public double getBstar() {
        return this.bstar;
    }

    private Cam16(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.hue = d2;
        this.chroma = d3;
        this.f15j = d4;
        this.q = d5;
        this.m = d6;
        this.s = d7;
        this.jstar = d8;
        this.astar = d9;
        this.bstar = d10;
    }

    public static Cam16 fromInt(int i2) {
        return fromIntInViewingConditions(i2, ViewingConditions.DEFAULT);
    }

    /*  WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at .core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at .core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at .core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at .core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at .core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at .core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at .core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at .core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at .core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at .core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at .core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    static Cam16 fromIntInViewingConditions(int i2, ViewingConditions viewingConditions) {
        double dLinearized = ColorUtils.linearized((16711680 & i2) >> 16);
        double dLinearized2 = ColorUtils.linearized((65280 & i2) >> 8);
        double dLinearized3 = ColorUtils.linearized(i2 & 255);
        return fromXyzInViewingConditions((0.41233895d * dLinearized) + (0.35762064d * dLinearized2) + (0.18051042d * dLinearized3), (0.2126d * dLinearized) + (0.7152d * dLinearized2) + (0.0722d * dLinearized3), (dLinearized * 0.01932141d) + (dLinearized2 * 0.11916382d) + (dLinearized3 * 0.95034478d), viewingConditions);
    }

    static Cam16 fromXyzInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double[][] dArr = XYZ_TO_CAM16RGB;
        double[] dArr2 = dArr[0];
        double d5 = (dArr2[0] * d2) + (dArr2[1] * d3) + (dArr2[2] * d4);
        double[] dArr3 = dArr[1];
        double d6 = (dArr3[0] * d2) + (dArr3[1] * d3) + (dArr3[2] * d4);
        double[] dArr4 = dArr[2];
        double d7 = (dArr4[0] * d2) + (dArr4[1] * d3) + (dArr4[2] * d4);
        double d8 = viewingConditions.getRgbD()[0] * d5;
        double d9 = viewingConditions.getRgbD()[1] * d6;
        double d10 = viewingConditions.getRgbD()[2] * d7;
        double dPow = Math.pow((viewingConditions.getFl() * Math.abs(d8)) / 100.0d, 0.42d);
        double dPow2 = Math.pow((viewingConditions.getFl() * Math.abs(d9)) / 100.0d, 0.42d);
        double dPow3 = Math.pow((viewingConditions.getFl() * Math.abs(d10)) / 100.0d, 0.42d);
        double dSignum = ((Math.signum(d8) * 400.0d) * dPow) / (dPow + 27.13d);
        double dSignum2 = ((Math.signum(d9) * 400.0d) * dPow2) / (dPow2 + 27.13d);
        double dSignum3 = ((Math.signum(d10) * 400.0d) * dPow3) / (dPow3 + 27.13d);
        double d11 = (((dSignum * 11.0d) + ((-12.0d) * dSignum2)) + dSignum3) / 11.0d;
        double d12 = ((dSignum + dSignum2) - (dSignum3 * 2.0d)) / 9.0d;
        double d13 = dSignum2 * 20.0d;
        double d14 = (((dSignum * 20.0d) + d13) + (21.0d * dSignum3)) / 20.0d;
        double d15 = (((dSignum * 40.0d) + d13) + dSignum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d12, d11));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double d16 = degrees;
        double radians = Math.toRadians(d16);
        double dPow4 = Math.pow((d15 * viewingConditions.getNbb()) / viewingConditions.getAw(), viewingConditions.getC() * viewingConditions.getZ()) * 100.0d;
        double d17 = dPow4 / 100.0d;
        double c2 = (4.0d / viewingConditions.getC()) * Math.sqrt(d17) * (viewingConditions.getAw() + 4.0d) * viewingConditions.getFlRoot();
        double dPow5 = Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d) * Math.pow(((((((Math.cos(Math.toRadians(d16 < 20.14d ? d16 + 360.0d : d16) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * viewingConditions.getNc()) * viewingConditions.getNcb()) * Math.hypot(d11, d12)) / (d14 + 0.305d), 0.9d);
        double dSqrt = Math.sqrt(d17) * dPow5;
        double flRoot = dSqrt * viewingConditions.getFlRoot();
        double dLog1p = Math.log1p(flRoot * 0.0228d) * 43.859649122807014d;
        return new Cam16(d16, dSqrt, dPow4, c2, flRoot, Math.sqrt((dPow5 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0d)) * 50.0d, (1.7000000000000002d * dPow4) / ((0.007d * dPow4) + 1.0d), dLog1p * Math.cos(radians), dLog1p * Math.sin(radians));
    }

    static Cam16 fromJch(double d2, double d3, double d4) {
        return fromJchInViewingConditions(d2, d3, d4, ViewingConditions.DEFAULT);
    }

    private static Cam16 fromJchInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double d5 = d2 / 100.0d;
        double c2 = (4.0d / viewingConditions.getC()) * Math.sqrt(d5) * (viewingConditions.getAw() + 4.0d) * viewingConditions.getFlRoot();
        double flRoot = d3 * viewingConditions.getFlRoot();
        double dSqrt = Math.sqrt(((d3 / Math.sqrt(d5)) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0d)) * 50.0d;
        double radians = Math.toRadians(d4);
        double d6 = (1.7000000000000002d * d2) / ((0.007d * d2) + 1.0d);
        double dLog1p = 43.859649122807014d * Math.log1p(flRoot * 0.0228d);
        return new Cam16(d4, d3, d2, c2, flRoot, dSqrt, d6, Math.cos(radians) * dLog1p, Math.sin(radians) * dLog1p);
    }

    public static Cam16 fromUcs(double d2, double d3, double d4) {
        return fromUcsInViewingConditions(d2, d3, d4, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromUcsInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double dExpm1 = (Math.expm1(Math.hypot(d3, d4) * 0.0228d) / 0.0228d) / viewingConditions.getFlRoot();
        double dAtan2 = Math.atan2(d4, d3) * 57.29577951308232d;
        if (dAtan2 < 0.0d) {
            dAtan2 += 360.0d;
        }
        return fromJchInViewingConditions(d2 / (1.0d - ((d2 - 100.0d) * 0.007d)), dExpm1, dAtan2, viewingConditions);
    }

    public int toInt() {
        return viewed(ViewingConditions.DEFAULT);
    }

    int viewed(ViewingConditions viewingConditions) {
        double[] dArrXyzInViewingConditions = xyzInViewingConditions(viewingConditions, this.tempArray);
        return ColorUtils.argbFromXyz(dArrXyzInViewingConditions[0], dArrXyzInViewingConditions[1], dArrXyzInViewingConditions[2]);
    }

    double[] xyzInViewingConditions(ViewingConditions viewingConditions, double[] dArr) {
        double dPow = Math.pow(((getChroma() == 0.0d || getJ() == 0.0d) ? 0.0d : getChroma() / Math.sqrt(getJ() / 100.0d)) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double radians = Math.toRadians(getHue());
        double dCos = (Math.cos(2.0d + radians) + 3.8d) * 0.25d;
        double aw = viewingConditions.getAw() * Math.pow(getJ() / 100.0d, (1.0d / viewingConditions.getC()) / viewingConditions.getZ());
        double nc = dCos * 3846.153846153846d * viewingConditions.getNc() * viewingConditions.getNcb();
        double nbb = aw / viewingConditions.getNbb();
        double dSin = Math.sin(radians);
        double dCos2 = Math.cos(radians);
        double d2 = (((0.305d + nbb) * 23.0d) * dPow) / (((nc * 23.0d) + ((11.0d * dPow) * dCos2)) + ((dPow * 108.0d) * dSin));
        double d3 = dCos2 * d2;
        double d4 = d2 * dSin;
        double d5 = nbb * 460.0d;
        double d6 = (((451.0d * d3) + d5) + (288.0d * d4)) / 1403.0d;
        double d7 = ((d5 - (891.0d * d3)) - (261.0d * d4)) / 1403.0d;
        double d8 = ((d5 - (d3 * 220.0d)) - (d4 * 6300.0d)) / 1403.0d;
        double dSignum = Math.signum(d6) * (100.0d / viewingConditions.getFl()) * Math.pow(Math.max(0.0d, (Math.abs(d6) * 27.13d) / (400.0d - Math.abs(d6))), 2.380952380952381d);
        double dSignum2 = Math.signum(d7) * (100.0d / viewingConditions.getFl()) * Math.pow(Math.max(0.0d, (Math.abs(d7) * 27.13d) / (400.0d - Math.abs(d7))), 2.380952380952381d);
        double dSignum3 = Math.signum(d8) * (100.0d / viewingConditions.getFl()) * Math.pow(Math.max(0.0d, (Math.abs(d8) * 27.13d) / (400.0d - Math.abs(d8))), 2.380952380952381d);
        double d9 = dSignum / viewingConditions.getRgbD()[0];
        double d10 = dSignum2 / viewingConditions.getRgbD()[1];
        double d11 = dSignum3 / viewingConditions.getRgbD()[2];
        double[][] dArr2 = CAM16RGB_TO_XYZ;
        double[] dArr3 = dArr2[0];
        double d12 = (dArr3[0] * d9) + (dArr3[1] * d10) + (dArr3[2] * d11);
        double[] dArr4 = dArr2[1];
        double d13 = (dArr4[0] * d9) + (dArr4[1] * d10) + (dArr4[2] * d11);
        double[] dArr5 = dArr2[2];
        double d14 = (d9 * dArr5[0]) + (d10 * dArr5[1]) + (d11 * dArr5[2]);
        if (dArr != null) {
            dArr[0] = d12;
            dArr[1] = d13;
            dArr[2] = d14;
            return dArr;
        }
        return new double[]{d12, d13, d14};
    }
}
