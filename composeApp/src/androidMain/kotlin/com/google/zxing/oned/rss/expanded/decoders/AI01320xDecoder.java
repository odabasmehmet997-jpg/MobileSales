package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    
    public int checkWeight(final int i2) {
        return 10000 > i2 ? i2 : i2 - 10000;
    }

    AI01320xDecoder(final BitArray bitArray) {
        super(bitArray);
    }

    
    public void addWeightCode(final StringBuilder sb, final int i2) {
        if (10000 > i2) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
