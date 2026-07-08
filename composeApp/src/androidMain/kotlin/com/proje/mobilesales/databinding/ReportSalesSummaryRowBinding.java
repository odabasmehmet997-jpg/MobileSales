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



public final class ReportSalesSummaryRowBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCount;

   
    public final AppCompatTextView tvFicheType;

   
    public final AppCompatTextView tvNetTotal;

   
    public final AppCompatTextView tvTotal;

   
    public final AppCompatTextView tvTotalDiscount;

   
    public final AppCompatTextView tvTotalVat;

    private ReportSalesSummaryRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        tvCount = appCompatTextView;
        tvFicheType = appCompatTextView2;
        tvNetTotal = appCompatTextView3;
        tvTotal = appCompatTextView4;
        tvTotalDiscount = appCompatTextView5;
        tvTotalVat = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportSalesSummaryRowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportSalesSummaryRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportSalesSummaryRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_sales_summary_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportSalesSummaryRowBinding.bind(inflate);
    }

   
    public static ReportSalesSummaryRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvCount;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCount);
            if (null != appCompatTextView) {
                i2 = R.id.tvFicheType;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvFicheType);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvNetTotal;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvTotal;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvTotalDiscount;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvTotalDiscount);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvTotalVat;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvTotalVat);
                                if (null != appCompatTextView6) {
                                    return new ReportSalesSummaryRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
