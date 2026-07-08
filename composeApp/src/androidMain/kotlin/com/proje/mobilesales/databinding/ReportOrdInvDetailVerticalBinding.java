package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportOrdInvDetailVerticalBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final ScrollView rootView;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDefinition;

   
    public final AppCompatTextView tvGrossTotal;

   
    public final AppCompatTextView tvLineType;

   
    public final AppCompatTextView tvNetTotal;

   
    public final AppCompatTextView tvQuantity;

    private ReportOrdInvDetailVerticalBinding(final ScrollView scrollView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = scrollView;
        linearMaterial2 = linearLayout;
        tvCode = appCompatTextView;
        tvDefinition = appCompatTextView2;
        tvGrossTotal = appCompatTextView3;
        tvLineType = appCompatTextView4;
        tvNetTotal = appCompatTextView5;
        tvQuantity = appCompatTextView6;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static ReportOrdInvDetailVerticalBinding inflate(final LayoutInflater layoutInflater) {
        return ReportOrdInvDetailVerticalBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportOrdInvDetailVerticalBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_ord_inv_detail_vertical, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportOrdInvDetailVerticalBinding.bind(inflate);
    }

   
    public static ReportOrdInvDetailVerticalBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
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
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvQuantity;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvQuantity);
                                if (null != appCompatTextView6) {
                                    return new ReportOrdInvDetailVerticalBinding((ScrollView) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
