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

public final class ItemCustomerGeneralBinding implements ViewBinding {

   
    public final LinearLayout lnCustomerGeneralProp;

   
    private final CardView rootView;

   
    public final TextView txtCustomerGeneralPropKey;

   
    public final TextView txtCustomerGeneralPropValue;

    private ItemCustomerGeneralBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2) {
        rootView = cardView;
        lnCustomerGeneralProp = linearLayout;
        txtCustomerGeneralPropKey = textView;
        txtCustomerGeneralPropValue = textView2;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCustomerGeneralBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCustomerGeneralBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCustomerGeneralBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_customer_general, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCustomerGeneralBinding.bind(inflate);
    }

   
    public static ItemCustomerGeneralBinding bind(final View view) {
        int i2 = R.id.ln_customerGeneralProp;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerGeneralProp);
        if (null != linearLayout) {
            i2 = R.id.txt_customerGeneralPropKey;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_customerGeneralPropKey);
            if (null != textView) {
                i2 = R.id.txt_customerGeneralPropValue;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_customerGeneralPropValue);
                if (null != textView2) {
                    return new ItemCustomerGeneralBinding((CardView) view, linearLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
