package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentPrivacyPolicyDialogOldBinding implements ViewBinding {

   
    public final AppCompatButton btnRead;

   
    public final LoadingItemBinding llProgressBar;

   
    private final ConstraintLayout rootView;

   
    public final WebView webView;

    private FragmentPrivacyPolicyDialogOldBinding(final ConstraintLayout constraintLayout, final AppCompatButton appCompatButton, final LoadingItemBinding loadingItemBinding, final WebView webView) {
        rootView = constraintLayout;
        btnRead = appCompatButton;
        llProgressBar = loadingItemBinding;
        this.webView = webView;
    }

    
   
    public ConstraintLayout getRoot() {
        return rootView;
    }

   
    public static FragmentPrivacyPolicyDialogOldBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentPrivacyPolicyDialogOldBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentPrivacyPolicyDialogOldBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_privacy_policy_dialog_old, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentPrivacyPolicyDialogOldBinding.bind(inflate);
    }

   
    public static FragmentPrivacyPolicyDialogOldBinding bind(final View view) {
        int i2 = R.id.btnRead;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnRead);
        if (null != appCompatButton) {
            i2 = R.id.llProgressBar;
            final View findChildViewById = ViewBindings.findChildViewById(view, R.id.llProgressBar);
            if (null != findChildViewById) {
                final LoadingItemBinding bind = LoadingItemBinding.bind(findChildViewById);
                final WebView webView = ViewBindings.findChildViewById(view, R.id.webView);
                if (null != webView) {
                    return new FragmentPrivacyPolicyDialogOldBinding((ConstraintLayout) view, appCompatButton, bind, webView);
                }
                i2 = R.id.webView;
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
