package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportOrderAvgColumnBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final ScrollView rootView;

   
    public final AppCompatTextView tvAmount;

   
    public final AppCompatTextView tvDate;

   
    public final AppCompatTextView tvDiscount;

   
    public final AppCompatTextView tvExplanation;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvNetAmount;

    private ReportOrderAvgColumnBinding(final ScrollView scrollView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = scrollView;
        linearMaterial2 = linearLayout;
        tvAmount = appCompatTextView;
        tvDate = appCompatTextView2;
        tvDiscount = appCompatTextView3;
        tvExplanation = appCompatTextView4;
        tvFicheNo = appCompatTextView5;
        tvNetAmount = appCompatTextView6;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static ReportOrderAvgColumnBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrderAvgColumnBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrderAvgColumnBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_order_avg_column, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrderAvgColumnBinding.bind(inflate);
    }

   
    public static ReportOrderAvgColumnBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvAmount;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
            if (null != appCompatTextView) {
                i2 = R.id.tvDate;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDate);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvDiscount;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDiscount);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvExplanation;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvExplanation);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvFicheNo;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvNetAmount;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvNetAmount);
                                if (null != appCompatTextView6) {
                                    return new ReportOrderAvgColumnBinding((ScrollView) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
