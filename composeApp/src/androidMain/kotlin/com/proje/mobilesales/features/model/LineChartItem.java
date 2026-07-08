package com.proje.mobilesales.features.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ChartItem;

public class LineChartItem extends ChartItem {
    private LineChart lineChart;

    public int getItemType() {
        return 1;
    }

    public LineChartItem(final ChartData<?> chartData) {
        super(chartData);
    }

    public Bitmap getChartBitmap() {
        return lineChart.getChartBitmap();
    }

    public View getView(final int i2, View view, final Context context) {
        final ViewHolder viewHolder;
        if (null == view) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item_linechart, null);
            viewHolder.chart = view.findViewById(R.id.chart);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final LineChart lineChart = viewHolder.chart;
        this.lineChart = lineChart;
        lineChart.setDescription("");
        viewHolder.chart.setDrawGridBackground(false);
        viewHolder.chart.setDescriptionTextSize(16.0f);
        final XAxis xAxis = viewHolder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelRotationAngle(270.0f);
        final YAxis axisLeft = viewHolder.chart.getAxisLeft();
        axisLeft.setLabelCount(5, false);
        axisLeft.setStartAtZero(true);
        viewHolder.chart.getAxisRight().setEnabled(false);
        viewHolder.chart.getLegend().setEnabled(false);
        viewHolder.chart.setData((LineData) mChartData);
        viewHolder.chart.animateX(750);
        return view;
    }

    private static class ViewHolder {
        LineChart chart;

        private ViewHolder() {
        }
    }
}
