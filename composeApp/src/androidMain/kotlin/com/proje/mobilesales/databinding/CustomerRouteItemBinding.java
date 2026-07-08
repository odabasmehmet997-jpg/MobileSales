package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;  
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class CustomerRouteItemBinding implements ViewBinding {

    public final LinearLayout lnCityTowmLayout;
    public final LinearLayout lnShipAddressLayout;
    public final RelativeLayout rltCustomerContainer;
    public final RelativeLayout rltCustomerDetail;
    public final RelativeLayout rltCustomerHeader;
    public final RelativeLayout rltShipAddress;
    private final CardView rootView;
    public final TextView txtCustomerCity;
    public final TextView txtCustomerCode;
    public final TextView txtCustomerName;
    public final TextView txtCustomerTown;
    public final TextView txtShipAddress;
    private CustomerRouteItemBinding(final CardView cardView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5) {
        rootView = cardView;
        lnCityTowmLayout = linearLayout;
        lnShipAddressLayout = linearLayout2;
        rltCustomerContainer = relativeLayout;
        rltCustomerDetail = relativeLayout2;
        rltCustomerHeader = relativeLayout3;
        rltShipAddress = relativeLayout4;
        txtCustomerCity = textView;
        txtCustomerCode = textView2;
        txtCustomerName = textView3;
        txtCustomerTown = textView4;
        txtShipAddress = textView5;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static CustomerRouteItemBinding inflate(final LayoutInflater layoutInflater) {
        return CustomerRouteItemBinding.inflate(layoutInflater, null, false);
    }
    public static CustomerRouteItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.customer_route_item, viewGroup, false);
        if (z && viewGroup != null) {
            viewGroup.addView(inflate);
        }
        return CustomerRouteItemBinding.bind(inflate);
    }
    public static CustomerRouteItemBinding bind(final View view) {
        int i2 = R.id.ln_city_towm_layout;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_city_towm_layout);
        if (null != linearLayout) {
            i2 = R.id.ln_ship_address_layout;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_ship_address_layout);
            if (null != linearLayout2) {
                i2 = R.id.rlt_customer_container;
                final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_customer_container);
                if (null != relativeLayout) {
                    i2 = R.id.rlt_customer_detail;
                    final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_customer_detail);
                    if (null != relativeLayout2) {
                        i2 = R.id.rlt_customer_header;
                        final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_customer_header);
                        if (null != relativeLayout3) {
                            i2 = R.id.rlt_shipAddress;
                            final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_shipAddress);
                            if (null != relativeLayout4) {
                                i2 = R.id.txt_customer_city;
                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_customer_city);
                                if (null != textView) {
                                    i2 = R.id.txt_customerCode;
                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_customerCode);
                                    if (null != textView2) {
                                        i2 = R.id.txt_customerName;
                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_customerName);
                                        if (null != textView3) {
                                            i2 = R.id.txt_customer_town;
                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_customer_town);
                                            if (null != textView4) {
                                                i2 = R.id.txt_shipAddress;
                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_shipAddress);
                                                if (null != textView5) {
                                                    return new CustomerRouteItemBinding((CardView) view, linearLayout, linearLayout2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3, textView4, textView5);
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
