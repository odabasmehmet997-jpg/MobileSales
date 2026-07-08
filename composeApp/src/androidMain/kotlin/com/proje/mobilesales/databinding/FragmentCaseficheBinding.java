package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class FragmentCaseficheBinding implements ViewBinding {
    public final EditText etAmount;
    public final EditText etExplanation;
    public final EditText etVoucherNo;
    public final LinearLayout lnAmount;
    public final LinearLayout lnAuthCode;
    public final LinearLayout lnCase;
    public final LinearLayout lnCurrency;
    public final LinearLayout lnCustomerTradingGroup;
    public final LinearLayout lnDepartment;
    public final LinearLayout lnExplanation;
    public final LinearLayout lnProjectCode;
    public final LinearLayout lnSalesManCode;
    public final LinearLayout lnSpecialCode;
    public final LinearLayout lnVoucherNo;
    public final LinearLayout lnWorkplace;
    private final ConstraintLayout rootView;
    public final TextView txtAmount;
    public final TextView txtAuthCode;
    public final TextView txtCurrency;
    public final TextView txtCustomerTradingGroup;
    public final TextView txtDepartment;
    public final TextView txtKasa;
    public final TextView txtProjectCode;
    public final TextView txtSalesManCode;
    public final TextView txtSpecialCode;
    public final TextView txtWorkplace;
    private FragmentCaseficheBinding(final ConstraintLayout constraintLayout, final EditText editText, final EditText editText2, final EditText editText3, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10) {
        rootView = constraintLayout;
        etAmount = editText;
        etExplanation = editText2;
        etVoucherNo = editText3;
        lnAmount = linearLayout;
        lnAuthCode = linearLayout2;
        lnCase = linearLayout3;
        lnCurrency = linearLayout4;
        lnCustomerTradingGroup = linearLayout5;
        lnDepartment = linearLayout6;
        lnExplanation = linearLayout7;
        lnProjectCode = linearLayout8;
        lnSalesManCode = linearLayout9;
        lnSpecialCode = linearLayout10;
        lnVoucherNo = linearLayout11;
        lnWorkplace = linearLayout12;
        txtAmount = textView;
        txtAuthCode = textView2;
        txtCurrency = textView3;
        txtCustomerTradingGroup = textView4;
        txtDepartment = textView5;
        txtKasa = textView6;
        txtProjectCode = textView7;
        txtSalesManCode = textView8;
        txtSpecialCode = textView9;
        txtWorkplace = textView10;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static FragmentCaseficheBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCaseficheBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCaseficheBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_casefiche, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCaseficheBinding.bind(inflate);
    }
    public static FragmentCaseficheBinding bind(final View view) {
        int i2 = R.id.et_amount;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.et_amount);
        if (null != editText) {
            i2 = R.id.et_explanation;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.et_explanation);
            if (null != editText2) {
                i2 = R.id.et_voucherNo;
                final EditText editText3 = ViewBindings.findChildViewById(view, R.id.et_voucherNo);
                if (null != editText3) {
                    i2 = R.id.ln_amount;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_amount);
                    if (null != linearLayout) {
                        i2 = R.id.ln_authCode;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_authCode);
                        if (null != linearLayout2) {
                            i2 = R.id.ln_case;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_case);
                            if (null != linearLayout3) {
                                i2 = R.id.ln_currency;
                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_currency);
                                if (null != linearLayout4) {
                                    i2 = R.id.ln_customerTradingGroup;
                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_customerTradingGroup);
                                    if (null != linearLayout5) {
                                        i2 = R.id.ln_department;
                                        final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_department);
                                        if (null != linearLayout6) {
                                            i2 = R.id.ln_explanation;
                                            final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_explanation);
                                            if (null != linearLayout7) {
                                                i2 = R.id.ln_projectCode;
                                                final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_projectCode);
                                                if (null != linearLayout8) {
                                                    i2 = R.id.ln_SalesManCode;
                                                    final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_SalesManCode);
                                                    if (null != linearLayout9) {
                                                        i2 = R.id.ln_specialCode;
                                                        final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_specialCode);
                                                        if (null != linearLayout10) {
                                                            i2 = R.id.ln_voucherNo;
                                                            final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_voucherNo);
                                                            if (null != linearLayout11) {
                                                                i2 = R.id.ln_workplace;
                                                                final LinearLayout linearLayout12 = ViewBindings.findChildViewById(view, R.id.ln_workplace);
                                                                if (null != linearLayout12) {
                                                                    i2 = R.id.txt_amount;
                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_amount);
                                                                    if (null != textView) {
                                                                        i2 = R.id.txt_authCode;
                                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_authCode);
                                                                        if (null != textView2) {
                                                                            i2 = R.id.txt_currency;
                                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_currency);
                                                                            if (null != textView3) {
                                                                                i2 = R.id.txt_customerTradingGroup;
                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_customerTradingGroup);
                                                                                if (null != textView4) {
                                                                                    i2 = R.id.txt_department;
                                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_department);
                                                                                    if (null != textView5) {
                                                                                        i2 = R.id.txt_Kasa;
                                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_Kasa);
                                                                                        if (null != textView6) {
                                                                                            i2 = R.id.txt_projectCode;
                                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_projectCode);
                                                                                            if (null != textView7) {
                                                                                                i2 = R.id.txt_SalesManCode;
                                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_SalesManCode);
                                                                                                if (null != textView8) {
                                                                                                    i2 = R.id.txt_specialCode;
                                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_specialCode);
                                                                                                    if (null != textView9) {
                                                                                                        i2 = R.id.txt_workplace;
                                                                                                        final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_workplace);
                                                                                                        if (null != textView10) {
                                                                                                            return new FragmentCaseficheBinding((ConstraintLayout) view, editText, editText2, editText3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
