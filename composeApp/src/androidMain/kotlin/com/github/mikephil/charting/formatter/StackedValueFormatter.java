package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class StackedValueFormatter implements ValueFormatter {
    private final String mAppendix;
    private final boolean mDrawWholeStack;
    private final DecimalFormat mFormat;

    public StackedValueFormatter(boolean z, String str, int i) {
        this.mDrawWholeStack = z;
        this.mAppendix = str;
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
        BarEntry barEntry;
        float[] vals;
        if (this.mDrawWholeStack || !(entry instanceof BarEntry) || null == (vals = (barEntry = (BarEntry) entry).getVals ())) {
            return this.mFormat.format (f) + this.mAppendix;
        } else if (vals[vals.length - 1] != f) {
            return "";
        } else {
            return this.mFormat.format (barEntry.getVal ()) + this.mAppendix;
        }
    }
}
