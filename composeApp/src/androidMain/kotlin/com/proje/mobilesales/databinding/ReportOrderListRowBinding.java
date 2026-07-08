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



public final class ReportOrderListRowBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtViewAmount;

   
    public final AppCompatTextView txtViewDate;

   
    public final AppCompatTextView txtViewFicheNo;

   
    public final AppCompatTextView txtViewState;

   
    public final AppCompatTextView txtViewStatus;

   
    public final AppCompatTextView txtViewType;

    private ReportOrderListRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        txtViewAmount = appCompatTextView;
        txtViewDate = appCompatTextView2;
        txtViewFicheNo = appCompatTextView3;
        txtViewState = appCompatTextView4;
        txtViewStatus = appCompatTextView5;
        txtViewType = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportOrderListRowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrderListRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrderListRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_order_list_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrderListRowBinding.bind(inflate);
    }

   
    public static ReportOrderListRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.txtViewAmount;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtViewAmount);
            if (null != appCompatTextView) {
                i2 = R.id.txtViewDate;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtViewDate);
                if (null != appCompatTextView2) {
                    i2 = R.id.txtViewFicheNo;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtViewFicheNo);
                    if (null != appCompatTextView3) {
                        i2 = R.id.txtViewState;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewState);
                        if (null != appCompatTextView4) {
                            i2 = R.id.txtViewStatus;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtViewStatus);
                            if (null != appCompatTextView5) {
                                i2 = R.id.txtViewType;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txtViewType);
                                if (null != appCompatTextView6) {
                                    return new ReportOrderListRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
