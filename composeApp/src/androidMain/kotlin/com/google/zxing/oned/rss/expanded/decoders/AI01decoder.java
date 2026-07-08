package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01decoder extends AbstractExpandedDecoder {
    AI01decoder(final BitArray bitArray) {
        super(bitArray);
    }

    
    public final void encodeCompressedGtin(final StringBuilder sb, final int i2) {
        sb.append("(01)");
        final int length = sb.length();
        sb.append('9');
        this.encodeCompressedGtinWithoutAI(sb, i2, length);
    }

    
    public final void encodeCompressedGtinWithoutAI(final StringBuilder sb, final int i2, final int i3) {
        for (int i4 = 0; 4 > i4; i4++) {
            final int extractNumericValueFromBitArray = this.getGeneralDecoder().extractNumericValueFromBitArray((i4 * 10) + i2, 10);
            if (0 == extractNumericValueFromBitArray / 100) {
                sb.append('0');
            }
            if (0 == extractNumericValueFromBitArray / 10) {
                sb.append('0');
            }
            sb.append(extractNumericValueFromBitArray);
        }
        AI01decoder.appendCheckDigit(sb, i3);
    }

    private static void appendCheckDigit(final StringBuilder sb, final int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; 13 > i5; i5++) {
            int charAt = sb.charAt(i5 + i2) - '0';
            if (0 == (i5 & 1)) {
                charAt *= 3;
            }
            i4 += charAt;
        }
        final int i6 = 10 - (i4 % 10);
        if (10 != i6) {
            i3 = i6;
        }
        sb.append(i3);
    }
}
