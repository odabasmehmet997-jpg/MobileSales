package com.google.zxing.oned.rss.expanded;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;

import java.util.*;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS;
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    static {
        final int[] iArr = new int[8];
        final int[] iArr2 = iArr;
        // fill-array-data instruction
        iArr[0] = 1;
        iArr[1] = 3;
        iArr[2] = 9;
        iArr[3] = 27;
        iArr[4] = 81;
        iArr[5] = 32;
        iArr[6] = 96;
        iArr[7] = 77;
        final int[] iArr3 = new int[8];
        final int[] iArr4 = iArr3;
        // fill-array-data instruction
        iArr3[0] = 20;
        iArr3[1] = 60;
        iArr3[2] = 180;
        iArr3[3] = 118;
        iArr3[4] = 143;
        iArr3[5] = 7;
        iArr3[6] = 21;
        iArr3[7] = 63;
        final int[] iArr5 = new int[8];
        final int[] iArr6 = iArr5;
        // fill-array-data instruction
        iArr5[0] = 189;
        iArr5[1] = 145;
        iArr5[2] = 13;
        iArr5[3] = 39;
        iArr5[4] = 117;
        iArr5[5] = 140;
        iArr5[6] = 209;
        iArr5[7] = 205;
        final int[] iArr7 = new int[8];
        final int[] iArr8 = iArr7;
        // fill-array-data instruction
        iArr7[0] = 193;
        iArr7[1] = 157;
        iArr7[2] = 49;
        iArr7[3] = 147;
        iArr7[4] = 19;
        iArr7[5] = 57;
        iArr7[6] = 171;
        iArr7[7] = 91;
        final int[] iArr9 = new int[8];
        final int[] iArr10 = iArr9;
        // fill-array-data instruction
        iArr9[0] = 62;
        iArr9[1] = 186;
        iArr9[2] = 136;
        iArr9[3] = 197;
        iArr9[4] = 169;
        iArr9[5] = 85;
        iArr9[6] = 44;
        iArr9[7] = 132;
        final int[] iArr11 = new int[8];
        final int[] iArr12 = iArr11;
        // fill-array-data instruction
        iArr11[0] = 185;
        iArr11[1] = 133;
        iArr11[2] = 188;
        iArr11[3] = 142;
        iArr11[4] = 4;
        iArr11[5] = 12;
        iArr11[6] = 36;
        iArr11[7] = 108;
        final int[] iArr13 = new int[8];
        final int[] iArr14 = iArr13;
        // fill-array-data instruction
        iArr13[0] = 113;
        iArr13[1] = 128;
        iArr13[2] = 173;
        iArr13[3] = 97;
        iArr13[4] = 80;
        iArr13[5] = 29;
        iArr13[6] = 87;
        iArr13[7] = 50;
        final int[] iArr15 = new int[8];
        final int[] iArr16 = iArr15;
        // fill-array-data instruction
        iArr15[0] = 150;
        iArr15[1] = 28;
        iArr15[2] = 84;
        iArr15[3] = 41;
        iArr15[4] = 123;
        iArr15[5] = 158;
        iArr15[6] = 52;
        iArr15[7] = 156;
        final int[] iArr17 = new int[8];
        final int[] iArr18 = iArr17;
        // fill-array-data instruction
        iArr17[0] = 46;
        iArr17[1] = 138;
        iArr17[2] = 203;
        iArr17[3] = 187;
        iArr17[4] = 139;
        iArr17[5] = 206;
        iArr17[6] = 196;
        iArr17[7] = 166;
        final int[] iArr19 = new int[8];
        final int[] iArr20 = iArr19;
        // fill-array-data instruction
        iArr19[0] = 76;
        iArr19[1] = 17;
        iArr19[2] = 51;
        iArr19[3] = 153;
        iArr19[4] = 37;
        iArr19[5] = 111;
        iArr19[6] = 122;
        iArr19[7] = 155;
        final int[] iArr21 = new int[8];
        // fill-array-data instruction
        iArr21[0] = 43;
        iArr21[1] = 129;
        iArr21[2] = 176;
        iArr21[3] = 106;
        iArr21[4] = 107;
        iArr21[5] = 110;
        iArr21[6] = 119;
        iArr21[7] = 146;
        final int[] iArr22 = new int[8];
        final int[] iArr23 = iArr22;
        // fill-array-data instruction
        iArr22[0] = 16;
        iArr22[1] = 48;
        iArr22[2] = 144;
        iArr22[3] = 10;
        iArr22[4] = 30;
        iArr22[5] = 90;
        iArr22[6] = 59;
        iArr22[7] = 177;
        final int[] iArr24 = new int[8];
        final int[] iArr25 = iArr24;
        // fill-array-data instruction
        iArr24[0] = 109;
        iArr24[1] = 116;
        iArr24[2] = 137;
        iArr24[3] = 200;
        iArr24[4] = 178;
        iArr24[5] = 112;
        iArr24[6] = 125;
        iArr24[7] = 164;
        final int[] iArr26 = new int[8];
        final int[] iArr27 = iArr26;
        // fill-array-data instruction
        iArr26[0] = 70;
        iArr26[1] = 210;
        iArr26[2] = 208;
        iArr26[3] = 202;
        iArr26[4] = 184;
        iArr26[5] = 130;
        iArr26[6] = 179;
        iArr26[7] = 115;
        final int[] iArr28 = new int[8];
        final int[] iArr29 = iArr28;
        // fill-array-data instruction
        iArr28[0] = 134;
        iArr28[1] = 191;
        iArr28[2] = 151;
        iArr28[3] = 31;
        iArr28[4] = 93;
        iArr28[5] = 68;
        iArr28[6] = 204;
        iArr28[7] = 190;
        final int[] iArr30 = new int[8];
        final int[] iArr31 = iArr30;
        // fill-array-data instruction
        iArr30[0] = 148;
        iArr30[1] = 22;
        iArr30[2] = 66;
        iArr30[3] = 198;
        iArr30[4] = 172;
        iArr30[5] = 94;
        iArr30[6] = 71;
        iArr30[7] = 2;
        final int[] iArr32 = new int[8];
        final int[] iArr33 = iArr32;
        // fill-array-data instruction
        iArr32[0] = 6;
        iArr32[1] = 18;
        iArr32[2] = 54;
        iArr32[3] = 162;
        iArr32[4] = 64;
        iArr32[5] = 192;
        iArr32[6] = 154;
        iArr32[7] = 40;
        final int[] iArr34 = new int[8];
        final int[] iArr35 = iArr34;
        // fill-array-data instruction
        iArr34[0] = 120;
        iArr34[1] = 149;
        iArr34[2] = 25;
        iArr34[3] = 75;
        iArr34[4] = 14;
        iArr34[5] = 42;
        iArr34[6] = 126;
        iArr34[7] = 167;
        final int[] iArr36 = new int[8];
        final int[] iArr37 = iArr36;
        // fill-array-data instruction
        iArr36[0] = 79;
        iArr36[1] = 26;
        iArr36[2] = 78;
        iArr36[3] = 23;
        iArr36[4] = 69;
        iArr36[5] = 207;
        iArr36[6] = 199;
        iArr36[7] = 175;
        final int[] iArr38 = new int[8];
        final int[] iArr39 = iArr38;
        // fill-array-data instruction
        iArr38[0] = 103;
        iArr38[1] = 98;
        iArr38[2] = 83;
        iArr38[3] = 38;
        iArr38[4] = 114;
        iArr38[5] = 131;
        iArr38[6] = 182;
        iArr38[7] = 124;
        final int[] iArr40 = new int[8];
        final int[] iArr41 = iArr40;
        // fill-array-data instruction
        iArr40[0] = 161;
        iArr40[1] = 61;
        iArr40[2] = 183;
        iArr40[3] = 127;
        iArr40[4] = 170;
        iArr40[5] = 88;
        iArr40[6] = 53;
        iArr40[7] = 159;
        final int[] iArr42 = new int[8];
        final int[] iArr43 = iArr42;
        // fill-array-data instruction
        iArr42[0] = 55;
        iArr42[1] = 165;
        iArr42[2] = 73;
        iArr42[3] = 8;
        iArr42[4] = 24;
        iArr42[5] = 72;
        iArr42[6] = 5;
        iArr42[7] = 15;
        final int[] iArr44 = new int[8];
        // fill-array-data instruction
        iArr44[0] = 45;
        iArr44[1] = 135;
        iArr44[2] = 194;
        iArr44[3] = 160;
        iArr44[4] = 58;
        iArr44[5] = 174;
        iArr44[6] = 100;
        iArr44[7] = 89;
        WEIGHTS = new int[][]{iArr2, iArr4, iArr6, iArr8, iArr10, iArr12, iArr14, iArr16, iArr18, iArr20, iArr21, iArr23, iArr25, iArr27, iArr29, iArr31, iArr33, iArr35, iArr37, iArr39, iArr41, iArr43, iArr44};
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        pairs.clear();
        startFromEven = false;
        try {
            return RSSExpandedReader.constructResult(this.decodeRow2pairs(i2, bitArray));
        } catch (final NotFoundException unused) {
            pairs.clear();
            startFromEven = true;
            return RSSExpandedReader.constructResult(this.decodeRow2pairs(i2, bitArray));
        }
    }

    public void reset() {
        pairs.clear();
        rows.clear();
    }

    
    public List<ExpandedPair> decodeRow2pairs(final int i2, final BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                pairs.add(this.retrieveNextPair(bitArray, pairs, i2));
            } catch (final NotFoundException e2) {
                if (pairs.isEmpty()) {
                    throw e2;
                } else if (this.checkChecksum()) {
                    return pairs;
                } else {
                    final boolean isEmpty = rows.isEmpty();
                    this.storeRow(i2, false);
                    if (!isEmpty) {
                        final List<ExpandedPair> checkRows = this.checkRows(false);
                        if (null != checkRows) {
                            return checkRows;
                        }
                        final List<ExpandedPair> checkRows2 = this.checkRows(true);
                        if (null != checkRows2) {
                            return checkRows2;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    private List<ExpandedPair> checkRows(final boolean z) {
        List<ExpandedPair> list = null;
        if (25 < this.rows.size()) {
            rows.clear();
            return null;
        }
        pairs.clear();
        if (z) {
            Collections.reverse(rows);
        }
        try {
            list = this.checkRows(new ArrayList(), 0);
        } catch (final NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(rows);
        }
        return list;
    }

    private List<ExpandedPair> checkRows(final List<ExpandedRow> list, int i2) throws NotFoundException {
        while (i2 < rows.size()) {
            final ExpandedRow expandedRow = rows.get(i2);
            pairs.clear();
            for (final ExpandedRow pairs2 : list) {
                pairs.addAll(pairs2.getPairs());
            }
            pairs.addAll(expandedRow.getPairs());
            if (!RSSExpandedReader.isValidSequence(pairs)) {
                i2++;
            } else if (this.checkChecksum()) {
                return pairs;
            } else {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return this.checkRows(arrayList, i2 + 1);
                } catch (final NotFoundException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isValidSequence(final List<ExpandedPair> list) {
        for (final int[] iArr : RSSExpandedReader.FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr.length) {
                int i2 = 0;
                while (i2 < list.size()) {
                    if (list.get(i2).getFinderPattern().getValue() == iArr[i2]) {
                        i2++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void storeRow(final int i2, final boolean z) {
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i3 >= rows.size()) {
                break;
            }
            final ExpandedRow expandedRow = rows.get(i3);
            if (expandedRow.getRowNumber() > i2) {
                z2 = expandedRow.isEquivalent(pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(pairs);
                i3++;
            }
        }
        if (!z2 && !z3 && !RSSExpandedReader.isPartialRow(pairs, rows)) {
            rows.add(i3, new ExpandedRow(pairs, i2, z));
            RSSExpandedReader.removePartialRows(pairs, rows);
        }
    }

    private static void removePartialRows(final List<ExpandedPair> list, final List<ExpandedRow> list2) {
        final Iterator<ExpandedRow> it = list2.iterator();
        while (it.hasNext()) {
            final ExpandedRow next = it.next();
            if (next.getPairs().size() != list.size()) {
                final Iterator<ExpandedPair> it2 = next.getPairs().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        it.remove();
                        break;
                    }
                    final ExpandedPair next2 = it2.next();
                    final Iterator<ExpandedPair> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        } else if (next2.equals(it3.next())) {
                        }
                    }
                }
            }
        }
    }

    private static boolean isPartialRow(final Iterable<ExpandedPair> iterable, final Iterable<ExpandedRow> iterable2) {
        for (final ExpandedRow next : iterable2) {
            final Iterator<ExpandedPair> it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    return true;
                }
                final ExpandedPair next2 = it.next();
                final Iterator<ExpandedPair> it2 = next.getPairs().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (next2.equals(it2.next())) {
                        }
                    }
                }
            }
        }
        return false;
    }

    static Result constructResult(final List<ExpandedPair> list) throws NotFoundException, FormatException {
        final String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        final ResultPoint[] resultPoints = list.get(0).getFinderPattern().getResultPoints();
        final ResultPoint[] resultPoints2 = list.get(list.size() - 1).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        final ExpandedPair expandedPair = pairs.get(0);
        final DataCharacter leftChar = expandedPair.getLeftChar();
        final DataCharacter rightChar = expandedPair.getRightChar();
        if (null == rightChar) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i2 = 2;
        for (int i3 = 1; i3 < pairs.size(); i3++) {
            final ExpandedPair expandedPair2 = pairs.get(i3);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            final int i4 = i2 + 1;
            final DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (null != rightChar2) {
                checksumPortion += rightChar2.getChecksumPortion();
                i2 += 2;
            } else {
                i2 = i4;
            }
        }
        return ((i2 - 4) * 211) + (checksumPortion % 211) == leftChar.getValue();
    }

    private static int getNextSecondBar(final BitArray bitArray, final int i2) {
        if (bitArray.get(i2)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i2));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i2));
    }

    
    public ExpandedPair retrieveNextPair(final BitArray bitArray, final List<ExpandedPair> list, final int i2) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = 0 == list.size() % 2;
        if (startFromEven) {
            z = !z;
        }
        int i3 = -1;
        boolean z2 = true;
        do {
            this.findNextPair(bitArray, list, i3);
            parseFoundFinderPattern = this.parseFoundFinderPattern(bitArray, i2, z);
            if (null == parseFoundFinderPattern) {
                i3 = RSSExpandedReader.getNextSecondBar(bitArray, startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        final DataCharacter decodeDataCharacter = this.decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        if (list.isEmpty() || !list.get(list.size() - 1).mustBeLast()) {
            try {
                dataCharacter = this.decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
            } catch (final NotFoundException unused) {
                dataCharacter = null;
            }
            return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, true);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void findNextPair(final BitArray bitArray, final List<ExpandedPair> list, int i2) throws NotFoundException {
        final int[] decodeFinderCounters = this.getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        final int size = bitArray.getSize();
        if (0 > i2) {
            if (list.isEmpty()) {
                i2 = 0;
            } else {
                i2 = list.get(list.size() - 1).getFinderPattern().getStartEnd()[1];
            }
        }
        boolean z = 0 != list.size() % 2;
        if (startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (true) {
            if (i2 >= size) {
                break;
            }
            final boolean z3 = bitArray.get(i2);
            final boolean z4 = !z3;
            if (z3) {
                z2 = z4;
                break;
            } else {
                i2++;
                z2 = z4;
            }
        }
        int i3 = 0;
        boolean z5 = z2;
        int i4 = i2;
        while (i2 < size) {
            if (bitArray.get(i2) ^ z5) {
                decodeFinderCounters[i3] = decodeFinderCounters[i3] + 1;
            } else {
                if (3 == i3) {
                    if (z) {
                        RSSExpandedReader.reverseCounters(decodeFinderCounters);
                    }
                    if (isFinderPattern(decodeFinderCounters)) {
                        final int[] iArr = startEnd;
                        iArr[0] = i4;
                        iArr[1] = i2;
                        return;
                    }
                    if (z) {
                        RSSExpandedReader.reverseCounters(decodeFinderCounters);
                    }
                    i4 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                decodeFinderCounters[i3] = 1;
                z5 = !z5;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void reverseCounters(final int[] iArr) {
        final int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            final int i3 = iArr[i2];
            final int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    private FinderPattern parseFoundFinderPattern(final BitArray bitArray, final int i2, final boolean z) {
        final int i3;
        final int i4;
        final int i5;
        if (z) {
            int i6 = startEnd[0] - 1;
            while (0 <= i6 && !bitArray.get(i6)) {
                i6--;
            }
            final int i7 = i6 + 1;
            final int[] iArr = startEnd;
            i5 = iArr[0] - i7;
            i3 = iArr[1];
            i4 = i7;
        } else {
            final int[] iArr2 = startEnd;
            final int i8 = iArr2[0];
            final int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i3 = nextUnset;
            i4 = i8;
            i5 = nextUnset - startEnd[1];
        }
        final int[] decodeFinderCounters = this.getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i5;
        try {
            return new FinderPattern(parseFinderValue(decodeFinderCounters, RSSExpandedReader.FINDER_PATTERNS), new int[]{i4, i3}, i4, i3, i2);
        } catch (final NotFoundException unused) {
            return null;
        }
    }

    
    public DataCharacter decodeDataCharacter(final BitArray bitArray, final FinderPattern finderPattern, final boolean z, final boolean z2) throws NotFoundException {
        final BitArray bitArray2 = bitArray;
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
            recordPattern(bitArray2, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i2 = 0;
            for (int length = dataCharacterCounters.length - 1; i2 < length; length--) {
                final int i3 = dataCharacterCounters[i2];
                dataCharacterCounters[i2] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i3;
                i2++;
            }
        }
        final float sum = MathUtils.sum(dataCharacterCounters) / 17.0f;
        final float f2 = (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0]) / 15.0f;
        if (0.3f >= Math.abs(sum - f2) / f2) {
            final int[] oddCounts = this.getOddCounts();
            final int[] evenCounts = this.getEvenCounts();
            final float[] oddRoundingErrors = this.getOddRoundingErrors();
            final float[] evenRoundingErrors = this.getEvenRoundingErrors();
            for (int i4 = 0; i4 < dataCharacterCounters.length; i4++) {
                final float f3 = (dataCharacterCounters[i4]) / sum;
                int i5 = (int) (0.5f + f3);
                if (0 >= i5) {
                    if (0.3f <= f3) {
                        i5 = 1;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (8 < i5) {
                    if (8.7f >= f3) {
                        i5 = 8;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                final int i6 = i4 / 2;
                if (0 == (i4 & 1)) {
                    oddCounts[i6] = i5;
                    oddRoundingErrors[i6] = f3 - i5;
                } else {
                    evenCounts[i6] = i5;
                    evenRoundingErrors[i6] = f3 - i5;
                }
            }
            this.adjustOddEvenCounts(17);
            final int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
            int i7 = 0;
            int i8 = 0;
            for (int length2 = oddCounts.length - 1; 0 <= length2; length2--) {
                if (RSSExpandedReader.isNotA1left(finderPattern, z, z2)) {
                    i7 += oddCounts[length2] * RSSExpandedReader.WEIGHTS[value][length2 * 2];
                }
                i8 += oddCounts[length2];
            }
            int i9 = 0;
            for (int length3 = evenCounts.length - 1; 0 <= length3; length3--) {
                if (RSSExpandedReader.isNotA1left(finderPattern, z, z2)) {
                    i9 += evenCounts[length3] * RSSExpandedReader.WEIGHTS[value][(length3 * 2) + 1];
                }
            }
            final int i10 = i7 + i9;
            if (0 != (i8 & 1) || 13 < i8 || 4 > i8) {
                throw NotFoundException.getNotFoundInstance();
            }
            final int i11 = (13 - i8) / 2;
            final int i12 = RSSExpandedReader.SYMBOL_WIDEST[i11];
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i12, true) * RSSExpandedReader.EVEN_TOTAL_SUBSET[i11]) + RSSUtils.getRSSvalue(evenCounts, 9 - i12, false) + RSSExpandedReader.GSUM[i11], i10);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isNotA1left(final FinderPattern finderPattern, final boolean z, final boolean z2) {
        return 0 != finderPattern.getValue() || !z || !z2;
    }

    private void adjustOddEvenCounts(final int r11) throws com.google.zxing.NotFoundException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.adjustOddEvenCounts(int):void");
    }
}
