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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;



public final class FragmentProductDetailBinding implements ViewBinding {

   
    public final FrameLayout chartFrame;

   
    public final FrameLayout chartFrame2;

   
    public final RelativeLayout contentFrame;

   
    public final LinearLayout lnCustomerBalanceTable;

   
    public final LinearLayout lnCustomerInvoiceRow;

   
    public final LinearLayout lnCustomerOrderRow;

   
    public final LinearLayout lnCustomerTotalRow;

   
    public final LinearLayout priceLayout;

   
    public final TextView priceText;

   
    public final LinearLayout purchaseLayout;

   
    public final TextView purchaseText;

   
    public final RelativeLayout rltChartContentFrame;

   
    public final RelativeLayout rltCustomerTop10Product;

   
    public final View rltCustomerTop10ProductView;

   
    public final RelativeLayout rltMonthlySales;

   
    private final RelativeLayout rootView;

   
    public final LinearLayout stockLayout;

   
    public final TextView stockText;

   
    public final AppBarSwipeRefreshLayout swipeLayout;

   
    public final TextView txtBrand;

   
    public final TextView txtCustomerTop10Product;

   
    public final TextView txtGroupCode;

   
    public final TextView txtMonthlySalary;

   
    public final TextView txtPaybackKdvRate;

   
    public final TextView txtPaymentType;

   
    public final TextView txtProductCode;

   
    public final TextView txtSalesKdvRate;

   
    public final TextView txtSpecCodeFive;

   
    public final TextView txtSpecCodeFour;

   
    public final TextView txtSpecCodeOne;

   
    public final TextView txtSpecCodeThree;

   
    public final TextView txtSpecCodeTwo;

   
    public final LinearLayout unitLayout;

   
    public final TextView unitText;

