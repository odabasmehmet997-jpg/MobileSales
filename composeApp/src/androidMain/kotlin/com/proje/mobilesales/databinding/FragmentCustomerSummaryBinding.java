package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;



public final class FragmentCustomerSummaryBinding implements ViewBinding {

   
    public final FrameLayout chartFrame;

   
    public final FrameLayout chartFrame2;

   
    public final RelativeLayout contentFrame;

   
    public final EmptyListBinding empty;

   
    public final LinearLayout lnCustomerBalanceTable;

   
    public final LinearLayout lnCustomerBalanceTotalRow;

   
    public final LinearLayout lnCustomerInvoiceRow;

   
    public final LinearLayout lnCustomerOrderRow;

   
    public final LinearLayout lnCustomerTotalRow;

   
    public final RelativeLayout rltCurrencyContent;

   
    public final RelativeLayout rltCustomerSales;

   
    public final View rltCustomerSalesView;

   
    public final RelativeLayout rltCustomerTop10Product;

   
    public final View rltCustomerTop10ProductView;

   
    public final RelativeLayout rltExtractCurrencyBalance;

   
    public final View rltExtractCurrencyBalanceView;

   
    private final RelativeLayout rootView;

   
    public final AppBarSwipeRefreshLayout swipeLayout;

   
    public final TextView txtCustomerBalanceTotal;

   
    public final TextView txtCustomerInvoiceTotal;

   
    public final TextView txtCustomerOrderTotal;

   
    public final AppCompatCheckedTextView txtCustomerSalesTitle;

   
    public final TextView txtCustomerTop10Product;

   
    public final TextView txtCustomerTotal;

   
    public final TextView txtExtractCurrencyTitle;

    private FragmentCustomerSummaryBinding(final RelativeLayout relativeLayout, final FrameLayout frameLayout, final FrameLayout frameLayout2, final RelativeLayout relativeLayout2, final EmptyListBinding emptyListBinding, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final View view, final RelativeLayout relativeLayout5, final View view2, final RelativeLayout relativeLayout6, final View view3, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final TextView textView, final TextView textView2, final TextView textView3, final AppCompatCheckedTextView appCompatCheckedTextView, final TextView textView4, final TextView textView5, final TextView textView6) {
        rootView = relativeLayout;
        chartFrame = frameLayout;
        chartFrame2 = frameLayout2;
        contentFrame = relativeLayout2;
        empty = emptyListBinding;
        lnCustomerBalanceTable = linearLayout;
        lnCustomerBalanceTotalRow = linearLayout2;
        lnCustomerInvoiceRow = linearLayout3;
        lnCustomerOrderRow = linearLayout4;
        lnCustomerTotalRow = linearLayout5;
        rltCurrencyContent = relativeLayout3;
        rltCustomerSales = relativeLayout4;
        rltCustomerSalesView = view;
        rltCustomerTop10Product = relativeLayout5;
        rltCustomerTop10ProductView = view2;
        rltExtractCurrencyBalance = relativeLayout6;
        rltExtractCurrencyBalanceView = view3;
        swipeLayout = appBarSwipeRefreshLayout;
        txtCustomerBalanceTotal = textView;
        txtCustomerInvoiceTotal = textView2;
        txtCustomerOrderTotal = textView3;
        txtCustomerSalesTitle = appCompatCheckedTextView;
        txtCustomerTop10Product = textView4;
        txtCustomerTotal = textView5;
        txtExtractCurrencyTitle = textView6;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentCustomerSummaryBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerSummaryBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentCustomerSummaryBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_summary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerSummaryBinding.bind(inflate);
    }

   
    public static FragmentCustomerSummaryBinding bind(final View view) {
        int i2 = R.id.chartFrame;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.chartFrame);
        if (null != frameLayout) {
            i2 = R.id.chartFrame2;
            final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.chartFrame2);
            if (null != frameLayout2) {
                i2 = R.id.content_frame;
                final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
                if (null != relativeLayout) {
                    i2 = R.id.empty;
                    final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
                    if (null != findChildViewById) {
                        final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
                        i2 = R.id.ln_customerBalanceTable;
                        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerBalanceTable);
                        if (null != linearLayout) {
                            i2 = R.id.ln_customerBalanceTotalRow;
                            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_customerBalanceTotalRow);
                            if (null != linearLayout2) {
                                i2 = R.id.ln_customerInvoiceRow;
                                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_customerInvoiceRow);
                                if (null != linearLayout3) {
                                    i2 = R.id.ln_customerOrderRow;
                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_customerOrderRow);
                                    if (null != linearLayout4) {
                                        i2 = R.id.ln_customerTotalRow;
                                        final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_customerTotalRow);
                                        if (null != linearLayout5) {
                                            i2 = R.id.rlt_currencyContent;
                                            final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_currencyContent);
                                            if (null != relativeLayout2) {
                                                i2 = R.id.rlt_customerSales;
                                                final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_customerSales);
                                                if (null != relativeLayout3) {
                                                    i2 = R.id.rlt_customerSalesView;
                                                    final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.rlt_customerSalesView);
                                                    if (null != findChildViewById2) {
                                                        i2 = R.id.rlt_customerTop10Product;
                                                        final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_customerTop10Product);
                                                        if (null != relativeLayout4) {
                                                            i2 = R.id.rlt_customerTop10ProductView;
                                                            final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.rlt_customerTop10ProductView);
                                                            if (null != findChildViewById3) {
                                                                i2 = R.id.rlt_extractCurrencyBalance;
                                                                final RelativeLayout relativeLayout5 = ViewBindings.findChildViewById(view, R.id.rlt_extractCurrencyBalance);
                                                                if (null != relativeLayout5) {
                                                                    i2 = R.id.rlt_extractCurrencyBalanceView;
                                                                    final View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.rlt_extractCurrencyBalanceView);
                                                                    if (null != findChildViewById4) {
                                                                        i2 = R.id.swipe_layout;
                                                                        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                                                                        if (null != appBarSwipeRefreshLayout) {
                                                                            i2 = R.id.txt_customerBalanceTotal;
                                                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_customerBalanceTotal);
                                                                            if (null != textView) {
                                                                                i2 = R.id.txt_customerInvoiceTotal;
                                                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_customerInvoiceTotal);
                                                                                if (null != textView2) {
                                                                                    i2 = R.id.txt_customerOrderTotal;
                                                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_customerOrderTotal);
                                                                                    if (null != textView3) {
                                                                                        i2 = R.id.txt_customerSalesTitle;
                                                                                        final AppCompatCheckedTextView appCompatCheckedTextView = ViewBindings.findChildViewById(view, R.id.txt_customerSalesTitle);
                                                                                        if (null != appCompatCheckedTextView) {
                                                                                            i2 = R.id.txt_customerTop10Product;
                                                                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_customerTop10Product);
                                                                                            if (null != textView4) {
                                                                                                i2 = R.id.txt_customerTotal;
                                                                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_customerTotal);
                                                                                                if (null != textView5) {
                                                                                                    i2 = R.id.txt_extractCurrencyTitle;
                                                                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_extractCurrencyTitle);
                                                                                                    if (null != textView6) {
                                                                                                        return new FragmentCustomerSummaryBinding((RelativeLayout) view, frameLayout, frameLayout2, relativeLayout, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, relativeLayout2, relativeLayout3, findChildViewById2, relativeLayout4, findChildViewById3, relativeLayout5, findChildViewById4, appBarSwipeRefreshLayout, textView, textView2, textView3, appCompatCheckedTextView, textView4, textView5, textView6);
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
