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
public final class ExtractInvoiceDetailRowBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final AppCompatTextView tvAmount;
    public final AppCompatTextView tvItem;
    public final AppCompatTextView tvKdv;
    public final AppCompatTextView tvKdvAmount;
    public final AppCompatTextView tvPrPrice;
    public final AppCompatTextView tvPrice;
    public final AppCompatTextView tvTotal;
    private ExtractInvoiceDetailRowBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7) {
        rootView = linearLayout;
        tvAmount = appCompatTextView;
        tvItem = appCompatTextView2;
        tvKdv = appCompatTextView3;
        tvKdvAmount = appCompatTextView4;
        tvPrPrice = appCompatTextView5;
        tvPrice = appCompatTextView6;
        tvTotal = appCompatTextView7;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractInvoiceDetailRowBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractInvoiceDetailRowBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractInvoiceDetailRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_invoice_detail_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractInvoiceDetailRowBinding.bind(inflate);
    }
    public static ExtractInvoiceDetailRowBinding bind(final View view) {
        int i2 = R.id.tvAmount;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
        if (null != appCompatTextView) {
            i2 = R.id.tvItem;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvItem);
            if (null != appCompatTextView2) {
                i2 = R.id.tvKdv;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvKdv);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvKdvAmount;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvKdvAmount);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvPrPrice;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvPrPrice);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvPrice;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvPrice);
                            if (null != appCompatTextView6) {
                                i2 = R.id.tvTotal;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                                if (null != appCompatTextView7) {
                                    return new ExtractInvoiceDetailRowBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
