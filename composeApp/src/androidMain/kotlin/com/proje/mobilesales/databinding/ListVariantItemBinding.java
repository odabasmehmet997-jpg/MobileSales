package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListVariantItemBinding implements ViewBinding {

   
    public final AppCompatCheckBox cbListItem;

   
    public final AppCompatEditText edtUserAmount;

   
    public final LinearLayout linearCheckList;

   
    public final LinearLayout lnProductActualStock;

   
    public final LinearLayout lnProductRealStock;

   
    private final LinearLayout rootView;

   
    public final TextView tvCode;

   
    public final TextView tvVariantName;

   
    public final TextView txtProductActualStock;

   
    public final TextView txtProductActualStockText;

   
    public final TextView txtProductRealStockText;

   
    public final TextView txtRealStock;

    private ListVariantItemBinding(final LinearLayout linearLayout, final AppCompatCheckBox appCompatCheckBox, final AppCompatEditText appCompatEditText, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6) {
        rootView = linearLayout;
        cbListItem = appCompatCheckBox;
        edtUserAmount = appCompatEditText;
        linearCheckList = linearLayout2;
        lnProductActualStock = linearLayout3;
        lnProductRealStock = linearLayout4;
        tvCode = textView;
        tvVariantName = textView2;
        txtProductActualStock = textView3;
        txtProductActualStockText = textView4;
        txtProductRealStockText = textView5;
        txtRealStock = textView6;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListVariantItemBinding inflate(final LayoutInflater layoutInflater) {
        return ListVariantItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListVariantItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_variant_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListVariantItemBinding.bind(inflate);
    }

   
    public static ListVariantItemBinding bind(final View view) {
        int i2 = R.id.cbListItem;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.cbListItem);
        if (null != appCompatCheckBox) {
            i2 = R.id.edtUserAmount;
            final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edtUserAmount);
            if (null != appCompatEditText) {
                final LinearLayout linearLayout = (LinearLayout) view;
                i2 = R.id.ln_product_actual_stock;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_product_actual_stock);
                if (null != linearLayout2) {
                    i2 = R.id.ln_product_real_stock;
                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_product_real_stock);
                    if (null != linearLayout3) {
                        i2 = R.id.tvCode;
                        final TextView textView = ViewBindings.findChildViewById(view, R.id.tvCode);
                        if (null != textView) {
                            i2 = R.id.tvVariantName;
                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvVariantName);
                            if (null != textView2) {
                                i2 = R.id.txt_product_actual_stock;
                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock);
                                if (null != textView3) {
                                    i2 = R.id.txt_product_actual_stock_text;
                                    final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock_text);
                                    if (null != textView4) {
                                        i2 = R.id.txt_product_real_stock_text;
                                        final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_product_real_stock_text);
                                        if (null != textView5) {
                                            i2 = R.id.txt_real_stock;
                                            final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_real_stock);
                                            if (null != textView6) {
                                                return new ListVariantItemBinding(linearLayout, appCompatCheckBox, appCompatEditText, linearLayout, linearLayout2, linearLayout3, textView, textView2, textView3, textView4, textView5, textView6);
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
