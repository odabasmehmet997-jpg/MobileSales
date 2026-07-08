package com.proje.mobilesales.core.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.github.mikephil.charting.data.ChartData;
import java.io.File;
import java.io.FileOutputStream;

public abstract class ChartItem {
    protected static final int TYPE_BARCHART = 0;
    protected static final int TYPE_LINECHART = 1;
    protected static final int TYPE_PIECHART = 2;
    protected ChartData<?> mChartData;
    public abstract Bitmap getChartBitmap();
    public abstract int getItemType();
    public abstract View getView(int i2, View view, Context context);
    public ChartItem(ChartData<?> chartData) {
        this.mChartData = chartData;
    }
    public boolean saveChart(String str, String str2) {
        Bitmap chartBitmap = getChartBitmap();
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + str + ".png");
            chartBitmap.compress(Bitmap.CompressFormat.PNG, 40, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
