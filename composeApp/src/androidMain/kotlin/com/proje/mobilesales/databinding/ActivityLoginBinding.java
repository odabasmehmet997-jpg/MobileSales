package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.proje.mobilesales.R;
 
public final class ActivityLoginBinding implements ViewBinding {
    public final AppCompatImageView appLogo;
    public final AppCompatButton btnAyarlar;
    public final AppCompatButton btnGiris;
    public final AppCompatEditText etPwd;
    public final AppCompatEditText etUserName;
    public final LinearLayout flags;
    public final AppCompatImageButton imgEN;
    public final AppCompatImageButton imgRU;
    public final AppCompatImageButton imgTR;
    public final LinearLayout loginFrame;
    private final LinearLayout rootView;
    public final AppCompatCheckBox swtRememberMe;
    public final TextInputLayout txtInptPassword;
    public final TextInputLayout txtInptUserName;
    public final TextView txtVersion;
    private ActivityLoginBinding( final LinearLayout linearLayout,  final AppCompatImageView appCompatImageView,  final AppCompatButton appCompatButton,  final AppCompatButton appCompatButton2,  final AppCompatEditText appCompatEditText,  final AppCompatEditText appCompatEditText2,  final LinearLayout linearLayout2,  final AppCompatImageButton appCompatImageButton,  final AppCompatImageButton appCompatImageButton2,  final AppCompatImageButton appCompatImageButton3,  final LinearLayout linearLayout3,  final AppCompatCheckBox appCompatCheckBox,  final TextInputLayout textInputLayout,  final TextInputLayout textInputLayout2,  final TextView textView) {
        rootView = linearLayout;
        appLogo = appCompatImageView;
        btnAyarlar = appCompatButton;
        btnGiris = appCompatButton2;
        etPwd = appCompatEditText;
        etUserName = appCompatEditText2;
        flags = linearLayout2;
        imgEN = appCompatImageButton;
        imgRU = appCompatImageButton2;
        imgTR = appCompatImageButton3;
        loginFrame = linearLayout3;
        swtRememberMe = appCompatCheckBox;
        txtInptPassword = textInputLayout;
        txtInptUserName = textInputLayout2;
        txtVersion = textView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityLoginBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityLoginBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityLoginBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityLoginBinding.bind(inflate);
    }
    public static ActivityLoginBinding bind( final View view) {
        int i2 = R.id.app_logo;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.app_logo);
        if (null != appCompatImageView) {
            i2 = R.id.btnAyarlar;
            final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnAyarlar);
            if (null != appCompatButton) {
                i2 = R.id.btnGiris;
                final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnGiris);
                if (null != appCompatButton2) {
                    i2 = R.id.etPwd;
                    final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.etPwd);
                    if (null != appCompatEditText) {
                        i2 = R.id.etUserName;
                        final AppCompatEditText appCompatEditText2 = ViewBindings.findChildViewById(view, R.id.etUserName);
                        if (null != appCompatEditText2) {
                            i2 = R.id.flags;
                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.flags);
                            if (null != linearLayout) {
                                i2 = R.id.imgEN;
                                final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgEN);
                                if (null != appCompatImageButton) {
                                    i2 = R.id.imgRU;
                                    final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgRU);
                                    if (null != appCompatImageButton2) {
                                        i2 = R.id.imgTR;
                                        final AppCompatImageButton appCompatImageButton3 = ViewBindings.findChildViewById(view, R.id.imgTR);
                                        if (null != appCompatImageButton3) {
                                            i2 = R.id.loginFrame;
                                            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.loginFrame);
                                            if (null != linearLayout2) {
                                                i2 = R.id.swtRememberMe;
                                                final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.swtRememberMe);
                                                if (null != appCompatCheckBox) {
                                                    i2 = R.id.txtInptPassword;
                                                    final TextInputLayout textInputLayout = ViewBindings.findChildViewById(view, R.id.txtInptPassword);
                                                    if (null != textInputLayout) {
                                                        i2 = R.id.txtInptUserName;
                                                        final TextInputLayout textInputLayout2 = ViewBindings.findChildViewById(view, R.id.txtInptUserName);
                                                        if (null != textInputLayout2) {
                                                            i2 = R.id.txtVersion;
                                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txtVersion);
                                                            if (null != textView) {
                                                                return new ActivityLoginBinding((LinearLayout) view, appCompatImageView, appCompatButton, appCompatButton2, appCompatEditText, appCompatEditText2, linearLayout, appCompatImageButton, appCompatImageButton2, appCompatImageButton3, linearLayout2, appCompatCheckBox, textInputLayout, textInputLayout2, textView);
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
