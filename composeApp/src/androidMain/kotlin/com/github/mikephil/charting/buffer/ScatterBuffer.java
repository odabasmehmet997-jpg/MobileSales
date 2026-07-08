package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

public class ScatterBuffer extends AbstractBuffer<IScatterDataSet> {
    public ScatterBuffer(final int i) {
        super (i);
    }
    protected void addForm(final float f, final float f2) {
        final float[] fArr = this.buffer;
        final int i = this.index;
        final int i2 = i + 1;
        this.index = i2;
        fArr[i] = f;
        this.index = i + 2;
        fArr[i2] = f2;
    }
    public void feed(final IScatterDataSet iScatterDataSet) {
        throw new UnsupportedOperationException ("com.github.mikephil.charting.buffer.ScatterBuffer.feed(com.github.mikephil.charting.interfaces.datasets.IScatterDataSet):void");
    }
}
