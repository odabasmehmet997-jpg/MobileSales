package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSalesLogisticsBinding implements ViewBinding {

   
    public final ImageView imgSalesShipAddressAccountClear;

   
    public final ImageView imgSalesShipAddressClear;

   
    public final LinearLayout lnSalesInvoiceAddress;

   
    public final LinearLayout lnSalesShipAddress;

   
    public final LinearLayout lnSalesShipAddressCustomer;

   
    public final LinearLayout lnShipAgent;

   
    public final LinearLayout lnShipType;

   
    public final LinearLayout lnTotalGrossVolume;

   
    public final LinearLayout lnTotalGrossWeight;

   
    public final LinearLayout lnTotalNetVolume;

   
    public final LinearLayout lnTotalNetWeight;

   
    private final LinearLayout rootView;

   
    public final TextView txtSalesInvoiceAddress;

   
    public final TextView txtSalesShipAddress;

   
    public final TextView txtSalesShipAddressAccount;

   
    public final TextView txtSalesShipAgent;

   
    public final TextView txtSalesShipType;

   
    public final TextView txtTotalGrossVolume;

   
    public final TextView txtTotalGrossWeight;

   
    public final TextView txtTotalNetVolume;

   
    public final TextView txtTotalNetWeight;

    private FragmentSalesLogisticsBinding(final LinearLayout linearLayout, final ImageView imageView, final ImageView imageView2, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9) {
        rootView = linearLayout;
        imgSalesShipAddressAccountClear = imageView;
        imgSalesShipAddressClear = imageView2;
        lnSalesInvoiceAddress = linearLayout2;
        lnSalesShipAddress = linearLayout3;
        lnSalesShipAddressCustomer = linearLayout4;
        lnShipAgent = linearLayout5;
        lnShipType = linearLayout6;
        lnTotalGrossVolume = linearLayout7;
        lnTotalGrossWeight = linearLayout8;
        lnTotalNetVolume = linearLayout9;
        lnTotalNetWeight = linearLayout10;
        txtSalesInvoiceAddress = textView;
        txtSalesShipAddress = textView2;
        txtSalesShipAddressAccount = textView3;
        txtSalesShipAgent = textView4;
        txtSalesShipType = textView5;
        txtTotalGrossVolume = textView6;
        txtTotalGrossWeight = textView7;
        txtTotalNetVolume = textView8;
        txtTotalNetWeight = textView9;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSalesLogisticsBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSalesLogisticsBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSalesLogisticsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_sales_logistics, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSalesLogisticsBinding.bind(inflate);
    }

   
    public static FragmentSalesLogisticsBinding bind(final View view) {
        int i2 = R.id.img_salesShipAddressAccountClear;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_salesShipAddressAccountClear);
        if (null != imageView) {
            i2 = R.id.img_salesShipAddressClear;
            final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.img_salesShipAddressClear);
            if (null != imageView2) {
                i2 = R.id.ln_salesInvoiceAddress;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_salesInvoiceAddress);
                if (null != linearLayout) {
                    i2 = R.id.ln_salesShipAddress;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_salesShipAddress);
                    if (null != linearLayout2) {
                        i2 = R.id.ln_salesShipAddressCustomer;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_salesShipAddressCustomer);
                        if (null != linearLayout3) {
                            i2 = R.id.ln_shipAgent;
                            final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_shipAgent);
                            if (null != linearLayout4) {
                                i2 = R.id.ln_shipType;
                                final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_shipType);
                                if (null != linearLayout5) {
                                    i2 = R.id.ln_totalGrossVolume;
                                    final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_totalGrossVolume);
                                    if (null != linearLayout6) {
                                        i2 = R.id.ln_totalGrossWeight;
                                        final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_totalGrossWeight);
                                        if (null != linearLayout7) {
                                            i2 = R.id.ln_totalNetVolume;
                                            final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_totalNetVolume);
                                            if (null != linearLayout8) {
                                                i2 = R.id.ln_totalNetWeight;
                                                final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_totalNetWeight);
                                                if (null != linearLayout9) {
                                                    i2 = R.id.txt_salesInvoiceAddress;
                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_salesInvoiceAddress);
                                                    if (null != textView) {
                                                        i2 = R.id.txt_salesShipAddress;
                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_salesShipAddress);
                                                        if (null != textView2) {
                                                            i2 = R.id.txt_salesShipAddressAccount;
                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_salesShipAddressAccount);
                                                            if (null != textView3) {
                                                                i2 = R.id.txt_salesShipAgent;
                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_salesShipAgent);
                                                                if (null != textView4) {
                                                                    i2 = R.id.txt_salesShipType;
                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_salesShipType);
                                                                    if (null != textView5) {
                                                                        i2 = R.id.txt_totalGrossVolume;
                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_totalGrossVolume);
                                                                        if (null != textView6) {
                                                                            i2 = R.id.txt_totalGrossWeight;
                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_totalGrossWeight);
                                                                            if (null != textView7) {
                                                                                i2 = R.id.txt_totalNetVolume;
                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_totalNetVolume);
                                                                                if (null != textView8) {
                                                                                    i2 = R.id.txt_totalNetWeight;
                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_totalNetWeight);
                                                                                    if (null != textView9) {
                                                                                        return new FragmentSalesLogisticsBinding((LinearLayout) view, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
