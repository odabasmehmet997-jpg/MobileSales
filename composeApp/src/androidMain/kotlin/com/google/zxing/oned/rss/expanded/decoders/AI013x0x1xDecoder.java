package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(final BitArray bitArray, final String str, final String str2) {
        super(bitArray);
        dateCode = str2;
        firstAIdigits = str;
    }

    public String parseInformation() throws NotFoundException {
        if (84 == getInformation().getSize()) {
            final StringBuilder sb = new StringBuilder();
            this.encodeCompressedGtin(sb, 8);
            this.encodeCompressedWeight(sb, 48, 20);
            this.encodeCompressedDate(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void encodeCompressedDate(final StringBuilder sb, final int i2) {
        final int extractNumericValueFromBitArray = this.getGeneralDecoder().extractNumericValueFromBitArray(i2, 16);
        if (38400 != extractNumericValueFromBitArray) {
            sb.append('(');
            sb.append(dateCode);
            sb.append(')');
            final int i3 = extractNumericValueFromBitArray % 32;
            final int i4 = extractNumericValueFromBitArray / 32;
            final int i5 = (i4 % 12) + 1;
            final int i6 = i4 / 12;
            if (0 == i6 / 10) {
                sb.append('0');
            }
            sb.append(i6);
            if (0 == i5 / 10) {
                sb.append('0');
            }
            sb.append(i5);
            if (0 == i3 / 10) {
                sb.append('0');
            }
            sb.append(i3);
        }
    }

    
    public void addWeightCode(final StringBuilder sb, final int i2) {
        sb.append('(');
        sb.append(firstAIdigits);
        sb.append(i2 / 100000);
        sb.append(')');
    }

    
    public int checkWeight(final int i2) {
        return i2 % 100000;
    }
}
