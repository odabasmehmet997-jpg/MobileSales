package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportSalesOrderInvoiceBinding implements ViewBinding {

   
    public final AppCompatCheckBox chGroup;

   
    public final AppCompatImageButton imgList;

   
    public final ActivityBaseReportWcfBinding linearDateLayout;

   
    public final LinearLayout linearFilterDiv;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final LinearLayout linearProgressSpin;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

   
    public final AppCompatSpinner spOrderType;

   
    public final AppCompatSpinner spUser;

   
    public final AppCompatSpinner spWareHouse;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDefinition;

   
    public final AppCompatTextView tvGrossTotal;

   
    public final AppCompatTextView tvLineType;

   
    public final TextView tvNetTotal;

   
    public final AppCompatTextView tvQuantity;

   
    public final AppCompatTextView tvShowing;

   
    public final AppCompatTextView tvTotal;

    private ReportSalesOrderInvoiceBinding(final LinearLayout linearLayout, final AppCompatCheckBox appCompatCheckBox, final AppCompatImageButton appCompatImageButton, final ActivityBaseReportWcfBinding activityBaseReportWcfBinding, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final ListView listView, final AppCompatSpinner appCompatSpinner, final AppCompatSpinner appCompatSpinner2, final AppCompatSpinner appCompatSpinner3, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final TextView textView, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7) {
        rootView = linearLayout;
        chGroup = appCompatCheckBox;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        linearProgressSpin = linearLayout5;
        lvReportOrder = listView;
        spOrderType = appCompatSpinner;
        spUser = appCompatSpinner2;
        spWareHouse = appCompatSpinner3;
        tvCode = appCompatTextView;
        tvDefinition = appCompatTextView2;
        tvGrossTotal = appCompatTextView3;
        tvLineType = appCompatTextView4;
        tvNetTotal = textView;
        tvQuantity = appCompatTextView5;
        tvShowing = appCompatTextView6;
        tvTotal = appCompatTextView7;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportSalesOrderInvoiceBinding inflate(final LayoutInflater layoutInflater) {
        return ReportSalesOrderInvoiceBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportSalesOrderInvoiceBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_sales_order_invoice, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportSalesOrderInvoiceBinding.bind(inflate);
    }

   
    public static ReportSalesOrderInvoiceBinding bind(final View view) {
        int i2 = R.id.chGroup;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chGroup);
        if (null != appCompatCheckBox) {
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
                                i2 = R.id.linearProgressSpin;
                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.linearProgressSpin);
                                if (null != linearLayout4) {
                                    i2 = R.id.lvReportOrder;
                                    final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                                    if (null != listView) {
                                        i2 = R.id.spOrderType;
                                        final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spOrderType);
                                        if (null != appCompatSpinner) {
                                            i2 = R.id.spUser;
                                            final AppCompatSpinner appCompatSpinner2 = ViewBindings.findChildViewById(view, R.id.spUser);
                                            if (null != appCompatSpinner2) {
                                                i2 = R.id.spWareHouse;
                                                final AppCompatSpinner appCompatSpinner3 = ViewBindings.findChildViewById(view, R.id.spWareHouse);
                                                if (null != appCompatSpinner3) {
                                                    i2 = R.id.tvCode;
                                                    final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCode);
                                                    if (null != appCompatTextView) {
                                                        i2 = R.id.tvDefinition_;
                                                        final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDefinition_);
                                                        if (null != appCompatTextView2) {
                                                            i2 = R.id.tvGrossTotal;
                                                            final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvGrossTotal);
                                                            if (null != appCompatTextView3) {
                                                                i2 = R.id.tvLineType;
                                                                final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvLineType);
                                                                if (null != appCompatTextView4) {
                                                                    i2 = R.id.tvNetTotal;
                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                                                                    if (null != textView) {
                                                                        i2 = R.id.tvQuantity;
                                                                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvQuantity);
                                                                        if (null != appCompatTextView5) {
                                                                            i2 = R.id.tvShowing;
                                                                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvShowing);
                                                                            if (null != appCompatTextView6) {
                                                                                i2 = R.id.tvTotal;
                                                                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                                                                                if (null != appCompatTextView7) {
                                                                                    return new ReportSalesOrderInvoiceBinding((LinearLayout) view, appCompatCheckBox, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, listView, appCompatSpinner, appCompatSpinner2, appCompatSpinner3, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, textView, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
