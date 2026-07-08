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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportOrderListBinding implements ViewBinding {

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

   
    public final AppCompatSpinner spOrderStatus;

   
    public final AppCompatSpinner spStatus;

    private ReportOrderListBinding(final LinearLayout linearLayout, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final ListView listView, final AppCompatSpinner appCompatSpinner, final AppCompatSpinner appCompatSpinner2) {
        rootView = linearLayout;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        lvReportOrder = listView;
        spOrderStatus = appCompatSpinner;
        spStatus = appCompatSpinner2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportOrderListBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrderListBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrderListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_order_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrderListBinding.bind(inflate);
    }

   
    public static ReportOrderListBinding bind(final View view) {
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
                            i2 = R.id.lvReportOrder;
                            final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                            if (null != listView) {
                                i2 = R.id.spOrderStatus;
                                final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spOrderStatus);
                                if (null != appCompatSpinner) {
                                    i2 = R.id.spStatus;
                                    final AppCompatSpinner appCompatSpinner2 = ViewBindings.findChildViewById(view, R.id.spStatus);
                                    if (null != appCompatSpinner2) {
                                        return new ReportOrderListBinding((LinearLayout) view, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, listView, appCompatSpinner, appCompatSpinner2);
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
