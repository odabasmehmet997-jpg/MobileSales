package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class ItemCabinBinding implements ViewBinding {

    
    public final ImageView btnCabinOperation;

    
    public final LinearLayout lnBrand;

    
    public final LinearLayout lnCabinCode;

    
    public final RelativeLayout lnCabinContainer;

    
    private final CardView rootView;

    
    public final TextView txtCabinBrand;

    
    public final TextView txtCabinCode;

    
    public final TextView txtCabinModel;

    private ItemCabinBinding(final CardView cardView, final ImageView imageView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final RelativeLayout relativeLayout, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = cardView;
        btnCabinOperation = imageView;
        lnBrand = linearLayout;
        lnCabinCode = linearLayout2;
        lnCabinContainer = relativeLayout;
        txtCabinBrand = textView;
        txtCabinCode = textView2;
        txtCabinModel = textView3;
    }
    
    public CardView getRoot() {
        return rootView;
    }

    
    public static ItemCabinBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCabinBinding.inflate(layoutInflater, null, false);
    }

    
    public static ItemCabinBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_cabin, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCabinBinding.bind(inflate);
    }

    
    public static ItemCabinBinding bind(final View view) {
        int i2 = R.id.btn_cabinOperation;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.btn_cabinOperation);
        if (null != imageView) {
            i2 = R.id.ln_brand;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_brand);
            if (null != linearLayout) {
                i2 = R.id.ln_CabinCode;
                final LinearLayout linearLayout2 =  ViewBindings.findChildViewById(view, R.id.ln_CabinCode);
                if (null != linearLayout2) {
                    i2 = R.id.ln_CabinContainer;
                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.ln_CabinContainer);
                    if (null != relativeLayout) {
                        i2 = R.id.txt_CabinBrand;
                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_CabinBrand);
                        if (null != textView) {
                            i2 = R.id.txt_CabinCode;
                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_CabinCode);
                            if (null != textView2) {
                                i2 = R.id.txt_CabinModel;
                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_CabinModel);
                                if (null != textView3) {
                                    return new ItemCabinBinding((CardView) view, imageView, linearLayout, linearLayout2, relativeLayout, textView, textView2, textView3);
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
