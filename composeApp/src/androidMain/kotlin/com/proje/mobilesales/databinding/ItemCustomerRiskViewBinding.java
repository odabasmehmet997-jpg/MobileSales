package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemCustomerRiskViewBinding implements ViewBinding {

   
    public final CheckBox chkRisk;

   
    public final LinearLayout lnRiskContainer;

   
    private final CardView rootView;

   
    public final TextView txtRiskClosed;

   
    public final TextView txtRiskLimit;

   
    public final TextView txtRiskName;

   
    public final TextView txtRiskTotal;

    private ItemCustomerRiskViewBinding(final CardView cardView, final CheckBox checkBox, final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = cardView;
        chkRisk = checkBox;
        lnRiskContainer = linearLayout;
        txtRiskClosed = textView;
        txtRiskLimit = textView2;
        txtRiskName = textView3;
        txtRiskTotal = textView4;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCustomerRiskViewBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCustomerRiskViewBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCustomerRiskViewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_customer_risk_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCustomerRiskViewBinding.bind(inflate);
    }

   
    public static ItemCustomerRiskViewBinding bind(final View view) {
        int i2 = R.id.chk_risk;
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_risk);
        if (null != checkBox) {
            i2 = R.id.ln_riskContainer;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_riskContainer);
            if (null != linearLayout) {
                i2 = R.id.txt_riskClosed;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_riskClosed);
                if (null != textView) {
                    i2 = R.id.txt_riskLimit;
                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_riskLimit);
                    if (null != textView2) {
                        i2 = R.id.txt_riskName;
                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_riskName);
                        if (null != textView3) {
                            i2 = R.id.txt_riskTotal;
                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_riskTotal);
                            if (null != textView4) {
                                return new ItemCustomerRiskViewBinding((CardView) view, checkBox, linearLayout, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
