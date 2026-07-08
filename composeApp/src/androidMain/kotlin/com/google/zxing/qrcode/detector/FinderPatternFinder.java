package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder {
    private boolean hasSkipped;
    private final BitMatrix image;
    private final ResultPointCallback resultPointCallback;
    private final List<FinderPattern> possibleCenters = new ArrayList();
    private final int[] crossCheckStateCount = new int[5];
    private final float r0 = 0;
    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.resultPointCallback = resultPointCallback;
    }
    final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int r5 = (height * 3) / 228;
        if (r5 < 3 || z) {
            r5 = 3;
        }
        int[] r2 = new int[5];
        int r7 = r5 - 1;
        boolean zHaveMultiplyConfirmedCenters = false;
        while (r7 < height && !zHaveMultiplyConfirmedCenters) {
            r2[0] = 0;
            r2[1] = 0;
            r2[2] = 0;
            r2[3] = 0;
            r2[4] = 0;
            int r11 = 0;
            int r12 = 0;
            while (r11 < width) {
                if (this.image.get(r11, r7)) {
                    if ((r12 & 1) == 1) {
                        r12++;
                    }
                    r2[r12] = r2[r12] + 1;
                } else if ((r12 & 1) != 0) {
                    r2[r12] = r2[r12] + 1;
                } else if (r12 == 4) {
                    if (foundPatternCross(r2) && handlePossibleCenter(r2, r7, r11, z2)) {
                        if (this.hasSkipped) {
                            zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                        } else {
                            int r52 = findRowSkip();
                            int r122 = r2[2];
                            if (r52 > r122) {
                                r7 += (r52 - r122) - 2;
                                r11 = width - 1;
                            }
                        }
                        r2[0] = 0;
                        r2[1] = 0;
                        r2[2] = 0;
                        r2[3] = 0;
                        r2[4] = 0;
                        r12 = 0;
                        r5 = 2;
                    } else {
                        r2[0] = r2[2];
                        r2[1] = r2[3];
                        r2[2] = r2[4];
                        r2[3] = 1;
                        r2[4] = 0;
                        r12 = 3;
                    }
                } else {
                    r12++;
                    r2[r12] = r2[r12] + 1;
                }
                r11++;
            }
            if (foundPatternCross(r2) && handlePossibleCenter(r2, r7, width, z2)) {
                r5 = r2[0];
                if (this.hasSkipped) {
                    zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                }
            }
            r7 += r5;
        }
        FinderPattern[] finderPatternArrSelectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(finderPatternArrSelectBestPatterns);
        return new FinderPatternInfo(finderPatternArrSelectBestPatterns);
    }
    private static float centerFromEnd(int[] r1, int r2) {
        return ((r2 - r1[4]) - r1[3]) - (r1[2] / 2.0f);
    }
    protected static boolean foundPatternCross(int[] r7) {
        int r2 = 0;
        for (int r1 = 0; r1 < 5; r1++) {
            int r3 = r7[r1];
            if (r3 == 0) {
                return false;
            }
            r2 += r3;
        }
        if (r2 < 7) {
            return false;
        }
        float f2 = r2 / 7.0f;
        float f3 = f2 / 2.0f;
        return Math.abs(f2 - ((float) r7[0])) < f3 && Math.abs(f2 - ((float) r7[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) r7[2])) < 3.0f * f3 && Math.abs(f2 - ((float) r7[3])) < f3 && Math.abs(f2 - ((float) r7[4])) < f3;
    }
    private int[] getCrossCheckStateCount() {
        int[] r2 = this.crossCheckStateCount;
        r2[0] = 0;
        r2[1] = 0;
        r2[2] = 0;
        r2[3] = 0;
        r2[4] = 0;
        return r2;
    }
    private boolean crossCheckDiagonal(int r17, int r18, int r19, int r20) {
        int r11;
        int r112;
        int r14;
        int r113;
        int r13;
        int r132;
        int r12;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int r6 = 0;
        while (r17 >= r6 && r18 >= r6 && this.image.get(r18 - r6, r17 - r6)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            r6++;
        }
        if (r17 >= r6 && r18 >= r6) {
            while (r17 >= r6 && r18 >= r6 && !this.image.get(r18 - r6, r17 - r6)) {
                int r9 = crossCheckStateCount[1];
                if (r9 > r19) {
                    break;
                }
                crossCheckStateCount[1] = r9 + 1;
                r6++;
            }
            if (r17 >= r6 && r18 >= r6 && crossCheckStateCount[1] <= r19) {
                while (r17 >= r6 && r18 >= r6 && this.image.get(r18 - r6, r17 - r6)) {
                    int r92 = crossCheckStateCount[0];
                    if (r92 > r19) {
                        break;
                    }
                    crossCheckStateCount[0] = r92 + 1;
                    r6++;
                }
                if (crossCheckStateCount[0] > r19) {
                    return false;
                }
                int height = this.image.getHeight();
                int width = this.image.getWidth();
                int r10 = 1;
                while (true) {
                    r11 = r17 + r10;
                    if (r11 >= height || (r12 = r18 + r10) >= width || !this.image.get(r12, r11)) {
                        break;
                    }
                    crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                    r10++;
                }
                if (r11 < height && r18 + r10 < width) {
                    while (true) {
                        r112 = r17 + r10;
                        if (r112 >= height || (r13 = r18 + r10) >= width || this.image.get(r13, r112) || (r132 = crossCheckStateCount[3]) >= r19) {
                            break;
                        }
                        crossCheckStateCount[3] = r132 + 1;
                        r10++;
                    }
                    if (r112 < height && r18 + r10 < width && crossCheckStateCount[3] < r19) {
                        while (true) {
                            int r114 = r17 + r10;
                            if (r114 >= height || (r14 = r18 + r10) >= width || !this.image.get(r14, r114) || (r113 = crossCheckStateCount[4]) >= r19) {
                                break;
                            }
                            crossCheckStateCount[4] = r113 + 1;
                            r10++;
                        }
                        int r0 = crossCheckStateCount[4];
                        return r0 < r19 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + r0) - r20) < r20 * 2 && foundPatternCross(crossCheckStateCount);
                    }
                }
            }
        }
        return false;
    }
    private float crossCheckVertical(int r10, int r11, int r12, int r13) {
        int r8;
        int r7;
        int r72;
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int r2 = r10;
        while (r2 >= 0 && bitMatrix.get(r11, r2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            r2--;
        }
        if (r2 < 0) {
            return Float.NaN;
        }
        while (r2 >= 0 && !bitMatrix.get(r11, r2)) {
            int r6 = crossCheckStateCount[1];
            if (r6 > r12) {
                break;
            }
            crossCheckStateCount[1] = r6 + 1;
            r2--;
        }
        if (r2 >= 0 && crossCheckStateCount[1] <= r12) {
            while (r2 >= 0 && bitMatrix.get(r11, r2) && (r72 = crossCheckStateCount[0]) <= r12) {
                crossCheckStateCount[0] = r72 + 1;
                r2--;
            }
            if (crossCheckStateCount[0] > r12) {
                return Float.NaN;
            }
            int r102 = r10 + 1;
            while (r102 < height && bitMatrix.get(r11, r102)) {
                crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                r102++;
            }
            if (r102 == height) {
                return Float.NaN;
            }
            while (r102 < height && !bitMatrix.get(r11, r102) && (r7 = crossCheckStateCount[3]) < r12) {
                crossCheckStateCount[3] = r7 + 1;
                r102++;
            }
            if (r102 != height && crossCheckStateCount[3] < r12) {
                while (r102 < height && bitMatrix.get(r11, r102) && (r8 = crossCheckStateCount[4]) < r12) {
                    crossCheckStateCount[4] = r8 + 1;
                    r102++;
                }
                int r112 = crossCheckStateCount[4];
                if (r112 < r12 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + r112) - r13) * 5 < r13 * 2 && foundPatternCross(crossCheckStateCount)) {
                    return centerFromEnd(crossCheckStateCount, r102);
                }
            }
        }
        return Float.NaN;
    }
    private float crossCheckHorizontal(int r10, int r11, int r12, int r13) {
        int r8;
        int r7;
        int r72;
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int r2 = r10;
        while (r2 >= 0 && bitMatrix.get(r2, r11)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            r2--;
        }
        if (r2 < 0) {
            return Float.NaN;
        }
        while (r2 >= 0 && !bitMatrix.get(r2, r11)) {
            int r6 = crossCheckStateCount[1];
            if (r6 > r12) {
                break;
            }
            crossCheckStateCount[1] = r6 + 1;
            r2--;
        }
        if (r2 >= 0 && crossCheckStateCount[1] <= r12) {
            while (r2 >= 0 && bitMatrix.get(r2, r11) && (r72 = crossCheckStateCount[0]) <= r12) {
                crossCheckStateCount[0] = r72 + 1;
                r2--;
            }
            if (crossCheckStateCount[0] > r12) {
                return Float.NaN;
            }
            int r102 = r10 + 1;
            while (r102 < width && bitMatrix.get(r102, r11)) {
                crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
                r102++;
            }
            if (r102 == width) {
                return Float.NaN;
            }
            while (r102 < width && !bitMatrix.get(r102, r11) && (r7 = crossCheckStateCount[3]) < r12) {
                crossCheckStateCount[3] = r7 + 1;
                r102++;
            }
            if (r102 != width && crossCheckStateCount[3] < r12) {
                while (r102 < width && bitMatrix.get(r102, r11) && (r8 = crossCheckStateCount[4]) < r12) {
                    crossCheckStateCount[4] = r8 + 1;
                    r102++;
                }
                int r112 = crossCheckStateCount[4];
                if (r112 < r12 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + r112) - r13) * 5 < r13 && foundPatternCross(crossCheckStateCount)) {
                    return centerFromEnd(crossCheckStateCount, r102);
                }
            }
        }
        return Float.NaN;
    }
    protected final boolean handlePossibleCenter(int[] r7, int r8, int r9, boolean z) {
        int r0 = 0;
        int r1 = r7[0] + r7[1] + r7[2] + r7[3] + r7[4];
        int r92 = (int) centerFromEnd(r7, r9);
        float fCrossCheckVertical = crossCheckVertical(r8, r92, r7[2], r1);
        if (!Float.isNaN(fCrossCheckVertical)) {
            int r4 = (int) fCrossCheckVertical;
            float fCrossCheckHorizontal = crossCheckHorizontal(r92, r4, r7[2], r1);
            if (!Float.isNaN(fCrossCheckHorizontal) && (!z || crossCheckDiagonal(r4, (int) fCrossCheckHorizontal, r7[2], r1))) {
                float f2 = r1 / 7.0f;
                while (true) {
                    if (r0 < this.possibleCenters.size()) {
                        FinderPattern finderPattern = this.possibleCenters.get(r0);
                        if (finderPattern.aboutEquals(f2, fCrossCheckVertical, fCrossCheckHorizontal)) {
                            this.possibleCenters.set(r0, finderPattern.combineEstimate(fCrossCheckVertical, fCrossCheckHorizontal, f2));
                            break;
                        }
                        r0++;
                    } else {
                        FinderPattern finderPattern2 = new FinderPattern(fCrossCheckHorizontal, fCrossCheckVertical, f2);
                        this.possibleCenters.add(finderPattern2);
                        ResultPointCallback resultPointCallback = this.resultPointCallback;
                        if (resultPointCallback != null) {
                            resultPointCallback.foundPossibleResultPoint(finderPattern2);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }
    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float fAbs = 0.0f;
        int r4 = 0;
        float estimatedModuleSize = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                r4++;
                estimatedModuleSize += finderPattern.getEstimatedModuleSize();
            }
        }
        if (r4 < 3) {
            return false;
        }
        float f2 = estimatedModuleSize / size;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().getEstimatedModuleSize() - f2);
        }
        return fAbs <= estimatedModuleSize * 0.05f;
    }
    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float estimatedModuleSize = 0.0f;
        if (size > 3) {
            Iterator<FinderPattern> it = this.possibleCenters.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it.hasNext()) {
                float estimatedModuleSize2 = it.next().getEstimatedModuleSize();
                f2 += estimatedModuleSize2;
                f3 += estimatedModuleSize2 * estimatedModuleSize2;
            }
            float f4 = f2 / size;
            float fSqrt = (float) Math.sqrt((f3 / r0) - (f4 * f4));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f4));
            float fMax = Math.max(0.2f * f4, fSqrt);
            int r6 = 0;
            while (r6 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(r6).getEstimatedModuleSize() - f4) > fMax) {
                    this.possibleCenters.remove(r6);
                    r6--;
                }
                r6++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
            while (it2.hasNext()) {
                estimatedModuleSize += it2.next().getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(estimatedModuleSize / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    private record FurthestFromAverageComparator(float average) implements Serializable, Comparator<FinderPattern> {

        @Override // java.util.Comparator
            public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
                float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
                float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
                if (fAbs < fAbs2) {
                    return -1;
                }
                return fAbs == fAbs2 ? 0 : 1;
            }
        }

    private record CenterComparator(float average) implements Serializable, Comparator<FinderPattern> {

        @Override // java.util.Comparator
            public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
                if (finderPattern2.getCount() == finderPattern.getCount()) {
                    float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
                    float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
                    if (fAbs < fAbs2) {
                        return 1;
                    }
                    return fAbs == fAbs2 ? 0 : -1;
                }
                return finderPattern2.getCount() - finderPattern.getCount();
            }
        }
}
