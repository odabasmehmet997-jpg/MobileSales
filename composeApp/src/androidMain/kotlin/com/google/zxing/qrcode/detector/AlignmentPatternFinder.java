package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

final class AlignmentPatternFinder {
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final int[] crossCheckStateCount = new int[3];
    AlignmentPatternFinder(BitMatrix bitMatrix, int r3, int r4, int r5, int r6, float f2, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.startX = r3;
        this.startY = r4;
        this.width = r5;
        this.height = r6;
        this.moduleSize = f2;
        this.resultPointCallback = resultPointCallback;
    }
    AlignmentPattern find() throws NotFoundException {
        AlignmentPattern alignmentPatternHandlePossibleCenter;
        AlignmentPattern alignmentPatternHandlePossibleCenter2;
        int r0 = this.startX;
        int r1 = this.height;
        int r2 = this.width + r0;
        int r3 = this.startY + (r1 / 2);
        int[] r4 = new int[3];
        for (int r6 = 0; r6 < r1; r6++) {
            int r7 = ((r6 & 1) == 0 ? (r6 + 1) / 2 : -((r6 + 1) / 2)) + r3;
            r4[0] = 0;
            r4[1] = 0;
            r4[2] = 0;
            int r10 = r0;
            while (r10 < r2 && !this.image.get(r10, r7)) {
                r10++;
            }
            int r11 = 0;
            while (r10 < r2) {
                if (!this.image.get(r10, r7)) {
                    if (r11 == 1) {
                        r11++;
                    }
                    r4[r11] = r4[r11] + 1;
                } else if (r11 == 1) {
                    r4[1] = r4[1] + 1;
                } else if (r11 == 2) {
                    if (foundPatternCross(r4) && (alignmentPatternHandlePossibleCenter2 = handlePossibleCenter(r4, r7, r10)) != null) {
                        return alignmentPatternHandlePossibleCenter2;
                    }
                    r4[0] = r4[2];
                    r4[1] = 1;
                    r4[2] = 0;
                    r11 = 1;
                } else {
                    r11++;
                    r4[r11] = r4[r11] + 1;
                }
                r10++;
            }
            if (foundPatternCross(r4) && (alignmentPatternHandlePossibleCenter = handlePossibleCenter(r4, r7, r2)) != null) {
                return alignmentPatternHandlePossibleCenter;
            }
        }
        if (!this.possibleCenters.isEmpty()) {
            return this.possibleCenters.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }
    private static float centerFromEnd(int[] r1, int r2) {
        return (r2 - r1[2]) - (r1[1] / 2.0f);
    }
    private boolean foundPatternCross(int[] r5) {
        float f2 = this.moduleSize;
        float f3 = f2 / 2.0f;
        for (int r2 = 0; r2 < 3; r2++) {
            if (Math.abs(f2 - r5[r2]) >= f3) {
                return false;
            }
        }
        return true;
    }
    private float crossCheckVertical(int r10, int r11, int r12, int r13) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] r2 = this.crossCheckStateCount;
        r2[0] = 0;
        r2[1] = 0;
        r2[2] = 0;
        int r6 = r10;
        while (r6 >= 0 && bitMatrix.get(r11, r6)) {
            int r7 = r2[1];
            if (r7 > r12) {
                break;
            }
            r2[1] = r7 + 1;
            r6--;
        }
        if (r6 >= 0 && r2[1] <= r12) {
            while (r6 >= 0 && !bitMatrix.get(r11, r6)) {
                int r8 = r2[0];
                if (r8 > r12) {
                    break;
                }
                r2[0] = r8 + 1;
                r6--;
            }
            if (r2[0] > r12) {
                return Float.NaN;
            }
            int r102 = r10 + 1;
            while (r102 < height && bitMatrix.get(r11, r102)) {
                int r62 = r2[1];
                if (r62 > r12) {
                    break;
                }
                r2[1] = r62 + 1;
                r102++;
            }
            if (r102 != height && r2[1] <= r12) {
                while (r102 < height && !bitMatrix.get(r11, r102)) {
                    int r63 = r2[2];
                    if (r63 > r12) {
                        break;
                    }
                    r2[2] = r63 + 1;
                    r102++;
                }
                int r112 = r2[2];
                if (r112 <= r12 && Math.abs(((r2[0] + r2[1]) + r112) - r13) * 5 < r13 * 2 && foundPatternCross(r2)) {
                    return centerFromEnd(r2, r102);
                }
            }
        }
        return Float.NaN;
    }
    private AlignmentPattern handlePossibleCenter(int[] r7, int r8, int r9) {
        int r1 = r7[0] + r7[1] + r7[2];
        float fCenterFromEnd = centerFromEnd(r7, r9);
        float fCrossCheckVertical = crossCheckVertical(r8, (int) fCenterFromEnd, r7[1] * 2, r1);
        if (Float.isNaN(fCrossCheckVertical)) {
            return null;
        }
        float f2 = ((r7[0] + r7[1]) + r7[2]) / 3.0f;
        for (AlignmentPattern alignmentPattern : this.possibleCenters) {
            if (alignmentPattern.aboutEquals(f2, fCrossCheckVertical, fCenterFromEnd)) {
                return alignmentPattern.combineEstimate(fCrossCheckVertical, fCenterFromEnd, f2);
            }
        }
        AlignmentPattern alignmentPattern2 = new AlignmentPattern(fCenterFromEnd, fCrossCheckVertical, f2);
        this.possibleCenters.add(alignmentPattern2);
        ResultPointCallback resultPointCallback = this.resultPointCallback;
        if (resultPointCallback == null) {
            return null;
        }
        resultPointCallback.foundPossibleResultPoint(alignmentPattern2);
        return null;
    }
}
