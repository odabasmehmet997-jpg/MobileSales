package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import org.kxml2.wap.Wbxml;

import java.util.Arrays;
import java.util.Map;

public final class Code39Reader extends OneDReader {
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static {
        final int[] iArr = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, Wbxml.EXT_1, 448, 145, 400, 208, 133, 388, Wbxml.LITERAL_AC, 148, 168, 162, 138, 42};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[39];
    }

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(final boolean z) {
        this(z, false);
    }

    public Code39Reader(final boolean z, final boolean z2) {
        usingCheckDigit = z;
        extendedMode = z2;
        decodeRowResult = new StringBuilder(20);
        counters = new int[9];
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        final String str;
        final int[] iArr = counters;
        Arrays.fill(iArr, 0);
        final StringBuilder sb = decodeRowResult;
        sb.setLength(0);
        final int[] findAsteriskPattern = Code39Reader.findAsteriskPattern(bitArray, iArr);
        int nextSet = bitArray.getNextSet(findAsteriskPattern[1]);
        final int size = bitArray.getSize();
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            final int narrowWidePattern = Code39Reader.toNarrowWidePattern(iArr);
            if (0 <= narrowWidePattern) {
                final char patternToChar = Code39Reader.patternToChar(narrowWidePattern);
                sb.append(patternToChar);
                int i3 = nextSet;
                for (final int i4 : iArr) {
                    i3 += i4;
                }
                final int nextSet2 = bitArray.getNextSet(i3);
                if ('*' == patternToChar) {
                    sb.setLength(sb.length() - 1);
                    int i5 = 0;
                    for (final int i6 : iArr) {
                        i5 += i6;
                    }
                    final int i7 = (nextSet2 - nextSet) - i5;
                    if (nextSet2 == size || (i7 << 1) >= i5) {
                        if (usingCheckDigit) {
                            final int length = sb.length() - 1;
                            int i8 = 0;
                            for (int i9 = 0; i9 < length; i9++) {
                                i8 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. /+%".indexOf(decodeRowResult.charAt(i9));
                            }
                            if (sb.charAt(length) == "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. /+%".charAt(i8 % 43)) {
                                sb.setLength(length);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (0 != sb.length()) {
                            if (extendedMode) {
                                str = Code39Reader.decodeExtended(sb);
                            } else {
                                str = sb.toString();
                            }
                            final float f2 = i2;
                            return new Result(str, null, new ResultPoint[]{new ResultPoint((findAsteriskPattern[1] + findAsteriskPattern[0]) / 2.0f, f2), new ResultPoint(nextSet + (i5 / 2.0f), f2)}, BarcodeFormat.CODE_39);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                nextSet = nextSet2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int[] findAsteriskPattern(final BitArray bitArray, final int[] iArr) throws NotFoundException {
        final int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        final int length = iArr.length;
        boolean z = false;
        int i2 = 0;
        int i3 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                final int i4 = length - 1;
                if (i2 != i4) {
                    i2++;
                } else if (Code39Reader.toNarrowWidePattern(iArr) == Code39Reader.ASTERISK_ENCODING && bitArray.isRange(Math.max(0, i3 - ((nextSet - i3) / 2)), i3, false)) {
                    return new int[]{i3, nextSet};
                } else {
                    i3 += iArr[0] + iArr[1];
                    final int i5 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i4] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(final int[] iArr) {
        final int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3 = Integer.MAX_VALUE;
            for (final int i4 : iArr) {
                if (i4 < i3 && i4 > i2) {
                    i3 = i4;
                }
            }
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < length; i8++) {
                final int i9 = iArr[i8];
                if (i9 > i3) {
                    i6 |= 1 << ((length - 1) - i8);
                    i5++;
                    i7 += i9;
                }
            }
            if (3 == i5) {
                for (int i10 = 0; i10 < length && 0 < i5; i10++) {
                    final int i11 = iArr[i10];
                    if (i11 > i3) {
                        i5--;
                        if ((i11 << 1) >= i7) {
                            return -1;
                        }
                    }
                }
                return i6;
            } else if (3 >= i5) {
                return -1;
            } else {
                i2 = i3;
            }
        }
    }

    private static char patternToChar(final int i2) throws NotFoundException {
        int i3 = 0;
        while (true) {
            final int[] iArr = Code39Reader.CHARACTER_ENCODINGS;
            if (i3 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            } else if (iArr[i3] == i2) {
                return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. */+%".charAt(i3);
            } else {
                i3++;
            }
        }
    }

    private static String decodeExtended(final CharSequence charSequence) throws FormatException {
        char c2;
        int i2;
        final int length = charSequence.length();
        final StringBuilder sb = new StringBuilder(length);
        int i3 = 0;
        while (i3 < length) {
            final char charAt = charSequence.charAt(i3);
            if ('+' == charAt || '' == charAt || '%' == charAt || '/' == charAt) {
                i3++;
                final char charAt2 = charSequence.charAt(i3);
                if ('' != charAt) {
                    if ('%' != charAt) {
                        if ('+' != charAt) {
                            if ('/' != charAt) {
                                c2 = 0;
                            } else if ('A' <= charAt2 && 'O' >= charAt2) {
                                i2 = charAt2 - ' ';
                            } else if ('Z' == charAt2) {
                                c2 = ':';
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            sb.append(c2);
                        } else if ('A' > charAt2 || 'Z' < charAt2) {
                            throw FormatException.getFormatInstance();
                        } else {
                            i2 = charAt2 + ' ';
                        }
                    } else if ('A' <= charAt2 && 'E' >= charAt2) {
                        i2 = charAt2 - '&';
                    } else if ('F' > charAt2 || 'W' < charAt2) {
                        throw FormatException.getFormatInstance();
                    } else {
                        i2 = charAt2 - 11;
                    }
                } else if ('A' > charAt2 || 'Z' < charAt2) {
                    throw FormatException.getFormatInstance();
                } else {
                    i2 = charAt2 - '@';
                }
                c2 = (char) i2;
                sb.append(c2);
            } else {
                sb.append(charAt);
            }
            i3++;
        }
        return sb.toString();
    }
}
