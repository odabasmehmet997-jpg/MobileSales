package com.google.zxing.oned.rss;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException {
        RSS14Reader.addOrTally(possibleLeftPairs, this.decodePair(bitArray, false, i2, map));
        bitArray.reverse();
        RSS14Reader.addOrTally(possibleRightPairs, this.decodePair(bitArray, true, i2, map));
        bitArray.reverse();
        for (final Pair next : possibleLeftPairs) {
            if (1 < next.getCount()) {
                for (final Pair next2 : possibleRightPairs) {
                    if (1 < next2.getCount() && RSS14Reader.checkChecksum(next, next2)) {
                        return RSS14Reader.constructResult(next, next2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(final Collection<Pair> collection, final Pair pair) {
        if (null != pair) {
            for (final Pair next : collection) {
                if (next.getValue() == pair.getValue()) {
                    next.incrementCount();
                    return;
                }
            }
            collection.add(pair);
        }
    }

    public void reset() {
        possibleLeftPairs.clear();
        possibleRightPairs.clear();
    }

    private static Result constructResult(final Pair pair, final Pair pair2) {
        final String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + pair2.getValue());
        final StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); 0 < length; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i2 = 0;
        for (int i3 = 0; 13 > i3; i3++) {
            int charAt = sb.charAt(i3) - '0';
            if (0 == (i3 & 1)) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        int i4 = 10 - (i2 % 10);
        if (10 == i4) {
            i4 = 0;
        }
        sb.append(i4);
        final ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        final ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(sb.toString(), null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(final Pair pair, final Pair pair2) {
        final int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (72 < value) {
            value--;
        }
        if (8 < value) {
            value--;
        }
        return checksumPortion == value;
    }

    private Pair decodePair(final BitArray bitArray, final boolean z, final int i2, final Map<DecodeHintType, ?> map) {
        final ResultPointCallback resultPointCallback;
        try {
            final int[] findFinderPattern = this.findFinderPattern(bitArray, 0, z);
            final FinderPattern parseFoundFinderPattern = this.parseFoundFinderPattern(bitArray, i2, z, findFinderPattern);
            if (null == map) {
                resultPointCallback = null;
            } else {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            }
            if (null != resultPointCallback) {
                float f2 = (findFinderPattern[0] + findFinderPattern[1]) / 2.0f;
                if (z) {
                    f2 = (bitArray.getSize() - 1) - f2;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f2, i2));
            }
            final DataCharacter decodeDataCharacter = this.decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            final DataCharacter decodeDataCharacter2 = this.decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (final NotFoundException unused) {
            return null;
        }
    }

    private DataCharacter decodeDataCharacter(final BitArray bitArray, final FinderPattern finderPattern, final boolean z) throws NotFoundException {
        final BitArray bitArray2 = bitArray;
        final boolean z2 = z;
        final int[] dataCharacterCounters = this.getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            recordPatternInReverse(bitArray2, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray2, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            int i2 = 0;
            for (int length = dataCharacterCounters.length - 1; i2 < length; length--) {
                final int i3 = dataCharacterCounters[i2];
                dataCharacterCounters[i2] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i3;
                i2++;
            }
        }
        final int i4 = z2 ? 16 : 15;
        final float sum = ((float) MathUtils.sum(dataCharacterCounters)) / i4;
        final int[] oddCounts = this.getOddCounts();
        final int[] evenCounts = this.getEvenCounts();
        final float[] oddRoundingErrors = this.getOddRoundingErrors();
        final float[] evenRoundingErrors = this.getEvenRoundingErrors();
        for (int i5 = 0; i5 < dataCharacterCounters.length; i5++) {
            final float f2 = dataCharacterCounters[i5] / sum;
            int i6 = (int) (0.5f + f2);
            if (0 >= i6) {
                i6 = 1;
            } else if (8 < i6) {
                i6 = 8;
            }
            final int i7 = i5 / 2;
            if (0 == (i5 & 1)) {
                oddCounts[i7] = i6;
                oddRoundingErrors[i7] = f2 - i6;
            } else {
                evenCounts[i7] = i6;
                evenRoundingErrors[i7] = f2 - i6;
            }
        }
        this.adjustOddEvenCounts(z2, i4);
        int i8 = 0;
        int i9 = 0;
        for (int length2 = oddCounts.length - 1; 0 <= length2; length2--) {
            final int i10 = oddCounts[length2];
            i8 = (i8 * 9) + i10;
            i9 += i10;
        }
        int i11 = 0;
        int i12 = 0;
        for (int length3 = evenCounts.length - 1; 0 <= length3; length3--) {
            final int i13 = evenCounts[length3];
            i11 = (i11 * 9) + i13;
            i12 += i13;
        }
        final int i14 = i8 + (i11 * 3);
        if (z2) {
            if (0 != (i9 & 1) || 12 < i9 || 4 > i9) {
                throw NotFoundException.getNotFoundInstance();
            }
            final int i15 = (12 - i9) / 2;
            final int i16 = RSS14Reader.OUTSIDE_ODD_WIDEST[i15];
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i16, false) * RSS14Reader.OUTSIDE_EVEN_TOTAL_SUBSET[i15]) + RSSUtils.getRSSvalue(evenCounts, 9 - i16, true) + RSS14Reader.OUTSIDE_GSUM[i15], i14);
        } else if (0 != (i12 & 1) || 10 < i12 || 4 > i12) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            final int i17 = (10 - i12) / 2;
            final int i18 = RSS14Reader.INSIDE_ODD_WIDEST[i17];
            return new DataCharacter((RSSUtils.getRSSvalue(evenCounts, 9 - i18, false) * RSS14Reader.INSIDE_ODD_TOTAL_SUBSET[i17]) + RSSUtils.getRSSvalue(oddCounts, i18, true) + RSS14Reader.INSIDE_GSUM[i17], i14);
        }
    }

    private int[] findFinderPattern(final BitArray bitArray, int i2, final boolean z) throws NotFoundException {
        final int[] decodeFinderCounters = this.getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        final int size = bitArray.getSize();
        boolean z2 = false;
        while (i2 < size) {
            z2 = !bitArray.get(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < size) {
            if (bitArray.get(i2) ^ z2) {
                decodeFinderCounters[i4] = decodeFinderCounters[i4] + 1;
            } else {
                if (3 != i4) {
                    i4++;
                } else if (isFinderPattern(decodeFinderCounters)) {
                    return new int[]{i3, i2};
                } else {
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i4--;
                }
                decodeFinderCounters[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(final BitArray bitArray, final int i2, final boolean z, final int[] iArr) throws NotFoundException {
        final int i3;
        final int i4;
        final boolean z2 = bitArray.get(iArr[0]);
        int i5 = iArr[0] - 1;
        while (0 <= i5 && (bitArray.get(i5) ^ z2)) {
            i5--;
        }
        final int i6 = i5 + 1;
        final int[] decodeFinderCounters = this.getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = iArr[0] - i6;
        final int parseFinderValue = parseFinderValue(decodeFinderCounters, RSS14Reader.FINDER_PATTERNS);
        final int i7 = iArr[1];
        if (z) {
            i3 = (bitArray.getSize() - 1) - i7;
            i4 = (bitArray.getSize() - 1) - i6;
        } else {
            i3 = i7;
            i4 = i6;
        }
        return new FinderPattern(parseFinderValue, new int[]{i6, iArr[1]}, i4, i3, i2);
    }

    private void adjustOddEvenCounts(final boolean r10, final int r11) throws com.google.zxing.NotFoundException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.adjustOddEvenCounts(boolean, int):void");
    }
}
