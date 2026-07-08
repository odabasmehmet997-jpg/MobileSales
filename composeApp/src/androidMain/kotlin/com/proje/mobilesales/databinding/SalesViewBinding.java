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



public final class SalesViewBinding implements ViewBinding {

   
    public final ButtonMoreBinding buttonMore;

   
    public final ImageView imgSales;

   
    public final LinearLayout lnSales;

   
    public final RelativeLayout lnSalesInfoNot;

   
    public final LinearLayout lnSalesNot;

   
    public final LinearLayout lnSalesPayment;

   
    public final RelativeLayout rlEdocStatus;

   
    public final RelativeLayout rltCustomerDetail;

   
    public final RelativeLayout rltProductContainer;

   
    public final RelativeLayout rltProductHeader;

   
    public final RelativeLayout rltSpacer;

   
    private final View rootView;

   
    public final TextView txtEdocStatus;

   
    public final TextView txtFicheCancel;

   
    public final TextView txtOrderFicheStatus;

   
    public final TextView txtSalesDocumentNo;

   
    public final TextView txtSalesFicheDate;

   
    public final TextView txtSalesFicheDef;

   
    public final TextView txtSalesFicheId;

   
    public final TextView txtSalesPayPlan;

   
    public final TextView txtSalesShipAddress;

   
    public final TextView txtSalesShipAddressDefinition;

   
    public final TextView txtSalesSpecode;

   
    public final TextView txtSalesStatus;

   
    public final TextView txtSalesTotal;

   
    public final TextView txtSalesTradeDay;

   
    public final TintableTextView txtSalesTransfer;

    private SalesViewBinding(final View view, final ButtonMoreBinding buttonMoreBinding, final ImageView imageView, final LinearLayout linearLayout, final RelativeLayout relativeLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final RelativeLayout relativeLayout5, final RelativeLayout relativeLayout6, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TextView textView14, final TintableTextView tintableTextView) {
        rootView = view;
        buttonMore = buttonMoreBinding;
        imgSales = imageView;
        lnSales = linearLayout;
        lnSalesInfoNot = relativeLayout;
        lnSalesNot = linearLayout2;
        lnSalesPayment = linearLayout3;
        rlEdocStatus = relativeLayout2;
        rltCustomerDetail = relativeLayout3;
        rltProductContainer = relativeLayout4;
        rltProductHeader = relativeLayout5;
        rltSpacer = relativeLayout6;
        txtEdocStatus = textView;
        txtFicheCancel = textView2;
        txtOrderFicheStatus = textView3;
        txtSalesDocumentNo = textView4;
        txtSalesFicheDate = textView5;
        txtSalesFicheDef = textView6;
        txtSalesFicheId = textView7;
        txtSalesPayPlan = textView8;
        txtSalesShipAddress = textView9;
        txtSalesShipAddressDefinition = textView10;
        txtSalesSpecode = textView11;
        txtSalesStatus = textView12;
        txtSalesTotal = textView13;
        txtSalesTradeDay = textView14;
        txtSalesTransfer = tintableTextView;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static SalesViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.sales_view, viewGroup);
        return SalesViewBinding.bind(viewGroup);
    }

   
    public static SalesViewBinding bind(final View view) {
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
                        i2 = R.id.ln_sales_not;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_sales_not);
                        if (null != linearLayout2) {
                            i2 = R.id.ln_sales_payment;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_sales_payment);
                            if (null != linearLayout3) {
                                i2 = R.id.rl_edoc_status;
                                final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rl_edoc_status);
                                if (null != relativeLayout2) {
                                    i2 = R.id.rlt_customer_detail;
                                    final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_customer_detail);
                                    if (null != relativeLayout3) {
                                        i2 = R.id.rlt_product_container;
                                        final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                        if (null != relativeLayout4) {
                                            i2 = R.id.rlt_product_header;
                                            final RelativeLayout relativeLayout5 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                            if (null != relativeLayout5) {
                                                i2 = R.id.rlt_spacer;
                                                final RelativeLayout relativeLayout6 = ViewBindings.findChildViewById(view, R.id.rlt_spacer);
                                                if (null != relativeLayout6) {
                                                    i2 = R.id.txt_edoc_status;
                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_edoc_status);
                                                    if (null != textView) {
                                                        i2 = R.id.txt_fiche_cancel;
                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_fiche_cancel);
                                                        if (null != textView2) {
                                                            i2 = R.id.txt_order_fiche_status;
                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_order_fiche_status);
                                                            if (null != textView3) {
                                                                i2 = R.id.txt_sales_document_no;
                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_sales_document_no);
                                                                if (null != textView4) {
                                                                    i2 = R.id.txt_sales_fiche_date;
                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_date);
                                                                    if (null != textView5) {
                                                                        i2 = R.id.txt_sales_fiche_def;
                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_def);
                                                                        if (null != textView6) {
                                                                            i2 = R.id.txt_sales_fiche_id;
                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_sales_fiche_id);
                                                                            if (null != textView7) {
                                                                                i2 = R.id.txt_sales_pay_plan;
                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_sales_pay_plan);
                                                                                if (null != textView8) {
                                                                                    i2 = R.id.txt_sales_ship_address;
                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_sales_ship_address);
                                                                                    if (null != textView9) {
                                                                                        i2 = R.id.txt_sales_ship_address_definition;
                                                                                        final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_sales_ship_address_definition);
                                                                                        if (null != textView10) {
                                                                                            i2 = R.id.txt_sales_specode;
                                                                                            final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_sales_specode);
                                                                                            if (null != textView11) {
                                                                                                i2 = R.id.txt_sales_status;
                                                                                                final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_sales_status);
                                                                                                if (null != textView12) {
                                                                                                    i2 = R.id.txt_sales_total;
                                                                                                    final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_sales_total);
                                                                                                    if (null != textView13) {
                                                                                                        i2 = R.id.txt_sales_trade_day;
                                                                                                        final TextView textView14 = ViewBindings.findChildViewById(view, R.id.txt_sales_trade_day);
                                                                                                        if (null != textView14) {
                                                                                                            i2 = R.id.txt_sales_transfer;
                                                                                                            final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_sales_transfer);
                                                                                                            if (null != tintableTextView) {
                                                                                                                return new SalesViewBinding(view, bind, imageView, linearLayout, relativeLayout, linearLayout2, linearLayout3, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, tintableTextView);
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
