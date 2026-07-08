package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportSalesSummaryBinding implements ViewBinding {

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final LinearLayout linearProgressSpin;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

   
    public final AppCompatSpinner spUser;

   
    public final AppCompatTextView tvCount;

   
    public final AppCompatTextView tvFicheType;

   
    public final AppCompatTextView tvNetTotal;

   
    public final AppCompatTextView tvTotal;

   
    public final AppCompatTextView tvTotalDiscount;

   
    public final AppCompatTextView tvTotalVat;

    private ReportSalesSummaryBinding(final LinearLayout linearLayout, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final ListView listView, final AppCompatSpinner appCompatSpinner, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        linearProgressSpin = linearLayout5;
        lvReportOrder = listView;
        spUser = appCompatSpinner;
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

   
    public static ReportSalesSummaryBinding inflate(final LayoutInflater layoutInflater) {
        return ReportSalesSummaryBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportSalesSummaryBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_sales_summary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportSalesSummaryBinding.bind(inflate);
    }

   
    public static ReportSalesSummaryBinding bind(final View view) {
        int i2 = R.id.imgList;
        final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgList);
        if (null != appCompatImageButton) {
            i2 = R.id.linearDateLayout;
            final View findChildViewById = ViewBindings.findChildViewById(view, R.id.linearDateLayout);
            if (null != findChildViewById) {
                final ActivityBaseReportWcfBinding bind = ActivityBaseReportWcfBinding.bind(findChildViewById);
                i2 = R.id.linearFilterDiv;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearFilterDiv);
                if (null != linearLayout) {
                    i2 = R.id.linearMaterial2;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
                    if (null != linearLayout2) {
                        i2 = R.id.linearProgress;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.linearProgress);
                        if (null != linearLayout3) {
                            i2 = R.id.linearProgressSpin;
                            final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.linearProgressSpin);
                            if (null != linearLayout4) {
                                i2 = R.id.lvReportOrder;
                                final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                                if (null != listView) {
                                    i2 = R.id.spUser;
                                    final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spUser);
                                    if (null != appCompatSpinner) {
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
                                                                return new ReportSalesSummaryBinding((LinearLayout) view, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, listView, appCompatSpinner, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
