package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ProductOperationDiscountBinding implements ViewBinding {

   
    public final EditText edtProductDiscount;

   
    public final ImageButton imgBtnProductDiscountTypeChange;

   
    public final RelativeLayout rltProductOperationDiscount;

   
    private final RelativeLayout rootView;

   
    public final TextView txtProductDiscountMinus;

    private ProductOperationDiscountBinding(final RelativeLayout relativeLayout, final EditText editText, final ImageButton imageButton, final RelativeLayout relativeLayout2, final TextView textView) {
        rootView = relativeLayout;
        edtProductDiscount = editText;
        imgBtnProductDiscountTypeChange = imageButton;
        rltProductOperationDiscount = relativeLayout2;
        txtProductDiscountMinus = textView;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static ProductOperationDiscountBinding inflate(final LayoutInflater layoutInflater) {
        return ProductOperationDiscountBinding.inflate(layoutInflater, null, false);
    }

   
    public static ProductOperationDiscountBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.product_operation_discount, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ProductOperationDiscountBinding.bind(inflate);
    }

   
    public static ProductOperationDiscountBinding bind(final View view) {
        int i2 = R.id.edt_product_discount;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_product_discount);
        if (null != editText) {
            i2 = R.id.imgBtn_product_discount_type_change;
            final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.imgBtn_product_discount_type_change);
            if (null != imageButton) {
                final RelativeLayout relativeLayout = (RelativeLayout) view;
                i2 = R.id.txt_product_discount_minus;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_product_discount_minus);
                if (null != textView) {
                    return new ProductOperationDiscountBinding(relativeLayout, editText, imageButton, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
