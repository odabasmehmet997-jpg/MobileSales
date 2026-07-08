package com.google.zxing.common;

public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9, final float f10) {
        a11 = f2;
        a12 = f5;
        a13 = f8;
        a21 = f3;
        a22 = f6;
        a23 = f9;
        a31 = f4;
        a32 = f7;
        a33 = f10;
    }
    public static PerspectiveTransform quadrilateralToQuadrilateral(final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9, final float f10, final float f11, final float f12, final float f13, final float f14, final float f15, final float f16, final float f17) {
        return PerspectiveTransform.squareToQuadrilateral(f10, f11, f12, f13, f14, f15, f16, f17).times(PerspectiveTransform.quadrilateralToSquare(f2, f3, f4, f5, f6, f7, f8, f9));
    }
    public void transformPoints(final float[] fArr) {
        final float[] fArr2 = fArr;
        final int length = fArr2.length;
        final float f2 = a11;
        final float f3 = a12;
        final float f4 = a13;
        final float f5 = a21;
        final float f6 = a22;
        final float f7 = a23;
        final float f8 = a31;
        final float f9 = a32;
        final float f10 = a33;
        for (int i2 = 0; i2 < length; i2 += 2) {
            final float f11 = fArr2[i2];
            final int i3 = i2 + 1;
            final float f12 = fArr2[i3];
            final float f13 = (f4 * f11) + (f7 * f12) + f10;
            fArr2[i2] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
            fArr2[i3] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
        }
    }
    public static PerspectiveTransform squareToQuadrilateral(final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9) {
        final float f10 = ((f2 - f4) + f6) - f8;
        final float f11 = ((f3 - f5) + f7) - f9;
        if (0.0f == f10 && 0.0f == f11) {
            return new PerspectiveTransform(f4 - f2, f6 - f4, f2, f5 - f3, f7 - f5, f3, 0.0f, 0.0f, 1.0f);
        }
        final float f12 = f4 - f6;
        final float f13 = f8 - f6;
        final float f14 = f5 - f7;
        final float f15 = f9 - f7;
        final float f16 = (f12 * f15) - (f13 * f14);
        final float f17 = ((f15 * f10) - (f13 * f11)) / f16;
        final float f18 = ((f12 * f11) - (f10 * f14)) / f16;
        return new PerspectiveTransform((f17 * f4) + (f4 - f2), (f18 * f8) + (f8 - f2), f2, (f5 - f3) + (f17 * f5), (f9 - f3) + (f18 * f9), f3, f17, f18, 1.0f);
    }
    public static PerspectiveTransform quadrilateralToSquare(final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9) {
        return PerspectiveTransform.squareToQuadrilateral(f2, f3, f4, f5, f6, f7, f8, f9).buildAdjoint();
    }
    public PerspectiveTransform buildAdjoint() {
        final float f2 = a22;
        final float f3 = a33;
        final float f4 = a23;
        final float f5 = a32;
        final float f6 = (f2 * f3) - (f4 * f5);
        final float f7 = a31;
        final float f8 = a21;
        final float f9 = (f4 * f7) - (f8 * f3);
        final float f10 = (f8 * f5) - (f2 * f7);
        final float f11 = a13;
        final float f12 = a12;
        final float f13 = (f11 * f5) - (f12 * f3);
        final float f14 = a11;
        return new PerspectiveTransform(f6, f9, f10, f13, (f3 * f14) - (f11 * f7), (f7 * f12) - (f5 * f14), (f12 * f4) - (f11 * f2), (f11 * f8) - (f4 * f14), (f14 * f2) - (f12 * f8));
    }
    public PerspectiveTransform times(final PerspectiveTransform perspectiveTransform) {
        final PerspectiveTransform perspectiveTransform2 = perspectiveTransform;
        final float f2 = a11;
        final float f3 = perspectiveTransform2.a11;
        final float f4 = a21;
        final float f5 = perspectiveTransform2.a12;
        final float f6 = a31;
        final float f7 = perspectiveTransform2.a13;
        final float f8 = (f2 * f3) + (f4 * f5) + (f6 * f7);
        final float f9 = perspectiveTransform2.a21;
        final float f10 = perspectiveTransform2.a22;
        final float f11 = perspectiveTransform2.a23;
        final float f12 = (f2 * f9) + (f4 * f10) + (f6 * f11);
        final float f13 = perspectiveTransform2.a31;
        final float f14 = perspectiveTransform2.a32;
        final float f15 = perspectiveTransform2.a33;
        final float f16 = (f2 * f13) + (f4 * f14) + (f6 * f15);
        final float f17 = a12;
        final float f18 = f16;
        final float f19 = a22;
        final float f20 = f12;
        final float f21 = a32;
        final float f22 = (f17 * f3) + (f19 * f5) + (f21 * f7);
        final float f23 = (f21 * f15) + (f17 * f13) + (f19 * f14);
        final float f24 = a13;
        final float f25 = a23;
        final float f26 = (f3 * f24) + (f5 * f25);
        final float f27 = a33;
        final float f28 = (f24 * f13) + (f25 * f14) + (f27 * f15);
        return new PerspectiveTransform(f8, f20, f18, f22, (f17 * f9) + (f19 * f10) + (f21 * f11), f23, (f7 * f27) + f26, (f9 * f24) + (f10 * f25) + (f11 * f27), f28);
    }
}
