package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class LastOrderProductItemBinding implements ViewBinding {

   
    public final LinearLayout lnAmountLayout;

   
    public final LinearLayout lnDateLayout;

   
    public final LinearLayout lnPriceLayout;

   
    public final RelativeLayout rltDate;

   
    public final RelativeLayout rltProductAmount;

   
    public final RelativeLayout rltProductContainer;

   
    public final RelativeLayout rltProductHeader;

   
    public final TextView rltProductName;

   
    public final RelativeLayout rltProductPrice;

   
    private final CardView rootView;

   
    public final TextView txtDate;

   
    public final TextView txtDiscount;

   
    public final TextView txtNetPrice;

   
    public final TextView txtPrice;

   
    public final TextView txtProductAmount;

   
    public final TextView txtProductCode;

   
    public final TextView txtShippedAmount;

    private LastOrderProductItemBinding(final CardView cardView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final TextView textView, final RelativeLayout relativeLayout5, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8) {
        rootView = cardView;
        lnAmountLayout = linearLayout;
        lnDateLayout = linearLayout2;
        lnPriceLayout = linearLayout3;
        rltDate = relativeLayout;
        rltProductAmount = relativeLayout2;
        rltProductContainer = relativeLayout3;
        rltProductHeader = relativeLayout4;
        rltProductName = textView;
        rltProductPrice = relativeLayout5;
        txtDate = textView2;
        txtDiscount = textView3;
        txtNetPrice = textView4;
        txtPrice = textView5;
        txtProductAmount = textView6;
        txtProductCode = textView7;
        txtShippedAmount = textView8;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static LastOrderProductItemBinding inflate(final LayoutInflater layoutInflater) {
        return LastOrderProductItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static LastOrderProductItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.last_order_product_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return LastOrderProductItemBinding.bind(inflate);
    }

   
    public static LastOrderProductItemBinding bind(final View view) {
        int i2 = R.id.ln_amount_layout;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_amount_layout);
        if (null != linearLayout) {
            i2 = R.id.ln_date_layout;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_date_layout);
            if (null != linearLayout2) {
                i2 = R.id.ln_price_layout;
                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_price_layout);
                if (null != linearLayout3) {
                    i2 = R.id.rlt_date;
                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_date);
                    if (null != relativeLayout) {
                        i2 = R.id.rlt_product_amount;
                        final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_product_amount);
                        if (null != relativeLayout2) {
                            i2 = R.id.rlt_product_container;
                            final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                            if (null != relativeLayout3) {
                                i2 = R.id.rlt_product_header;
                                final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                if (null != relativeLayout4) {
                                    i2 = R.id.rlt_productName;
                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.rlt_productName);
                                    if (null != textView) {
                                        i2 = R.id.rlt_product_price;
                                        final RelativeLayout relativeLayout5 = ViewBindings.findChildViewById(view, R.id.rlt_product_price);
                                        if (null != relativeLayout5) {
                                            i2 = R.id.txt_date;
                                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_date);
                                            if (null != textView2) {
                                                i2 = R.id.txt_discount;
                                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_discount);
                                                if (null != textView3) {
                                                    i2 = R.id.txt_net_price;
                                                    final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_net_price);
                                                    if (null != textView4) {
                                                        i2 = R.id.txt_price;
                                                        final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_price);
                                                        if (null != textView5) {
                                                            i2 = R.id.txt_product_amount;
                                                            final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_product_amount);
                                                            if (null != textView6) {
                                                                i2 = R.id.txt_productCode;
                                                                final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                                                if (null != textView7) {
                                                                    i2 = R.id.txt_shipped_amount;
                                                                    final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_shipped_amount);
                                                                    if (null != textView8) {
                                                                        return new LastOrderProductItemBinding((CardView) view, linearLayout, linearLayout2, linearLayout3, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, relativeLayout5, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
