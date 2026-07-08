package com.proje.mobilesales.features.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View; 
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ChartItem;
import java.text.DecimalFormat;

public class PieChartItem extends ChartItem {
    private PieChart pieChart;
    public int getItemType() {
        return 2;
    }
    public PieChartItem(final ChartData<?> chartData) {
        super(chartData);
    }
    public Bitmap getChartBitmap() {
        return pieChart.getChartBitmap();
    }
    public View getView(final int i2, View view, final Context context) {
        final ViewHolder viewHolder;
        if (null == view) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item_piechart, null);
            viewHolder.chart = view.findViewById(R.id.chart);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final PieChart pieChart = viewHolder.chart;
        this.pieChart = pieChart;
        pieChart.setDescription("");
        viewHolder.chart.setDescriptionTextSize(20.0f);
        viewHolder.chart.setHoleRadius(52.0f);
        viewHolder.chart.setTransparentCircleRadius(57.0f);
        viewHolder.chart.setCenterTextSize(9.0f);
        viewHolder.chart.setUsePercentValues(false);
        viewHolder.chart.setExtraOffsets(5.0f, 10.0f, 5.0f, 10.0f);
        mChartData.setValueFormatter(new ValueFormatter() { // from class: com.proje.mobilesales.features.model.PieChartItem.1
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(final float f2, final Entry entry, final int i3, final ViewPortHandler viewPortHandler) {
                return new DecimalFormat(".##").format(f2);
            }
        });
        mChartData.setValueTextSize(11.0f);
        mChartData.setValueTextColor(ViewCompat.MEASURED_STATE_MASK);
        viewHolder.chart.setData((PieData) mChartData);
        viewHolder.chart.getLegend().setEnabled(true);
        viewHolder.chart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        viewHolder.chart.getLegend().setWordWrapEnabled(true);
        viewHolder.chart.animateY(TypedValues.Custom.TYPE_INT);
        return view;
    }
    private static class ViewHolder {
        PieChart chart;

        private ViewHolder() {
        }
    }
}
