package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSalesPaymentBinding implements ViewBinding {

   
    public final EditText edtCustomerDiscountRatio;

   
    public final EditText edtSalesDiscountRatio1;

   
    public final EditText edtSalesDiscountRatio2;

   
    public final EditText edtSalesDiscountRatio3;

   
    public final EditText edtSalesDiscountRatio4;

   
    public final EditText edtSalesDiscountRatio5;

   
    public final EditText edtSalesDiscountTotal1;

   
    public final EditText edtSalesDiscountTotal2;

   
    public final EditText edtSalesDiscountTotal3;

   
    public final EditText edtSalesDiscountTotal4;

   
    public final EditText edtSalesDiscountTotal5;

   
    public final LinearLayout lnCustomerDiscountRate;

   
    public final LinearLayout lnDiscountContainer;

   
    public final LinearLayout lnDueDate;

   
    public final LinearLayout lnSalesDetailDiscount1;

   
    public final LinearLayout lnSalesDiscount2;

   
    public final LinearLayout lnSalesDiscount3;

   
    public final LinearLayout lnSalesDiscount4;

   
    public final LinearLayout lnSalesDiscount5;

   
    public final LinearLayout lnSalesPayPlan;

   
    public final LinearLayout lnSalesProjectCode;

   
    public final LinearLayout lnSalesTradeGroup;

   
    private final LinearLayout rootView;

   
    public final TextView textView;

   
    public final TextView textView2;

   
    public final TextView txtDiscountCardHeader;

   
    public final TextView txtDueDate;

   
    public final TextView txtSalesDiscountCart1;

   
    public final TextView txtSalesDiscountCart2;

   
    public final TextView txtSalesDiscountCart3;

   
    public final TextView txtSalesDiscountCart4;

   
    public final TextView txtSalesDiscountCart5;

   
    public final TextView txtSalesPayPlan;

   
    public final TextView txtSalesProjectCode;

   
    public final TextView txtSalesTradeGroup;

    private FragmentSalesPaymentBinding(final LinearLayout linearLayout, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final EditText editText6, final EditText editText7, final EditText editText8, final EditText editText9, final EditText editText10, final EditText editText11, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12) {
        rootView = linearLayout;
        edtCustomerDiscountRatio = editText;
        edtSalesDiscountRatio1 = editText2;
        edtSalesDiscountRatio2 = editText3;
        edtSalesDiscountRatio3 = editText4;
        edtSalesDiscountRatio4 = editText5;
        edtSalesDiscountRatio5 = editText6;
        edtSalesDiscountTotal1 = editText7;
        edtSalesDiscountTotal2 = editText8;
        edtSalesDiscountTotal3 = editText9;
        edtSalesDiscountTotal4 = editText10;
        edtSalesDiscountTotal5 = editText11;
        lnCustomerDiscountRate = linearLayout2;
        lnDiscountContainer = linearLayout3;
        lnDueDate = linearLayout4;
        lnSalesDetailDiscount1 = linearLayout5;
        lnSalesDiscount2 = linearLayout6;
        lnSalesDiscount3 = linearLayout7;
        lnSalesDiscount4 = linearLayout8;
        lnSalesDiscount5 = linearLayout9;
        lnSalesPayPlan = linearLayout10;
        lnSalesProjectCode = linearLayout11;
        lnSalesTradeGroup = linearLayout12;
        this.textView = textView;
        this.textView2 = textView2;
        txtDiscountCardHeader = textView3;
        txtDueDate = textView4;
        txtSalesDiscountCart1 = textView5;
        txtSalesDiscountCart2 = textView6;
        txtSalesDiscountCart3 = textView7;
        txtSalesDiscountCart4 = textView8;
        txtSalesDiscountCart5 = textView9;
        txtSalesPayPlan = textView10;
        txtSalesProjectCode = textView11;
        txtSalesTradeGroup = textView12;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSalesPaymentBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSalesPaymentBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSalesPaymentBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_sales_payment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSalesPaymentBinding.bind(inflate);
    }

   
    public static FragmentSalesPaymentBinding bind(final View view) {
        int i2 = R.id.edt_customerDiscountRatio;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_customerDiscountRatio);
        if (null != editText) {
            i2 = R.id.edt_salesDiscountRatio1;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountRatio1);
            if (null != editText2) {
                i2 = R.id.edt_salesDiscountRatio2;
                final EditText editText3 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountRatio2);
                if (null != editText3) {
                    i2 = R.id.edt_salesDiscountRatio3;
                    final EditText editText4 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountRatio3);
                    if (null != editText4) {
                        i2 = R.id.edt_salesDiscountRatio4;
                        final EditText editText5 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountRatio4);
                        if (null != editText5) {
                            i2 = R.id.edt_salesDiscountRatio5;
                            final EditText editText6 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountRatio5);
                            if (null != editText6) {
                                i2 = R.id.edt_salesDiscountTotal1;
                                final EditText editText7 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountTotal1);
                                if (null != editText7) {
                                    i2 = R.id.edt_salesDiscountTotal2;
                                    final EditText editText8 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountTotal2);
                                    if (null != editText8) {
                                        i2 = R.id.edt_salesDiscountTotal3;
                                        final EditText editText9 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountTotal3);
                                        if (null != editText9) {
                                            i2 = R.id.edt_salesDiscountTotal4;
                                            final EditText editText10 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountTotal4);
                                            if (null != editText10) {
                                                i2 = R.id.edt_salesDiscountTotal5;
                                                final EditText editText11 = ViewBindings.findChildViewById(view, R.id.edt_salesDiscountTotal5);
                                                if (null != editText11) {
                                                    i2 = R.id.ln_customerDiscountRate;
                                                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerDiscountRate);
                                                    if (null != linearLayout) {
                                                        i2 = R.id.ln_discountContainer;
                                                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_discountContainer);
                                                        if (null != linearLayout2) {
                                                            i2 = R.id.ln_dueDate;
                                                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_dueDate);
                                                            if (null != linearLayout3) {
                                                                i2 = R.id.ln_salesDetailDiscount1;
                                                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount1);
                                                                if (null != linearLayout4) {
                                                                    i2 = R.id.ln_salesDiscount2;
                                                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_salesDiscount2);
                                                                    if (null != linearLayout5) {
                                                                        i2 = R.id.ln_salesDiscount3;
                                                                        final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_salesDiscount3);
                                                                        if (null != linearLayout6) {
                                                                            i2 = R.id.ln_salesDiscount4;
                                                                            final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_salesDiscount4);
                                                                            if (null != linearLayout7) {
                                                                                i2 = R.id.ln_salesDiscount5;
                                                                                final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_salesDiscount5);
                                                                                if (null != linearLayout8) {
                                                                                    i2 = R.id.ln_salesPayPlan;
                                                                                    final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_salesPayPlan);
                                                                                    if (null != linearLayout9) {
                                                                                        i2 = R.id.ln_salesProjectCode;
                                                                                        final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_salesProjectCode);
                                                                                        if (null != linearLayout10) {
                                                                                            i2 = R.id.ln_salesTradeGroup;
                                                                                            final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_salesTradeGroup);
                                                                                            if (null != linearLayout11) {
                                                                                                i2 = R.id.textView;
                                                                                                final TextView textView = ViewBindings.findChildViewById(view, R.id.textView);
                                                                                                if (null != textView) {
                                                                                                    i2 = R.id.textView2;
                                                                                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.textView2);
                                                                                                    if (null != textView2) {
                                                                                                        i2 = R.id.txtDiscountCardHeader;
                                                                                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txtDiscountCardHeader);
                                                                                                        if (null != textView3) {
                                                                                                            i2 = R.id.txt_dueDate;
                                                                                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_dueDate);
                                                                                                            if (null != textView4) {
                                                                                                                i2 = R.id.txt_salesDiscountCart1;
                                                                                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_salesDiscountCart1);
                                                                                                                if (null != textView5) {
                                                                                                                    i2 = R.id.txt_salesDiscountCart2;
                                                                                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_salesDiscountCart2);
                                                                                                                    if (null != textView6) {
                                                                                                                        i2 = R.id.txt_salesDiscountCart3;
                                                                                                                        final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_salesDiscountCart3);
                                                                                                                        if (null != textView7) {
                                                                                                                            i2 = R.id.txt_salesDiscountCart4;
                                                                                                                            final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_salesDiscountCart4);
                                                                                                                            if (null != textView8) {
                                                                                                                                i2 = R.id.txt_salesDiscountCart5;
                                                                                                                                final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_salesDiscountCart5);
                                                                                                                                if (null != textView9) {
                                                                                                                                    i2 = R.id.txt_salesPayPlan;
                                                                                                                                    final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_salesPayPlan);
                                                                                                                                    if (null != textView10) {
                                                                                                                                        i2 = R.id.txt_salesProjectCode;
                                                                                                                                        final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_salesProjectCode);
                                                                                                                                        if (null != textView11) {
                                                                                                                                            i2 = R.id.txt_salesTradeGroup;
                                                                                                                                            final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_salesTradeGroup);
                                                                                                                                            if (null != textView12) {
                                                                                                                                                return new FragmentSalesPaymentBinding((LinearLayout) view, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
