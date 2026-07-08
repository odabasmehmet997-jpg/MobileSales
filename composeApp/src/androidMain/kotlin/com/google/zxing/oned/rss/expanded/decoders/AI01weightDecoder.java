package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder {
    
    public abstract void addWeightCode(StringBuilder sb, int i2);

    
    public abstract int checkWeight(int i2);

    AI01weightDecoder(final BitArray bitArray) {
        super(bitArray);
    }

    
    public final void encodeCompressedWeight(final StringBuilder sb, final int i2, final int i3) {
        final int extractNumericValueFromBitArray = this.getGeneralDecoder().extractNumericValueFromBitArray(i2, i3);
        this.addWeightCode(sb, extractNumericValueFromBitArray);
        final int checkWeight = this.checkWeight(extractNumericValueFromBitArray);
        int i4 = 100000;
        for (int i5 = 0; 5 > i5; i5++) {
            if (0 == checkWeight / i4) {
                sb.append('0');
            }
            i4 /= 10;
        }
        sb.append(checkWeight);
    }
}
