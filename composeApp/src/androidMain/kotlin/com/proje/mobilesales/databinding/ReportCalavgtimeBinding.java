package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportCalavgtimeBinding implements ViewBinding {

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearProgress;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtViewCreditCalDate;

   
    public final AppCompatTextView txtViewCreditCalDay;

   
    public final AppCompatTextView txtViewCreditCalTotal;

   
    public final AppCompatTextView txtViewDebitCalDay;

   
    public final AppCompatTextView txtViewDebitCalTotal;

   
    public final AppCompatTextView txtViewDebitDate;

    private ReportCalavgtimeBinding(final LinearLayout linearLayout, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearProgress = linearLayout3;
        txtViewCreditCalDate = appCompatTextView;
        txtViewCreditCalDay = appCompatTextView2;
        txtViewCreditCalTotal = appCompatTextView3;
        txtViewDebitCalDay = appCompatTextView4;
        txtViewDebitCalTotal = appCompatTextView5;
        txtViewDebitDate = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportCalavgtimeBinding inflate(final LayoutInflater layoutInflater) {
        return ReportCalavgtimeBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportCalavgtimeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_calavgtime, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportCalavgtimeBinding.bind(inflate);
    }

   
    public static ReportCalavgtimeBinding bind(final View view) {
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
                    i2 = R.id.linearProgress;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearProgress);
                    if (null != linearLayout2) {
                        i2 = R.id.txtViewCreditCalDate;
                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtViewCreditCalDate);
                        if (null != appCompatTextView) {
                            i2 = R.id.txtViewCreditCalDay;
                            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtViewCreditCalDay);
                            if (null != appCompatTextView2) {
                                i2 = R.id.txtViewCreditCalTotal;
                                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtViewCreditCalTotal);
                                if (null != appCompatTextView3) {
                                    i2 = R.id.txtViewDebitCalDay;
                                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewDebitCalDay);
                                    if (null != appCompatTextView4) {
                                        i2 = R.id.txtViewDebitCalTotal;
                                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtViewDebitCalTotal);
                                        if (null != appCompatTextView5) {
                                            i2 = R.id.txtViewDebitDate;
                                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txtViewDebitDate);
                                            if (null != appCompatTextView6) {
                                                return new ReportCalavgtimeBinding((LinearLayout) view, appCompatImageButton, bind, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
