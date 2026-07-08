package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(final int i, final float f, final int i2, final boolean z) {
        super (i, f, i2, z);
    }
    public void feed(final IBarDataSet iBarDataSet) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float entryCount = iBarDataSet.getEntryCount () * this.phaseX;
        final int i = this.mDataSetCount - 1;
        final float f8 = this.mBarSpace / 2.0f;
        final float f9 = this.mGroupSpace / 2.0f;
        int i2 = 0;
        while (i2 < entryCount) {
            final BarEntry barEntry = iBarDataSet.getEntryForIndex (i2);
            final float xIndex = (barEntry.getXIndex () + (barEntry.getXIndex () * i) + this.mDataSetIndex) + (this.mGroupSpace * barEntry.getXIndex ()) + f9;
            float val = barEntry.getVal ();
            final float[] vals = barEntry.getVals ();
            float f10 = 0.0f;
            float f11 = 0.5f;
            if (!this.mContainsStacks || null == vals) {
                f = entryCount;
                final float f12 = (xIndex - 0.5f) + f8;
                final float f13 = (xIndex + 0.5f) - f8;
                if (this.mInverted) {
                    f3 = 0.0f;
                    f2 = 0.0f <= val ? val : 0.0f;
                    if (0.0f < val) {
                        val = 0.0f;
                    }
                } else {
                    f3 = 0.0f;
                    final float f14 = 0.0f <= val ? val : 0.0f;
                    if (0.0f < val) {
                        val = 0.0f;
                    }
                    val = f14;
                    f2 = val;
                }
                if (val > f3) {
                    val *= this.phaseY;
                } else {
                    f2 *= this.phaseY;
                }
                this.addBar(f2, f13, val, f12);
            } else {
                float f15 = -barEntry.getNegativeSum ();
                float f16 = 0.0f;
                int i3 = 0;
                while (i3 < vals.length) {
                    final float f17 = vals[i3];
                    if (f17 >= f10) {
                        f4 = f17 + f16;
                        f5 = f15;
                        f15 = f16;
                        f16 = f4;
                    } else {
                        f4 = f15 + Math.abs (f17);
                        f5 = Math.abs (f17) + f15;
                    }
                    final float f18 = (xIndex - f11) + f8;
                    final float f19 = (xIndex + f11) - f8;
                    if (this.mInverted) {
                        f7 = f15 >= f4 ? f15 : f4;
                        if (f15 > f4) {
                            f15 = f4;
                        }
                        f6 = entryCount;
                    } else {
                        final float f20 = f15 >= f4 ? f15 : f4;
                        if (f15 > f4) {
                            f15 = f4;
                        }
                        f6 = entryCount;
                        f7 = f15;
                        f15 = f20;
                    }
                    final float f21 = this.phaseY;
                    this.addBar(f7 * f21, f19, f15 * f21, f18);
                    i3++;
                    f15 = f5;
                    entryCount = f6;
                    f10 = 0.0f;
                    f11 = 0.5f;
                }
                f = entryCount;
            }
            i2++;
            entryCount = f;
        }
        this.reset();
    }
}
