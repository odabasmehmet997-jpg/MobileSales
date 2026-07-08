package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FragmentCashcreditficheDetailenterBinding implements ViewBinding {
    public final AppCompatCheckBox chkUse3d;
    public final EditText edtPhone;
    public final EditText etAmount;
    public final EditText etApprovalNo;
    public final EditText etCreditCardNo;
    public final EditText etVoucherNo;
    public final ImageButton imgPhone;
    public final LinearLayout lnAmount;
    public final LinearLayout lnApprovalNo;
    public final LinearLayout lnCreditCardNo;
    public final LinearLayout lnPayments;
    public final LinearLayout lnPhoneNumber;
    public final LinearLayout lnUse3d;
    public final LinearLayout lnVoucherNo;
    private final ConstraintLayout rootView;
    public final TextView txtPayments;
    private FragmentCashcreditficheDetailenterBinding(final ConstraintLayout constraintLayout, final AppCompatCheckBox appCompatCheckBox, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final ImageButton imageButton, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final TextView textView) {
        rootView = constraintLayout;
        chkUse3d = appCompatCheckBox;
        edtPhone = editText;
        etAmount = editText2;
        etApprovalNo = editText3;
        etCreditCardNo = editText4;
        etVoucherNo = editText5;
        imgPhone = imageButton;
        lnAmount = linearLayout;
        lnApprovalNo = linearLayout2;
        lnCreditCardNo = linearLayout3;
        lnPayments = linearLayout4;
        lnPhoneNumber = linearLayout5;
        lnUse3d = linearLayout6;
        lnVoucherNo = linearLayout7;
        txtPayments = textView;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static FragmentCashcreditficheDetailenterBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCashcreditficheDetailenterBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCashcreditficheDetailenterBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_cashcreditfiche_detailenter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCashcreditficheDetailenterBinding.bind(inflate);
    }
    public static FragmentCashcreditficheDetailenterBinding bind(final View view) {
        int i2 = R.id.chkUse3d;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chkUse3d);
        if (null != appCompatCheckBox) {
            i2 = R.id.edt_phone;
            final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_phone);
            if (null != editText) {
                i2 = R.id.et_amount;
                final EditText editText2 = ViewBindings.findChildViewById(view, R.id.et_amount);
                if (null != editText2) {
                    i2 = R.id.et_approvalNo;
                    final EditText editText3 = ViewBindings.findChildViewById(view, R.id.et_approvalNo);
                    if (null != editText3) {
                        i2 = R.id.et_creditCardNo;
                        final EditText editText4 = ViewBindings.findChildViewById(view, R.id.et_creditCardNo);
                        if (null != editText4) {
                            i2 = R.id.et_voucherNo;
                            final EditText editText5 = ViewBindings.findChildViewById(view, R.id.et_voucherNo);
                            if (null != editText5) {
                                i2 = R.id.img_Phone;
                                final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_Phone);
                                if (null != imageButton) {
                                    i2 = R.id.ln_amount;
                                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_amount);
                                    if (null != linearLayout) {
                                        i2 = R.id.ln_approvalNo;
                                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_approvalNo);
                                        if (null != linearLayout2) {
                                            i2 = R.id.ln_creditCardNo;
                                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_creditCardNo);
                                            if (null != linearLayout3) {
                                                i2 = R.id.ln_payments;
                                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_payments);
                                                if (null != linearLayout4) {
                                                    i2 = R.id.ln_PhoneNumber;
                                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_PhoneNumber);
                                                    if (null != linearLayout5) {
                                                        i2 = R.id.ln_use3d;
                                                        final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_use3d);
                                                        if (null != linearLayout6) {
                                                            i2 = R.id.ln_voucherNo;
                                                            final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_voucherNo);
                                                            if (null != linearLayout7) {
                                                                i2 = R.id.txt_payments;
                                                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_payments);
                                                                if (null != textView) {
                                                                    return new FragmentCashcreditficheDetailenterBinding((ConstraintLayout) view, appCompatCheckBox, editText, editText2, editText3, editText4, editText5, imageButton, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, textView);
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
