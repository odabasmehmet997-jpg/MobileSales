package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.PieChart;
import com.proje.mobilesales.R;



public final class ListItemPiechartBinding implements ViewBinding {

   
    public final PieChart chart;

   
    private final LinearLayout rootView;

    private ListItemPiechartBinding(final LinearLayout linearLayout, final PieChart pieChart) {
        rootView = linearLayout;
        chart = pieChart;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemPiechartBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemPiechartBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemPiechartBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_piechart, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemPiechartBinding.bind(inflate);
    }

   
    public static ListItemPiechartBinding bind(final View view) {
        final PieChart pieChart = ViewBindings.findChildViewById(view, R.id.chart);
        if (null != pieChart) {
            return new ListItemPiechartBinding((LinearLayout) view, pieChart);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.chart));
    }
}
