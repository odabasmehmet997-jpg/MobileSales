package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import cardtek.masterpass.attributes.MasterPassEditText;
import com.proje.mobilesales.R;



public final class FragmentPinSmsBinding implements ViewBinding {
    public final AppCompatButton btnCancel;

   
    public final AppCompatButton btnResend;

   
    public final AppCompatButton btnValidate;

   
    public final LinearLayout buttonLayout;

   
    public final LinearLayout contentView;

   
    public final TextView emptyView;

   
    public final LinearLayout materialBackground;

   
    public final LinearLayout messageContentView;

   
    public final MasterPassEditText pin;

   
    public final AppCompatTextView resendTimer;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView title;

   
    public final AppCompatTextView txtDialogDescription;

    private FragmentPinSmsBinding(final LinearLayout linearLayout, final AppCompatButton appCompatButton, final AppCompatButton appCompatButton2, final AppCompatButton appCompatButton3, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final TextView textView, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final MasterPassEditText masterPassEditText, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        btnCancel = appCompatButton;
        btnResend = appCompatButton2;
        btnValidate = appCompatButton3;
        buttonLayout = linearLayout2;
        contentView = linearLayout3;
        emptyView = textView;
        materialBackground = linearLayout4;
        messageContentView = linearLayout5;
        pin = masterPassEditText;
        resendTimer = appCompatTextView;
        title = appCompatTextView2;
        txtDialogDescription = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentPinSmsBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentPinSmsBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentPinSmsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_pin_sms, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentPinSmsBinding.bind(inflate);
    }

   
    public static FragmentPinSmsBinding bind(final View view) {
        int i2 = R.id.btnCancel;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (null != appCompatButton) {
            i2 = R.id.btnResend;
            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnResend);
            if (null != appCompatButton2) {
                i2 = R.id.btnValidate;
                final AppCompatButton appCompatButton3 = ViewBindings.findChildViewById(view, R.id.btnValidate);
                if (null != appCompatButton3) {
                    i2 = R.id.buttonLayout;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.buttonLayout);
                    if (null != linearLayout) {
                        i2 = R.id.contentView;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.contentView);
                        if (null != linearLayout2) {
                            i2 = R.id.empty_view;
                            final TextView textView = ViewBindings.findChildViewById(view, R.id.empty_view);
                            if (null != textView) {
                                i2 = R.id.material_background;
                                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.material_background);
                                if (null != linearLayout3) {
                                    i2 = R.id.message_content_view;
                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.message_content_view);
                                    if (null != linearLayout4) {
                                        i2 = R.id.pin;
                                        final MasterPassEditText masterPassEditText = ViewBindings.findChildViewById(view, R.id.pin);
                                        if (null != masterPassEditText) {
                                            i2 = R.id.resendTimer;
                                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.resendTimer);
                                            if (null != appCompatTextView) {
                                                i2 = R.id.title;
                                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.title);
                                                if (null != appCompatTextView2) {
                                                    i2 = R.id.txtDialogDescription;
                                                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtDialogDescription);
                                                    if (null != appCompatTextView3) {
                                                        return new FragmentPinSmsBinding((LinearLayout) view, appCompatButton, appCompatButton2, appCompatButton3, linearLayout, linearLayout2, textView, linearLayout3, linearLayout4, masterPassEditText, appCompatTextView, appCompatTextView2, appCompatTextView3);
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
