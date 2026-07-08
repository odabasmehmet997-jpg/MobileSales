package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.view.CustomHorizontalScrollView;

public final class ActivityUserReportsGridBinding implements ViewBinding {
    public final CustomHorizontalScrollView reportBodyHCW;
    public final CustomHorizontalScrollView reportFooterDataHCW;
    public final CustomHorizontalScrollView reportFooterHCW;
    public final CustomHorizontalScrollView reportHeaderHCW;
    private final LinearLayout rootView;
    public final TableRow userReportFooterDataRow;
    public final TableRow userReportFooterRow;
    public final TableRow userReportHeaderRow;
    public final RecyclerView userReportRecyclerView;
    private ActivityUserReportsGridBinding(final LinearLayout linearLayout, final CustomHorizontalScrollView customHorizontalScrollView, final CustomHorizontalScrollView customHorizontalScrollView2, final CustomHorizontalScrollView customHorizontalScrollView3, final CustomHorizontalScrollView customHorizontalScrollView4, final TableRow tableRow, final TableRow tableRow2, final TableRow tableRow3, final RecyclerView recyclerView) {
        rootView = linearLayout;
        reportBodyHCW = customHorizontalScrollView;
        reportFooterDataHCW = customHorizontalScrollView2;
        reportFooterHCW = customHorizontalScrollView3;
        reportHeaderHCW = customHorizontalScrollView4;
        userReportFooterDataRow = tableRow;
        userReportFooterRow = tableRow2;
        userReportHeaderRow = tableRow3;
        userReportRecyclerView = recyclerView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityUserReportsGridBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityUserReportsGridBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityUserReportsGridBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_user_reports_grid, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityUserReportsGridBinding.bind(inflate);
    }
    public static ActivityUserReportsGridBinding bind(final View view) {
        int i2 = R.id.reportBodyHCW;
        final CustomHorizontalScrollView customHorizontalScrollView = ViewBindings.findChildViewById(view, R.id.reportBodyHCW);
        if (null != customHorizontalScrollView) {
            i2 = R.id.reportFooterDataHCW;
            final CustomHorizontalScrollView customHorizontalScrollView2 = ViewBindings.findChildViewById(view, R.id.reportFooterDataHCW);
            if (null != customHorizontalScrollView2) {
                i2 = R.id.reportFooterHCW;
                final CustomHorizontalScrollView customHorizontalScrollView3 = ViewBindings.findChildViewById(view, R.id.reportFooterHCW);
                if (null != customHorizontalScrollView3) {
                    i2 = R.id.reportHeaderHCW;
                    final CustomHorizontalScrollView customHorizontalScrollView4 = ViewBindings.findChildViewById(view, R.id.reportHeaderHCW);
                    if (null != customHorizontalScrollView4) {
                        i2 = R.id.userReportFooterDataRow;
                        final TableRow tableRow = ViewBindings.findChildViewById(view, R.id.userReportFooterDataRow);
                        if (null != tableRow) {
                            i2 = R.id.userReportFooterRow;
                            final TableRow tableRow2 = ViewBindings.findChildViewById(view, R.id.userReportFooterRow);
                            if (null != tableRow2) {
                                i2 = R.id.userReportHeaderRow;
                                final TableRow tableRow3 = ViewBindings.findChildViewById(view, R.id.userReportHeaderRow);
                                if (null != tableRow3) {
                                    i2 = R.id.userReportRecyclerView;
                                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.userReportRecyclerView);
                                    if (null != recyclerView) {
                                        return new ActivityUserReportsGridBinding((LinearLayout) view, customHorizontalScrollView, customHorizontalScrollView2, customHorizontalScrollView3, customHorizontalScrollView4, tableRow, tableRow2, tableRow3, recyclerView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
