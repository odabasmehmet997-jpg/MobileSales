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
public final class FragmentCashcreditficheHeaderBinding implements ViewBinding {
    public final EditText etExplanation1;
    public final EditText etExplanation2;
    public final EditText etExplanation3;
    public final EditText etExplanation4;
    public final EditText etInstallment;
    public final LinearLayout lnAccount;
    public final LinearLayout lnAuthCode;
    public final LinearLayout lnBank;
    public final LinearLayout lnCustomerTradingGroup;
    public final LinearLayout lnDepartment;
    public final LinearLayout lnExplanation1;
    public final LinearLayout lnExplanation2;
    public final LinearLayout lnExplanation3;
    public final LinearLayout lnExplanation4;
    public final LinearLayout lnInstallment;
    public final LinearLayout lnProjectCode;
    public final LinearLayout lnPromiseCode;
    public final LinearLayout lnSpecialCode;
    public final LinearLayout lnWorkplace;
    private final ConstraintLayout rootView;
    public final TextView txtAccount;
    public final TextView txtAuthCode;
    public final TextView txtBank;
    public final TextView txtCustomerTradingGroup;
    public final TextView txtDepartment;
    public final TextView txtProjectCode;
    public final TextView txtPromiseCode;
    public final TextView txtSpecialCode;
    public final TextView txtWorkplace;
    private FragmentCashcreditficheHeaderBinding(final ConstraintLayout constraintLayout, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final LinearLayout linearLayout13, final LinearLayout linearLayout14, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9) {
        rootView = constraintLayout;
        etExplanation1 = editText;
        etExplanation2 = editText2;
        etExplanation3 = editText3;
        etExplanation4 = editText4;
        etInstallment = editText5;
        lnAccount = linearLayout;
        lnAuthCode = linearLayout2;
        lnBank = linearLayout3;
        lnCustomerTradingGroup = linearLayout4;
        lnDepartment = linearLayout5;
        lnExplanation1 = linearLayout6;
        lnExplanation2 = linearLayout7;
        lnExplanation3 = linearLayout8;
        lnExplanation4 = linearLayout9;
        lnInstallment = linearLayout10;
        lnProjectCode = linearLayout11;
        lnPromiseCode = linearLayout12;
        lnSpecialCode = linearLayout13;
        lnWorkplace = linearLayout14;
        txtAccount = textView;
        txtAuthCode = textView2;
        txtBank = textView3;
        txtCustomerTradingGroup = textView4;
        txtDepartment = textView5;
        txtProjectCode = textView6;
        txtPromiseCode = textView7;
        txtSpecialCode = textView8;
        txtWorkplace = textView9;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static FragmentCashcreditficheHeaderBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCashcreditficheHeaderBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCashcreditficheHeaderBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_cashcreditfiche_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCashcreditficheHeaderBinding.bind(inflate);
    }
    public static FragmentCashcreditficheHeaderBinding bind(final View view) {
        int i2 = R.id.etExplanation1;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.etExplanation1);
        if (null != editText) {
            i2 = R.id.etExplanation2;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.etExplanation2);
            if (null != editText2) {
                i2 = R.id.etExplanation3;
                final EditText editText3 = ViewBindings.findChildViewById(view, R.id.etExplanation3);
                if (null != editText3) {
                    i2 = R.id.etExplanation4;
                    final EditText editText4 = ViewBindings.findChildViewById(view, R.id.etExplanation4);
                    if (null != editText4) {
                        i2 = R.id.etInstallment;
                        final EditText editText5 = ViewBindings.findChildViewById(view, R.id.etInstallment);
                        if (null != editText5) {
                            i2 = R.id.ln_account;
                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_account);
                            if (null != linearLayout) {
                                i2 = R.id.ln_authCode;
                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_authCode);
                                if (null != linearLayout2) {
                                    i2 = R.id.ln_bank;
                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_bank);
                                    if (null != linearLayout3) {
                                        i2 = R.id.ln_customerTradingGroup;
                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_customerTradingGroup);
                                        if (null != linearLayout4) {
                                            i2 = R.id.ln_department;
                                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_department);
                                            if (null != linearLayout5) {
                                                i2 = R.id.ln_explanation1;
                                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_explanation1);
                                                if (null != linearLayout6) {
                                                    i2 = R.id.ln_Explanation2;
                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_Explanation2);
                                                    if (null != linearLayout7) {
                                                        i2 = R.id.ln_explanation3;
                                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_explanation3);
                                                        if (null != linearLayout8) {
                                                            i2 = R.id.ln_explanation4;
                                                            final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_explanation4);
                                                            if (null != linearLayout9) {
                                                                i2 = R.id.ln_installment;
                                                                final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_installment);
                                                                if (null != linearLayout10) {
                                                                    i2 = R.id.ln_projectCode;
                                                                    final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_projectCode);
                                                                    if (null != linearLayout11) {
                                                                        i2 = R.id.ln_promiseCode;
                                                                        final LinearLayout linearLayout12 = ViewBindings.findChildViewById(view, R.id.ln_promiseCode);
                                                                        if (null != linearLayout12) {
                                                                            i2 = R.id.ln_specialCode;
                                                                            final LinearLayout linearLayout13 = ViewBindings.findChildViewById(view, R.id.ln_specialCode);
                                                                            if (null != linearLayout13) {
                                                                                i2 = R.id.ln_workplace;
                                                                                final LinearLayout linearLayout14 = ViewBindings.findChildViewById(view, R.id.ln_workplace);
                                                                                if (null != linearLayout14) {
                                                                                    i2 = R.id.txt_account;
                                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_account);
                                                                                    if (null != textView) {
                                                                                        i2 = R.id.txt_authCode;
                                                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_authCode);
                                                                                        if (null != textView2) {
                                                                                            i2 = R.id.txt_bank;
                                                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_bank);
                                                                                            if (null != textView3) {
                                                                                                i2 = R.id.txt_customerTradingGroup;
                                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_customerTradingGroup);
                                                                                                if (null != textView4) {
                                                                                                    i2 = R.id.txt_department;
                                                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_department);
                                                                                                    if (null != textView5) {
                                                                                                        i2 = R.id.txt_projectCode;
                                                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_projectCode);
                                                                                                        if (null != textView6) {
                                                                                                            i2 = R.id.txt_promiseCode;
                                                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_promiseCode);
                                                                                                            if (null != textView7) {
                                                                                                                i2 = R.id.txt_specialCode;
                                                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_specialCode);
                                                                                                                if (null != textView8) {
                                                                                                                    i2 = R.id.txt_workplace;
                                                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_workplace);
                                                                                                                    if (null != textView9) {
                                                                                                                        return new FragmentCashcreditficheHeaderBinding((ConstraintLayout) view, editText, editText2, editText3, editText4, editText5, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
