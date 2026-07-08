package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
 
public final class ActivityLastOrderProductsBinding implements ViewBinding { 
    public final AppCompatCheckBox chkAllSalesmen;
    public final LinearLayout linearFilterDiv; 
    public final RecyclerView rcwList; 
    private final LinearLayout rootView; 
    public final AppBarSwipeRefreshLayout swipeLayout;
    private ActivityLastOrderProductsBinding(final LinearLayout linearLayout, final AppCompatCheckBox appCompatCheckBox, final LinearLayout linearLayout2, final RecyclerView recyclerView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        rootView = linearLayout;
        chkAllSalesmen = appCompatCheckBox;
        linearFilterDiv = linearLayout2;
        rcwList = recyclerView;
        swipeLayout = appBarSwipeRefreshLayout;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityLastOrderProductsBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityLastOrderProductsBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityLastOrderProductsBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_last_order_products, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityLastOrderProductsBinding.bind(inflate);
    } 
    public static ActivityLastOrderProductsBinding bind(final View view) {
        int i2 = R.id.chk_all_salesmen;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chk_all_salesmen);
        if (null != appCompatCheckBox) {
            i2 = R.id.linear_filter_div;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linear_filter_div);
            if (null != linearLayout) {
                i2 = R.id.rcwList;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                if (null != recyclerView) {
                    i2 = R.id.swipe_layout;
                    final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                    if (null != appBarSwipeRefreshLayout) {
                        return new ActivityLastOrderProductsBinding((LinearLayout) view, appCompatCheckBox, linearLayout, recyclerView, appBarSwipeRefreshLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
