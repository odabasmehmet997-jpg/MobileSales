package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.SelectionDetail;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> {
    protected T mChart;

    public ChartHighlighter(final T t) {
        mChart = t;
    }

    public com.github.mikephil.charting.highlight.Highlight getHighlight(final float f, final float f2) {
        final int xIndex = this.getXIndex(f);
        final SelectionDetail selectionDetail = this.getSelectionDetail(xIndex, f2, -1);
        if (null == selectionDetail) {
            return null;
        }
        return new Highlight (xIndex, selectionDetail.value, selectionDetail.dataIndex, selectionDetail.dataSetIndex);
    }

    public int getXIndex(final float f) {
        final float[] fArr = new float[2];
        fArr[0] = f;
        mChart.getTransformer (YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        return Math.round (fArr[0]);
    }

    protected SelectionDetail getSelectionDetail(final int i, final float f, final int i2) {
        final List<SelectionDetail> selectionDetailsAtIndex = this.getSelectionDetailsAtIndex(i, i2);
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        final float minimumDistance = Utils.getMinimumDistance (selectionDetailsAtIndex, f, axisDependency);
        final YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        if (minimumDistance >= Utils.getMinimumDistance (selectionDetailsAtIndex, f, axisDependency2)) {
            axisDependency = axisDependency2;
        }
        return Utils.getClosestSelectionDetailByPixelY (selectionDetailsAtIndex, f, axisDependency);
    }

    protected List<SelectionDetail> getSelectionDetailsAtIndex(final int r14, final int r15) {
        /*
            r13 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            T extends com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider r1 = r13.mChart
            com.github.mikephil.charting.data.BarLineScatterCandleBubbleData r1 = r1.getData()
            if (r1 != 0) goto L_0x000e
            return r0
        L_0x000e:
            r1 = 2
            float[] r1 = new float[r1]
            T extends com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider r2 = r13.mChart
            com.github.mikephil.charting.data.BarLineScatterCandleBubbleData r2 = r2.getData()
            int r2 = r2.getDataSetCount()
            r3 = 0
            r4 = r3
        L_0x001d:
            if (r4 >= r2) goto L_0x006f
            r5 = -1
            if (r15 <= r5) goto L_0x0025
            if (r15 == r4) goto L_0x0025
            goto L_0x006c
        L_0x0025:
            T extends com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider r5 = r13.mChart
            com.github.mikephil.charting.data.BarLineScatterCandleBubbleData r5 = r5.getData()
            com.github.mikephil.charting.interfaces.datasets.IDataSet r5 = r5.getDataSetByIndex(r4)
            boolean r6 = r5.isHighlightEnabled()
            if (r6 != 0) goto L_0x0036
            goto L_0x006c
        L_0x0036:
            float[] r6 = r5.getYValsForXIndex(r14)
            int r7 = r6.length
            r8 = r3
        L_0x003c:
            if (r8 >= r7) goto L_0x006c
            r9 = r6[r8]
            boolean r10 = java.lang.Float.isNaN(r9)
            if (r10 == 0) goto L_0x0047
            goto L_0x0069
        L_0x0047:
            r10 = 1
            r1[r10] = r9
            T extends com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider r11 = r13.mChart
            com.github.mikephil.charting.components.YAxisAxisDependency r12 = r5.getAxisDependency()
            com.github.mikephil.charting.utils.Transformer r11 = r11.getTransformer(r12)
            r11.pointValuesToPixel(r1)
            r11 = r1[r10]
            boolean r11 = java.lang.Float.isNaN(r11)
            if (r11 != 0) goto L_0x0069
            com.github.mikephil.charting.utils.SelectionDetail r11 = new com.github.mikephil.charting.utils.SelectionDetail
            r10 = r1[r10]
            r11.<init>(r10, r9, r4, r5)
            r0.add(r11)
        L_0x0069:
            int r8 = r8 + 1
            goto L_0x003c
        L_0x006c:
            int r4 = r4 + 1
            goto L_0x001d
        L_0x006f:
            return r0
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.highlight.ChartHighlighter.getSelectionDetailsAtIndex(int, int):java.util.List");
    }
}
