package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;
    public void reset() {
    }
    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return this.decode(binaryBitmap, null);
    }
    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return this.doDecode(binaryBitmap, map);
        } catch (final NotFoundException e2) {
            if (null == map || !map.containsKey(DecodeHintType.TRY_HARDER) || !binaryBitmap.isRotateSupported()) {
                throw e2;
            }
            final BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            final Result doDecode = this.doDecode(rotateCounterClockwise, map);
            final Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
            int i2 = 270;
            if (null != resultMetadata) {
                final ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                if (resultMetadata.containsKey(resultMetadataType)) {
                    i2 = (((Integer) resultMetadata.get(resultMetadataType)).intValue() + 270) % 360;
                }
            }
            doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i2));
            final ResultPoint[] resultPoints = doDecode.getResultPoints();
            if (null != resultPoints) {
                final int height = rotateCounterClockwise.getHeight();
                for (int i3 = 0; i3 < resultPoints.length; i3++) {
                    resultPoints[i3] = new ResultPoint((height - resultPoints[i3].getY()) - 1.0f, resultPoints[i3].getX());
                }
            }
            return doDecode;
        }
    }
    private com.google.zxing.Result doDecode(final com.google.zxing.BinaryBitmap r22, final java.util.Map<com.google.zxing.DecodeHintType, ?> r23) throws com.google.zxing.NotFoundException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }
    protected static void recordPattern(final BitArray bitArray, int i2, final int[] iArr) throws NotFoundException {
        final int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        final int size = bitArray.getSize();
        if (i2 < size) {
            boolean z = !bitArray.get(i2);
            while (i2 < size) {
                if (bitArray.get(i2) == z) {
                    i3++;
                    if (i3 == length) {
                        break;
                    }
                    iArr[i3] = 1;
                    z = !z;
                } else {
                    iArr[i3] = iArr[i3] + 1;
                }
                i2++;
            }
            if (i3 == length) {
                return;
            }
            if (i3 != length - 1 || i2 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }
    protected static void recordPatternInReverse(final BitArray bitArray, int i2, final int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i2);
        while (0 < i2 && 0 <= length) {
            i2--;
            if (bitArray.get(i2) != z) {
                length--;
                z = !z;
            }
        }
        if (0 > length) {
            OneDReader.recordPattern(bitArray, i2 + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }
    protected static float patternMatchVariance(final int[] iArr, final int[] iArr2, final float f2) {
        final int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        final float f3 = i2;
        final float f4 = f3 / i3;
        final float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            final int i6 = iArr[i5];
            final float f7 = iArr2[i5] * f4;
            final float f8 = i6;
            final float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }
}
