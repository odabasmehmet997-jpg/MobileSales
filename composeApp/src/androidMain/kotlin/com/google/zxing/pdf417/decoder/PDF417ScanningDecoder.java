package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PDF417ScanningDecoder {
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private static boolean checkCodewordSkew(final int i2, final int i3, final int i4) {
        return i3 + -2 <= i2 && i2 <= i4 + 2;
    }

    private static int getNumberOfECCodeWords(final int i2) {
        return 2 << i2;
    }

    public static DecoderResult decode(final BitMatrix bitMatrix, final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4, final int i2, final int i3) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultColumn detectionResultColumn;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        DetectionResult detectionResult = null;
        int i8 = 0;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        while (true) {
            if (2 <= i8) {
                break;
            }
            if (null != resultPoint) {
                detectionResultRowIndicatorColumn = PDF417ScanningDecoder.getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i2, i3);
            }
            if (null != resultPoint3) {
                detectionResultRowIndicatorColumn2 = PDF417ScanningDecoder.getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i2, i3);
            }
            detectionResult = PDF417ScanningDecoder.merge(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2);
            if (null == detectionResult) {
                throw NotFoundException.getNotFoundInstance();
            } else if (0 != i8 || null == detectionResult.getBoundingBox() || (detectionResult.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResult.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                detectionResult.setBoundingBox(boundingBox);
            } else {
                boundingBox = detectionResult.getBoundingBox();
                i8++;
            }
        }
        detectionResult.setBoundingBox(boundingBox);
        final int barcodeColumnCount = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, detectionResultRowIndicatorColumn);
        detectionResult.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn2);
        final boolean z = null != detectionResultRowIndicatorColumn;
        int i9 = i2;
        int i10 = i3;
        for (int i11 = 1; i11 <= barcodeColumnCount; i11++) {
            final int i12 = z ? i11 : barcodeColumnCount - i11;
            if (null == detectionResult.getDetectionResultColumn(i12)) {
                if (0 == i12 || i12 == barcodeColumnCount) {
                    detectionResultColumn = new DetectionResultRowIndicatorColumn(boundingBox, 0 == i12);
                } else {
                    detectionResultColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.setDetectionResultColumn(i12, detectionResultColumn);
                int i13 = -1;
                int minY = boundingBox.getMinY();
                int i14 = -1;
                while (minY <= boundingBox.getMaxY()) {
                    final int startColumn = PDF417ScanningDecoder.getStartColumn(detectionResult, i12, minY, z);
                    if (0 <= startColumn && startColumn <= boundingBox.getMaxX()) {
                        i7 = startColumn;
                    } else if (i14 != i13) {
                        i7 = i14;
                    } else {
                        i6 = i14;
                        i5 = minY;
                        i4 = i13;
                        i14 = i6;
                        minY = i5 + 1;
                        i13 = i4;
                    }
                    i6 = i14;
                    final int i15 = minY;
                    i4 = i13;
                    final Codeword detectCodeword = PDF417ScanningDecoder.detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i7, i15, i9, i10);
                    i5 = i15;
                    if (null != detectCodeword) {
                        detectionResultColumn.setCodeword(i5, detectCodeword);
                        i9 = Math.min(i9, detectCodeword.getWidth());
                        i10 = Math.max(i10, detectCodeword.getWidth());
                        i14 = i7;
                        minY = i5 + 1;
                        i13 = i4;
                    }
                    i14 = i6;
                    minY = i5 + 1;
                    i13 = i4;
                }
            }
        }
        return PDF417ScanningDecoder.createDecoderResult(detectionResult);
    }

    private static DetectionResult merge(final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        final BarcodeMetadata barcodeMetadata;
        if ((null == detectionResultRowIndicatorColumn && null == detectionResultRowIndicatorColumn2) || null == (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2))) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(PDF417ScanningDecoder.adjustBoundingBox(detectionResultRowIndicatorColumn), PDF417ScanningDecoder.adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    private static BoundingBox adjustBoundingBox(final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        final int[] rowHeights;
        if (null == detectionResultRowIndicatorColumn || null == (rowHeights = detectionResultRowIndicatorColumn.getRowHeights())) {
            return null;
        }
        final int max = PDF417ScanningDecoder.getMax(rowHeights);
        int i2 = 0;
        int i3 = 0;
        for (final int i4 : rowHeights) {
            i3 += max - i4;
            if (0 < i4) {
                break;
            }
        }
        final Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        int i5 = 0;
        while (0 < i3 && null == codewords[i5]) {
            i3--;
            i5++;
        }
        for (int length = rowHeights.length - 1; 0 <= length; length--) {
            final int i6 = rowHeights[length];
            i2 += max - i6;
            if (0 < i6) {
                break;
            }
        }
        int length2 = codewords.length - 1;
        while (0 < i2 && null == codewords[length2]) {
            i2--;
            length2--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i3, i2, detectionResultRowIndicatorColumn.isLeft());
    }

    private static int getMax(final int[] iArr) {
        int i2 = -1;
        for (final int max : iArr) {
            i2 = Math.max(i2, max);
        }
        return i2;
    }

    private static BarcodeMetadata getBarcodeMetadata(final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        final BarcodeMetadata barcodeMetadata;
        final BarcodeMetadata barcodeMetadata2;
        if (null == detectionResultRowIndicatorColumn || null == (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata())) {
            if (null == detectionResultRowIndicatorColumn2) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        } else if (null == detectionResultRowIndicatorColumn2 || null == (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) || barcodeMetadata.getColumnCount() == barcodeMetadata2.getColumnCount() || barcodeMetadata.getErrorCorrectionLevel() == barcodeMetadata2.getErrorCorrectionLevel() || barcodeMetadata.getRowCount() == barcodeMetadata2.getRowCount()) {
            return barcodeMetadata;
        } else {
            return null;
        }
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(final BitMatrix bitMatrix, final BoundingBox boundingBox, final ResultPoint resultPoint, final boolean z, final int i2, final int i3) {
        int endX;
        final boolean z2 = z;
        final DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z2);
        int i4 = 0;
        while (2 > i4) {
            final int i5 = 0 == i4 ? 1 : -1;
            int x = (int) resultPoint.getX();
            int y = (int) resultPoint.getY();
            while (y <= boundingBox.getMaxY() && y >= boundingBox.getMinY()) {
                final Codeword detectCodeword = PDF417ScanningDecoder.detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i2, i3);
                if (null != detectCodeword) {
                    detectionResultRowIndicatorColumn.setCodeword(y, detectCodeword);
                    if (z2) {
                        endX = detectCodeword.getStartX();
                    } else {
                        endX = detectCodeword.getEndX();
                    }
                    x = endX;
                }
                y += i5;
            }
            i4++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static void adjustCodewordCount(final DetectionResult detectionResult, final BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        final int[] value = barcodeValueArr[0][1].getValue();
        final int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - PDF417ScanningDecoder.getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (0 == value.length) {
            if (0 >= barcodeColumnCount || 928 < barcodeColumnCount) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        } else if (value[0] != barcodeColumnCount) {
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        }
    }

    private static DecoderResult createDecoderResult(final DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        final BarcodeValue[][] createBarcodeMatrix = PDF417ScanningDecoder.createBarcodeMatrix(detectionResult);
        PDF417ScanningDecoder.adjustCodewordCount(detectionResult, createBarcodeMatrix);
        final ArrayList arrayList = new ArrayList();
        final int[] iArr = new int[(detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount())];
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < detectionResult.getBarcodeRowCount(); i2++) {
            int i3 = 0;
            while (i3 < detectionResult.getBarcodeColumnCount()) {
                final int i4 = i3 + 1;
                final int[] value = createBarcodeMatrix[i2][i4].getValue();
                final int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i2) + i3;
                if (0 == value.length) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (1 == value.length) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                i3 = i4;
            }
        }
        final int size = arrayList2.size();
        final int[][] iArr2 = new int[size][];
        for (int i5 = 0; i5 < size; i5++) {
            iArr2[i5] = (int[]) arrayList2.get(i5);
        }
        return PDF417ScanningDecoder.createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(final int i2, final int[] iArr, final int[] iArr2, final int[] iArr3, final int[][] iArr4) throws FormatException, ChecksumException {
        final int length = iArr3.length;
        final int[] iArr5 = new int[length];
        int i3 = 100;
        while (true) {
            final int i4 = i3 - 1;
            if (0 < i3) {
                for (int i5 = 0; i5 < length; i5++) {
                    iArr[iArr3[i5]] = iArr4[i5][iArr5[i5]];
                }
                try {
                    return PDF417ScanningDecoder.decodeCodewords(iArr, i2, iArr2);
                } catch (final ChecksumException unused) {
                    if (0 != length) {
                        int i6 = 0;
                        while (true) {
                            if (i6 >= length) {
                                break;
                            }
                            final int i7 = iArr5[i6];
                            if (i7 < iArr4[i6].length - 1) {
                                iArr5[i6] = i7 + 1;
                                break;
                            }
                            iArr5[i6] = 0;
                            if (i6 != length - 1) {
                                i6++;
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        i3 = i4;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private static BarcodeValue[][] createBarcodeMatrix(final DetectionResult detectionResult) {
        int rowNumber;
        final int barcodeRowCount = detectionResult.getBarcodeRowCount();
        final int[] iArr = new int[2];
        iArr[1] = detectionResult.getBarcodeColumnCount() + 2;
        iArr[0] = barcodeRowCount;
        final BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, iArr);
        for (final BarcodeValue[] barcodeValueArr2 : barcodeValueArr) {
            int i2 = 0;
            while (true) {
                if (i2 >= barcodeValueArr2.length) {
                    break;
                }
                barcodeValueArr2[i2] = new BarcodeValue();
                i2++;
            }
        }
        int i3 = 0;
        for (final DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (null != detectionResultColumn) {
                for (final Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (null != codeword && 0 <= (rowNumber = codeword.getRowNumber()) && rowNumber < barcodeValueArr.length) {
                        barcodeValueArr[rowNumber][i3].setValue(codeword.getValue());
                    }
                }
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static boolean isValidBarcodeColumn(final DetectionResult detectionResult, final int i2) {
        return 0 <= i2 && i2 <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static int getStartColumn(final DetectionResult detectionResult, int i2, final int i3, final boolean z) {
        final int i4 = z ? 1 : -1;
        final int i5 = i2 - i4;
        final Codeword codeword = PDF417ScanningDecoder.isValidBarcodeColumn(detectionResult, i5) ? detectionResult.getDetectionResultColumn(i5).getCodeword(i3) : null;
        if (null != codeword) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i2).getCodewordNearby(i3);
        if (null != codewordNearby) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (PDF417ScanningDecoder.isValidBarcodeColumn(detectionResult, i5)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i5).getCodewordNearby(i3);
        }
        if (null != codewordNearby) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i6 = 0;
        while (true) {
            i2 -= i4;
            if (PDF417ScanningDecoder.isValidBarcodeColumn(detectionResult, i2)) {
                for (final Codeword codeword2 : detectionResult.getDetectionResultColumn(i2).getCodewords()) {
                    if (null != codeword2) {
                        return (z ? codeword2.getEndX() : codeword2.getStartX()) + (i4 * i6 * (codeword2.getEndX() - codeword2.getStartX()));
                    }
                }
                i6++;
            } else {
                final BoundingBox boundingBox = detectionResult.getBoundingBox();
                return z ? boundingBox.getMinX() : boundingBox.getMaxX();
            }
        }
    }

    private static Codeword detectCodeword(final BitMatrix r7, final int r8, final int r9, final boolean r10, final int r11, final int r12, final int r13, final int r14) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.detectCodeword(com.google.zxing.common.BitMatrix, int, int, boolean, int, int, int, int):com.google.zxing.pdf417.decoder.Codeword");
    }

    private static int[] getModuleBitCount(final BitMatrix bitMatrix, int i2, final int i3, final boolean z, int i4, final int i5) {
        final int[] iArr = new int[8];
        final int i6 = z ? 1 : -1;
        int i7 = 0;
        boolean z2 = z;
        while (true) {
            if (!z) {
                if (i4 < i2) {
                    break;
                }
            } else if (i4 >= i3) {
                break;
            }
            if (8 <= i7) {
                break;
            } else if (bitMatrix.get(i4, i5) == z2) {
                iArr[i7] = iArr[i7] + 1;
                i4 += i6;
            } else {
                i7++;
                z2 = !z2;
            }
        }
        if (8 != i7) {
            if (z) {
                i2 = i3;
            }
            if (!(i4 == i2 && 7 == i7)) {
                return null;
            }
        }
        return iArr;
    }

    private static int adjustCodewordStartColumn(final BitMatrix bitMatrix, final int i2, final int i3, boolean z, final int i4, final int i5) {
        int i6 = z ? -1 : 1;
        int i7 = i4;
        for (int i8 = 0; 2 > i8; i8++) {
            while (true) {
                if (!z) {
                    if (i7 >= i3) {
                        continue;
                    }
                } else if (i7 < i2) {
                    continue;
                }
                if (z != bitMatrix.get(i7, i5)) {
                    continue;
                } else if (2 < Math.abs(i4 - i7)) {
                    return i4;
                } else {
                    i7 += i6;
                }
            }
        }
        return i7;
    }

    private static DecoderResult decodeCodewords(final int[] iArr, final int i2, final int[] iArr2) throws FormatException, ChecksumException {
        if (0 != iArr.length) {
            final int i3 = 1 << (i2 + 1);
            final int correctErrors = PDF417ScanningDecoder.correctErrors(iArr, iArr2, i3);
            PDF417ScanningDecoder.verifyCodewordCount(iArr, i3);
            final DecoderResult decode = DecodedBitStreamParser.decode(iArr, String.valueOf(i2));
            decode.setErrorsCorrected(Integer.valueOf(correctErrors));
            decode.setErasures(Integer.valueOf(iArr2.length));
            return decode;
        }
        throw FormatException.getFormatInstance();
    }

    private static int correctErrors(final int[] iArr, final int[] iArr2, final int i2) throws ChecksumException {
        if ((null == iArr2 || iArr2.length <= (i2 / 2) + 3) && 0 <= i2 && 512 >= i2) {
            return PDF417ScanningDecoder.errorCorrection.decode(iArr, i2, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void verifyCodewordCount(final int[] iArr, final int i2) throws FormatException {
        if (4 <= iArr.length) {
            final int i3 = iArr[0];
            if (i3 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (0 != i3) {
            } else {
                if (i2 < iArr.length) {
                    iArr[0] = iArr.length - i2;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] getBitCountForCodeword(int i2) {
        final int[] iArr = new int[8];
        int i3 = 0;
        int i4 = 7;
        while (true) {
            final int i5 = i2 & 1;
            if (i5 != i3) {
                i4--;
                if (0 > i4) {
                    return iArr;
                }
                i3 = i5;
            }
            iArr[i4] = iArr[i4] + 1;
            i2 >>= 1;
        }
    }

    private static int getCodewordBucketNumber(final int i2) {
        return PDF417ScanningDecoder.getCodewordBucketNumber(PDF417ScanningDecoder.getBitCountForCodeword(i2));
    }

    private static int getCodewordBucketNumber(final int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}
