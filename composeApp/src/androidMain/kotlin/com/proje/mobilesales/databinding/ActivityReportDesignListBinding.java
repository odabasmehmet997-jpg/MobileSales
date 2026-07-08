package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityReportDesignListBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final RecyclerView rwReportDesignList;
    private ActivityReportDesignListBinding( final LinearLayout linearLayout,  final RecyclerView recyclerView) {
        rootView = linearLayout;
        rwReportDesignList = recyclerView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityReportDesignListBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityReportDesignListBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityReportDesignListBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_report_design_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityReportDesignListBinding.bind(inflate);
    }
    public static ActivityReportDesignListBinding bind( final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwReportDesignList);
        if (null != recyclerView) {
            return new ActivityReportDesignListBinding((LinearLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rwReportDesignList));
    }
}
