package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

final class BarcodeValue {
    private final Map<Integer, Integer> values = new HashMap();

    BarcodeValue() {
    }

    
    public void setValue(final int i2) {
        Integer num = values.get(Integer.valueOf(i2));
        if (null == num) {
            num = 0;
        }
        values.put(Integer.valueOf(i2), Integer.valueOf(num.intValue() + 1));
    }

    
    public int[] getValue() {
        final ArrayList arrayList = new ArrayList();
        int i2 = -1;
        for (final Map.Entry next : values.entrySet()) {
            if (((Integer) next.getValue()).intValue() > i2) {
                i2 = ((Integer) next.getValue()).intValue();
                arrayList.clear();
                arrayList.add(next.getKey());
            } else if (((Integer) next.getValue()).intValue() == i2) {
                arrayList.add(next.getKey());
            }
        }
        return PDF417Common.toIntArray(arrayList);
    }
}
