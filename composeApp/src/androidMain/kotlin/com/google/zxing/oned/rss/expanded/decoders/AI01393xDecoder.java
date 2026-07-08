package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01393xDecoder extends AI01decoder {
    AI01393xDecoder(final BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() throws NotFoundException, FormatException {
        if (48 <= getInformation().getSize()) {
            final StringBuilder sb = new StringBuilder();
            this.encodeCompressedGtin(sb, 8);
            final int extractNumericValueFromBitArray = this.getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
            sb.append("(393");
            sb.append(extractNumericValueFromBitArray);
            sb.append(')');
            final int extractNumericValueFromBitArray2 = this.getGeneralDecoder().extractNumericValueFromBitArray(50, 10);
            if (0 == extractNumericValueFromBitArray2 / 100) {
                sb.append('0');
            }
            if (0 == extractNumericValueFromBitArray2 / 10) {
                sb.append('0');
            }
            sb.append(extractNumericValueFromBitArray2);
            sb.append(this.getGeneralDecoder().decodeGeneralPurposeField(60, (String) null).getNewString());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
