package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ReportOrderBinding implements ViewBinding {

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final LinearLayout linearProgressSpin;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

   
    public final TextView spUser;

   
    public final AppCompatTextView tvCode;

   
    public final TextView tvDefinition;

   
    public final AppCompatTextView tvFicheNo;

   
    public final TextView tvGrossTotal;

   
    public final TextView tvNetTotal;

   
    public final TextView tvOrderState;

   
    public final AppCompatTextView tvShowing;

   
    public final AppCompatTextView tvTotal;

    private ReportOrderBinding(final LinearLayout linearLayout, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final ListView listView, final TextView textView, final AppCompatTextView appCompatTextView, final TextView textView2, final AppCompatTextView appCompatTextView2, final TextView textView3, final TextView textView4, final TextView textView5, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        linearProgressSpin = linearLayout5;
        lvReportOrder = listView;
        spUser = textView;
        tvCode = appCompatTextView;
        tvDefinition = textView2;
        tvFicheNo = appCompatTextView2;
        tvGrossTotal = textView3;
        tvNetTotal = textView4;
        tvOrderState = textView5;
        tvShowing = appCompatTextView3;
        tvTotal = appCompatTextView4;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportOrderBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrderBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrderBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_order, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrderBinding.bind(inflate);
    }

   
    public static ReportOrderBinding bind(final View view) {
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
                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.spUser);
                                    if (null != textView) {
                                        i2 = R.id.tvCode;
                                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCode);
                                        if (null != appCompatTextView) {
                                            i2 = R.id.tvDefinition_;
                                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvDefinition_);
                                            if (null != textView2) {
                                                i2 = R.id.tvFicheNo;
                                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                                                if (null != appCompatTextView2) {
                                                    i2 = R.id.tvGrossTotal;
                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tvGrossTotal);
                                                    if (null != textView3) {
                                                        i2 = R.id.tvNetTotal;
                                                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                                                        if (null != textView4) {
                                                            i2 = R.id.tvOrderState;
                                                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.tvOrderState);
                                                            if (null != textView5) {
                                                                i2 = R.id.tvShowing;
                                                                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvShowing);
                                                                if (null != appCompatTextView3) {
                                                                    i2 = R.id.tvTotal;
                                                                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                                                                    if (null != appCompatTextView4) {
                                                                        return new ReportOrderBinding((LinearLayout) view, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, listView, textView, appCompatTextView, textView2, appCompatTextView2, textView3, textView4, textView5, appCompatTextView3, appCompatTextView4);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
