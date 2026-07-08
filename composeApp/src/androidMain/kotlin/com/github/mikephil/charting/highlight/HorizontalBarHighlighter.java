package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.SelectionDetail;

public class HorizontalBarHighlighter extends BarHighlighter {
    public HorizontalBarHighlighter(final BarDataProvider barDataProvider) {
        super (barDataProvider);
    }

    public com.github.mikephil.charting.highlight.Highlight getHighlight(final float f, final float f2) {
        final BarData barData = this.mChart.getBarData ();
        final int xIndex = this.getXIndex(f);
        final float base = this.getBase(f);
        final int dataSetCount = barData.getDataSetCount ();
        int i = ((int) base) % dataSetCount;
        if (0 > i) {
            i = 0;
        } else if (i >= dataSetCount) {
            i = dataSetCount - 1;
        }
        final SelectionDetail selectionDetail = this.getSelectionDetail(xIndex, f2, i);
        if (null == selectionDetail) {
            return null;
        }
        final IBarDataSet iBarDataSet = barData.getDataSetByIndex (i);
        if (!iBarDataSet.isStacked ()) {
            return new Highlight (xIndex, selectionDetail.value, selectionDetail.dataIndex, selectionDetail.dataSetIndex, -1);
        }
        final float[] fArr = new float[2];
        fArr[0] = f2;
        this.mChart.getTransformer (iBarDataSet.getAxisDependency ()).pixelsToValue (fArr);
        return this.getStackedHighlight(selectionDetail, iBarDataSet, xIndex, fArr[0]);
    }

    public int getXIndex(final float f) {
        if (!this.mChart.getBarData ().isGrouped ()) {
            final float[] fArr = new float[2];
            fArr[1] = f;
            this.mChart.getTransformer (YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
            return Math.round (fArr[1]);
        }
        final int base = ((int) this.getBase(f)) / this.mChart.getBarData ().getDataSetCount ();
        final int xValCount = this.mChart.getData ().getXValCount ();
        if (0 > base) {
            return 0;
        }
        return base >= xValCount ? xValCount - 1 : base;
    }

    protected float getBase(final float f) {
        final float[] fArr = new float[2];
        fArr[1] = f;
        this.mChart.getTransformer (YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        final float f2 = fArr[1];
        return f2 - (this.mChart.getBarData ().getGroupSpace () * ((int) (f2 / (this.mChart.getBarData ().getDataSetCount () + this.mChart.getBarData ().getGroupSpace ()))));
    }
}
