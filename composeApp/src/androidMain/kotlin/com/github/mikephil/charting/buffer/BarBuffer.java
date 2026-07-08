package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected float mGroupSpace;
    protected float mBarSpace;
    protected int mDataSetIndex;
    protected boolean mInverted;
    public BarBuffer(final int i, final float f, final int i2, final boolean z) {
        super (i);
        mGroupSpace = f;
        mDataSetCount = i2;
        mContainsStacks = z;
    }
    protected void setBarSpace(final float f) {
        mBarSpace = f;
    }
    protected void setDataSet(final int i) {
        mDataSetIndex = i;
    }
    protected void setInverted(final boolean z) {
        mInverted = z;
    }
    protected void addBar(final float f, final float f2, final float f3, final float f4) {
        final float[] fArr = this.buffer;
        final int i = this.index;
        final int i2 = i + 1;
        this.index = i2;
        fArr[i] = f;
        final int i3 = i + 2;
        this.index = i3;
        fArr[i2] = f2;
        final int i4 = i + 3;
        this.index = i4;
        fArr[i3] = f3;
        this.index = i + 4;
        fArr[i4] = f4;
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
        final int i = mDataSetCount - 1;
        final float f8 = mBarSpace / 2.0f;
        final float f9 = mGroupSpace / 2.0f;
        int i2 = 0;
        while (i2 < entryCount) {
            final BarEntry barEntry = iBarDataSet.getEntryForIndex (i2);
            final float xIndex = (barEntry.getXIndex () + (barEntry.getXIndex () * i) + mDataSetIndex) + (mGroupSpace * barEntry.getXIndex ()) + f9;
            float val = barEntry.getVal ();
            final float[] vals = barEntry.getVals ();
            float f10 = 0.0f;
            float f11 = 0.5f;
            if (!mContainsStacks || null == vals) {
                f = entryCount;
                final float f12 = (xIndex - 0.5f) + f8;
                final float f13 = (xIndex + 0.5f) - f8;
                if (mInverted) {
                    f3 = 0.0f;
                    f2 = 0.0f <= val ? val : 0.0f;
                    if (0.0f < val) {
                        val = 0.0f;
                    }
                } else {
                    f3 = 0.0f;
                    final float f14 = Math.max(0.0f, val);
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
                this.addBar(f12, val, f13, f2);
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
                    if (mInverted) {
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
                    this.addBar(f18, f15 * f21, f19, f7 * f21);
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