    private FragmentProductDetailBinding(final RelativeLayout relativeLayout, final FrameLayout frameLayout, final FrameLayout frameLayout2, final RelativeLayout relativeLayout2, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final TextView textView, final LinearLayout linearLayout6, final TextView textView2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final View view, final RelativeLayout relativeLayout5, final LinearLayout linearLayout7, final TextView textView3, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TextView textView14, final TextView textView15, final TextView textView16, final LinearLayout linearLayout8, final TextView textView17) {
        rootView = relativeLayout;
        chartFrame = frameLayout;
        chartFrame2 = frameLayout2;
        contentFrame = relativeLayout2;
        lnCustomerBalanceTable = linearLayout;
        lnCustomerInvoiceRow = linearLayout2;
        lnCustomerOrderRow = linearLayout3;
        lnCustomerTotalRow = linearLayout4;
        priceLayout = linearLayout5;
        priceText = textView;
        purchaseLayout = linearLayout6;
        purchaseText = textView2;
        rltChartContentFrame = relativeLayout3;
        rltCustomerTop10Product = relativeLayout4;
        rltCustomerTop10ProductView = view;
        rltMonthlySales = relativeLayout5;
        stockLayout = linearLayout7;
        stockText = textView3;
        swipeLayout = appBarSwipeRefreshLayout;
        txtBrand = textView4;
        txtCustomerTop10Product = textView5;
        txtGroupCode = textView6;
        txtMonthlySalary = textView7;
        txtPaybackKdvRate = textView8;
        txtPaymentType = textView9;
        txtProductCode = textView10;
        txtSalesKdvRate = textView11;
        txtSpecCodeFive = textView12;
        txtSpecCodeFour = textView13;
        txtSpecCodeOne = textView14;
        txtSpecCodeThree = textView15;
        txtSpecCodeTwo = textView16;
        unitLayout = linearLayout8;
        unitText = textView17;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentProductDetailBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentProductDetailBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentProductDetailBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_product_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentProductDetailBinding.bind(inflate);
    }

   
    public static FragmentProductDetailBinding bind(final View view) {
        int i2 = R.id.chartFrame;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.chartFrame);
        if (null != frameLayout) {
            i2 = R.id.chartFrame2;
            final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.chartFrame2);
            if (null != frameLayout2) {
                i2 = R.id.content_frame;
                final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
                if (null != relativeLayout) {
                    i2 = R.id.ln_customerBalanceTable;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerBalanceTable);
                    if (null != linearLayout) {
                        i2 = R.id.ln_customerInvoiceRow;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_customerInvoiceRow);
                        if (null != linearLayout2) {
                            i2 = R.id.ln_customerOrderRow;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_customerOrderRow);
                            if (null != linearLayout3) {
                                i2 = R.id.ln_customerTotalRow;
                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_customerTotalRow);
                                if (null != linearLayout4) {
                                    i2 = R.id.price_layout;
                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.price_layout);
                                    if (null != linearLayout5) {
                                        i2 = R.id.price_text;
                                        final TextView textView = ViewBindings.findChildViewById(view, R.id.price_text);
                                        if (null != textView) {
                                            i2 = R.id.purchase_layout;
                                            final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.purchase_layout);
                                            if (null != linearLayout6) {
                                                i2 = R.id.purchase_text;
                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.purchase_text);
                                                if (null != textView2) {
                                                    i2 = R.id.rlt_chart_content_frame;
                                                    final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_chart_content_frame);
                                                    if (null != relativeLayout2) {
                                                        i2 = R.id.rlt_customerTop10Product;
                                                        final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_customerTop10Product);
                                                        if (null != relativeLayout3) {
                                                            i2 = R.id.rlt_customerTop10ProductView;
                                                            final View findChildViewById = ViewBindings.findChildViewById(view, R.id.rlt_customerTop10ProductView);
                                                            if (null != findChildViewById) {
                                                                i2 = R.id.rlt_monthlySales;
                                                                final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_monthlySales);
                                                                if (null != relativeLayout4) {
                                                                    i2 = R.id.stock_layout;
                                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.stock_layout);
                                                                    if (null != linearLayout7) {
                                                                        i2 = R.id.stock_text;
                                                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.stock_text);
                                                                        if (null != textView3) {
                                                                            i2 = R.id.swipe_layout;
                                                                            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                                                                            if (null != appBarSwipeRefreshLayout) {
                                                                                i2 = R.id.txt_brand;
                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_brand);
                                                                                if (null != textView4) {
                                                                                    i2 = R.id.txt_customerTop10Product;
                                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_customerTop10Product);
                                                                                    if (null != textView5) {
                                                                                        i2 = R.id.txt_group_code;
                                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_group_code);
                                                                                        if (null != textView6) {
                                                                                            i2 = R.id.txt_monthly_salary;
                                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_monthly_salary);
                                                                                            if (null != textView7) {
                                                                                                i2 = R.id.txt_payback_kdv_rate;
                                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_payback_kdv_rate);
                                                                                                if (null != textView8) {
                                                                                                    i2 = R.id.txt_payment_type;
                                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_payment_type);
                                                                                                    if (null != textView9) {
                                                                                                        i2 = R.id.txt_product_code;
                                                                                                        final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_product_code);
                                                                                                        if (null != textView10) {
                                                                                                            i2 = R.id.txt_sales_kdv_rate;
                                                                                                            final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_sales_kdv_rate);
                                                                                                            if (null != textView11) {
                                                                                                                i2 = R.id.txt_spec_code_five;
                                                                                                                final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_spec_code_five);
                                                                                                                if (null != textView12) {
                                                                                                                    i2 = R.id.txt_spec_code_four;
                                                                                                                    final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_spec_code_four);
                                                                                                                    if (null != textView13) {
                                                                                                                        i2 = R.id.txt_spec_code_one;
                                                                                                                        final TextView textView14 = ViewBindings.findChildViewById(view, R.id.txt_spec_code_one);
                                                                                                                        if (null != textView14) {
                                                                                                                            i2 = R.id.txt_spec_code_three;
                                                                                                                            final TextView textView15 = ViewBindings.findChildViewById(view, R.id.txt_spec_code_three);
                                                                                                                            if (null != textView15) {
                                                                                                                                i2 = R.id.txt_spec_code_two;
                                                                                                                                final TextView textView16 = ViewBindings.findChildViewById(view, R.id.txt_spec_code_two);
                                                                                                                                if (null != textView16) {
                                                                                                                                    i2 = R.id.unit_layout;
                                                                                                                                    final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.unit_layout);
                                                                                                                                    if (null != linearLayout8) {
                                                                                                                                        i2 = R.id.unit_text;
                                                                                                                                        final TextView textView17 = ViewBindings.findChildViewById(view, R.id.unit_text);
                                                                                                                                        if (null != textView17) {
                                                                                                                                            return new FragmentProductDetailBinding((RelativeLayout) view, frameLayout, frameLayout2, relativeLayout, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView, linearLayout6, textView2, relativeLayout2, relativeLayout3, findChildViewById, relativeLayout4, linearLayout7, textView3, appBarSwipeRefreshLayout, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, linearLayout8, textView17);
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
