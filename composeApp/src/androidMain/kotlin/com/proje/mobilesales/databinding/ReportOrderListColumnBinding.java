package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportOrderListColumnBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvAmount;

   
    public final AppCompatTextView tvDate;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvState;

   
    public final AppCompatTextView tvStatus;

   
    public final AppCompatTextView tvType;

    private ReportOrderListColumnBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        tvAmount = appCompatTextView;
        tvDate = appCompatTextView2;
        tvFicheNo = appCompatTextView3;
        tvState = appCompatTextView4;
        tvStatus = appCompatTextView5;
        tvType = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportOrderListColumnBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrderListColumnBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrderListColumnBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_order_list_column, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrderListColumnBinding.bind(inflate);
    }

   
    public static ReportOrderListColumnBinding bind(final View view) {
        final LinearLayout linearLayout = (LinearLayout) view;
        int i2 = R.id.tvAmount;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
        if (null != appCompatTextView) {
            i2 = R.id.tvDate;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDate);
            if (null != appCompatTextView2) {
                i2 = R.id.tvFicheNo;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvState;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvState);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvStatus;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvStatus);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvType;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvType);
                            if (null != appCompatTextView6) {
                                return new ReportOrderListColumnBinding(linearLayout, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
