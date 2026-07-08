package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class DefaultFillFormatter implements FillFormatter {
    public float getFillLinePosition(ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
        float yChartMax = lineDataProvider.getYChartMax ();
        float yChartMin = lineDataProvider.getYChartMin ();
        LineData lineData = lineDataProvider.getLineData ();
        if (0.0f < iLineDataSet.getYMax () && 0.0f > iLineDataSet.getYMin ()) {
            return 0.0f;
        }
        if (0.0f < lineData.getYMax ()) {
            yChartMax = 0.0f;
        }
        if (0.0f > lineData.getYMin ()) {
            yChartMin = 0.0f;
        }
        return 0.0f <= iLineDataSet.getYMin () ? yChartMin : yChartMax;
    }
}
