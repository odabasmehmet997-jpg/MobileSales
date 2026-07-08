package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01392xDecoder extends AI01decoder {
    AI01392xDecoder(final BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() throws NotFoundException, FormatException {
        if (48 <= getInformation().getSize()) {
            final StringBuilder sb = new StringBuilder();
            this.encodeCompressedGtin(sb, 8);
            final int extractNumericValueFromBitArray = this.getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
            sb.append("(392");
            sb.append(extractNumericValueFromBitArray);
            sb.append(')');
            sb.append(this.getGeneralDecoder().decodeGeneralPurposeField(50, (String) null).getNewString());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
