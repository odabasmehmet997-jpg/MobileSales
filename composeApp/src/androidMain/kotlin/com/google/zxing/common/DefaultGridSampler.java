package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public BitMatrix sampleGrid(final BitMatrix bitMatrix, final int i2, final int i3, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9, final float f10, final float f11, final float f12, final float f13, final float f14, final float f15, final float f16, final float f17) throws NotFoundException {
        final BitMatrix bitMatrix2 = bitMatrix;
        final int i4 = i2;
        final int i5 = i3;
        return this.sampleGrid(bitMatrix, i2, i3, PerspectiveTransform.quadrilateralToQuadrilateral(f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17));
    }

    public BitMatrix sampleGrid(final BitMatrix bitMatrix, final int i2, final int i3, final PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (0 >= i2 || 0 >= i3) {
            throw NotFoundException.getNotFoundInstance();
        }
        final BitMatrix bitMatrix2 = new BitMatrix(i2, i3);
        final int i4 = i2 * 2;
        final float[] fArr = new float[i4];
        for (int i5 = 0; i5 < i3; i5++) {
            final float f2 = i5 + 0.5f;
            for (int i6 = 0; i6 < i4; i6 += 2) {
                fArr[i6] = (i6 / 2) + 0.5f;
                fArr[i6 + 1] = f2;
            }
            perspectiveTransform.transformPoints(fArr);
            checkAndNudgePoints(bitMatrix, fArr);
            int i7 = 0;
            while (i7 < i4) {
                try {
                    if (bitMatrix.get((int) fArr[i7], (int) fArr[i7 + 1])) {
                        bitMatrix2.set(i7 / 2, i5);
                    }
                    i7 += 2;
                } catch (final ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
