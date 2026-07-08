package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI013103decoder extends AI013x0xDecoder {
    
    public int checkWeight(final int i2) {
        return i2;
    }

    AI013103decoder(final BitArray bitArray) {
        super(bitArray);
    }

    
    public void addWeightCode(final StringBuilder sb, final int i2) {
        sb.append("(3103)");
    }
}
