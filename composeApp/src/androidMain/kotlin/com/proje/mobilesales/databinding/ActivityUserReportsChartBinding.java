package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityUserReportsChartBinding implements ViewBinding {
    public final ListView reportChartLV;
    public final AppCompatTextView reportChartTV;
    private final LinearLayout rootView;
    private ActivityUserReportsChartBinding(final LinearLayout linearLayout, final ListView listView, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        reportChartLV = listView;
        reportChartTV = appCompatTextView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityUserReportsChartBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityUserReportsChartBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityUserReportsChartBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_user_reports_chart, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityUserReportsChartBinding.bind(inflate);
    }
    public static ActivityUserReportsChartBinding bind(final View view) {
        int i2 = R.id.reportChartLV;
        final ListView listView = ViewBindings.findChildViewById(view, R.id.reportChartLV);
        if (null != listView) {
            i2 = R.id.reportChartTV;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.reportChartTV);
            if (null != appCompatTextView) {
                return new ActivityUserReportsChartBinding((LinearLayout) view, listView, appCompatTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
