package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.FichePropertyTextView;

public final class ActivityCustomerCurrencyInfoBinding implements ViewBinding {

    public final AppCompatButton btnClear;
    public final AppCompatButton btnOk;
    public final FichePropertyTextView fpeCurrency;
    public final FichePropertyTextView fptCurrencyAmount;
    public final FichePropertyTextView fptRate;
    public final FichePropertyTextView fptTotal;
    public final LinearLayout lnClear;
    public final LinearLayout lnOk;
    private final LinearLayout rootView;
    private ActivityCustomerCurrencyInfoBinding( final LinearLayout linearLayout,  final AppCompatButton appCompatButton,  final AppCompatButton appCompatButton2,  final FichePropertyTextView fichePropertyTextView,  final FichePropertyTextView fichePropertyTextView2,  final FichePropertyTextView fichePropertyTextView3,  final FichePropertyTextView fichePropertyTextView4,  final LinearLayout linearLayout2,  final LinearLayout linearLayout3) {
        rootView = linearLayout;
        btnClear = appCompatButton;
        btnOk = appCompatButton2;
        fpeCurrency = fichePropertyTextView;
        fptCurrencyAmount = fichePropertyTextView2;
        fptRate = fichePropertyTextView3;
        fptTotal = fichePropertyTextView4;
        lnClear = linearLayout2;
        lnOk = linearLayout3;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityCustomerCurrencyInfoBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityCustomerCurrencyInfoBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityCustomerCurrencyInfoBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_customer_currency_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityCustomerCurrencyInfoBinding.bind(inflate);
    }
    public static ActivityCustomerCurrencyInfoBinding bind( final View view) {
        int i2 = R.id.btnClear;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnClear);
        if (null != appCompatButton) {
            i2 = R.id.btnOk;
            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnOk);
            if (null != appCompatButton2) {
                i2 = R.id.fpe_currency;
                final FichePropertyTextView fichePropertyTextView = ViewBindings.findChildViewById(view, R.id.fpe_currency);
                if (null != fichePropertyTextView) {
                    i2 = R.id.fpt_currencyAmount;
                    final FichePropertyTextView fichePropertyTextView2 = ViewBindings.findChildViewById(view, R.id.fpt_currencyAmount);
                    if (null != fichePropertyTextView2) {
                        i2 = R.id.fpt_Rate;
                        final FichePropertyTextView fichePropertyTextView3 = ViewBindings.findChildViewById(view, R.id.fpt_Rate);
                        if (null != fichePropertyTextView3) {
                            i2 = R.id.fpt_Total;
                            final FichePropertyTextView fichePropertyTextView4 = ViewBindings.findChildViewById(view, R.id.fpt_Total);
                            if (null != fichePropertyTextView4) {
                                i2 = R.id.lnClear;
                                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lnClear);
                                if (null != linearLayout) {
                                    i2 = R.id.lnOk;
                                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnOk);
                                    if (null != linearLayout2) {
                                        return new ActivityCustomerCurrencyInfoBinding((LinearLayout) view, appCompatButton, appCompatButton2, fichePropertyTextView, fichePropertyTextView2, fichePropertyTextView3, fichePropertyTextView4, linearLayout, linearLayout2);
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
