package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FragmentBottomAlertDialogBinding implements ViewBinding {
    public final AppCompatButton btnCancel;
    public final AppCompatButton btnOk;
    public final LinearLayout lnCancel;
    public final LinearLayout lnOk;
    private final LinearLayout rootView;
    public final LinearLayout testid;
    public final AppCompatTextView txtDialogDescription;
    public final AppCompatTextView txtDialogTitle;
    private FragmentBottomAlertDialogBinding(final LinearLayout linearLayout, final AppCompatButton appCompatButton, final AppCompatButton appCompatButton2, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = linearLayout;
        btnCancel = appCompatButton;
        btnOk = appCompatButton2;
        lnCancel = linearLayout2;
        lnOk = linearLayout3;
        testid = linearLayout4;
        txtDialogDescription = appCompatTextView;
        txtDialogTitle = appCompatTextView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FragmentBottomAlertDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentBottomAlertDialogBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentBottomAlertDialogBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_bottom_alert_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentBottomAlertDialogBinding.bind(inflate);
    }
    public static FragmentBottomAlertDialogBinding bind(final View view) {
        int i2 = R.id.btnCancel;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (null != appCompatButton) {
            i2 = R.id.btnOk;
            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnOk);
            if (null != appCompatButton2) {
                i2 = R.id.lnCancel;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lnCancel);
                if (null != linearLayout) {
                    i2 = R.id.lnOk;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnOk);
                    if (null != linearLayout2) {
                        i2 = R.id.testid;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.testid);
                        if (null != linearLayout3) {
                            i2 = R.id.txtDialogDescription;
                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtDialogDescription);
                            if (null != appCompatTextView) {
                                i2 = R.id.txtDialogTitle;
                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtDialogTitle);
                                if (null != appCompatTextView2) {
                                    return new FragmentBottomAlertDialogBinding((LinearLayout) view, appCompatButton, appCompatButton2, linearLayout, linearLayout2, linearLayout3, appCompatTextView, appCompatTextView2);
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
