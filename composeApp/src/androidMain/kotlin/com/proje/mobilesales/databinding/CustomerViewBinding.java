package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;

public final class CustomerViewBinding implements ViewBinding {
    public final ImageButton imgCustomerLocation;
    public final ImageView imgProduct;
    public final LinearLayout lnCustomerDebit;
    public final LinearLayout lnCustomerEInvoice;
    public final LinearLayout lnSales;
    public final LinearLayout lnSalesInfoNot;
    public final LinearLayout lnSalesPayment;
    public final RelativeLayout rltCustomerDetail;
    public final RelativeLayout rltCustomerEInvoice;
    public final RelativeLayout rltProductContainer;
    public final RelativeLayout rltProductHeader;
    public final RelativeLayout rltSalesInfoNot;
    private final View rootView;
    public final TextView txtCustomerAverage;
    public final TextView txtCustomerDebit;
    public final TintableTextView txtCustomerEInvoiceNote;
    public final TextView txtCustomerEmail;
    public final TintableTextView txtCustomerInfoNot;
    public final TextView txtCustomerLastOrderDate;
    public final TextView txtCustomerPayPlan;
    public final TextView txtCustomerPerson;
    public final TextView txtCustomerTel;
    public final TextView txtProductCode;
    public final TextView txtProductName;
    public final TextView txtProductPrice;
    public final TextView txtRealStock;
    public final TextView txtSecondCustomerTitle;
    private CustomerViewBinding(final View view, final ImageButton imageButton, final ImageView imageView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final RelativeLayout relativeLayout5, final TextView textView, final TextView textView2, final TintableTextView tintableTextView, final TextView textView3, final TintableTextView tintableTextView2, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12) {
        rootView = view;
        imgCustomerLocation = imageButton;
        imgProduct = imageView;
        lnCustomerDebit = linearLayout;
        lnCustomerEInvoice = linearLayout2;
        lnSales = linearLayout3;
        lnSalesInfoNot = linearLayout4;
        lnSalesPayment = linearLayout5;
        rltCustomerDetail = relativeLayout;
        rltCustomerEInvoice = relativeLayout2;
        rltProductContainer = relativeLayout3;
        rltProductHeader = relativeLayout4;
        rltSalesInfoNot = relativeLayout5;
        txtCustomerAverage = textView;
        txtCustomerDebit = textView2;
        txtCustomerEInvoiceNote = tintableTextView;
        txtCustomerEmail = textView3;
        txtCustomerInfoNot = tintableTextView2;
        txtCustomerLastOrderDate = textView4;
        txtCustomerPayPlan = textView5;
        txtCustomerPerson = textView6;
        txtCustomerTel = textView7;
        txtProductCode = textView8;
        txtProductName = textView9;
        txtProductPrice = textView10;
        txtRealStock = textView11;
        txtSecondCustomerTitle = textView12;
    }
    public View getRoot() {
        return rootView;
    }
    public static CustomerViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.customer_view, viewGroup);
        return CustomerViewBinding.bind(viewGroup);
    }
    public static CustomerViewBinding bind(final View view) {
        int i2 = R.id.img_customer_location;
        final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_customer_location);
        if (null != imageButton) {
            i2 = R.id.img_product;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_product);
            if (null != imageView) {
                i2 = R.id.ln_customer_debit;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customer_debit);
                if (null != linearLayout) {
                    i2 = R.id.ln_customer_e_invoice;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_customer_e_invoice);
                    if (null != linearLayout2) {
                        i2 = R.id.ln_sales;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_sales);
                        if (null != linearLayout3) {
                            i2 = R.id.ln_sales_info_not;
                            final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_sales_info_not);
                            if (null != linearLayout4) {
                                i2 = R.id.ln_sales_payment;
                                final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_sales_payment);
                                if (null != linearLayout5) {
                                    i2 = R.id.rlt_customer_detail;
                                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_customer_detail);
                                    if (null != relativeLayout) {
                                        i2 = R.id.rlt_customer_e_invoice;
                                        final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_customer_e_invoice);
                                        if (null != relativeLayout2) {
                                            i2 = R.id.rlt_product_container;
                                            final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                            if (null != relativeLayout3) {
                                                i2 = R.id.rlt_product_header;
                                                final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                                if (null != relativeLayout4) {
                                                    i2 = R.id.rlt_sales_info_not;
                                                    final RelativeLayout relativeLayout5 = ViewBindings.findChildViewById(view, R.id.rlt_sales_info_not);
                                                    if (null != relativeLayout5) {
                                                        i2 = R.id.txt_customer_average;
                                                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_customer_average);
                                                        if (null != textView) {
                                                            i2 = R.id.txt_customer_debit;
                                                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_customer_debit);
                                                            if (null != textView2) {
                                                                i2 = R.id.txt_customer_e_invoice_note;
                                                                final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_customer_e_invoice_note);
                                                                if (null != tintableTextView) {
                                                                    i2 = R.id.txt_customer_email;
                                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_customer_email);
                                                                    if (null != textView3) {
                                                                        i2 = R.id.txt_customer_info_not;
                                                                        final TintableTextView tintableTextView2 = ViewBindings.findChildViewById(view, R.id.txt_customer_info_not);
                                                                        if (null != tintableTextView2) {
                                                                            i2 = R.id.txt_customer_last_order_date;
                                                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_customer_last_order_date);
                                                                            if (null != textView4) {
                                                                                i2 = R.id.txt_customer_pay_plan;
                                                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_customer_pay_plan);
                                                                                if (null != textView5) {
                                                                                    i2 = R.id.txt_customer_person;
                                                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_customer_person);
                                                                                    if (null != textView6) {
                                                                                        i2 = R.id.txt_customer_tel;
                                                                                        final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_customer_tel);
                                                                                        if (null != textView7) {
                                                                                            i2 = R.id.txt_productCode;
                                                                                            final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                                                                            if (null != textView8) {
                                                                                                i2 = R.id.txt_productName;
                                                                                                final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                                                                if (null != textView9) {
                                                                                                    i2 = R.id.txt_product_price;
                                                                                                    final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_product_price);
                                                                                                    if (null != textView10) {
                                                                                                        i2 = R.id.txt_real_stock;
                                                                                                        final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_real_stock);
                                                                                                        if (null != textView11) {
                                                                                                            i2 = R.id.txt_secondCustomerTitle;
                                                                                                            final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_secondCustomerTitle);
                                                                                                            if (null != textView12) {
                                                                                                                return new CustomerViewBinding(view, imageButton, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, textView, textView2, tintableTextView, textView3, tintableTextView2, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
