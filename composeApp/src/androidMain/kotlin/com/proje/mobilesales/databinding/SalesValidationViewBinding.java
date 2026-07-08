package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class SalesValidationViewBinding implements ViewBinding {

   
    public final ButtonMoreBinding buttonMore;

   
    public final ImageView imgSales;

   
    public final LinearLayout lnSales;

   
    public final RelativeLayout lnSalesInfoNot;

   
    public final LinearLayout lnSalesPayment;

   
    public final LinearLayout lnSalesSlsDefinition;

   
    public final RelativeLayout rltCustomerDetail;

   
    public final RelativeLayout rltProductContainer;

   
    public final RelativeLayout rltProductHeader;

   
    private final View rootView;

   
    public final TextView txtSalesCustomerName;

   
    public final TextView txtSalesDocumentNo;

   
    public final TextView txtSalesFicheDate;

   
    public final TextView txtSalesFicheDef;

   
    public final TextView txtSalesFicheId;

   
    public final TextView txtSalesInfoNot;

   
    public final TextView txtSalesPayPlan;

   
    public final TextView txtSalesShipAddressDefinition;

   
    public final TextView txtSalesSlsDefinition;

   
    public final TextView txtSalesSpecode;

   
    public final TextView txtSalesStatus;

   
    public final TextView txtSalesTotal;

   
    public final TextView txtSalesTradeDay;

   
    public final TintableTextView txtSalesTransfer;

    private SalesValidationViewBinding(final View view, final ButtonMoreBinding buttonMoreBinding, final ImageView imageView, final LinearLayout linearLayout, final RelativeLayout relativeLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TintableTextView tintableTextView) {
        rootView = view;
        buttonMore = buttonMoreBinding;
        imgSales = imageView;
        lnSales = linearLayout;
        lnSalesInfoNot = relativeLayout;
        lnSalesPayment = linearLayout2;
        lnSalesSlsDefinition = linearLayout3;
        rltCustomerDetail = relativeLayout2;
        rltProductContainer = relativeLayout3;
        rltProductHeader = relativeLayout4;
        txtSalesCustomerName = textView;
        txtSalesDocumentNo = textView2;
        txtSalesFicheDate = textView3;
        txtSalesFicheDef = textView4;
        txtSalesFicheId = textView5;
        txtSalesInfoNot = textView6;
        txtSalesPayPlan = textView7;
        txtSalesShipAddressDefinition = textView8;
        txtSalesSlsDefinition = textView9;
        txtSalesSpecode = textView10;
        txtSalesStatus = textView11;
        txtSalesTotal = textView12;
        txtSalesTradeDay = textView13;
        txtSalesTransfer = tintableTextView;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static SalesValidationViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.sales_validation_view, viewGroup);
        return SalesValidationViewBinding.bind(viewGroup);
    }

   
    public static SalesValidationViewBinding bind(final View view) {
        int i2 = R.id.button_more;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.button_more);
        if (null != findChildViewById) {
            final ButtonMoreBinding bind = ButtonMoreBinding.bind(findChildViewById);
            i2 = R.id.img_sales;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_sales);
            if (null != imageView) {
                i2 = R.id.ln_sales;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_sales);
                if (null != linearLayout) {
                    i2 = R.id.ln_sales_info_not;
                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.ln_sales_info_not);
                    if (null != relativeLayout) {
                        i2 = R.id.ln_sales_payment;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_sales_payment);
                        if (null != linearLayout2) {
                            i2 = R.id.ln_sales_slsDefinition;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_sales_slsDefinition);
                            if (null != linearLayout3) {
                                i2 = R.id.rlt_customer_detail;
                                final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_customer_detail);
                                if (null != relativeLayout2) {
                                    i2 = R.id.rlt_product_container;
                                    final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                    if (null != relativeLayout3) {
                                        i2 = R.id.rlt_product_header;
                                        final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                        if (null != relativeLayout4) {
                                            i2 = R.id.txt_sales_customer_name;
                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_sales_customer_name);
                                            if (null != textView) {
                                                i2 = R.id.txt_sales_document_no;
                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_sales_document_no);
                                                if (null != textView2) {
                                                    i2 = R.id.txt_sales_fiche_date;
                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_date);
                                                    if (null != textView3) {
                                                        i2 = R.id.txt_sales_fiche_def;
                                                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_def);
                                                        if (null != textView4) {
                                                            i2 = R.id.txt_sales_fiche_id;
                                                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_id);
                                                            if (null != textView5) {
                                                                i2 = R.id.txt_sales_info_not;
                                                                final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_sales_info_not);
                                                                if (null != textView6) {
                                                                    i2 = R.id.txt_sales_pay_plan;
                                                                    final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_sales_pay_plan);
                                                                    if (null != textView7) {
                                                                        i2 = R.id.txt_sales_ship_address_definition;
                                                                        final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_sales_ship_address_definition);
                                                                        if (null != textView8) {
                                                                            i2 = R.id.txt_sales_slsDefinition;
                                                                            final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_sales_slsDefinition);
                                                                            if (null != textView9) {
                                                                                i2 = R.id.txt_sales_specode;
                                                                                final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_sales_specode);
                                                                                if (null != textView10) {
                                                                                    i2 = R.id.txt_sales_status;
                                                                                    final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_sales_status);
                                                                                    if (null != textView11) {
                                                                                        i2 = R.id.txt_sales_total;
                                                                                        final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_sales_total);
                                                                                        if (null != textView12) {
                                                                                            i2 = R.id.txt_sales_trade_day;
                                                                                            final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_sales_trade_day);
                                                                                            if (null != textView13) {
                                                                                                i2 = R.id.txt_sales_transfer;
                                                                                                final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_sales_transfer);
                                                                                                if (null != tintableTextView) {
                                                                                                    return new SalesValidationViewBinding(view, bind, imageView, linearLayout, relativeLayout, linearLayout2, linearLayout3, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, tintableTextView);
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
