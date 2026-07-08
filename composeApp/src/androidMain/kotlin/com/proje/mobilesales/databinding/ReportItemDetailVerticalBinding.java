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



public final class ReportItemDetailVerticalBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout lnOrderStateVertical;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDefinition;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvGrossTotal;

   
    public final AppCompatTextView tvNetTotal;

   
    public final AppCompatTextView tvOrderState;

    private ReportItemDetailVerticalBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        lnOrderStateVertical = linearLayout3;
        tvCode = appCompatTextView;
        tvDefinition = appCompatTextView2;
        tvFicheNo = appCompatTextView3;
        tvGrossTotal = appCompatTextView4;
        tvNetTotal = appCompatTextView5;
        tvOrderState = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportItemDetailVerticalBinding inflate(final LayoutInflater layoutInflater) {
        return ReportItemDetailVerticalBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportItemDetailVerticalBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_item_detail_vertical, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportItemDetailVerticalBinding.bind(inflate);
    }

   
    public static ReportItemDetailVerticalBinding bind(final View view) {
        final LinearLayout linearLayout = (LinearLayout) view;
        int i2 = R.id.lnOrderStateVertical;
        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnOrderStateVertical);
        if (null != linearLayout2) {
            i2 = R.id.tvCode;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCode);
            if (null != appCompatTextView) {
                i2 = R.id.tvDefinition_;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDefinition_);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvFicheNo;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvGrossTotal;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvGrossTotal);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvNetTotal;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvOrderState;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvOrderState);
                                if (null != appCompatTextView6) {
                                    return new ReportItemDetailVerticalBinding(linearLayout, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
