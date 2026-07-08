package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.SelectionDetail;

import java.util.ArrayList;
import java.util.List;

public class CombinedHighlighter extends ChartHighlighter<BarLineScatterCandleBubbleDataProvider> {
    public CombinedHighlighter(final BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider) {
        super (barLineScatterCandleBubbleDataProvider);
    }

    protected List<SelectionDetail> getSelectionDetailsAtIndex(final int i, final int i2) {
        int i3;
        int i4;
        final ArrayList arrayList = new ArrayList ();
        final float[] fArr = new float[2];
        final List<ChartData> allData = ((CombinedData) this.mChart.getData ()).getAllData ();
        for (int i5 = 0; i5 < allData.size (); i5++) {
            for (int i6 = 0; i6 < allData.get (i5).getDataSetCount (); i6++) {
                final IDataSet dataSetByIndex = allData.get (i5).getDataSetByIndex (i6);
                if (dataSetByIndex.isHighlightEnabled ()) {
                    final float[] yValsForXIndex = dataSetByIndex.getYValsForXIndex (i);
                    int length = yValsForXIndex.length;
                    int i7 = 0;
                    while (i7 < length) {
                        final float f = yValsForXIndex[i7];
                        fArr[1] = f;
                        this.mChart.getTransformer (dataSetByIndex.getAxisDependency ()).pointValuesToPixel (fArr);
                        if (!Float.isNaN (fArr[1])) {
                            i4 = i7;
                            i3 = length;
                            arrayList.add (new SelectionDetail (fArr[1], f, i5, i6, dataSetByIndex));
                        } else {
                            i4 = i7;
                            i3 = length;
                        }
                        i7 = i4 + 1;
                        length = i3;
                    }
                }
            }
        }
        return arrayList;
    }
}
