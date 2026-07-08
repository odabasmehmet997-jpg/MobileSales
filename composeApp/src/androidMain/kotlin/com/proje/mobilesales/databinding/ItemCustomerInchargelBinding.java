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



public final class ItemCustomerInchargelBinding implements ViewBinding {

   
    public final LinearLayout lnCustomerIncharge;

   
    private final CardView rootView;

   
    public final TextView tvCustomerInchargeDefinition;

   
    public final TextView tvCustomerInchargeEmail;

   
    public final TextView tvCustomerInchargeName;

   
    public final TextView tvCustomerInchargeTel;

    private ItemCustomerInchargelBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = cardView;
        lnCustomerIncharge = linearLayout;
        tvCustomerInchargeDefinition = textView;
        tvCustomerInchargeEmail = textView2;
        tvCustomerInchargeName = textView3;
        tvCustomerInchargeTel = textView4;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCustomerInchargelBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCustomerInchargelBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCustomerInchargelBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_customer_inchargel, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCustomerInchargelBinding.bind(inflate);
    }

   
    public static ItemCustomerInchargelBinding bind(final View view) {
        int i2 = R.id.ln_customer_incharge;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customer_incharge);
        if (null != linearLayout) {
            i2 = R.id.tv_customer_incharge_definition;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_customer_incharge_definition);
            if (null != textView) {
                i2 = R.id.tv_customer_incharge_email;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_customer_incharge_email);
                if (null != textView2) {
                    i2 = R.id.tv_customer_incharge_name;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tv_customer_incharge_name);
                    if (null != textView3) {
                        i2 = R.id.tv_customer_incharge_tel;
                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tv_customer_incharge_tel);
                        if (null != textView4) {
                            return new ItemCustomerInchargelBinding((CardView) view, linearLayout, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
