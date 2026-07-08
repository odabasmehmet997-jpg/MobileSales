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



public final class ItemSurplusDiscountBinding implements ViewBinding {

   
    public final LinearLayout lnItemSurplusDiscount;

   
    private final CardView rootView;

   
    public final TextView tvDiscountCode;

   
    public final TextView tvDiscountDefinition;

   
    public final TextView tvSurplusDiscount1;

   
    public final TextView tvSurplusDiscount2;

   
    public final TextView tvSurplusDiscount3;

   
    public final TextView tvSurplusDiscount4;

   
    public final TextView tvSurplusDiscount5;

   
    public final TextView tvSurplusDiscount6;

   
    public final TextView tvSurplusExtraAmount;

   
    public final TextView tvSurplusStockAmount;

   
    public final TextView tvSurplusStockName;

    private ItemSurplusDiscountBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11) {
        rootView = cardView;
        lnItemSurplusDiscount = linearLayout;
        tvDiscountCode = textView;
        tvDiscountDefinition = textView2;
        tvSurplusDiscount1 = textView3;
        tvSurplusDiscount2 = textView4;
        tvSurplusDiscount3 = textView5;
        tvSurplusDiscount4 = textView6;
        tvSurplusDiscount5 = textView7;
        tvSurplusDiscount6 = textView8;
        tvSurplusExtraAmount = textView9;
        tvSurplusStockAmount = textView10;
        tvSurplusStockName = textView11;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemSurplusDiscountBinding inflate(final LayoutInflater layoutInflater) {
        return ItemSurplusDiscountBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemSurplusDiscountBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_surplus_discount, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemSurplusDiscountBinding.bind(inflate);
    }

   
    public static ItemSurplusDiscountBinding bind(final View view) {
        int i2 = R.id.ln_item_surplus_discount;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_item_surplus_discount);
        if (null != linearLayout) {
            i2 = R.id.tv_discount_code;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_discount_code);
            if (null != textView) {
                i2 = R.id.tv_discount_definition;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_discount_definition);
                if (null != textView2) {
                    i2 = R.id.tv_surplus_discount1;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount1);
                    if (null != textView3) {
                        i2 = R.id.tv_surplus_discount2;
                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount2);
                        if (null != textView4) {
                            i2 = R.id.tv_surplus_discount3;
                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount3);
                            if (null != textView5) {
                                i2 = R.id.tv_surplus_discount4;
                                final TextView textView6 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount4);
                                if (null != textView6) {
                                    i2 = R.id.tv_surplus_discount5;
                                    final TextView textView7 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount5);
                                    if (null != textView7) {
                                        i2 = R.id.tv_surplus_discount6;
                                        final TextView textView8 = ViewBindings.findChildViewById(view, R.id.tv_surplus_discount6);
                                        if (null != textView8) {
                                            i2 = R.id.tv_surplus_extra_amount;
                                            final TextView textView9 = ViewBindings.findChildViewById(view, R.id.tv_surplus_extra_amount);
                                            if (null != textView9) {
                                                i2 = R.id.tv_surplus_stock_amount;
                                                final TextView textView10 = ViewBindings.findChildViewById(view, R.id.tv_surplus_stock_amount);
                                                if (null != textView10) {
                                                    i2 = R.id.tv_surplus_stock_name;
                                                    final TextView textView11 = ViewBindings.findChildViewById(view, R.id.tv_surplus_stock_name);
                                                    if (null != textView11) {
                                                        return new ItemSurplusDiscountBinding((CardView) view, linearLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
