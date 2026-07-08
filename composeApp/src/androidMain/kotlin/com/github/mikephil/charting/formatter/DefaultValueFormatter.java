package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class DefaultValueFormatter implements ValueFormatter {
    private final DecimalFormat mFormat;

    public DefaultValueFormatter(int i) {
        StringBuffer stringBuffer = new StringBuffer ();
        for (int i2 = 0; i2 < i; i2++) {
            if (0 == i2) {
                stringBuffer.append (".");
            }
            stringBuffer.append ("0");
        }
        this.mFormat = new DecimalFormat ("###,###,###,##0" + stringBuffer);
    }

    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return this.mFormat.format (f);
    }
}
