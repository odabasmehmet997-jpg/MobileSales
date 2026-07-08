package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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



public final class ReportCollectionsListBinding implements ViewBinding {

   
    public final AppCompatTextView appCompatTextView;

   
    public final FrameLayout frmDebitFilter;

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final LinearLayout lnDebitFilter;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

   
    public final AppCompatSpinner spClose;

   
    public final AppCompatTextView txtViewCalDate;

   
    public final AppCompatTextView txtViewCalDay;

   
    public final AppCompatTextView txtViewCalTotal;

    private ReportCollectionsListBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final FrameLayout frameLayout, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final ListView listView, final AppCompatSpinner appCompatSpinner, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        this.appCompatTextView = appCompatTextView;
        frmDebitFilter = frameLayout;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        lnDebitFilter = linearLayout5;
        lvReportOrder = listView;
        spClose = appCompatSpinner;
        txtViewCalDate = appCompatTextView2;
        txtViewCalDay = appCompatTextView3;
        txtViewCalTotal = appCompatTextView4;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportCollectionsListBinding inflate(final LayoutInflater layoutInflater) {
        return ReportCollectionsListBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportCollectionsListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_collections_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportCollectionsListBinding.bind(inflate);
    }

   
    public static ReportCollectionsListBinding bind(final View view) {
        int i2 = R.id.appCompatTextView;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.appCompatTextView);
        if (null != appCompatTextView) {
            i2 = R.id.frm_debit_filter;
            final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.frm_debit_filter);
            if (null != frameLayout) {
                i2 = R.id.imgList;
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
                                    i2 = R.id.ln_debit_filter;
                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_debit_filter);
                                    if (null != linearLayout4) {
                                        i2 = R.id.lvReportOrder;
                                        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                                        if (null != listView) {
                                            i2 = R.id.spClose;
                                            final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spClose);
                                            if (null != appCompatSpinner) {
                                                i2 = R.id.txtViewCalDate;
                                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtViewCalDate);
                                                if (null != appCompatTextView2) {
                                                    i2 = R.id.txtViewCalDay;
                                                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtViewCalDay);
                                                    if (null != appCompatTextView3) {
                                                        i2 = R.id.txtViewCalTotal;
                                                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewCalTotal);
                                                        if (null != appCompatTextView4) {
                                                            return new ReportCollectionsListBinding((LinearLayout) view, appCompatTextView, frameLayout, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, listView, appCompatSpinner, appCompatTextView2, appCompatTextView3, appCompatTextView4);
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
