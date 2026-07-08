package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import java.util.List;

enum BitArrayBuilder {
    ;

    static BitArray buildBitArray(final List<ExpandedPair> list) {
        final int size = list.size() << 1;
        int i2 = size - 1;
        if (null == list.get(list.size() - 1).getRightChar()) {
            i2 = size - 2;
        }
        final BitArray bitArray = new BitArray(i2 * 12);
        int i3 = 0;
        final int value = list.get(0).getRightChar().getValue();
        for (int i4 = 11; 0 <= i4; i4--) {
            if (0 != ((1 << i4) & value)) {
                bitArray.set(i3);
            }
            i3++;
        }
        for (int i5 = 1; i5 < list.size(); i5++) {
            final ExpandedPair expandedPair = list.get(i5);
            final int value2 = expandedPair.getLeftChar().getValue();
            for (int i6 = 11; 0 <= i6; i6--) {
                if (0 != ((1 << i6) & value2)) {
                    bitArray.set(i3);
                }
                i3++;
            }
            if (null != expandedPair.getRightChar()) {
                final int value3 = expandedPair.getRightChar().getValue();
                for (int i7 = 11; 0 <= i7; i7--) {
                    if (0 != ((1 << i7) & value3)) {
                        bitArray.set(i3);
                    }
                    i3++;
                }
            }
        }
        return bitArray;
    }
}
