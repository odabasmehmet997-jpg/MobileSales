package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.BarChart;
import com.proje.mobilesales.R;



public final class ListItemBarchartBinding implements ViewBinding {

   
    public final BarChart chart;

   
    private final LinearLayout rootView;

    private ListItemBarchartBinding(final LinearLayout linearLayout, final BarChart barChart) {
        rootView = linearLayout;
        chart = barChart;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemBarchartBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemBarchartBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemBarchartBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_barchart, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemBarchartBinding.bind(inflate);
    }

   
    public static ListItemBarchartBinding bind(final View view) {
        final BarChart barChart = ViewBindings.findChildViewById(view, R.id.chart);
        if (null != barChart) {
            return new ListItemBarchartBinding((LinearLayout) view, barChart);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.chart));
    }
}
