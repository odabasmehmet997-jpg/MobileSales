package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(final BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(final BitMatrix bitMatrix, final int i2, final int i3, final int i4) throws NotFoundException {
        image = bitMatrix;
        final int height2 = bitMatrix.getHeight();
        height = height2;
        final int width2 = bitMatrix.getWidth();
        width = width2;
        final int i5 = i2 / 2;
        final int i6 = i3 - i5;
        leftInit = i6;
        final int i7 = i3 + i5;
        rightInit = i7;
        final int i8 = i4 - i5;
        upInit = i8;
        final int i9 = i4 + i5;
        downInit = i9;
        if (0 > i8 || 0 > i6 || i9 >= height2 || i7 >= width2) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i2 = leftInit;
        int i3 = rightInit;
        int i4 = upInit;
        int i5 = downInit;
        boolean z = false;
        int i6 = 1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = true;
        while (true) {
            if (!z7) {
                break;
            }
            boolean z8 = false;
            boolean z9 = true;
            while (true) {
                if ((z9 || !z2) && i3 < width) {
                    z9 = this.containsBlackPoint(i4, i5, i3, false);
                    if (z9) {
                        i3++;
                        z2 = true;
                        z8 = true;
                    } else if (!z2) {
                        i3++;
                    }
                }
            }
            if (i3 >= width) {
                break;
            }
            boolean z10 = true;
            while (true) {
                if ((z10 || !z3) && i5 < height) {
                    z10 = this.containsBlackPoint(i2, i3, i5, true);
                    if (z10) {
                        i5++;
                        z3 = true;
                        z8 = true;
                    } else if (!z3) {
                        i5++;
                    }
                }
            }
            if (i5 >= height) {
                break;
            }
            boolean z11 = true;
            while (true) {
                if ((z11 || !z4) && 0 <= i2) {
                    z11 = this.containsBlackPoint(i4, i5, i2, false);
                    if (z11) {
                        i2--;
                        z4 = true;
                        z8 = true;
                    } else if (!z4) {
                        i2--;
                    }
                }
            }
            if (0 > i2) {
                break;
            }
            z7 = z8;
            boolean z12 = true;
            while (true) {
                if ((z12 || !z6) && 0 <= i4) {
                    z12 = this.containsBlackPoint(i2, i3, i4, true);
                    if (z12) {
                        i4--;
                        z7 = true;
                        z6 = true;
                    } else if (!z6) {
                        i4--;
                    }
                }
            }
            if (0 > i4) {
                break;
            } else if (z7) {
                z5 = true;
            }
        }
        z = true;
        if (z || !z5) {
            throw NotFoundException.getNotFoundInstance();
        }
        final int i7 = i3 - i2;
        ResultPoint resultPoint = null;
        int i8 = 1;
        ResultPoint resultPoint2 = null;
        while (null == resultPoint2 && i8 < i7) {
            resultPoint2 = this.getBlackPointOnSegment(i2, (i5 - i8), (i2 + i8), i5);
            i8++;
        }
        if (null != resultPoint2) {
            int i9 = 1;
            ResultPoint resultPoint3 = null;
            while (null == resultPoint3 && i9 < i7) {
                resultPoint3 = this.getBlackPointOnSegment(i2, (i4 + i9), (i2 + i9), i4);
                i9++;
            }
            if (null != resultPoint3) {
                int i10 = 1;
                ResultPoint resultPoint4 = null;
                while (null == resultPoint4 && i10 < i7) {
                    resultPoint4 = this.getBlackPointOnSegment(i3, (i4 + i10), (i3 - i10), i4);
                    i10++;
                }
                if (null != resultPoint4) {
                    while (null == resultPoint && i6 < i7) {
                        resultPoint = this.getBlackPointOnSegment(i3, (i5 - i6), (i3 - i6), i5);
                        i6++;
                    }
                    if (null != resultPoint) {
                        return this.centerEdges(resultPoint, resultPoint2, resultPoint4, resultPoint3);
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint getBlackPointOnSegment(final float f2, final float f3, final float f4, final float f5) {
        final int round = MathUtils.round(MathUtils.distance(f2, f3, f4, f5));
        final float f6 = round;
        final float f7 = (f4 - f2) / f6;
        final float f8 = (f5 - f3) / f6;
        for (int i2 = 0; i2 < round; i2++) {
            final float f9 = i2;
            final int round2 = MathUtils.round((f9 * f7) + f2);
            final int round3 = MathUtils.round((f9 * f8) + f3);
            if (image.get(round2, round3)) {
                return new ResultPoint(round2, round3);
            }
        }
        return null;
    }

    private ResultPoint[] centerEdges(final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4) {
        final float x = resultPoint.getX();
        final float y = resultPoint.getY();
        final float x2 = resultPoint2.getX();
        final float y2 = resultPoint2.getY();
        final float x3 = resultPoint3.getX();
        final float y3 = resultPoint3.getY();
        final float x4 = resultPoint4.getX();
        final float y4 = resultPoint4.getY();
        if (x < width / 2.0f) {
            return new ResultPoint[]{new ResultPoint(x4 - 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 + 1.0f), new ResultPoint(x3 - 1.0f, y3 - 1.0f), new ResultPoint(x + 1.0f, y - 1.0f)};
        }
        return new ResultPoint[]{new ResultPoint(x4 + 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 - 1.0f), new ResultPoint(x3 - 1.0f, y3 + 1.0f), new ResultPoint(x - 1.0f, y - 1.0f)};
    }

    private boolean containsBlackPoint(int i2, final int i3, final int i4, final boolean z) {
        if (z) {
            while (i2 <= i3) {
                if (image.get(i2, i4)) {
                    return true;
                }
                i2++;
            }
            return false;
        }
        while (i2 <= i3) {
            if (image.get(i4, i2)) {
                return true;
            }
            i2++;
        }
        return false;
    }
}
