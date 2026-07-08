package com.proje.mobilesales.features.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ChartItem;
import java.text.DecimalFormat;

public class BarChartItem extends ChartItem {
    private BarChart barChart;

    public int getItemType() {
        return 0;
    }

    public Bitmap getChartBitmap() {
        return barChart.getChartBitmap();
    }

    public BarChartItem(final ChartData<?> chartData) {
        super(chartData);
    }

    public View getView(final int i2, View view, final Context context) {
        final ViewHolder viewHolder;
        if (null == view) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item_barchart, null);
            viewHolder.chart = view.findViewById(R.id.chart);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BarChart barChart = viewHolder.chart;
        this.barChart = barChart;
        barChart.setDescription("");
        viewHolder.chart.setDescriptionTextSize(16.0f);
        viewHolder.chart.setDrawGridBackground(false);
        viewHolder.chart.setDrawBarShadow(false);
        viewHolder.chart.getAxisRight().setEnabled(false);
        final XAxis xAxis = viewHolder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelRotationAngle(270.0f);
        final YAxis axisLeft = viewHolder.chart.getAxisLeft();
        axisLeft.setLabelCount(5, false);
        axisLeft.setSpaceTop(20.0f);
        viewHolder.chart.getLegend().setEnabled(false);
        viewHolder.chart.setData((BarData) mChartData);
        viewHolder.chart.animateY(TypedValues.TransitionType.TYPE_DURATION);
        mChartData.setValueFormatter(new ValueFormatter() { // from class: com.proje.mobilesales.features.model.BarChartItem.1
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(final float f2, final Entry entry, final int i3, final ViewPortHandler viewPortHandler) {
                return new DecimalFormat(".##").format(f2);
            }
        });
        return view;
    }

    private static class ViewHolder {
        BarChart chart;

        private ViewHolder() {
        }
    }
}
