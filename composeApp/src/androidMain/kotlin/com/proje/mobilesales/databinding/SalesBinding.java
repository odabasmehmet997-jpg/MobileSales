package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class SalesBinding implements ViewBinding {
    public final ListView gridSales;
    private final LinearLayout rootView;
    private SalesBinding(final LinearLayout linearLayout, final ListView listView) {
        rootView = linearLayout;
        gridSales = listView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static SalesBinding inflate(final LayoutInflater layoutInflater) {
        return SalesBinding.inflate(layoutInflater, null, false);
    }
    public static SalesBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.sales, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SalesBinding.bind(inflate);
    }
    public static SalesBinding bind(final View view) {
        final ListView listView = ViewBindings.findChildViewById(view, R.id.grid_sales);
        if (null != listView) {
            return new SalesBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.grid_sales));
    }
}
