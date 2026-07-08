package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;

import java.util.ArrayList;
import java.util.List;

public class CombinedData extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<?>> {
    private com.github.mikephil.charting.data.BarData mBarData;
    private com.github.mikephil.charting.data.BubbleData mBubbleData;
    private com.github.mikephil.charting.data.CandleData mCandleData;
    private com.github.mikephil.charting.data.LineData mLineData;
    private com.github.mikephil.charting.data.ScatterData mScatterData;

    public CombinedData() {
    }

    public CombinedData(List<String> list) {
        super (list);
    }

    public CombinedData(String[] strArr) {
        super (strArr);
    }

    public void setData(com.github.mikephil.charting.data.LineData lineData) {
        this.mLineData = lineData;
        this.mDataSets.addAll (lineData.getDataSets ());
        this.init();
    }

    public void setData(com.github.mikephil.charting.data.BarData barData) {
        this.mBarData = barData;
        this.mDataSets.addAll (barData.getDataSets ());
        this.init();
    }

    public void setData(com.github.mikephil.charting.data.ScatterData scatterData) {
        this.mScatterData = scatterData;
        this.mDataSets.addAll (scatterData.getDataSets ());
        this.init();
    }

    public void setData(com.github.mikephil.charting.data.CandleData candleData) {
        this.mCandleData = candleData;
        this.mDataSets.addAll (candleData.getDataSets ());
        this.init();
    }

    public void setData(com.github.mikephil.charting.data.BubbleData bubbleData) {
        this.mBubbleData = bubbleData;
        this.mDataSets.addAll (bubbleData.getDataSets ());
        this.init();
    }

    public com.github.mikephil.charting.data.BubbleData getBubbleData() {
        return this.mBubbleData;
    }

    public com.github.mikephil.charting.data.LineData getLineData() {
        return this.mLineData;
    }

    public com.github.mikephil.charting.data.BarData getBarData() {
        return this.mBarData;
    }

    public com.github.mikephil.charting.data.ScatterData getScatterData() {
        return this.mScatterData;
    }

    public com.github.mikephil.charting.data.CandleData getCandleData() {
        return this.mCandleData;
    }

    public List<ChartData> getAllData() {
        ArrayList arrayList = new ArrayList ();
        com.github.mikephil.charting.data.LineData lineData = this.mLineData;
        if (null != lineData) {
            arrayList.add (lineData);
        }
        com.github.mikephil.charting.data.BarData barData = this.mBarData;
        if (null != barData) {
            arrayList.add (barData);
        }
        com.github.mikephil.charting.data.ScatterData scatterData = this.mScatterData;
        if (null != scatterData) {
            arrayList.add (scatterData);
        }
        com.github.mikephil.charting.data.CandleData candleData = this.mCandleData;
        if (null != candleData) {
            arrayList.add (candleData);
        }
        com.github.mikephil.charting.data.BubbleData bubbleData = this.mBubbleData;
        if (null != bubbleData) {
            arrayList.add (bubbleData);
        }
        return arrayList;
    }

    public void notifyDataChanged() {
        LineData lineData = this.mLineData;
        if (null != lineData) {
            lineData.notifyDataChanged ();
        }
        BarData barData = this.mBarData;
        if (null != barData) {
            barData.notifyDataChanged ();
        }
        CandleData candleData = this.mCandleData;
        if (null != candleData) {
            candleData.notifyDataChanged ();
        }
        ScatterData scatterData = this.mScatterData;
        if (null != scatterData) {
            scatterData.notifyDataChanged ();
        }
        BubbleData bubbleData = this.mBubbleData;
        if (null != bubbleData) {
            bubbleData.notifyDataChanged ();
        }
        this.init();
    }

    public Entry getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight r6) {
        /*
            r5 = this;
            java.util.List r0 = r5.getAllData()
            int r1 = r6.getDataIndex()
            int r2 = r0.size()
            r3 = 0
            if (r1 < r2) goto L_0x0010
            return r3
        L_0x0010:
            int r1 = r6.getDataIndex()
            java.lang.Object r0 = r0.get(r1)
            com.github.mikephil.charting.data.ChartData r0 = (com.github.mikephil.charting.data.ChartData) r0
            int r1 = r6.getDataSetIndex()
            int r2 = r0.getDataSetCount()
            if (r1 < r2) goto L_0x0025
            return r3
        L_0x0025:
            int r1 = r6.getDataSetIndex()
            com.github.mikephil.charting.interfaces.datasets.IDataSet r0 = r0.getDataSetByIndex(r1)
            int r1 = r6.getXIndex()
            java.util.List r0 = r0.getEntriesForXIndex(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x0039:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005c
            java.lang.Object r1 = r0.next()
            com.github.mikephil.charting.data.Entry r1 = (com.github.mikephil.charting.data.Entry) r1
            float r2 = r1.getVal()
            float r4 = r6.getValue()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x005b
            float r2 = r6.getValue()
            boolean r2 = java.lang.Float.isNaN(r2)
            if (r2 == 0) goto L_0x0039
        L_0x005b:
            return r1
        L_0x005c:
            return r3
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.data.CombinedData.getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight):com.github.mikephil.charting.data.Entry");
    }
}
