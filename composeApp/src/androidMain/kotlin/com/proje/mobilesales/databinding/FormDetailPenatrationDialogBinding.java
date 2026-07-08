package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FormDetailPenatrationDialogBinding implements ViewBinding {
    public final AppCompatEditText editTextAmount;
    public final AppCompatEditText editTextPrice;
    public final LinearLayout formDetailPenetrationDialog;
    public final AppCompatRadioButton radioButtonFormPenetExist;
    public final AppCompatRadioButton radioButtonFormPenetNotExist;
    private final LinearLayout rootView;
    public final AppCompatSpinner spinnerCurr;
    public final AppCompatTextView textViewSpinnerCurr;
    private FormDetailPenatrationDialogBinding(final LinearLayout linearLayout, final AppCompatEditText appCompatEditText, final AppCompatEditText appCompatEditText2, final LinearLayout linearLayout2, final AppCompatRadioButton appCompatRadioButton, final AppCompatRadioButton appCompatRadioButton2, final AppCompatSpinner appCompatSpinner, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        editTextAmount = appCompatEditText;
        editTextPrice = appCompatEditText2;
        formDetailPenetrationDialog = linearLayout2;
        radioButtonFormPenetExist = appCompatRadioButton;
        radioButtonFormPenetNotExist = appCompatRadioButton2;
        spinnerCurr = appCompatSpinner;
        textViewSpinnerCurr = appCompatTextView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FormDetailPenatrationDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FormDetailPenatrationDialogBinding.inflate(layoutInflater, null, false);
    }
    public static FormDetailPenatrationDialogBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.form_detail_penatration_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FormDetailPenatrationDialogBinding.bind(inflate);
    }
    public static FormDetailPenatrationDialogBinding bind(final View view) {
        int i2 = R.id.editTextAmount;
        final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.editTextAmount);
        if (null != appCompatEditText) {
            i2 = R.id.editTextPrice;
            final AppCompatEditText appCompatEditText2 = ViewBindings.findChildViewById(view, R.id.editTextPrice);
            if (null != appCompatEditText2) {
                final LinearLayout linearLayout = (LinearLayout) view;
                i2 = R.id.radioButtonFormPenetExist;
                final AppCompatRadioButton appCompatRadioButton = ViewBindings.findChildViewById(view, R.id.radioButtonFormPenetExist);
                if (null != appCompatRadioButton) {
                    i2 = R.id.radioButtonFormPenetNotExist;
                    final AppCompatRadioButton appCompatRadioButton2 = ViewBindings.findChildViewById(view, R.id.radioButtonFormPenetNotExist);
                    if (null != appCompatRadioButton2) {
                        i2 = R.id.spinnerCurr;
                        final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spinnerCurr);
                        if (null != appCompatSpinner) {
                            i2 = R.id.textViewSpinnerCurr;
                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.textViewSpinnerCurr);
                            if (null != appCompatTextView) {
                                return new FormDetailPenatrationDialogBinding(linearLayout, appCompatEditText, appCompatEditText2, linearLayout, appCompatRadioButton, appCompatRadioButton2, appCompatSpinner, appCompatTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
