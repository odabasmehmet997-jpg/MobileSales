package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Detector {
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};
    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        List<ResultPoint[]> detect = detect(z, blackMatrix);
        if (detect.isEmpty()) {
            blackMatrix = blackMatrix.clone();
            blackMatrix.rotate180();
            detect = detect(z, blackMatrix);
        }
        return new PDF417DetectorResult();
    }
    private static java.util.List<com.google.zxing.ResultPoint[]> detect(boolean r8, com.google.zxing.common.BitMatrix r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.detect(boolean, com.google.zxing.common.BitMatrix):java.util.List");
    }
    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i2, int i3) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i2, i3, START_PATTERN), INDEXES_START_PATTERN);
        ResultPoint resultPoint = resultPointArr[4];
        if (null != resultPoint) {
            i3 = (int) resultPoint.getX();
            i2 = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i2, i3, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }
    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            resultPointArr[iArr[i2]] = resultPointArr2[i2];
        }
    }
    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        boolean z;
        int i7;
        int i8;
        int i9 = i2;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] iArr2 = new int[iArr.length];
        int i10 = i4;
        while (true) {
            if (i10 >= i9) {
                z = false;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, i5, i10, i3, false, iArr, iArr2);
            if (null != findGuardPattern) {
                int i11 = i10;
                int[] iArr3 = findGuardPattern;
                while (0 < i11) {
                    int i12 = i11 - 1;
                    int[] findGuardPattern2 = findGuardPattern(bitMatrix, i5, i12, i3, false, iArr, iArr2);
                    if (null == findGuardPattern2) {
                        break;
                    }
                    iArr3 = findGuardPattern2;
                    i11 = i12;
                }
                float f2 = i11;
                resultPointArr[0] = new ResultPoint(iArr3[0], f2);
                resultPointArr[1] = new ResultPoint(iArr3[1], f2);
                z = true;
                i10 = i11;
            } else {
                i10 += 5;
            }
        }
        int i13 = i10 + 1;
        if (z) {
            int[] iArr4 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            int i14 = i13;
            int i15 = 0;
            while (true) {
                if (i14 >= i9) {
                    i7 = i15;
                    i8 = i14;
                    break;
                }
                i7 = i15;
                i8 = i14;
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, iArr4[0], i14, i3, false, iArr, iArr2);
                if (null == findGuardPattern3 || 5 <= Math.abs(iArr4[0] - findGuardPattern3[0]) || 5 <= Math.abs(iArr4[1] - findGuardPattern3[1])) {
                    if (25 < i7) {
                        break;
                    }
                    i15 = i7 + 1;
                } else {
                    iArr4 = findGuardPattern3;
                    i15 = 0;
                }
                i14 = i8 + 1;
            }
            i13 = i8 - (i7 + 1);
            float f3 = i13;
            resultPointArr[2] = new ResultPoint(iArr4[0], f3);
            resultPointArr[3] = new ResultPoint(iArr4[1], f3);
        }
        if (10 > i13 - i10) {
            for (i6 = 0; 4 > i6; i6++) {
                resultPointArr[i6] = null;
            }
        }
        return resultPointArr;
    }
    private static int[] findGuardPattern(BitMatrix bitMatrix, int i2, int i3, int i4, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i5 = 0;
        while (bitMatrix.get(i2, i3) && 0 < i2) {
            int i6 = i5 + 1;
            if (3 <= i5) {
                break;
            }
            i2--;
            i5 = i6;
        }
        int length = iArr.length;
        boolean z2 = z;
        int i7 = 0;
        int i8 = i2;
        while (i2 < i4) {
            if (bitMatrix.get(i2, i3) ^ z2) {
                iArr2[i7] = iArr2[i7] + 1;
            } else {
                int i9 = length - 1;
                if (i7 != i9) {
                    i7++;
                } else if (0.42f > Detector.patternMatchVariance(iArr2, iArr, 0.8f)) {
                    return new int[]{i8, i2};
                } else {
                    i8 += iArr2[0] + iArr2[1];
                    int i10 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i10);
                    iArr2[i10] = 0;
                    iArr2[i9] = 0;
                    i7--;
                }
                iArr2[i7] = 1;
                z2 = !z2;
            }
            i2++;
        }
        if (i7 != length - 1 || 0.42f <= Detector.patternMatchVariance(iArr2, iArr, 0.8f)) {
            return null;
        }
        return new int[]{i8, i2 - 1};
    }
    private static float patternMatchVariance(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = iArr[i5];
            float f7 = iArr2[i5] * f4;
            float f8 = i6;
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }
}
