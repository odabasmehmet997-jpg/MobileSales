package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class ActivityBaseReportWcfBinding implements ViewBinding {
    public final LinearLayout linearDate1;
    public final LinearLayout linearDate2;
    private final View rootView;
    public final AppCompatTextView tvDate1;
    public final AppCompatTextView tvDate2;
    private ActivityBaseReportWcfBinding( final View view,  final LinearLayout linearLayout,  final LinearLayout linearLayout2,  final AppCompatTextView appCompatTextView,  final AppCompatTextView appCompatTextView2) {
        rootView = view;
        linearDate1 = linearLayout;
        linearDate2 = linearLayout2;
        tvDate1 = appCompatTextView;
        tvDate2 = appCompatTextView2;
    }
    public View getRoot() {
        return rootView;
    }
    public static ActivityBaseReportWcfBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.activity_base_report_wcf, viewGroup, false);
        return bind(viewGroup);
    }
    public static ActivityBaseReportWcfBinding bind( final View view) {
        int i2 = R.id.linearDate1;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearDate1);
        if (null != linearLayout) {
            i2 = R.id.linearDate2;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearDate2);
            if (null != linearLayout2) {
                i2 = R.id.tvDate1;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvDate1);
                if (null != appCompatTextView) {
                    i2 = R.id.tvDate2;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDate2);
                    if (null != appCompatTextView2) {
                        return new ActivityBaseReportWcfBinding(view, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
