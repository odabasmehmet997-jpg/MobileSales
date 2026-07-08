package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;

public final class AllreportBinding implements ViewBinding {
    public final ExpandableListView gridAllreport;
    public final AppBarSwipeRefreshLayout reportSwipeLayout;
    private final LinearLayout rootView;
    private AllreportBinding(final LinearLayout linearLayout, final ExpandableListView expandableListView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        rootView = linearLayout;
        gridAllreport = expandableListView;
        reportSwipeLayout = appBarSwipeRefreshLayout;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static AllreportBinding inflate(final LayoutInflater layoutInflater) {
        return AllreportBinding.inflate(layoutInflater, null, false);
    }
    public static AllreportBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.allreport, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AllreportBinding.bind(inflate);
    }
    public static AllreportBinding bind(final View view) {
        int i2 = R.id.grid_allreport;
        final ExpandableListView expandableListView = ViewBindings.findChildViewById(view, R.id.grid_allreport);
        if (null != expandableListView) {
            i2 = R.id.report_swipe_layout;
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.report_swipe_layout);
            if (null != appBarSwipeRefreshLayout) {
                return new AllreportBinding((LinearLayout) view, expandableListView, appBarSwipeRefreshLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
