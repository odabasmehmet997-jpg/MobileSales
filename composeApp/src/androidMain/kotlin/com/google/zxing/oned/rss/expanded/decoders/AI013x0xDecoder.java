package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

abstract class AI013x0xDecoder extends AI01weightDecoder {
    AI013x0xDecoder(final BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() throws NotFoundException {
        if (60 == getInformation().getSize()) {
            final StringBuilder sb = new StringBuilder();
            this.encodeCompressedGtin(sb, 5);
            this.encodeCompressedWeight(sb, 45, 15);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
