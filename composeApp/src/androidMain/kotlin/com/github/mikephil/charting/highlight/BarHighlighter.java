package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.SelectionDetail;

public class BarHighlighter extends ChartHighlighter<BarDataProvider> {
    public BarHighlighter(final BarDataProvider barDataProvider) {
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
            return new com.github.mikephil.charting.highlight.Highlight (xIndex, selectionDetail.value, selectionDetail.dataIndex, selectionDetail.dataSetIndex, -1);
        }
        final float[] fArr = new float[2];
        fArr[1] = f2;
        this.mChart.getTransformer (iBarDataSet.getAxisDependency ()).pixelsToValue (fArr);
        return this.getStackedHighlight(selectionDetail, iBarDataSet, xIndex, fArr[1]);
    }

    public int getXIndex(final float f) {
        if (!this.mChart.getBarData ().isGrouped ()) {
            return super.getXIndex (f);
        }
        final int base = ((int) this.getBase(f)) / this.mChart.getBarData ().getDataSetCount ();
        final int xValCount = this.mChart.getData ().getXValCount ();
        if (0 > base) {
            return 0;
        }
        return base >= xValCount ? xValCount - 1 : base;
    }

    public SelectionDetail getSelectionDetail(final int i, final float f, final int i2) {
        final int max = Math.max (i2, 0);
        final BarData barData = this.mChart.getBarData ();
        final IBarDataSet iBarDataSet = barData.getDataSetCount () > max ? barData.getDataSetByIndex (max) : null;
        if (null == iBarDataSet) {
            return null;
        }
        final float yValForXIndex = iBarDataSet.getYValForXIndex (i);
        if (Double.NaN == yValForXIndex) {
            return null;
        }
        return new SelectionDetail (yValForXIndex, max, iBarDataSet);
    }

    public com.github.mikephil.charting.highlight.Highlight getStackedHighlight(final SelectionDetail selectionDetail, final IBarDataSet iBarDataSet, final int i, final double d) {
        final BarEntry barEntry = iBarDataSet.getEntryForXIndex (i);
        if (null == barEntry) {
            return null;
        }
        if (null == barEntry.getVals ()) {
            return new com.github.mikephil.charting.highlight.Highlight (i, barEntry.getVal (), selectionDetail.dataIndex, selectionDetail.dataSetIndex);
        }
        final com.github.mikephil.charting.highlight.Range[] ranges = this.getRanges(barEntry);
        if (0 >= ranges.length) {
            return null;
        }
        final int closestStackIndex = this.getClosestStackIndex(ranges, (float) d);
        return new Highlight (i, barEntry.getPositiveSum () - barEntry.getNegativeSum (), selectionDetail.dataIndex, selectionDetail.dataSetIndex, closestStackIndex, ranges[closestStackIndex]);
    }

    protected int getClosestStackIndex(final com.github.mikephil.charting.highlight.Range[] rangeArr, final float f) {
        if (null == rangeArr || 0 == rangeArr.length) {
            return 0;
        }
        int i = 0;
        for (final com.github.mikephil.charting.highlight.Range range : rangeArr) {
            if (range.contains (f)) {
                return i;
            }
            i++;
        }
        final int max = Math.max (rangeArr.length - 1, 0);
        if (f > rangeArr[max].f818to) {
            return max;
        }
        return 0;
    }

    protected float getBase(final float f) {
        final float[] fArr = new float[2];
        fArr[0] = f;
        this.mChart.getTransformer (YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        final float f2 = fArr[0];
        return f2 - (this.mChart.getBarData ().getGroupSpace () * ((int) (f2 / (this.mChart.getBarData ().getDataSetCount () + this.mChart.getBarData ().getGroupSpace ()))));
    }

    protected com.github.mikephil.charting.highlight.Range[] getRanges(final BarEntry barEntry) {
        final float[] vals = barEntry.getVals ();
        if (null == vals || 0 == vals.length) {
            return new com.github.mikephil.charting.highlight.Range[0];
        }
        final int length = vals.length;
        final com.github.mikephil.charting.highlight.Range[] rangeArr = new com.github.mikephil.charting.highlight.Range[length];
        float f = -barEntry.getNegativeSum ();
        float f2 = 0.0f;
        for (int i = 0; i < length; i++) {
            final float f3 = vals[i];
            if (0.0f > f3) {
                rangeArr[i] = new com.github.mikephil.charting.highlight.Range (f, Math.abs (f3) + f);
                f += Math.abs (f3);
            } else {
                final float f4 = f3 + f2;
                rangeArr[i] = new Range (f2, f4);
                f2 = f4;
            }
        }
        return rangeArr;
    }
}
