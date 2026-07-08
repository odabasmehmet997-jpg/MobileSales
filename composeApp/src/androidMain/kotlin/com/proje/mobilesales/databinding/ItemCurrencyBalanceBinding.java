package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemCurrencyBalanceBinding implements ViewBinding {

   
    public final LinearLayout lnItemCurrencyBalance;

   
    private final CardView rootView;

   
    public final TextView tvIdBalance;

   
    public final TextView tvIdCurrency;

   
    public final TextView tvIdDebit;

   
    public final TextView tvLcBalance;

   
    public final TextView tvRcBalance;

   
    public final TextView tvTcCredit;

    private ItemCurrencyBalanceBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6) {
        rootView = cardView;
        lnItemCurrencyBalance = linearLayout;
        tvIdBalance = textView;
        tvIdCurrency = textView2;
        tvIdDebit = textView3;
        tvLcBalance = textView4;
        tvRcBalance = textView5;
        tvTcCredit = textView6;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCurrencyBalanceBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCurrencyBalanceBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCurrencyBalanceBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_currency_balance, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCurrencyBalanceBinding.bind(inflate);
    }

   
    public static ItemCurrencyBalanceBinding bind(final View view) {
        int i2 = R.id.ln_item_currency_balance;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_item_currency_balance);
        if (null != linearLayout) {
            i2 = R.id.tv_idBalance;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_idBalance);
            if (null != textView) {
                i2 = R.id.tv_idCurrency;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_idCurrency);
                if (null != textView2) {
                    i2 = R.id.tv_idDebit;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tv_idDebit);
                    if (null != textView3) {
                        i2 = R.id.tv_lcBalance;
                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tv_lcBalance);
                        if (null != textView4) {
                            i2 = R.id.tv_rcBalance;
                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.tv_rcBalance);
                            if (null != textView5) {
                                i2 = R.id.tv_tcCredit;
                                final TextView textView6 = ViewBindings.findChildViewById(view, R.id.tv_tcCredit);
                                if (null != textView6) {
                                    return new ItemCurrencyBalanceBinding((CardView) view, linearLayout, textView, textView2, textView3, textView4, textView5, textView6);
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
