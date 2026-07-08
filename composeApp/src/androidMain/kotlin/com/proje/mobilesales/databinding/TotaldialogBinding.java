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

public final class TotaldialogBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvTotal;

   
    public final AppCompatTextView tvTotalDiscount;

   
    public final AppCompatTextView tvTotalLabel;

   
    public final AppCompatTextView tvTotalNet;

   
    public final AppCompatTextView tvTotalNetLabel;

   
    public final AppCompatTextView tvTotalVat;

    private TotaldialogBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = linearLayout;
        tvTotal = appCompatTextView;
        tvTotalDiscount = appCompatTextView2;
        tvTotalLabel = appCompatTextView3;
        tvTotalNet = appCompatTextView4;
        tvTotalNetLabel = appCompatTextView5;
        tvTotalVat = appCompatTextView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static TotaldialogBinding inflate(final LayoutInflater layoutInflater) {
        return TotaldialogBinding.inflate(layoutInflater, null, false);
    }

   
    public static TotaldialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.totaldialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return TotaldialogBinding.bind(inflate);
    }

   
    public static TotaldialogBinding bind(final View view) {
        int i2 = R.id.tvTotal;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvTotal);
        if (null != appCompatTextView) {
            i2 = R.id.tvTotalDiscount;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvTotalDiscount);
            if (null != appCompatTextView2) {
                i2 = R.id.tvTotalLabel;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvTotalLabel);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvTotalNet;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvTotalNet);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvTotalNetLabel;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvTotalNetLabel);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvTotalVat;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvTotalVat);
                            if (null != appCompatTextView6) {
                                return new TotaldialogBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
