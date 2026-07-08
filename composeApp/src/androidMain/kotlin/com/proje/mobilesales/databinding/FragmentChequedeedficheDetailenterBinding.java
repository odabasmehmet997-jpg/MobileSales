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
public final class FragmentChequedeedficheDetailenterBinding implements ViewBinding {
    public final EditText etAccountNo;
    public final EditText etAmount;
    public final EditText etBankName;
    public final EditText etBranch;
    public final EditText etDebtor;
    public final EditText etGuarantor;
    public final EditText etPaymentPlace;
    public final EditText etSerialNo;
    public final EditText etStamp;
    public final LinearLayout lnAccountNo;
    public final LinearLayout lnAmount;
    public final LinearLayout lnBankName;
    public final LinearLayout lnBranch;
    public final LinearLayout lnDebtor;
    public final LinearLayout lnExpiry;
    public final LinearLayout lnGuarantor;
    public final LinearLayout lnPaymentPlace;
    public final LinearLayout lnSerialNo;
    public final LinearLayout lnStamp;
    private final ConstraintLayout rootView;
    public final TextView txtExpiry;
    private FragmentChequedeedficheDetailenterBinding(final ConstraintLayout constraintLayout, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final EditText editText6, final EditText editText7, final EditText editText8, final EditText editText9, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final TextView textView) {
        rootView = constraintLayout;
        etAccountNo = editText;
        etAmount = editText2;
        etBankName = editText3;
        etBranch = editText4;
        etDebtor = editText5;
        etGuarantor = editText6;
        etPaymentPlace = editText7;
        etSerialNo = editText8;
        etStamp = editText9;
        lnAccountNo = linearLayout;
        lnAmount = linearLayout2;
        lnBankName = linearLayout3;
        lnBranch = linearLayout4;
        lnDebtor = linearLayout5;
        lnExpiry = linearLayout6;
        lnGuarantor = linearLayout7;
        lnPaymentPlace = linearLayout8;
        lnSerialNo = linearLayout9;
        lnStamp = linearLayout10;
        txtExpiry = textView;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static FragmentChequedeedficheDetailenterBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentChequedeedficheDetailenterBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentChequedeedficheDetailenterBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_chequedeedfiche_detailenter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentChequedeedficheDetailenterBinding.bind(inflate);
    }
    public static FragmentChequedeedficheDetailenterBinding bind(final View view) {
        int i2 = R.id.et_accountNo;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.et_accountNo);
        if (null != editText) {
            i2 = R.id.et_amount;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.et_amount);
            if (null != editText2) {
                i2 = R.id.et_bankName;
                final EditText editText3 = ViewBindings.findChildViewById(view, R.id.et_bankName);
                if (null != editText3) {
                    i2 = R.id.et_branch;
                    final EditText editText4 = ViewBindings.findChildViewById(view, R.id.et_branch);
                    if (null != editText4) {
                        i2 = R.id.et_debtor;
                        final EditText editText5 = ViewBindings.findChildViewById(view, R.id.et_debtor);
                        if (null != editText5) {
                            i2 = R.id.et_guarantor;
                            final EditText editText6 = ViewBindings.findChildViewById(view, R.id.et_guarantor);
                            if (null != editText6) {
                                i2 = R.id.et_paymentPlace;
                                final EditText editText7 = ViewBindings.findChildViewById(view, R.id.et_paymentPlace);
                                if (null != editText7) {
                                    i2 = R.id.et_serialNo;
                                    final EditText editText8 = ViewBindings.findChildViewById(view, R.id.et_serialNo);
                                    if (null != editText8) {
                                        i2 = R.id.et_stamp;
                                        final EditText editText9 = ViewBindings.findChildViewById(view, R.id.et_stamp);
                                        if (null != editText9) {
                                            i2 = R.id.ln_accountNo;
                                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_accountNo);
                                            if (null != linearLayout) {
                                                i2 = R.id.ln_amount;
                                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_amount);
                                                if (null != linearLayout2) {
                                                    i2 = R.id.ln_bankName;
                                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_bankName);
                                                    if (null != linearLayout3) {
                                                        i2 = R.id.ln_branch;
                                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_branch);
                                                        if (null != linearLayout4) {
                                                            i2 = R.id.ln_debtor;
                                                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_debtor);
                                                            if (null != linearLayout5) {
                                                                i2 = R.id.ln_expiry;
                                                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_expiry);
                                                                if (null != linearLayout6) {
                                                                    i2 = R.id.ln_guarantor;
                                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_guarantor);
                                                                    if (null != linearLayout7) {
                                                                        i2 = R.id.ln_paymentPlace;
                                                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_paymentPlace);
                                                                        if (null != linearLayout8) {
                                                                            i2 = R.id.ln_serialNo;
                                                                            final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_serialNo);
                                                                            if (null != linearLayout9) {
                                                                                i2 = R.id.ln_stamp;
                                                                                final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_stamp);
                                                                                if (null != linearLayout10) {
                                                                                    i2 = R.id.txt_expiry;
                                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_expiry);
                                                                                    if (null != textView) {
                                                                                        return new FragmentChequedeedficheDetailenterBinding((ConstraintLayout) view, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, textView);
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
