package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.LineChart;
import com.proje.mobilesales.R;



public final class ListItemLinechartBinding implements ViewBinding {

   
    public final LineChart chart;

   
    private final LinearLayout rootView;

    private ListItemLinechartBinding(final LinearLayout linearLayout, final LineChart lineChart) {
        rootView = linearLayout;
        chart = lineChart;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemLinechartBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemLinechartBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemLinechartBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_linechart, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemLinechartBinding.bind(inflate);
    }

   
    public static ListItemLinechartBinding bind(final View view) {
        final LineChart lineChart = ViewBindings.findChildViewById(view, R.id.chart);
        if (null != lineChart) {
            return new ListItemLinechartBinding((LinearLayout) view, lineChart);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.chart));
    }
}
