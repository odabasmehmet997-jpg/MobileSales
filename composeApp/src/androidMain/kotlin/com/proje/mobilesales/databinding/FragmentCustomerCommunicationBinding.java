package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class FragmentCustomerCommunicationBinding implements ViewBinding {

   
    public final View divider1;

   
    public final View divider2;

   
    public final View divider3;

   
    public final View divider4;

   
    public final View divider5;

   
    public final ImageButton imgCustomerInvoiceAddress;

   
    public final ImageButton imgCustomerShipAddress;

   
    public final LinearLayout lnCustomerAllPerson;

   
    public final LinearLayout lnCustomerInvoiceAddress;

   
    public final LinearLayout lnCustomerShipAddress;

   
    public final RelativeLayout lnSalesPayment;

   
    public final NestedScrollView nestedScrollView;

   
    private final RelativeLayout rootView;

   
    public final TintableTextView txtAllPerson;

   
    public final TintableTextView txtCustomerInvoiceAddress;

   
    public final AppCompatTextView txtCustomerPerson;

   
    public final AppCompatTextView txtCustomerPerson1;

   
    public final TintableTextView txtCustomerShipAddress;

    private FragmentCustomerCommunicationBinding(final RelativeLayout relativeLayout, final View view, final View view2, final View view3, final View view4, final View view5, final ImageButton imageButton, final ImageButton imageButton2, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final RelativeLayout relativeLayout2, final NestedScrollView nestedScrollView, final TintableTextView tintableTextView, final TintableTextView tintableTextView2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final TintableTextView tintableTextView3) {
        rootView = relativeLayout;
        divider1 = view;
        divider2 = view2;
        divider3 = view3;
        divider4 = view4;
        divider5 = view5;
        imgCustomerInvoiceAddress = imageButton;
        imgCustomerShipAddress = imageButton2;
        lnCustomerAllPerson = linearLayout;
        lnCustomerInvoiceAddress = linearLayout2;
        lnCustomerShipAddress = linearLayout3;
        lnSalesPayment = relativeLayout2;
        this.nestedScrollView = nestedScrollView;
        txtAllPerson = tintableTextView;
        txtCustomerInvoiceAddress = tintableTextView2;
        txtCustomerPerson = appCompatTextView;
        txtCustomerPerson1 = appCompatTextView2;
        txtCustomerShipAddress = tintableTextView3;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentCustomerCommunicationBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerCommunicationBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentCustomerCommunicationBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_communication, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerCommunicationBinding.bind(inflate);
    }

   
    public static FragmentCustomerCommunicationBinding bind(final View view) {
        int i2 = R.id.divider1;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider1);
        if (null != findChildViewById) {
            i2 = R.id.divider2;
            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider2);
            if (null != findChildViewById2) {
                i2 = R.id.divider3;
                final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.divider3);
                if (null != findChildViewById3) {
                    i2 = R.id.divider4;
                    final View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.divider4);
                    if (null != findChildViewById4) {
                        i2 = R.id.divider5;
                        final View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.divider5);
                        if (null != findChildViewById5) {
                            i2 = R.id.img_customer_invoice_address;
                            final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_customer_invoice_address);
                            if (null != imageButton) {
                                i2 = R.id.img_customer_ship_address;
                                final ImageButton imageButton2 = ViewBindings.findChildViewById(view, R.id.img_customer_ship_address);
                                if (null != imageButton2) {
                                    i2 = R.id.ln_customer_all_person;
                                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customer_all_person);
                                    if (null != linearLayout) {
                                        i2 = R.id.ln_customer_invoice_address;
                                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_customer_invoice_address);
                                        if (null != linearLayout2) {
                                            i2 = R.id.ln_customer_ship_address;
                                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_customer_ship_address);
                                            if (null != linearLayout3) {
                                                i2 = R.id.ln_sales_payment;
                                                final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.ln_sales_payment);
                                                if (null != relativeLayout) {
                                                    i2 = R.id.nested_scroll_view;
                                                    final NestedScrollView nestedScrollView = ViewBindings.findChildViewById(view, R.id.nested_scroll_view);
                                                    if (null != nestedScrollView) {
                                                        i2 = R.id.txt_all_person;
                                                        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_all_person);
                                                        if (null != tintableTextView) {
                                                            i2 = R.id.txt_customer_invoice_address;
                                                            final TintableTextView tintableTextView2 = ViewBindings.findChildViewById(view, R.id.txt_customer_invoice_address);
                                                            if (null != tintableTextView2) {
                                                                i2 = R.id.txtCustomerPerson;
                                                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtCustomerPerson);
                                                                if (null != appCompatTextView) {
                                                                    i2 = R.id.txt_customer_person;
                                                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txt_customer_person);
                                                                    if (null != appCompatTextView2) {
                                                                        i2 = R.id.txt_customer_ship_address;
                                                                        final TintableTextView tintableTextView3 = ViewBindings.findChildViewById(view, R.id.txt_customer_ship_address);
                                                                        if (null != tintableTextView3) {
                                                                            return new FragmentCustomerCommunicationBinding((RelativeLayout) view, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, imageButton, imageButton2, linearLayout, linearLayout2, linearLayout3, relativeLayout, nestedScrollView, tintableTextView, tintableTextView2, appCompatTextView, appCompatTextView2, tintableTextView3);
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
