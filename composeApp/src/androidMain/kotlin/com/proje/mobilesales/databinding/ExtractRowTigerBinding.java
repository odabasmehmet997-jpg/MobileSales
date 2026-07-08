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
public final class ExtractRowTigerBinding implements ViewBinding {
    public final LinearLayout linearMaterial2;
    public final LinearLayout lnFicheBalance;
    public final LinearLayout lnFicheDescription;
    private final LinearLayout rootView;
    public final AppCompatTextView txtFicheBalance;
    public final AppCompatTextView txtFicheCredit;
    public final AppCompatTextView txtFicheDebit;
    public final AppCompatTextView txtFicheInfo;
    private ExtractRowTigerBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        lnFicheBalance = linearLayout3;
        lnFicheDescription = linearLayout4;
        txtFicheBalance = appCompatTextView;
        txtFicheCredit = appCompatTextView2;
        txtFicheDebit = appCompatTextView3;
        txtFicheInfo = appCompatTextView4;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractRowTigerBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractRowTigerBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractRowTigerBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_row_tiger, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractRowTigerBinding.bind(inflate);
    }
    public static ExtractRowTigerBinding bind(final View view) {
        final LinearLayout linearLayout = (LinearLayout) view;
        int i2 = R.id.ln_ficheBalance;
        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_ficheBalance);
        if (null != linearLayout2) {
            i2 = R.id.ln_ficheDescription;
            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_ficheDescription);
            if (null != linearLayout3) {
                i2 = R.id.txt_ficheBalance;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txt_ficheBalance);
                if (null != appCompatTextView) {
                    i2 = R.id.txt_ficheCredit;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txt_ficheCredit);
                    if (null != appCompatTextView2) {
                        i2 = R.id.txt_ficheDebit;
                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txt_ficheDebit);
                        if (null != appCompatTextView3) {
                            i2 = R.id.txt_ficheInfo;
                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txt_ficheInfo);
                            if (null != appCompatTextView4) {
                                return new ExtractRowTigerBinding(linearLayout, linearLayout, linearLayout2, linearLayout3, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
